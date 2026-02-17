package Service;

import Model.Supply;
import Model.SupplyType;
import Repository.SupplyRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SupplyService {
    private final SupplyRepository repo;

    public SupplyService(SupplyRepository repo) {
        this.repo = repo;
    }

    //CRUD
    public void addSupply(Supply supply) {
        repo.save(supply);
    }

    public List<Supply> getAllSupplies() {
        return repo.findAll();
    }

    public Supply getSupplyById(Integer id) {
        return repo.findById(id);
    }

    public void deleteSupply(Integer id) {
        repo.deleteById(id);
    }

    //filtern
    /*public List<Supply> findByTypAndStatus(SupplyType type) {
        return getAllSupplies().stream()

                .filter(t -> t.getType() == type)
                .collect(Collectors.toList());
    }*/


}
