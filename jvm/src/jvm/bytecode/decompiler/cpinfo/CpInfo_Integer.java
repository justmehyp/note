package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_Integer extends CpInfo {
    public byte[] bytes;

    public CpInfo_Integer(byte[] bytes) {
        super((byte) 3);
        this.bytes = bytes;
    }
}
