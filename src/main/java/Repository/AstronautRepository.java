package Repository;


import Model.Astronaut;

public class AstronautRepository extends InFileRepo<Astronaut> {
    public AstronautRepository() {
        super("astronauts.json", Astronaut.class);
    }
}
