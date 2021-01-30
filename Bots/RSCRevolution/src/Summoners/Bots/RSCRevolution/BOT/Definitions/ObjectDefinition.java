package Summoners.Bots.RSCRevolution.BOT.Definitions;

import client.Class60;
import com.rscrevolution.Class15_Sub2_Sub2;

import java.util.HashMap;

public class ObjectDefinition extends BaseDefinition {

    public ObjectDefinition(Class15_Sub2_Sub2 definition) {
        super(definition);
    }
    public static HashMap<Integer, ObjectDefinition> objectDefinitions = new HashMap<Integer, ObjectDefinition>();

    public static ObjectDefinition GetObjectDefinition(int id) {
        if (objectDefinitions.containsKey(id)) return objectDefinitions.get(id);
        Class15_Sub2_Sub2 objectDefinition = Class60.method884(id);
        ObjectDefinition definition = new ObjectDefinition(objectDefinition);
        objectDefinitions.put(id, definition);

        return definition;
    }
    public static ObjectDefinition GetObjectDefinition(Class15_Sub2_Sub2 definition) {
        return GetObjectDefinition(definition.anInt195);
    }
    public Class15_Sub2_Sub2 Definition() {
        return (Class15_Sub2_Sub2) this.definition;
    }

}
