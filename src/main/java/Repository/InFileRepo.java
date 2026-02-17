package Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public abstract class InFileRepo<T> implements CrudRepository<T> {

    private final Class<T> clazz;
    private final Path filePath;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<Integer, T> data = new LinkedHashMap<>();

    public InFileRepo(String fileName, Class<T> clazz) {
        this.clazz = clazz;
        this.filePath = Paths.get("src/main/resources/data/" + fileName);
        load();
    }

    private void load() {
        try {
            if (Files.notExists(filePath)) {
                return;
            }

            String json = Files.readString(filePath);
            if (json.isEmpty() || json.equals("[]")) return;

            List<T> list = objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));

            data.clear();
            for (T item : list) {
                Integer id = extractId(item);
                data.put(id, item);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load file: " + filePath, e);
        }
    }

    private void saveToFile() {
        try {
            List<T> list = new ArrayList<>(data.values());
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            Files.writeString(filePath, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + filePath, e);
        }
    }

    private Integer extractId(T entity) {
        try {
            return (Integer) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("Entity missing getId(): " + entity, e);
        }
    }

    private void setId(T entity, Integer id) {
        try {
            entity.getClass().getMethod("setId", int.class).invoke(entity, id);
        } catch (Exception e) {
            throw new RuntimeException("Entity missing setId(): " + entity, e);
        }
    }

    private Integer generateId() {
        return data.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public T findById(Integer id) {
        return data.get(id);
    }

    @Override
    public void save(T entity) {
        Integer id = extractId(entity);

        if (id == null || id == 0) {
            id = generateId();
            setId(entity, id);
        }

        data.put(id, entity);
        saveToFile();
    }

    @Override
    public void deleteById(Integer id) {
        data.remove(id);
        saveToFile();
    }
}