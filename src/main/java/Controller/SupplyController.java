package Controller;
import Service.SupplyService;

public class SupplyController {
    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    public void showAllSupplies() {
        supplyService.getAllSupplies().forEach(System.out::println);
    }
}
