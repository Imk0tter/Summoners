package Summoners.Bots.RSCRevolution.BOT.Objects;

import Summoners.Bots.RSCRevolution.BOT.Definitions.ItemDefinition;
import client.Class57_Sub1;
import client.Class57_Sub3;

import java.util.HashMap;

public class ItemObject extends BaseObject {
    protected static HashMap<Class57_Sub3, ItemObject> itemObjects = new HashMap<Class57_Sub3, ItemObject>();

    public static ItemObject GetItemObject(Class57_Sub3 itemObject) {
        if (itemObject == null) return null;
        //if (itemObjects.containsKey(itemObject)) return itemObjects.get(itemObject);

        ItemObject object = new ItemObject(itemObject);

        //itemObjects.put(itemObject, object);

        return object;

    }

    public ItemObject(Class57_Sub3 itemObject) {
        super(itemObject);

    }
    public Class57_Sub3 Object() { return (Class57_Sub3)object; }
    public ItemDefinition Definition() { return ItemDefinition.GetItemDefinition(Object().aClass15_Sub2_Sub3_627.anInt195); }

}
