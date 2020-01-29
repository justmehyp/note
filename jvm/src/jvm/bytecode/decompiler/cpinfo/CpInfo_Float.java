package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_Float extends CpInfo {
    public byte[] bytes;

    public CpInfo_Float(byte[] bytes) {
        super((byte) 4);
        this.bytes = bytes;
    }
}
