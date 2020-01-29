package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_String extends CpInfo {
    public short index;

    public CpInfo_String(short index) {
        super((byte) 8);
        this.index = index;
    }
}
