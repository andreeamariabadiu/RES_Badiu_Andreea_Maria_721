package Service;

import Model.Astronaut;
import Repository.AstronautRepository;

import java.util.List;

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


    }

