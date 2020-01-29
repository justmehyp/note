package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_FieldRef extends CpInfo {
    public short classInfoIndex;
    public short nameAndTypeIndex;

    public CpInfo_FieldRef(short classInfoIndex, short nameAndTypeIndex) {
        super((byte) 9);
        this.classInfoIndex = classInfoIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
