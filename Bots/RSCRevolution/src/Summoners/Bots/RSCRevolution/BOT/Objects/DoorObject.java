package Summoners.Bots.RSCRevolution.BOT.Objects;

import Summoners.Bots.RSCRevolution.BOT.Definitions.DoorObjectDefinition;
import client.Class57_Sub1;

import java.util.HashMap;

public class DoorObject extends BaseObject {
    protected static HashMap<Class57_Sub1, DoorObject> doorObjects = new HashMap<Class57_Sub1, DoorObject>();

    public static DoorObject GetObjectObject(Class57_Sub1 doorObject) {
        if (doorObject == null) return null;
        //if (doorObjects.containsKey(doorObject)) return doorObjects.get(doorObject);

        DoorObject object = new DoorObject(doorObject);

       // doorObjects.put(doorObject, object);

        return object;

    }

    public DoorObject(Class57_Sub1 itemObject) {
        super(itemObject);
    }

    public Class57_Sub1 Object() { return (Class57_Sub1)object; }

    public DoorObjectDefinition Definition() { return DoorObjectDefinition.GetDoorObjectDefinition(Object().aClass15_Sub2_Sub1_492); }

}
