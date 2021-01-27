package Summoners.Tools.Exceptions;

import Summoners.Tools.Remapper.ClassNodeContainer;

public class ParentAlreadyRegisteredException extends AlreadyRegisteredException {

    ClassNodeContainer classRegisteredTo;
    public ParentAlreadyRegisteredException(String message, ClassNodeContainer parent, ClassNodeContainer child) {
        super(message, parent);
        classRegisteredTo = child;
    }
}
