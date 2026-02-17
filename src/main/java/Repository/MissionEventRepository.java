package Repository;

import Model.MissionEvent;

public class MissionEventRepository extends InFileRepo<MissionEvent> {
    public MissionEventRepository() {
        super("events.json", MissionEvent.class);
    }
}
