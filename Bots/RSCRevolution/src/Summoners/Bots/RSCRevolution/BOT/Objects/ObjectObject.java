package Summoners.Bots.RSCRevolution.BOT.Objects;

import Summoners.Bots.RSCRevolution.BOT.Definitions.ObjectDefinition;
import client.Class57_Sub2;

import java.util.HashMap;

public class ObjectObject extends BaseObject {
    protected static HashMap<Class57_Sub2, ObjectObject> objectObjects = new HashMap<Class57_Sub2, ObjectObject>();

    public static ObjectObject GetObjectObject(Class57_Sub2 objectObject) {
        if (objectObject == null) return null;
       // if (objectObjects.containsKey(objectObject)) return objectObjects.get(objectObject);

        ObjectObject object = new ObjectObject(objectObject);

        //objectObjects.put(objectObject, object);

        return object;

    }

    public ObjectObject(Class57_Sub2 itemObject) {
        super(itemObject);
    }

    public Class57_Sub2 Object() { return (Class57_Sub2)object; }

    public ObjectDefinition Definition() { return ObjectDefinition.GetObjectDefinition(Object().aClass15_Sub2_Sub2_623); }

}