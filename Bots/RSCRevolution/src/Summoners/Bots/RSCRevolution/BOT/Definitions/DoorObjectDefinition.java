package Summoners.Bots.RSCRevolution.BOT.Definitions;

import client.Class60;
import com.rscrevolution.Class15_Sub2_Sub1;

import java.util.HashMap;

public class DoorObjectDefinition extends BaseDefinition {

    public static HashMap<Integer, DoorObjectDefinition> doorObjects = new HashMap<Integer, DoorObjectDefinition>();

    public static DoorObjectDefinition GetDoorObjectDefinition(int id) {
        if (doorObjects.containsKey(id)) return doorObjects.get(id);
        Class15_Sub2_Sub1 doorObjectDefinition = Class60.method878(id);
        DoorObjectDefinition definition = new DoorObjectDefinition(doorObjectDefinition);
        doorObjects.put(id, definition);


        return definition;
    }
    public DoorObjectDefinition(Class15_Sub2_Sub1 definition) {
        super(definition);
    }

    public static DoorObjectDefinition GetDoorObjectDefinition(Class15_Sub2_Sub1 definition) {
        return GetDoorObjectDefinition(definition.anInt195);
    }
    public Class15_Sub2_Sub1 Definition() {
        return (Class15_Sub2_Sub1) this.definition;
    }
}
