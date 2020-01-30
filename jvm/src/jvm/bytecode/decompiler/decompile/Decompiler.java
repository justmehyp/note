package jvm.bytecode.decompiler.decompile;

import jvm.bytecode.decompiler.*;
import jvm.bytecode.decompiler.attributeinfo.*;
import jvm.bytecode.decompiler.cpinfo.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

// todo 泛型
public class Decompiler {
    private String classFilePath;

    public Decompiler(String classFilePath) {
        this.classFilePath = classFilePath;
    }

    public void decompile(OutputStream os) throws IOException {
        ClassFileParser classFileParser = new ClassFileParser(classFilePath);
        ClassFile classFile = classFileParser.parse();
        byte[] source = generateSourceFile(classFile);
        os.write(source);
    }

    private byte[] generateSourceFile(ClassFile classFile) {
        SourceFile sourceFile = new SourceFile();

        CpInfo[] cpInfo = classFile.getCpInfo();
        String thisClassName = getClassName(cpInfo, classFile.getThisClassNameIndex());
        thisClassName = slash2Dot(thisClassName);
        sourceFile.packageName = getPackageName(thisClassName);
        sourceFile.accessLevel = determineAccessLevel(classFile.getAccessFlags());
        sourceFile.isStatic = determineIsStatic(classFile.getAccessFlags());
        sourceFile.isFinal = determineIsFinal(classFile.getAccessFlags());
        sourceFile.name = getSimpleName(thisClassName);
        sourceFile.type = determineType(classFile);
        sourceFile.fields = determineFields(classFile, sourceFile);
        sourceFile.methods = determineMethods(classFile, sourceFile);
        return sourceFile.toBytes();
    }

    private Field[] determineFields(ClassFile classFile, SourceFile sourceFile) {
        FieldInfo[] fieldInfos = classFile.getFieldInfo();
        Field[] fields = new Field[fieldInfos.length];
        for (int i = 0; i< fieldInfos.length; i++) {
            FieldInfo fieldInfo = fieldInfos[i];
            fields[i] = determineField(classFile, fieldInfo);
            fields[i].sourceFile = sourceFile;
        }
        return fields;
    }

    private Field determineField(ClassFile classFile, FieldInfo fieldInfo) {
        CpInfo[] cpInfo = classFile.getCpInfo();
        Field field = new Field();
        field.accessLevel = determineAccessLevel(fieldInfo.accessFlags);
        field.isStatic = determineIsStatic(fieldInfo.accessFlags);
        field.isFinal = determineIsFinal(fieldInfo.accessFlags);
        String binaryName = toBinaryName(getUtf8(cpInfo, fieldInfo.descriptorIndex)); // todo import
        field.type = getSimpleName(binaryName);
        field.name = getUtf8(cpInfo, fieldInfo.nameIndex);
        field.initValue = determineInitValue(fieldInfo, cpInfo);
        return field;
    }

    private String determineInitValue(FieldInfo fieldInfo, CpInfo[] cpInfo) {
        if (fieldInfo.attributeCount == 0) {
            return null;
        }
        for (AttributeInfo attribute : fieldInfo.attributes) {
            if ("ConstantValue".equals(getUtf8(cpInfo, attribute.attributeNameIndex))) {
                ConstantValueAttributeInfo attributeInfo = ConstantValueAttributeInfo.from(attribute);
                int index = NumberUtil.toUnsignedShort(attributeInfo.index);
                CpInfo cp = cpInfo[index];
                if (cp instanceof CpInfo_String) {
                    CpInfo_String string = (CpInfo_String) cp;
                    return "\"" + getUtf8(cpInfo, string.index) + "\"";
                } else if (cp instanceof CpInfo_Integer) {
                    CpInfo_Integer integer = (CpInfo_Integer) cp;
                    return String.valueOf(NumberUtil.toInt(integer.bytes));
                } else {
                    throw new RuntimeException("Unknown Constant Type: " + cp.getClass());
                }
            }
        }
        return null;
    }

    public static String toBinaryName(String name) {
        if ("Z".equals(name)) {
            return "boolean";
        }
        if ("B".equals(name)) {
            return "byte";
        }
        if ("S".equals(name)) {
            return "short";
        }
        if ("C".equals(name)) {
            return "char";
        }
        if ("I".equals(name)) {
            return "int";
        }
        if ("J".equals(name)) {
            return "long";
        }
        if ("F".equals(name)) {
            return "float";
        }
        if ("D".equals(name)) {
            return "double";
        }
        if ("V".equals(name)) {
            return "void";
        }

        if (name.startsWith("[")) {
            return toBinaryName(name.substring(1)) + "[]";
        }

        if (name.startsWith("L")) {
            return slash2Dot(name.substring(1, name.length() - 1));
        }
        else {
            throw new RuntimeException("Unknown Type: " + name);
        }
    }

    private Method[] determineMethods(ClassFile classFile, SourceFile sourceFile) {
        MethodInfo[] methodInfos = classFile.getMethodInfo();
        Method[] methods = new Method[methodInfos.length];
        for (int i = 0; i< methodInfos.length; i++) {
            MethodInfo methodInfo = methodInfos[i];
            methods[i] = determineMethod(classFile, methodInfo);
            methods[i].sourceFile = sourceFile;
        }
        return methods;
    }

    private Method determineMethod(ClassFile classFile, MethodInfo methodInfo) {
        CpInfo[] cpInfo = classFile.getCpInfo();
        Method method = new Method();
        method.accessLevel = determineAccessLevel(methodInfo.accessFlags);
        method.isStatic = determineIsStatic(methodInfo.accessFlags);
        method.isFinal = determineIsFinal(methodInfo.accessFlags);
        method.isNative = determineIsNative(methodInfo.accessFlags);
        String descriptor = getUtf8(cpInfo, methodInfo.descriptorIndex);
        String returnType = descriptor.substring(descriptor.lastIndexOf(')') + 1); // todo import
        method.returnType = getSimpleName(toBinaryName(returnType));
        method.name = getUtf8(cpInfo, methodInfo.nameIndex);
        method.params = determineParams(methodInfo, cpInfo);
//        method.exceptions = ; // todo
//        method.statements = ;
        return method;
    }

    private String[] determineParams(MethodInfo methodInfo, CpInfo[] cpInfo) {
        String descriptor = getUtf8(cpInfo, methodInfo.descriptorIndex);
        String params = descriptor.substring(1, descriptor.lastIndexOf(')'));
        if ("".equals(params)) {
            return new String[0];
        }
        String[] paramTypeArr = splitParamTypeArr(params);
        String[] result = new String[paramTypeArr.length];
        String[] locals = determineLocals(methodInfo, cpInfo);
        int startIndex = -1;
        if (locals != null) {
            startIndex = "this".equals(locals[0]) ? 1 : 0;
        }

        for (int i = 0; i < paramTypeArr.length; i++) {
            String paramType = paramTypeArr[i]; // todo import
            result[i] = getSimpleName(paramType);
            if (locals != null) {
                result[i] += " " + locals[i + startIndex];
            }
        }

        return result;
    }

    public static String[] splitParamTypeArr(String params) {
        List<String> result = new ArrayList<String>();
        int length = params.length();
        int index = 0;
        while (index < length) {
            Object[] paramAndIndex = ate(params, index);
            result.add((String) paramAndIndex[0]);
            index = (Integer) paramAndIndex[1];
        }
        return result.toArray(new String[0]);
    }

    private static Object[] ate(String params, int index) {
        if (params.charAt(index) == 'Z') {
            return new Object[] {"boolean", index + 1};
        } else if (params.charAt(index) == 'B') {
            return new Object[] {"byte", index + 1};
        } else if (params.charAt(index) == 'S') {
            return new Object[] {"short", index + 1};
        } else if (params.charAt(index) == 'C') {
            return new Object[] {"char", index + 1};
        } else if (params.charAt(index) == 'I') {
            return new Object[] {"int", index + 1};
        } else if (params.charAt(index) == 'J') {
            return new Object[] {"long", index + 1};
        } else if (params.charAt(index) == 'F') {
            return new Object[] {"float", index + 1};
        } else if (params.charAt(index) == 'D') {
            return new Object[] {"double", index + 1};
        } else if (params.charAt(index) == '[') {
            Object[] paramAndIndex = ate(params, index + 1);
            return new Object[] {paramAndIndex[0] + "[]", paramAndIndex[1]};
        } else if(params.charAt(index) == 'L') {
            int i = params.indexOf(';', index);
            return new Object[] {toBinaryName(params.substring(index, i + 1)), i + 1};
        } else {
            throw new RuntimeException("Unknown Parameter Type: " + params);
        }
    }

    private String[] determineLocals(MethodInfo methodInfo, CpInfo[] cpInfo) {
        if (methodInfo.attributeCount == 0) {
            return null;
        }

        for (AttributeInfo attribute : methodInfo.attributes) {
            String attributeName = getUtf8(cpInfo, attribute.attributeNameIndex);
            if ("Code".equals(attributeName)) {
                CodeAttributeInfo codeAttributeInfo = CodeAttributeInfo.from(attribute);
                if (codeAttributeInfo.attributeCount == 0) {
                    return null;
                }
                else {
                    for (AttributeInfo attributeInfo : codeAttributeInfo.attributes) {
                        attributeName = getUtf8(cpInfo, attributeInfo.attributeNameIndex);
                        if ("LocalVariableTable".equals(attributeName)) {
                            LocalVariableTableAttributeInfo localVariableTable = LocalVariableTableAttributeInfo.from(attributeInfo);
                            String[] result = new String[localVariableTable.localVariableTables.length];
                            LocalVariableTableAttributeInfo.LocalVariableTable[] localVariableTables = localVariableTable.localVariableTables;
                            for (int i = 0; i < localVariableTables.length; i++) {
                                LocalVariableTableAttributeInfo.LocalVariableTable variableTable = localVariableTables[i];
                                result[i] = getUtf8(cpInfo, variableTable.nameIndex);
                            }
                            return result;
                        }
                    }
                }
            }
        }

        return null;
    }

    //- ACC_FINAL      0X0010
    private boolean determineIsFinal(short accessFlags) {
        return (accessFlags & 0X0010) != 0;
    }

    //- ACC_NATIVE     0X0100
    private boolean determineIsNative(short accessFlags) {
        return (accessFlags & 0X0100) != 0;
    }

    //- ACC_STATIC     0X0008
    private boolean determineIsStatic(short accessFlags) {
        return (accessFlags & 0X0008) != 0;
    }

    //- ACC_PUBLIC     0X0001
    private String determineAccessLevel(short accessFlags) {
        if ((accessFlags & 0X0001) != 0) {
            return "public";
        }
        else if((accessFlags & 0X0002) != 0) {
            return "private";
        }
        else if((accessFlags & 0X0004) != 0) {
            return "protected";
        }
        else {
//            throw new RuntimeException("Unknown Access Level: [" + accessFlags + "]");
            return "";
        }
    }

    //- ACC_INTERFACE  0X0200
    //- ACC_ANNOTATION 0X2000
    //- ACC_ENUM       0X4000
    private String determineType(ClassFile classFile) {
        short accessFlags = classFile.getAccessFlags();
        if ((accessFlags & 0X0200) != 0) {
            return "interface";
        }
        else if ((accessFlags & 0X2000) != 0) {
            return "@interface";
        }
        else if ((accessFlags & 0X4000) != 0) {
            return "enum";
        }
        else {
            return "class";
        }
    }

    private String getClassName(CpInfo[] cpInfo, short classNameIndex) {
        if (classNameIndex == 0) {
            return "";
        }
        int index = NumberUtil.toUnsignedShort(classNameIndex);
        CpInfo_Class clazz = (CpInfo_Class) cpInfo[index];
        return getUtf8(cpInfo, clazz.index);
    }

    public String getUtf8(CpInfo[] cpInfo, short index) {
        int iindex = NumberUtil.toUnsignedShort(index);
        CpInfo_UTF8 utf8 = (CpInfo_UTF8) cpInfo[iindex];
        return new String(utf8.bytes, 0, utf8.length);
    }

    public static String slash2Dot(String string) {
        return string.replaceAll("/", "\\.");
    }

    public static String getSimpleName(String fullName) {
        int lastIndexOfDot = fullName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return fullName;
        }
        return fullName.substring(lastIndexOfDot + 1);
    }

    public static String getPackageName(String fullName) {
        int lastIndexOfDot = fullName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return fullName.substring(0, lastIndexOfDot);
    }
}
