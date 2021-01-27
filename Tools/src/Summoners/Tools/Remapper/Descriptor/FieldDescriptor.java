package Summoners.Tools.Remapper.Descriptor;

import java.util.ArrayList;
import java.util.HashMap;

public class FieldDescriptor {
    String type;
    int array;
    String object;

    final HashMap<String, String> classNameHashMap;

    public FieldDescriptor(HashMap<String, String> classNameHashMap) {
        this.classNameHashMap = classNameHashMap;

        this.type = "";
        this.array = 0;
        this.object = null;
    }

    public String getDescriptorString() {
        String ret = array > 0 ? "[".repeat(array) : "";
        ret += type;
        ret += getDescriptorObject();
        return ret;
    }

    public String getDescriptorObject() {
        if (this.type.equals("L"))
        return classNameHashMap.containsKey(this.object) ? classNameHashMap.get(this.object) : this.object;
        return "";
    }
    public String getDescriptorType() {
        return this.type;
    }
    public int getDescriptorArrayDepth() {
        return this.array;
    }
    public static FieldDescriptor ParseDescriptorString(String descriptorString, HashMap<String, String> classNameHashMap) {
        return ParseDescriptorString(descriptorString, new FieldDescriptor(classNameHashMap));

    }
    public static FieldDescriptor ParseDescriptorString(String descriptorString, FieldDescriptor currentDescriptor) {

        int length = descriptorString.length();
        boolean doBreak = false;
        for (int i = 0; i < length; ++i) {

            switch (descriptorString.charAt(i)) {
                case '[':
                    ++currentDescriptor.array;
                    break;

                case 'L':
                    int index = descriptorString.indexOf(';', i);
                    currentDescriptor.type = "L";
                    currentDescriptor.object = descriptorString.substring(i + 1, index);
                    return ParseDescriptorString(descriptorString.substring(index + 1, length), currentDescriptor);
                default:
                    currentDescriptor.type = Character.toString(descriptorString.charAt(i));
                    return ParseDescriptorString(descriptorString.substring(i + 1, length), currentDescriptor);
            }
        }
        return currentDescriptor;
    }
}
