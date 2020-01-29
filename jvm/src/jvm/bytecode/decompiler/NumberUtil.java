package jvm.bytecode.decompiler;

public class NumberUtil {

    public static int toUnsignedShort(short num) {
        return num & 0XFFFF;
    }
}
