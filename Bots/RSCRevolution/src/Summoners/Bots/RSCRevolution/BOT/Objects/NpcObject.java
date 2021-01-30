package Summoners.Bots.RSCRevolution.BOT.Objects;

import Summoners.Bots.RSCRevolution.BOT.Definitions.NpcDefinition;
import client.Class57_Sub4_Sub2;

import java.util.HashMap;

public class NpcObject extends MobObject {
    protected static HashMap<Class57_Sub4_Sub2, NpcObject> npcObjects = new HashMap<Class57_Sub4_Sub2, NpcObject>();
    public static NpcObject GetNpcObject(Class57_Sub4_Sub2 npcObject) {
        if (npcObject == null) return null;
        //MobObject mobObject = GetMobObject(npcObject);

        //if (npcObjects.containsKey(npcObject)) return npcObjects.get(npcObject);

        NpcObject object = new NpcObject(npcObject);

        //npcObjects.put(npcObject, object);


        return object;

    }
    public NpcObject(Class57_Sub4_Sub2 npcObject) {
        super(npcObject);
    }
    public Class57_Sub4_Sub2 Object() { return (Class57_Sub4_Sub2)object; }

    public NpcDefinition Definition() {
        return NpcDefinition.GetNpcDefinition(Object().aClass15_Sub2_Sub4_618);
    }
}
