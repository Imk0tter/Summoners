package Summoners.Tools.Publicizer;

import Summoners.Tools.Tree.InterfaceNode;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoader extends JarFile {

    private Map<String, ClassNode> classes;
    private Map<String, InterfaceNode> interfaces;

    public JarLoader(File f) throws IOException {
        super(f);
    }

    public void load(Map<String, Integer> classFlagMap, BufferedWriter writer) throws IOException {
        HashMap<String, ClassNode> classes = new HashMap<String, ClassNode>();
        HashMap<String, InterfaceNode> interfaces = new HashMap<String, InterfaceNode>();

        Enumeration<JarEntry> jarEntryEnumerator = this.entries();

        while (jarEntryEnumerator.hasMoreElements()) {
            JarEntry jarEntry = jarEntryEnumerator.nextElement();
            if (jarEntry.isDirectory()) {
                continue;
            }
            String jarEntryName = jarEntry.getName();

            if (jarEntryName.endsWith(".class")) {
                writer.write("Loading class: " + jarEntryName + "\n");writer.flush();
                jarEntryName = jarEntryName.substring(0, jarEntryName.lastIndexOf(".class"));
                ClassReader classReader = new ClassReader(this.getInputStream(jarEntry));

                InterfaceNode jarEntryInterfaceNode = new InterfaceNode(classFlagMap.containsKey(jarEntryName) ? classFlagMap.get(jarEntryName) : 0);

                //writer.write("Class Name: " + jarEntryName + ", Class Flags: " + jarEntryInterfaceNode.flags + "\n");writer.flush();


                ClassNode jarEntryClassNode = new ClassNode();
                classReader.accept(jarEntryClassNode, 0);
                jarEntryClassNode.accept(jarEntryInterfaceNode);
                classes.put(jarEntryName, jarEntryClassNode);
                interfaces.put(jarEntryName, jarEntryInterfaceNode);
            }
        }

        this.classes = classes;
        this.interfaces = interfaces;

        writer.write("interfaces count: " + this.interfaces.size() + "\n"); writer.flush();
        writer.write("classes count: " + this.classes.size() + "\n"); writer.flush();
    }

    public Map<String, InterfaceNode> GetInterfaceNodes() {
        return this.interfaces;
    }
    public Map<String, ClassNode> GetClassNodes() {
        return this.classes;
    }
}
