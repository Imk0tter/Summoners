package Summoners.Tools.Remapper;

import java.util.HashMap;

public class IgnoredClass {
    final public static int IGNORE_CLASS = 1;
    final public static int IGNORE_FIELDS = 2;
    final public static int IGNORE_METHODS = 4;

    int flags;

    String className;

    public IgnoredClass(String className, int flags) {
        this.className = className;
        this.flags = flags;

    }
    public String getClassName() {
        return this.className;
    }
    public int getFlags() {
        return this.flags;
    }
    public boolean ignoreClass() {
        return (flags & IGNORE_CLASS) != 0;
    }
    public boolean ignoreMethods() {
        return (flags & IGNORE_METHODS) != 0;
    }
    public boolean ignoreFields() {
        return (flags & IGNORE_FIELDS) != 0;
    }
    public static IgnoredClass CreateIgnoredClass(String className, int flags) {
        if (ignoredClasses.containsKey(className)) {
            return ignoredClasses.get(className);
        }
        IgnoredClass ignoredClass = new IgnoredClass(className, flags);

        ignoredClasses.put(className, ignoredClass);

        return ignoredClass;
    }
    public static HashMap<String, IgnoredClass> GetIgnoredClasses() {
        return ignoredClasses;
    }
    final static HashMap<String, IgnoredClass> ignoredClasses = new HashMap<String, IgnoredClass>();
}
