package Summoners.Bots.RSCRevolution.BOT.Definitions;

import client.Class60;
import com.rscrevolution.Class15_Sub2_Sub4;

import java.util.HashMap;

public class NpcDefinition extends BaseDefinition{
    public NpcDefinition(Class15_Sub2_Sub4 definition) {
        super(definition);
    }
    public static HashMap<Integer, NpcDefinition> npcDefinitions = new HashMap<Integer, NpcDefinition>();

    public static NpcDefinition GetNpcDefinition(int id) {
        if (npcDefinitions.containsKey(id)) return npcDefinitions.get(id);
        Class15_Sub2_Sub4 npcDefinition = Class60.method865(id);
        NpcDefinition definition = new NpcDefinition(npcDefinition);
        npcDefinitions.put(id, definition);

        return definition;
    }
    public static NpcDefinition GetNpcDefinition(Class15_Sub2_Sub4 definition) {
        return GetNpcDefinition(definition.anInt195);
    }
    public Class15_Sub2_Sub4 Definition() {
        return (Class15_Sub2_Sub4)this.definition;
    }

    public String getName() {
        return this.Definition().aString163;
    }
}
