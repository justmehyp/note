package jvm.bytecode.decompiler;

public class AccessFlags {
    public static final int ACC_PUBLIC     = 0X0001;
    public static final int ACC_FINAL      = 0X0010;
    public static final int ACC_SUPER      = 0X0020;
    public static final int ACC_INTERFACE  = 0X0200;
    public static final int ACC_ABSTRACT   = 0X0400;
    public static final int ACC_SYNTHETIC  = 0X1000;
    public static final int ACC_ANNOTATION = 0X2000;
    public static final int ACC_ENUM       = 0X4000;

    public static String toString(int accessFlags) {
        String str = "";
        if ((accessFlags & ACC_PUBLIC) != 0) {
            str += "ACC_PUBLIC ";
        }
        if ((accessFlags & ACC_ABSTRACT) != 0) {
            str += "ACC_ABSTRACT ";
        }
        if ((accessFlags & ACC_FINAL) != 0) {
            str += "ACC_FINAL ";
        }
        if ((accessFlags & ACC_SUPER) != 0) {
            str += "ACC_SUPER ";
        }
        if ((accessFlags & ACC_INTERFACE) != 0) {
            str += "ACC_INTERFACE ";
        }
        if ((accessFlags & ACC_SYNTHETIC) != 0) {
            str += "ACC_SYNTHETIC ";
        }
        if ((accessFlags & ACC_ANNOTATION) != 0) {
            str += "ACC_ANNOTATION ";
        }
        if ((accessFlags & ACC_ENUM) != 0) {
            str += "ACC_ENUM ";
        }
        return str;
    }
}
