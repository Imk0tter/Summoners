package Summoners.Tools.Remapper.Descriptor;

import java.util.ArrayList;
import java.util.HashMap;

public class MethodDescriptor {

    public String type;
    public int array;
    public boolean returnValue;

    public static ArrayList<MethodDescriptor> ParseDescriptorString(String descriptorString, HashMap<String, String> classNameHashMap) {
        ArrayList<MethodDescriptor> descriptorList = new ArrayList<MethodDescriptor>();
        return ParseDescriptorString(descriptorString, descriptorList, classNameHashMap);

    }

    public static String GetDescriptorString(ArrayList<MethodDescriptor> list) {
        String ret = "(";
        for (MethodDescriptor d : list) {
            if (d.array > 0) ret += "[".repeat(d.array);
            if (!d.returnValue) ret += d.type;
            else
                ret += ")" + d.type;
        }
        return ret;
    }
    public static ArrayList<MethodDescriptor> ParseDescriptorString(String descriptorString, ArrayList<MethodDescriptor> descriptorList, HashMap<String, String> classNameHashMap) {
        boolean returnValue = false;

        int length = descriptorString.length();
        MethodDescriptor currentDescriptor = new MethodDescriptor();
        boolean doBreak = false;
        for (int i = 0; i < length; ++i) {

            switch (descriptorString.charAt(i)) {
                case '[':
                    ++currentDescriptor.array;
                    break;

                case 'L':
                    int index = descriptorString.indexOf(';', i);
                    String type = descriptorString.substring(i + 1, index);
                    currentDescriptor.type = "L" + (classNameHashMap.containsKey(type) ? classNameHashMap.get(type) : type) + ";";
                    currentDescriptor.returnValue = returnValue;
                    descriptorList.add(currentDescriptor);
                    return ParseDescriptorString(descriptorString.substring(index + 1, length), descriptorList, classNameHashMap);
                case ')':
                    returnValue = true;
                    break;
                case '(':
                    returnValue = false;
                    break;
                default:
                    currentDescriptor.type = Character.toString(descriptorString.charAt(i));
                    currentDescriptor.returnValue = returnValue;
                    descriptorList.add(currentDescriptor);
                    return ParseDescriptorString(descriptorString.substring(i + 1, length), descriptorList, classNameHashMap);
            }
        }
        return descriptorList;
    }
}
