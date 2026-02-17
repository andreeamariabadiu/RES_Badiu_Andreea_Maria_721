package Service;

import Model.MissionEvent;
import Model.MissionEventType;
import Repository.MissionEventRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MissionEventService {
    private final MissionEventRepository repo;

        public MissionEventService(MissionEventRepository repo) {
        this.repo = repo;
    }

    //CRUD
    public void addEvent(MissionEvent event) {
        repo.save(event);
    }

    public List<MissionEvent> getAllEvents() {
        return repo.findAll();
    }

    public MissionEvent getEventById(Integer id) {
        return repo.findById(id);
    }

    public void deleteEvent(Integer id) {
        repo.deleteById(id);
    }

    public int computedPointsScore(MissionEvent event) {
        int s = event.getBasePoints();
        switch (event.getType()) {
            case EVA:
                return s + event.getDay()* 2;
            case SYSTEM_FAILURE:
                return s - 3 - event.getDay();
            case SCIENCE:
                return + s + (event.getDay()%4);
            case MEDICAL:
                return s -2 * (event.getDay()%3);
            case COMMUNICATION:
                return s + 5;
            default:
                return s;
        }
    }

    public void generateReport(String filename) throws IOException {
        Map<MissionEventType, Long> counts = getAllEvents().stream()
                .collect(Collectors.groupingBy(MissionEvent::getType, Collectors.counting()));

        List<String> lines = new ArrayList<>();

        counts.forEach((type, count) -> {
            lines.add(type + " -> " + count);
        });

        Files.write(Path.of(filename), lines);
    }
}
