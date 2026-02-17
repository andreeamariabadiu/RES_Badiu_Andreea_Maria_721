package Model;

public class Supply {
        int id;
        int astronautId;
        SupplyType type;
        int value;

        //Constructor
        public Supply() {}


        public Supply(int id, int astronautId, SupplyType type, int value) {
            this.id = id;
            this.astronautId = astronautId;
            this.type = type;
            this.value = value;
        }

        //Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAstronautId() {
            return astronautId;
        }

        public void setAstronautId(int astronautId) {
            this.astronautId = astronautId;
        }


        public SupplyType getType() {
            return type;
        }

        public void setType(SupplyType type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
