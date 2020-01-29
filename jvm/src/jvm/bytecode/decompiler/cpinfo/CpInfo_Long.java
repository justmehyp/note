package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_Long extends CpInfo {
    public byte[] bytes;

    public CpInfo_Long(byte[] bytes) {
        super((byte) 5);
        this.bytes = bytes;
    }
}
