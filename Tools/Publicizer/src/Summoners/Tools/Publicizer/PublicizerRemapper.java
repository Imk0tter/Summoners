package Summoners.Tools.Publicizer;

import Summoners.Tools.Remapper.Remapper;
import Summoners.Tools.Tree.LinkedInterfaceNode;
import org.objectweb.asm.Opcodes;

import java.util.Map;

public class PublicizerRemapper extends Remapper {
    private Map<String, LinkedInterfaceNode> tree;

    public PublicizerRemapper(Map<String, LinkedInterfaceNode> tree) {
        this.tree = tree;
    }

    @Override
    public String mapFieldName(String owner, String name, String desc, int access) {
        LinkedInterfaceNode node = tree.get(owner);
        if (node != null) {
            return node.getFieldName(name, desc);
        }
        return name;
    }

    @Override
    public int mapFieldAccess(String owner, String name, String desc, int access) {
        return (access | Opcodes.ACC_PUBLIC) & ~(Opcodes.ACC_FINAL | Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED);
    }

    @Override
    public String mapMethodName(String owner, String name, String desc, int access) {
        LinkedInterfaceNode node = tree.get(owner);
        if (node != null) {
            return node.getMethodName(name, desc);
        }
        return name;
    }

    @Override
    public int mapMethodAccess(String owner, String name, String descriptor, int access) {
        if (name.equals("<init>") || name.equals("<clinit>") || name.equals("main"))
            return access;
        int newAccess = Opcodes.ACC_PUBLIC;
        //if ((access & Opcodes.ACC_STATIC) == 0)
        //    newAccess |= Opcodes.ACC_ABSTRACT;
        return (access | newAccess) & ~(Opcodes.ACC_FINAL | Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED);

    }

    @Override
    public int mapAccess(String internalName, int access) {
        if ((access & Opcodes.ACC_INTERFACE) != 0 || (access & Opcodes.ACC_ENUM) != 0) {
            return (access | Opcodes.ACC_PUBLIC) & ~(Opcodes.ACC_FINAL | Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED);
        }
        int newAccess = Opcodes.ACC_PUBLIC;
        //if ((access & Opcodes.ACC_STATIC) == 0)
         //   newAccess |= Opcodes.ACC_ABSTRACT;
        return (access | newAccess) & ~(Opcodes.ACC_FINAL | Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED);
    }

    @Override
    public String map(String typeName) {
        //System.out.println("Mapping: TypeName = '" + typeName + "', mappedName = '"+ tree.get(typeName) +"'");

        LinkedInterfaceNode node = tree.get(typeName);
        if (node != null) {
            String name = node.getNewName();
            if (name != null) {
                return name;
            }
        }
        return typeName;
    }
}
