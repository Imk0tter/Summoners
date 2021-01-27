package Summoners.Tools.Provider;

import Summoners.Tools.Tree.InterfaceNode;
import Summoners.Tools.Tree.NodeFlags;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class SCLClassProvider extends ClassProvider {

    public SCLClassProvider() {
        super(null);
    }

    public SCLClassProvider(ClassProvider delegate) {
        super(delegate);
    }

    public InterfaceNode getClass(String name) {
        try {
            InterfaceNode cn = new InterfaceNode(NodeFlags.FLAG_EXTERNAL);
            ClassReader cr = new ClassReader(name);
            cr.accept(cn, 0);

            System.out.println("SCLClassProvider: Class Name: " + cn.name + ", Flags: " + cn.flags);
            return cn;
        } catch (IOException ex) {
            if (cp != null) {
                return cp.getClass(name);
            }
        }
        return null;
    }

}
