package Summoners.Tools.File;

import Summoners.Tools.Remapper.IgnoredClass;
import Summoners.Tools.Stream.SummonersOutputStream;
import Summoners.Tools.Tree.NodeFlags;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public class SummonersConfigFile extends SummonersFile {
    public SummonersConfigFile(String fileName) throws FileNotFoundException {
        super(fileName);
        ignoredClassMap = new HashMap<String, Integer>();

        try { processConfig(); }
        catch (IOException e) {

        }
    }

    public synchronized void processConfig() throws IOException {
        this.canRead(true);
        String currentLine = "";
        try {
            while ((currentLine = this.readLine()) != null)  {
                System.out.println("Current Line: " + currentLine);
                processLine(currentLine.strip());
            }
        } catch (IOException e) {

        }
        finally {

        }
    }
    public void processLine(String s) {
        //System.out.println("String: " + s);
        String command = s.substring(0, s.indexOf(":",1)).toLowerCase();
        String value = s.substring(s.indexOf(":", 1) + 1, s.length());


        System.out.println("Command: " + command + " Value: " + value);
        switch (command) {
            case "ignore":
                addIgnoredClass(value.substring(0, value.indexOf(":")), value.substring(value.indexOf(":") + 1, value.length()).split(":"));
                break;
            case "manifest":
                manifestClass = value;
                break;
            default:
                break;
        }
    }

    public Map<String, Integer> ignoredClassMap;

    public synchronized void addIgnoredClass(String ignoredClassName, String[] modifiers) {
        int flags = 0;

        for (String s : modifiers) {
            switch (s.toLowerCase()) {
                case "class":
                    flags |= NodeFlags.FLAG_IGNORE_CLASS;
                    break;
                case "fields":
                    flags |= NodeFlags.FLAG_IGNORE_FIELDS;
                    break;
                case "methods":
                    flags |= NodeFlags.FLAG_IGNORE_METHODS;
                    break;
                default:
                    break;
            }
        }
        ignoredClassMap.put(ignoredClassName, flags);
    }
    public synchronized void writeManifestFile(JarOutputStream jarOutputStream) throws IOException {
        if (manifestClass != null) {
            SummonersOutputStream outputStream = new SummonersOutputStream(jarOutputStream);
            JarEntry manifestEntry = new JarEntry("META-INF/MANIFEST.MF");

            jarOutputStream.putNextEntry(manifestEntry);

            outputStream.writeLine("Manifest-Version: 1.0");
            outputStream.writeLine("Main-Class: " + manifestClass);
            outputStream.writeLine("Coded-By: Imk0tter");
            outputStream.flush();

            jarOutputStream.closeEntry();
        }
    }

    ArrayList<IgnoredClass> classesIgnored;
    String manifestClass;

}
