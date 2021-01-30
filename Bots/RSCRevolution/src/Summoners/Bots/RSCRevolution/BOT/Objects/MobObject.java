package Summoners.Bots.RSCRevolution.BOT.Objects;

import Summoners.Bots.RSCRevolution.BOT.Definitions.AnimationDefinition;
import client.Class57_Sub4;
import com.rscrevolution.Class15_Sub3;

import java.util.ArrayList;
import java.util.HashMap;

public class MobObject extends BaseObject {

    protected static HashMap<Class57_Sub4, MobObject> mobObjects = new HashMap<Class57_Sub4, MobObject>();

    public static MobObject GetMobObject(Class57_Sub4 mobObject) {
        if (mobObject == null) return null;
        //if (mobObjects.containsKey(mobObject)) return mobObjects.get(mobObject);

        MobObject object = new MobObject(mobObject);

       // mobObjects.put(mobObject, object);

        return object;

    }

    public MobObject(Class57_Sub4 mobObject) {
        super(mobObject);
    }

    public Class57_Sub4 Object() { return (Class57_Sub4)object; }

    public int getProjectileRange() { return Object().anInt1060; }

    public MobObject getAttackingCharacter() { return GetMobObject(Object().aClass57_Sub4_1049); }

    public int getIncomingSpriteProjectile() { return Object().anInt626; }

    public int[] getWaypointsX() { return Object().anInt_Array1045; }
    public int[] getWaypointsY() { return Object().anInt_Array1057; }
    public int getWaypointsCurrent() { return Object().anInt1051; }
    public int getLevel() { return Object().anInt1041; }
    public int getCurrentHp() { return Object().anInt1050; }
    public int getMaxHp() { return Object().anInt1046; }
    public int getDamageTaken() { return Object().anInt1061; }
    public int getCombatTimer() { return Object().anInt1055;}
    public int getNextAnimation() { return Object().anInt1054; }
    public int getAnimationCurrent() { return Object().anInt1047; }
    public int getBubbleItem() { return Object().anInt1053; }
    public int getBubbleTimeout() { return Object().anInt1058; }

    public ArrayList<AnimationDefinition> getAnimationDefinitions() {
        ArrayList<AnimationDefinition> animationDefinitions = new ArrayList<AnimationDefinition>();

        for (Class15_Sub3 animationDefinition : Object().aClass15_Sub3_Array1056) {
            animationDefinitions.add(AnimationDefinition.GetAnimationDefinition(animationDefinition));
        }
        return animationDefinitions;
    }
}
