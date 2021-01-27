package Summoners.Tools.Exceptions;

import Summoners.Tools.Remapper.ClassNodeContainer;
public class ChildAlreadyRegisteredException extends AlreadyRegisteredException {

    ClassNodeContainer classRegisteredTo;
    public ChildAlreadyRegisteredException(String message, ClassNodeContainer parent, ClassNodeContainer child) {
        super(message, child);
        this.classRegisteredTo = parent;
    }

}
