package Summoners.Tools.Publicizer;

import Summoners.Tools.File.SummonersConfigFile;
import Summoners.Tools.Remapper.ClassRemapper;
import Summoners.Tools.Remapper.Remapper;
import Summoners.Tools.Tree.*;
import Summoners.Tools.Provider.*;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public class Publicizer {

    public static void main(String[] args) {
        String inputFileName = args.length > 0 ? args[0] : "input.jar";
        String outputFileName = args.length > 1 ? args[1] : "output.jar";
        String configFileName = args.length > 2 ? args[2] : "config.cfg";

        try {
            Publicizer publicizer = new Publicizer(inputFileName, outputFileName, configFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Publicizer(String inputFileName, String outputFileName, String configFileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write("Loading configuration settings from config file '" + configFileName + "'\n");writer.flush();

        SummonersConfigFile configFile = new SummonersConfigFile(configFileName);

        writer.write("End loading configuration\n\n");writer.flush();


        JarLoader jarLoader = new JarLoader(new File(inputFileName));

        writer.write("Loading classes from jar file '" + inputFileName + "'\n");writer.flush();
        jarLoader.load(configFile.ignoredClassMap, writer);

        this.interfaceNodes = jarLoader.GetInterfaceNodes();

        writer.write("End loading classes\n\n");writer.flush();

        writer.write("Building linkedInterFaceNode tree\n");writer.flush();
        Map<String, LinkedInterfaceNode> linkedInterfaceNodes = buildTree(new SCLClassProvider());

        writer.write("End building linkedInterfaceNode tree\n\n");writer.flush();

        writer.write("Renaming classes in linkedInterfaceNode tree\n");writer.flush();
        Remapper remapper = this.rename(linkedInterfaceNodes);

        writer.write("End renaming classes\n\n");writer.flush();
        Map<String, ClassNode> classNodes = jarLoader.GetClassNodes();

        File outputFile = new File(outputFileName);
        if (outputFile.exists())
            outputFile.delete();
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(outputFile));
        writer.write("Dumping class info...\n");writer.flush();

        dumpMappings(linkedInterfaceNodes, writer);

        writer.write("End dump\n\n");writer.flush();

        writer.write("Writing classes to jar file '" + outputFileName+ "'\n");writer.flush();

        for (ClassNode classNode : classNodes.values()) {
            ClassWriter currentClassWriter = new ClassWriter(0);
            ClassVisitor classRemapper = new ClassRemapper(currentClassWriter, remapper);
            classNode.accept(classRemapper);

            byte[] data = currentClassWriter.toByteArray();

            LinkedInterfaceNode currentInterfaceNode = linkedInterfaceNodes.get(classNode.name);

            String className = currentInterfaceNode.getNewName() != null ? currentInterfaceNode.getNewName() : currentInterfaceNode.name;

            JarEntry currentFileJarEntry = new JarEntry(className + ".class");
            jarOutputStream.putNextEntry(currentFileJarEntry);
            jarOutputStream.write(data, 0, data.length);
            jarOutputStream.closeEntry();
        }

        configFile.writeManifestFile(jarOutputStream);

        jarOutputStream.close();

        writer.write("End writing classes\n\n");writer.flush();

        writer.write("Finished!\n");writer.flush();

    }
    private Map<String, InterfaceNode> interfaceNodes;

    public static void dumpMappings(Map<String, LinkedInterfaceNode> tree, BufferedWriter writer) throws IOException {
        for (LinkedInterfaceNode node : tree.values()) {
            if (!node.HasNodeFlags(NodeFlags.FLAG_EXTERNAL | NodeFlags.FLAG_IGNORE_CLASS, false)) {
                String newName = node.getNewName();
                if (newName != null) {
                    writer.newLine();
                    writer.write("	Old Class:" + node.name + " New Class:"
                            + node.getNewName());
                    writer.newLine();

                    if (!node.HasNodeFlags(NodeFlags.FLAG_IGNORE_FIELDS, true)) {
                        writer.write("Fields Renamed \n");
                        writer.newLine();
                        for (MemberNode field : node.getFields()) {
                            String desc = field.desc;
                            String name_old = field.name;
                            String name_new = node.getFieldName(name_old, desc);
                            if (!name_old.equals(name_new)) {
                                writer.write("		Old Field: " + name_old + " New: " + name_new + " Field Signature: " + desc);
                                writer.newLine();
                            }
                        }
                        writer.newLine();
                    }
                    if (!node.HasNodeFlags(NodeFlags.FLAG_IGNORE_METHODS, true)) {
                        writer.write("Methods Renamed \n");
                        writer.newLine();
                        for (MemberNode method : node.getMethods()) {
                            String desc = method.desc;
                            String name_old = method.name;
                            String name_new = node.getMethodName(name_old,
                                    method.desc);
                            if (!name_old.equals(name_new)) {
                                writer.write("		Old Method Name: " + name_old + " New Name: " + name_new + " Method Signature: " + desc);
                                writer.newLine();
                            }
                        }
                    }
                } else {
                    writer.newLine();
                    writer.write("	Class:" + node.name);
                    writer.newLine();

                    if (!node.HasNodeFlags(NodeFlags.FLAG_IGNORE_FIELDS, true)) {
                        writer.write("Fields Renamed \n");
                        writer.newLine();
                        for (MemberNode field : node.getFields()) {
                            String desc = field.desc;
                            String name_old = field.name;
                            String name_new = node.getFieldName(name_old, desc);
                            if (!name_old.equals(name_new)) {
                                writer.write("		Old Field: " + name_old + " New: " + name_new + " Field Signature: " + desc);
                                writer.newLine();
                            }
                        }
                        writer.newLine();
                    }

                    if (!node.HasNodeFlags(NodeFlags.FLAG_IGNORE_METHODS, true)) {
                        writer.write("Methods Renamed \n");
                        writer.newLine();
                        for (MemberNode method : node.getMethods()) {
                            String desc = method.desc;
                            String name_old = method.name;
                            String name_new = node.getMethodName(name_old,
                                    method.desc);
                            if (!name_old.equals(name_new)) {
                                writer.write("		Old Method Name: " + name_old + " New Name: " + name_new + " Method Signature: " + desc);
                                writer.newLine();
                            }
                        }
                    }
                }
            }
        }
        writer.flush();
    }
    
    // TODO: Pass renaming rules to this method.
    public Remapper rename(Map<String, LinkedInterfaceNode> tree) {
        int fieldPostfix = 1;

        int classPostfix = 1;
        int enumPostfix = 1;
        int interfacePostfix = 1;

        int methodPostfix = 1;

        LinkedList<LinkedInterfaceNode> visited = new LinkedList<LinkedInterfaceNode>();

        for (LinkedInterfaceNode node : tree.values()) {
            if (!node.HasNodeFlags(NodeFlags.FLAG_EXTERNAL | NodeFlags.FLAG_IGNORE_CLASS, false)) {

                if (!(tree.containsKey(node.superName) && !tree.get(node.superName).HasNodeFlags(NodeFlags.FLAG_EXTERNAL | NodeFlags.FLAG_IGNORE_CLASS, false))) {
                    if ((node.access & Opcodes.ACC_INTERFACE) != 0) {
                        if (node.rename("Interface" + interfacePostfix, visited)) {
                            ++interfacePostfix;
                        }
                    } else if ((node.access & Opcodes.ACC_ENUM) != 0) {
                        if (node.rename("Enumerator" + enumPostfix, visited)) {
                            ++enumPostfix;
                        }
                    } else {
                        if (node.rename("Class" + classPostfix, visited)) {
                            ++classPostfix;
                        }
                    }
                }
            }
        }

        PublicizerRemapper remapper = new PublicizerRemapper(tree);
        for (LinkedInterfaceNode node : tree.values()) {
            if (!node.HasNodeFlags(NodeFlags.FLAG_EXTERNAL, true)) {
                for (MemberNode field : node.getFields()) {
                    if (node.renameField(field, getFieldName(remapper,
                            field.desc, fieldPostfix))) {
                        ++fieldPostfix;
                    }
                }
                for (MemberNode method : node.getMethods()) {
                    if(method.name.length() < 4) {
                        if (node.renameMethod(method, "method" + methodPostfix)) {
                            ++methodPostfix;
                        }
                    }
                }
            }
        }
        return remapper;
    }

    private String getFieldName(Remapper remapper, String desc, int idx) {
        char[] chars = desc.toCharArray();
        int i = 0;
        for (int len = chars.length; i < len; ++i) {
            if (chars[i] != '[') {
                break;
            }
        }
        StringBuilder builder = new StringBuilder();
        switch (chars[i]) {
            case 'I':
                builder.append("anInt");
                break;
            case 'Z':
                builder.append("aBoolean");
                break;
            case 'B':
                builder.append("aByte");
                break;
            case 'S':
                builder.append("aShort");
                break;
            case 'C':
                builder.append("aChar");
                break;
            case 'J':
                builder.append("aLong");
                break;
            case 'F':
                builder.append("aFloat");
                break;
            case 'D':
                builder.append("aDouble");
                break;
            case 'L':
                String name = remapper
                        .map(desc.substring(i + 1, desc.indexOf(";")));
                name = name.substring(name.lastIndexOf('/') + 1);
                char first = name.toLowerCase().charAt(0);
                if (first == 'a' || first == 'e' || first == 'i' || first == 'o'
                        || first == 'u') {
                    builder.append("an");
                } else {
                    builder.append("a");
                }
                builder.append(name);
                builder.append("_Array".repeat(i));
                char last = builder.charAt(builder.length() - 1);
                if (last >= '0' && last <= '9') {
                    builder.append("_");
                }
                return builder.append(idx).toString();
            default:
                builder.append("aField");
        }
        builder.append("_Array".repeat(i));
        return builder.append(idx).toString();
    }


    public Map<String, LinkedInterfaceNode> buildTree(ClassProvider provider) {
        boolean complete;
        do {
            complete = true;
            classes: for (InterfaceNode node : interfaceNodes.values()) {
                String name = node.name;
                for (String parent : node.parents) {
                    InterfaceNode p = interfaceNodes.get(parent);
                    if (p == null) {
                        p = provider.getClass(parent);
                        if (p != null) {

                            interfaceNodes.put(parent, p);
                            complete = false;
                            break classes;
                        } else {
							System.out.println("Class " + parent
									+ " could not be provided for class "
									+ node.name + ".");
                        }
                    }
                    if(p != null) {
                        List<String> children = p.children;
                        if (!children.contains(name)) {
                            children.add(name);
                        }
                    }
                }
            }
        } while (!complete);
        Map<String, LinkedInterfaceNode> tree = new HashMap<String, LinkedInterfaceNode>();
        for (InterfaceNode cn : interfaceNodes.values()) {
            tree.put(cn.name, new LinkedInterfaceNode(cn.name, cn.access,
                    cn.superName, cn.flags, cn.fields, cn.methods));
        }
        for (LinkedInterfaceNode in : tree.values()) {
            InterfaceNode cn = interfaceNodes.get(in.name);
            if (cn != null) {
                for (String parent : cn.parents) {
                    LinkedInterfaceNode p = tree.get(parent);
                    if (p == null) {
                        System.err.println("Parent " + parent + " not found!");
                    } else {
                        in.addParent(p);
                    }
                }
                for (String child : cn.children) {
                    in.addChild(tree.get(child));
                }
            }
        }
        return tree;
    }
}
