package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_MethodRef extends CpInfo {
    public short classInfoIndex;
    public short nameAndTypeIndex;

    public CpInfo_MethodRef(short classInfoIndex, short nameAndTypeIndex) {
        super((byte) 10);
        this.classInfoIndex = classInfoIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
