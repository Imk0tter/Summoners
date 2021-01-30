package Summoners.Bots.RSCRevolution.BOT.Definitions;

import client.Class60;
import com.rscrevolution.Class15_Sub3;

import java.util.HashMap;

public class AnimationDefinition extends BaseDefinition {

    public AnimationDefinition(Class15_Sub3 definition) {
        super(definition);
    }
    public static HashMap<Integer, AnimationDefinition> animationDefinitions = new HashMap<Integer, AnimationDefinition>();

    public static AnimationDefinition GetAnimationDefinition(int id) {
        if (animationDefinitions.containsKey(id)) return animationDefinitions.get(id);
        Class15_Sub3 animationDefinition = Class60.method873(id);
        AnimationDefinition definition = new AnimationDefinition(animationDefinition);
        animationDefinitions.put(id, definition);

        return definition;
    }
    public static AnimationDefinition GetAnimationDefinition(Class15_Sub3 definition) {
        return GetAnimationDefinition(definition.anInt195);
    }
    public Class15_Sub3 Definition() {
        return (Class15_Sub3) this.definition;
    }

}