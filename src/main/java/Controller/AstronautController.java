package Controller;

import Service.AstronautService;

public class AstronautController {
    private final AstronautService astronautService;

    public AstronautController(AstronautService astronautService) {
        this.astronautService = astronautService;
    }

    public void showAllFines() {
        astronautService.getAllAstronauts().forEach(System.out::println);
    }
}
