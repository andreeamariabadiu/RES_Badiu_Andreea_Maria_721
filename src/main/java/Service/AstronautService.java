package Service;

import Model.Astronaut;
import Model.AstronautStatus;
import Model.Supply;
import Repository.AstronautRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AstronautService {
        private final AstronautRepository repo;

        public AstronautService(AstronautRepository repo) {
            this.repo = repo;
        }

        //CRUD
        public void addAstronaut(Astronaut astronaut) {
            repo.save(astronaut);
        }

        public List<Astronaut> getAllAstronauts() {
            return repo.findAll();
        }

        public Astronaut getAstronautById(Integer id) {
            return repo.findById(id);
        }

        public void deleteAstronaut(Integer id) {
            repo.deleteById(id);
        }


    //filtern
    public List<Astronaut> findByStatus( AstronautStatus status) {
        return getAllAstronauts().stream()
                .filter(t -> t.getStatus() == status)

                .collect(Collectors.toList());
    }

    //sortieren
    public List<Astronaut> getSortedAstronauts() {
        return getAllAstronauts().stream()
                .sorted(Comparator.comparing(Astronaut::getExperienceLevel).reversed()
                        .thenComparingInt(Astronaut::getId).reversed())
                .collect(Collectors.toList());
    }

    //save to file sortiert
    public void saveSortedAstronautsToFile(String filename) throws IOException {
        List<String> lines = getSortedAstronauts().stream()
                .map(Astronaut::toString)
                .collect(Collectors.toList());

        Files.write(Path.of(filename), lines);
    }
}

