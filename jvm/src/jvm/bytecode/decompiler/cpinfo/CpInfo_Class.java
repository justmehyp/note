package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_Class extends CpInfo {
    public short index;

    public CpInfo_Class(short index) {
        super((byte) 7);
        this.index = index;
    }
}
