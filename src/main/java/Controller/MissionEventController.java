package Controller;

import Service.MissionEventService;

import java.io.IOException;

public class MissionEventController {
    private final MissionEventService missionEventService;

    public MissionEventController(MissionEventService missionEventService) {
        this.missionEventService = missionEventService;
    }

    public void showAllEvents() {
        missionEventService.getAllEvents().forEach(System.out::println);
    }

    public void showRiskScore() {
        missionEventService.getAllEvents().stream()
                .limit(5)
                .forEach(e -> {;
                    int computed = missionEventService.computedPointsScore(e);
                    System.out.println("Event " + e.getId() +
                            " -> basePoints=" + e.getBasePoints() +
                            " -> computedPoints=" + computed);
                });
    }

    public void generateReport() {
        try {
            missionEventService.generateReport("mission_report.txt");
            System.out.println("Erstellt!");
        } catch (IOException e) {
            System.out.println("Fehler" + e.getMessage());
        }
    }
}
