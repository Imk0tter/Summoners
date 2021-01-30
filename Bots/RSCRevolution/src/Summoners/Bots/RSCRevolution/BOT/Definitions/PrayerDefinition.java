package Summoners.Bots.RSCRevolution.BOT.Definitions;

import client.Class60;
import com.rscrevolution.Class15_Sub2_Sub5;

import java.util.HashMap;

public class PrayerDefinition extends BaseDefinition {

    public PrayerDefinition(Class15_Sub2_Sub5 definition) {
        super(definition);
    }
    public static HashMap<Integer, PrayerDefinition> prayerDefinitions = new HashMap<Integer, PrayerDefinition>();

    public static PrayerDefinition GetPrayerDefinition(int id) {
        if (prayerDefinitions.containsKey(id)) return prayerDefinitions.get(id);
        Class15_Sub2_Sub5 prayerDefinition = Class60.method888(id);
        PrayerDefinition definition = new PrayerDefinition(prayerDefinition);
        prayerDefinitions.put(id, definition);

        return definition;
    }
    public static PrayerDefinition GetPrayerDefinition(Class15_Sub2_Sub5 definition) {
        return GetPrayerDefinition(definition.anInt195);
    }

    public Class15_Sub2_Sub5 Definition() {
        return (Class15_Sub2_Sub5) this.definition;
    }
}
