package jvm.bytecode.decompiler;

import jvm.bytecode.decompiler.attributeinfo.AttributeInfo;
import jvm.bytecode.decompiler.cpinfo.CpInfo;
import jvm.bytecode.decompiler.cpinfo.CpInfo_Class;
import jvm.bytecode.decompiler.cpinfo.CpInfo_UTF8;

import java.lang.reflect.Modifier;

public class ClassFile {

    private int magic;

    private short minor;
    private short major;

    private short cpCount;
    private CpInfo[] cpInfo;

    private short accessFlags;

    private short thisClassNameIndex;

    private short superClassNameIndex;

    private short interfaceCount;
    private short[] interfaceNameIndexes;

    private short fieldCount;
    private FieldInfo[] fieldInfo;

    private short methodCount;
    private MethodInfo[] methodInfo;

    private short attributeCount;
    private AttributeInfo[] attributeInfo;

    private ClassFile() {
    }

    public static ClassFileBuilder builder() {
        return new ClassFileBuilder();
    }

    public static class ClassFileBuilder {
        private ClassFile classFile = new ClassFile();

        public ClassFile build() {
            return classFile;
        }

        public ClassFileBuilder magic(int magic) {
            classFile.magic = magic;
            return this;
        }

        public ClassFileBuilder minor(short minor) {
            classFile.minor = minor;
            return this;
        }

        public ClassFileBuilder major(short major) {
            classFile.major = major;
            return this;
        }

        public ClassFileBuilder cpCount(short cpCount) {
            classFile.cpCount = cpCount;
            return this;
        }

        public ClassFileBuilder cpInfo(CpInfo[] cpInfo) {
            classFile.cpInfo = cpInfo;
            return this;
        }

        public ClassFileBuilder accessFlags(short accessFlags) {
            classFile.accessFlags = accessFlags;
            return this;
        }

        public ClassFileBuilder thisClassNameIndex(short thisClassNameIndex) {
            classFile.thisClassNameIndex = thisClassNameIndex;
            return this;
        }

        public ClassFileBuilder superClassNameIndex(short superClassNameIndex) {
            classFile.superClassNameIndex = superClassNameIndex;
            return this;
        }

        public ClassFileBuilder interfaceCount(short interfaceCount) {
            classFile.interfaceCount = interfaceCount;
            return this;
        }

        public ClassFileBuilder interfaceNameIndexes(short[] interfaceNameIndexes) {
            classFile.interfaceNameIndexes = interfaceNameIndexes;
            return this;
        }

        public ClassFileBuilder fieldCount(short fieldCount) {
            classFile.fieldCount = fieldCount;
            return this;
        }

        public ClassFileBuilder fieldInfo(FieldInfo[] fieldInfo) {
            classFile.fieldInfo = fieldInfo;
            return this;
        }

        public ClassFileBuilder methodCount(short methodCount) {
            classFile.methodCount = methodCount;
            return this;
        }

        public ClassFileBuilder methodInfo(MethodInfo[] methodInfo) {
            classFile.methodInfo = methodInfo;
            return this;
        }

        public ClassFileBuilder attributeCount(short attributeCount) {
            classFile.attributeCount = attributeCount;
            return this;
        }

        public ClassFileBuilder attributeInfo(AttributeInfo[] attributeInfo) {
            classFile.attributeInfo = attributeInfo;
            return this;
        }
    }

    @Override
    public String toString() {
        return "Magic: " + String.format("%X", magic) + "\n"
                + "Minor: " + String.format("%04X", NumberUtil.toUnsignedShort(minor)) + " Major: " + String.format("%04X", NumberUtil.toUnsignedShort(major)) + "\n"
                + "Constant Pool Count: " + cpCount + "\n"
                + "Access Flag: " + AccessFlags.toString(accessFlags) + "\n"
                + "Class Name: " + getThisClass() + "\n"
                + "Super Class: " + getSuperClass() + "\n"
                + "Interface Count:" + interfaceCount + "\n"
                + "Field Count: " + fieldCount + "\n"
                + "Method Count: " + methodCount + "\n"
                + "Attribute Count: " + attributeCount
                ;
    }

    private String getThisClass() {
        return getClassName(thisClassNameIndex);
    }

    private String getSuperClass() {
        return getClassName(superClassNameIndex);
    }

    private String getClassName(int nameIndex) {
        CpInfo_Class clazz = (CpInfo_Class) this.cpInfo[nameIndex];
        CpInfo_UTF8 utf8 = (CpInfo_UTF8) this.cpInfo[clazz.index];
        return new String(utf8.bytes);
    }

    // getter
    public int getMagic() {
        return magic;
    }

    public short getMinor() {
        return minor;
    }

    public short getMajor() {
        return major;
    }

    public short getCpCount() {
        return cpCount;
    }

    public CpInfo[] getCpInfo() {
        return cpInfo;
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public short getThisClassNameIndex() {
        return thisClassNameIndex;
    }

    public short getSuperClassNameIndex() {
        return superClassNameIndex;
    }

    public short getInterfaceCount() {
        return interfaceCount;
    }

    public short[] getInterfaceNameIndexes() {
        return interfaceNameIndexes;
    }

    public short getFieldCount() {
        return fieldCount;
    }

    public FieldInfo[] getFieldInfo() {
        return fieldInfo;
    }

    public short getMethodCount() {
        return methodCount;
    }

    public MethodInfo[] getMethodInfo() {
        return methodInfo;
    }

    public short getAttributeCount() {
        return attributeCount;
    }

    public AttributeInfo[] getAttributeInfo() {
        return attributeInfo;
    }
}
