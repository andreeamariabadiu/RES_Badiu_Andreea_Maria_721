package Repository;

import Model.Supply;
import Model.SupplyType;

public class SupplyRepository extends InFileRepo<Supply> {
    public SupplyRepository() {
        super("supplies.json", Supply.class);
    }
}
