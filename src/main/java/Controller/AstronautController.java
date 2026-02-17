package Controller;
import Model.Astronaut;
import Model.AstronautStatus;
import Service.AstronautService;


import java.io.IOException;
import java.util.List;

public class AstronautController {
    private final AstronautService astronautService;

    public AstronautController(AstronautService astronautService) {
        this.astronautService = astronautService;
    }

    public void showAllAstronauts() {
        astronautService.getAllAstronauts().forEach(System.out::println);
    }

    public void showFilteredAstronauts(AstronautStatus status) {
        List<Astronaut> filteredAstronauts = astronautService.findByStatus( status);
        filteredAstronauts.forEach(System.out::println);
    }

    public void showSortedAstronauts(){
        astronautService.getSortedAstronauts().forEach(System.out::println);
    }

    public void saveSortedToFile() {
        try {
            astronautService.saveSortedAstronautsToFile("astronauts_sorted.txt");
            System.out.println("Gespeichert");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

}
