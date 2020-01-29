package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_Double extends CpInfo {
    public byte[] bytes;

    public CpInfo_Double(byte[] bytes) {
        super((byte) 6);
        this.bytes = bytes;
    }
}
