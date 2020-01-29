package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_UTF8 extends CpInfo {
    public short length;
    public byte[] bytes;

    public CpInfo_UTF8(short length, byte[] bytes) {
        super((byte) 1);
        this.length = length;
        this.bytes = bytes;
    }
}
