package Summoners.Tools.Provider;

import Summoners.Tools.Tree.InterfaceNode;

public abstract class ClassProvider {

    protected ClassProvider cp;

    public ClassProvider(ClassProvider delegate) {
        this.cp = delegate;
    }

    public abstract InterfaceNode getClass(String name);

}
