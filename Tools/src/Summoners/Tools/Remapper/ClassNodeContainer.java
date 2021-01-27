package Summoners.Tools.Remapper;

import Summoners.Tools.Exceptions.*;

import Summoners.Tools.Remapper.Descriptor.FieldDescriptor;
import Summoners.Tools.Remapper.Descriptor.MethodDescriptor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassNodeContainer {
    ClassNode classNode;

    ClassNodeContainer parent;
    ArrayList<ClassNodeContainer> children;

    ClassType classType;
    public boolean pack;

    public ClassNodeContainer(ClassNode classNode, boolean pack) {
        this.pack = pack;

        this.classNode = classNode;

        if ((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
            classType = ClassType.INTERFACE;
        } else if ((classNode.access & Opcodes.ACC_ENUM) != 0) {
            classType = ClassType.ENUMERATOR;
        } else {
            classType = ClassType.CLASS;
        }
        this.children = new ArrayList<ClassNodeContainer>();
    }

    public ClassType getType() {
        return this.classType;
    }

    public ClassNodeContainer getParent() {
        return this.parent;
    }

    public void setParent(ClassNodeContainer parent) throws ParentAlreadyRegisteredException, ChildAlreadyRegisteredException {
        if (this.parent != null) throw new ParentAlreadyRegisteredException("Parent already registered", parent, this);
        if (parent.children.contains(this))
            throw new ChildAlreadyRegisteredException("Child already Registered", parent, this);
        this.parent = parent;
        this.parent.addChild(this);
    }

    public void addChild(ClassNodeContainer child) {
        this.children.add(child);
    }

    public ArrayList<ClassNodeContainer> getChildren() {
        return this.children;
    }

    public String getSuperclassName() {
        return this.classNode.superName;
    }

    public String getClassName() {
        return classNode.name;
    }

    public ClassNode getClassNode() {
        return classNode;
    }

    public static ClassNodeContainer CreateClassNodeContainer(ClassNode classNode, boolean pack) {
        if (classNodeContainerHashMap.containsKey(classNode.name)) {
            return classNodeContainerHashMap.get(classNode.name);
        }
        ClassNodeContainer classNodeContainer = new ClassNodeContainer(classNode, pack);
        classNodeContainerHashMap.put(classNode.name, classNodeContainer);

        switch (classNodeContainer.getType()) {
            case INTERFACE:
                classNodeContainerInterfaceHashMap.put(classNode.name, classNodeContainer);
                break;
            case ENUMERATOR:
                classNodeContainerEnumeratorHashMap.put(classNode.name, classNodeContainer);
                break;
            case CLASS:
                classNodeContainerClassHashMap.put(classNode.name, classNodeContainer);
                break;
            default:
                break;
        }
        return classNodeContainer;
    }

    public static ArrayList<ClassNodeContainer> CreateClassHeirarchy() {
        ArrayList<ClassNodeContainer> superClassArrayList = new ArrayList<ClassNodeContainer>();
        int currentClassNumber = 1;
        int currentInterfaceNumber = 1;
        int currentEnumeratorNumber = 1;
        int currentUnknownNumber = 1;


        for (Map.Entry<String, ClassNodeContainer> entry : classNodeContainerHashMap.entrySet()) {
            String className = entry.getKey();
            ClassNodeContainer currentClassContainer = entry.getValue();
            if (classNodeContainerHashMap.containsKey(currentClassContainer.getSuperclassName())) {
                ClassNodeContainer currentClassContainerSuperClass = classNodeContainerHashMap.get(currentClassContainer.getSuperclassName());
                try {
                    currentClassContainer.setParent(currentClassContainerSuperClass);
                } catch (ChildAlreadyRegisteredException e) {
                    e.printStackTrace();
                } catch (ParentAlreadyRegisteredException e) {
                    e.printStackTrace();
                }
            }
            if (!classNodeContainerHashMap.containsKey(currentClassContainer.getSuperclassName())) {
                superClassArrayList.add(currentClassContainer);
            }
        }

        for (ClassNodeContainer c : superClassArrayList) {
            switch (c.getType()) {
                case INTERFACE:
                    PopulateClassNameHashMap("Interface_" + currentInterfaceNumber++, c);
                    break;
                case ENUMERATOR:
                    PopulateClassNameHashMap("Enumerator_" + currentEnumeratorNumber++, c);
                    break;
                case CLASS:
                    PopulateClassNameHashMap("Class_" + currentClassNumber++, c);
                    break;
                default:
                    PopulateClassNameHashMap("Unknown_" + currentUnknownNumber++, c);
            }
        }

        for (ClassNodeContainer c : superClassArrayList) {
            PopulateFieldNameHashMap(c, null);
        }

        for (ClassNodeContainer c : superClassArrayList) {
            PopulateMethodNameHashMap(c, null);
        }

        System.out.println("Dumping class names");
        for (Map.Entry<String, String> e : classNameHashMap.entrySet()) {
            System.out.println("Old class name: " + e.getKey());
            System.out.println("New class name: " + e.getValue());
            System.out.println("");
        }
        System.out.println("End class names");


        System.out.println("Dumping field names");
        for (Map.Entry<String, String> e : fieldNameHashMap.entrySet()) {
            System.out.println("Field name descriptor: " + e.getKey());
            System.out.println("Field name: " + e.getValue());
            System.out.println("");
        }
        System.out.println("End field names");


        System.out.println("Dumping method names");
        for (Map.Entry<String, String> e : methodNameHashMap.entrySet()) {
            System.out.println("Method name descriptor: " + e.getKey());
            System.out.println("Method name: " + e.getValue());
            System.out.println("");
        }
        System.out.println("End method names");
        return superClassArrayList;
    }

    public static HashMap<String, ClassNodeContainer> GetClassNodeContainerHashMap() {
        return classNodeContainerHashMap;
    }

    public static void PopulateMethodNameHashMap(ClassNodeContainer currentClassNodeContainer, ArrayList<ClassNodeContainer> parents) {
        ArrayList<ClassNodeContainer> newParents = parents;
        if (newParents == null)
            newParents = new ArrayList<ClassNodeContainer>();

        ClassNode currentClassNode = currentClassNodeContainer.getClassNode();

        String newClassName = classNameHashMap.get(currentClassNodeContainer.getClassName());

        HashMap<String, IgnoredClass> ignoredClasses = IgnoredClass.GetIgnoredClasses();
        boolean ignoreMethods = ignoredClasses.containsKey(currentClassNodeContainer.getClassName()) ? ignoredClasses.get(currentClassNodeContainer.getClassName()).ignoreMethods() : false;

        for (MethodNode f : currentClassNode.methods) {
            ArrayList<MethodDescriptor> descList = MethodDescriptor.ParseDescriptorString(f.desc, classNameHashMap);

            String desc = MethodDescriptor.GetDescriptorString(descList);

            String prefix = ignoreMethods ? f.name : "method" + methodCount++;

            methodNameHashMap.put(newClassName + "." + f.name + desc, prefix);



        }

        ArrayList<ClassNodeContainer> parentClassNodeArrayList = new ArrayList<ClassNodeContainer>();

        for (int i = newParents.size(); i > 0; --i) {
            parentClassNodeArrayList.add(newParents.get(i - 1));
        }

        for (ClassNodeContainer currentParentClassNodeContainer : parentClassNodeArrayList) {
            ClassNode currentParentClassNode = currentParentClassNodeContainer.getClassNode();
            String parentClassName = classNameHashMap.get(currentParentClassNode.name);

            for (MethodNode f : currentParentClassNode.methods) {
                if ((f.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED | Opcodes.ACC_ABSTRACT)) != 0) {

                    ArrayList<MethodDescriptor> descList = MethodDescriptor.ParseDescriptorString(f.desc, classNameHashMap);

                    String desc = MethodDescriptor.GetDescriptorString(descList);

                    String newMethodName = methodNameHashMap.get(parentClassName + "." + f.name + desc);
                    methodNameHashMap.put(newClassName + "." + f.name + desc, newMethodName);
                    break;
                }
            }

        }

        newParents = (ArrayList<ClassNodeContainer>) newParents.clone();
        newParents.add(currentClassNodeContainer);

        for (ClassNodeContainer c : currentClassNodeContainer.getChildren()) {
            PopulateMethodNameHashMap(c, newParents);
        }
    }

    public static int methodCount = 1;

    public static void PopulateClassNameHashMap(String newClassName, ClassNodeContainer currentClassNodeContainer) {
        int subClassNumber = 1;

        ClassNode currentClassNode = currentClassNodeContainer.getClassNode();
        String oldClassName = currentClassNode.name;
        String reiter = newClassName;

        newClassName = oldClassName.substring(0, oldClassName.lastIndexOf("/") + 1) + newClassName;

        HashMap<String, IgnoredClass> ignoredClasses = IgnoredClass.GetIgnoredClasses();
        boolean ignoreClass = ignoredClasses.containsKey(currentClassNodeContainer.getClassName()) ? ignoredClasses.get(currentClassNodeContainer.getClassName()).ignoreClass() : false;

        classNameHashMap.put(currentClassNodeContainer.getClassName(), ignoreClass ? currentClassNodeContainer.getClassName() : newClassName);


        for (ClassNodeContainer c : currentClassNodeContainer.getChildren()) {
            PopulateClassNameHashMap(reiter + "_Sub_" + subClassNumber++, c);
        }
    }

    public static void PopulateFieldNameHashMap(ClassNodeContainer currentClassNodeContainer, ArrayList<ClassNodeContainer> parents) {
        ArrayList<ClassNodeContainer> newParents = parents;
        if (newParents == null)
            newParents = new ArrayList<ClassNodeContainer>();

        ClassNode currentClassNode = currentClassNodeContainer.getClassNode();

        String newClassName = classNameHashMap.get(currentClassNodeContainer.getClassName());


        HashMap<String, IgnoredClass> ignoredClasses = IgnoredClass.GetIgnoredClasses();
        boolean ignoreFields = ignoredClasses.containsKey(currentClassNodeContainer.getClassName()) ? ignoredClasses.get(currentClassNodeContainer.getClassName()).ignoreFields() : false;

        if (!ignoreFields) {
            for (FieldNode f : currentClassNode.fields) {
                FieldDescriptor d = FieldDescriptor.ParseDescriptorString(f.desc, classNameHashMap);

                String desc = d.getDescriptorString();

                if (!fieldTypeCountHashMap.containsKey(d.getDescriptorType())) {
                    fieldTypeCountHashMap.put(d.getDescriptorType(), 1);
                }
                int count = fieldTypeCountHashMap.get(d.getDescriptorType());
                String prefix = typeMap.get(d.getDescriptorType());
                String objectName = d.getDescriptorObject().substring(d.getDescriptorObject().lastIndexOf("/") + 1, d.getDescriptorObject().length());
                prefix += objectName + (d.getDescriptorArrayDepth() > 0 ? "_Array".repeat(d.getDescriptorArrayDepth()) : "") + ("_" + count);


                fieldTypeCountHashMap.put(d.getDescriptorType(), count + 1);

                fieldNameHashMap.put(newClassName + "." + f.name + desc, prefix);

                //System.out.println("ASS: " + newClassName + "." + f.name + desc + " ASS: " + prefix);

            }


            ArrayList<ClassNodeContainer> parentClassNodeArrayList = new ArrayList<ClassNodeContainer>();

            for (int i = newParents.size(); i > 0; --i) {
                parentClassNodeArrayList.add(newParents.get(i - 1));
            }

            for (ClassNodeContainer currentParentClassNodeContainer : parentClassNodeArrayList) {
                ClassNode currentParentClassNode = currentParentClassNodeContainer.getClassNode();
                String parentClassName = classNameHashMap.get(currentParentClassNode.name);

                for (FieldNode f : currentParentClassNode.fields) {
                    if ((f.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0) {

                        FieldDescriptor fieldDescriptor = FieldDescriptor.ParseDescriptorString(f.desc, classNameHashMap);
                        String desc = fieldDescriptor.getDescriptorString();
                        String newFieldName = fieldNameHashMap.get(parentClassName + "." + f.name + desc);
                        fieldNameHashMap.put(newClassName + "." + f.name + desc, newFieldName);
                    }
                }
            }
        }

        newParents = (ArrayList<ClassNodeContainer>) newParents.clone();
        newParents.add(currentClassNodeContainer);

        for (ClassNodeContainer c : currentClassNodeContainer.getChildren()) {
            PopulateFieldNameHashMap(c, newParents);
        }
    }

    private static final HashMap<String, String> typeMap = new HashMap<String, String>();

    static {
        typeMap.put("B", "aByte");
        typeMap.put("Z", "aBool");
        typeMap.put("I", "anInt");
        typeMap.put("C", "aChar");
        typeMap.put("J", "aLong");
        typeMap.put("D", "aDouble");
        typeMap.put("L", "a");
        typeMap.put("F", "aFloat");
        typeMap.put("S", "aShort");
    }

    public static HashMap<String, String> GetClassNameHashMap() {
        return classNameHashMap;
    }

    public static HashMap<String, String> GetFieldNameHashMap() {
        return fieldNameHashMap;
    }

    public static HashMap<String, String> GetMethodNameHashMap() {
        return methodNameHashMap;
    }

    final static HashMap<String, ClassNodeContainer> classNodeContainerHashMap = new HashMap<String, ClassNodeContainer>();

    final static HashMap<String, ClassNodeContainer> classNodeContainerInterfaceHashMap = new HashMap<String, ClassNodeContainer>();
    final static HashMap<String, ClassNodeContainer> classNodeContainerEnumeratorHashMap = new HashMap<String, ClassNodeContainer>();
    final static HashMap<String, ClassNodeContainer> classNodeContainerClassHashMap = new HashMap<String, ClassNodeContainer>();

    final static HashMap<String, String> classNameHashMap = new HashMap<String, String>();
    final static HashMap<String, String> fieldNameHashMap = new HashMap<String, String>();
    final static HashMap<String, String> methodNameHashMap = new HashMap<String, String>();

    final static HashMap<String, Integer> fieldTypeCountHashMap = new HashMap<String, Integer>();
    final static HashMap<String, Integer> methodTypeCountHashMap = new HashMap<String, Integer>();
}
