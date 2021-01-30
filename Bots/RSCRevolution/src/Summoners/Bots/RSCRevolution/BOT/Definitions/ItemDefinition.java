package Summoners.Bots.RSCRevolution.BOT.Definitions;

import client.Class60;
import com.rscrevolution.Class15_Sub2_Sub3;

import java.util.HashMap;

public class ItemDefinition extends BaseDefinition {
    public ItemDefinition(Class15_Sub2_Sub3 definition) {
        super(definition);
    }
    public static HashMap<Integer, ItemDefinition> itemDefinitions = new HashMap<Integer, ItemDefinition>();

    public static ItemDefinition GetItemDefinition(int id) {
        if (itemDefinitions.containsKey(id)) return itemDefinitions.get(id);
        Class15_Sub2_Sub3 itemDefinition = Class60.method872(id);
        ItemDefinition definition = new ItemDefinition(itemDefinition);
        itemDefinitions.put(id, definition);

        return definition;
    }
    public static ItemDefinition GetItemDefinition(Class15_Sub2_Sub3 definition) {
        return GetItemDefinition(definition.anInt195);
    }
    public Class15_Sub2_Sub3 Definition() {
        return (Class15_Sub2_Sub3)this.definition;
    }

    public boolean isStackable() { return Definition().aBoolean208; }
    public boolean isEquipable() { return Definition().aBoolean206; }

    public String getName() { return Definition().aString159; }
    public String getDescription() { return Definition().aString160; }
}
