package jvm.bytecode.decompiler;

public class NumberUtil {

    public static int toUnsignedShort(short num) {
        return num & 0XFFFF;
    }

    public static int toInt(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes count not be null");
        }
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Length Of [bytes] Is Not '4'");
        }
        return bytes[0] << 24 + bytes[1] << 16 + bytes[2] << 8 + bytes[3];
    }
}
