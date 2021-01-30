package Summoners.Bots.RSCRevolution.BOT.Objects;

import client.Class57_Sub4_Sub1;

import java.util.HashMap;

public class PlayerObject extends MobObject {
    protected static HashMap<Class57_Sub4_Sub1, PlayerObject> playerObjects = new HashMap<Class57_Sub4_Sub1, PlayerObject>();
    public static PlayerObject GetPlayerObject(Class57_Sub4_Sub1 playerObject) {
        if (playerObject == null) return null;
       // MobObject mobObject = GetMobObject(playerObject);

       // if (playerObjects.containsKey(playerObject)) return playerObjects.get(playerObject);

        PlayerObject object = new PlayerObject(playerObject);

        //playerObjects.put(playerObject, object);

        return object;

    }
    public PlayerObject(Class57_Sub4_Sub1 npcObject) {
        super(npcObject);
    }
    public Class57_Sub4_Sub1 Object() { return (Class57_Sub4_Sub1)object; }

    public String getPlayerName() {
        return Object().aString583;
    }
    public String getTeamName() {
        return Object().aString585;
    }

    public int colourSkin() { return Object().anInt582; }
    public int colourHair() { return Object().anInt578; }
    public int colourTop() { return Object().anInt580; }
    public int colourBottom() { return Object().anInt584; }

}
