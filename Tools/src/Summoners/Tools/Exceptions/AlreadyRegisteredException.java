package Summoners.Tools.Exceptions;

import Summoners.Tools.Remapper.ClassNodeContainer;

public class AlreadyRegisteredException extends Exception {
    ClassNodeContainer registeredClass;

    public AlreadyRegisteredException(String message, ClassNodeContainer registeredClass) {
        super(message);
        this.registeredClass = registeredClass;

    }
}
