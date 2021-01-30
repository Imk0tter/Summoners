package Summoners.Bots.RSCRevolution.BOT.Definitions;

import client.Class60;
import com.rscrevolution.Class15_Sub2_Sub6;

import java.util.HashMap;

public class SpellDefinition extends BaseDefinition {
    public static HashMap<Integer, SpellDefinition> spellObjects = new HashMap<Integer, SpellDefinition>();

    public SpellDefinition(Class15_Sub2_Sub6 definition) {
        super(definition);
    }

    public static SpellDefinition GetSpellDefinition(int id) {
        if (spellObjects.containsKey(id)) return spellObjects.get(id);
        Class15_Sub2_Sub6 spellDefinition = Class60.method880(id);
        SpellDefinition definition = new SpellDefinition(spellDefinition);
        spellObjects.put(id, definition);

        return definition;
    }
    public static SpellDefinition GetSpellDefinition(Class15_Sub2_Sub6 definition) {
        return GetSpellDefinition(definition.anInt195);
    }

    public Class15_Sub2_Sub6 Definition() {
        return (Class15_Sub2_Sub6) this.definition;
    }
}
