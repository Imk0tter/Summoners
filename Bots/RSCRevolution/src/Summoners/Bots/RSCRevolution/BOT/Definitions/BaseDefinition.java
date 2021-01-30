package Summoners.Bots.RSCRevolution.BOT.Definitions;

import com.rscrevolution.Class15;

public abstract class BaseDefinition {
    protected Class15 definition;

    public BaseDefinition(Class15 definition) {
        this.definition = definition;
    }

    public int getID() { return this.definition.anInt195; }

}
