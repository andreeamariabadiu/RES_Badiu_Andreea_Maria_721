
import Controller.AstronautController;
import Controller.MissionEventController;
import Controller.SupplyController;
import Model.AstronautStatus;
import Model.MissionEvent;
import Repository.AstronautRepository;
import Repository.MissionEventRepository;
import Repository.SupplyRepository;
import Service.AstronautService;
import Service.MissionEventService;
import Service.SupplyService;

import java.util.Scanner;

public class Main {
    static void main() {

        AstronautRepository astronautRepository = new AstronautRepository();
        MissionEventRepository missionEventRepository = new MissionEventRepository();
        SupplyRepository supplyRepository = new SupplyRepository();

        AstronautService astronautService = new AstronautService(astronautRepository);
        MissionEventService missionEventService = new MissionEventService(missionEventRepository);
        SupplyService vehicleService = new SupplyService(supplyRepository);

        AstronautController astronautController = new AstronautController(astronautService);
        MissionEventController missionEventController = new MissionEventController(missionEventService);
        //SupplyController supplyController = new SupplyRepository(supplyService);

        Scanner scnanner = new Scanner(System.in);

        System.out.println("Supplies loaded:" + vehicleService.getAllSupplies().size());
        System.out.println("Events loaded:" + missionEventService.getAllEvents().size());
        System.out.println("Astronauts loaded:" + astronautService.getAllAstronauts().size());

        System.out.println();

        //supplyController.showAllSupplies();

        System.out.println();

        //2
        System.out.print("Enter Astronaut status: ");
        if (scnanner.hasNextLine()) {
            String typeInput = scnanner.nextLine();
            System.out.print("Enter Astronauts Status: ");
            if (scnanner.hasNextLine()) {
                String statusInput = scnanner.nextLine();

                try {
                    astronautController.showFilteredAstronauts(
                            AstronautStatus.valueOf(statusInput.toUpperCase())
                    );
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input for astronaut  status.");
                }
            } else {
                System.out.println("Invalid input for astronaut status.");
            }
        } else {
            System.out.println("Invalid input for astronaut type.");
        }

        //3
        System.out.println();
        System.out.println("    Astronauts sorted: ");
        astronautController.showSortedAstronauts();

        //4
        System.out.println();
        astronautController.saveSortedToFile();

        //5
        System.out.println();
        missionEventController.showComputedPoints();

        //7
        System.out.println();
        missionEventController.generateReport();

    }
}
