package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_NameAndTypeRef extends CpInfo {
    public short nameIndex;
    public short descriptorIndex;

    public CpInfo_NameAndTypeRef(short nameIndex, short descriptorIndex) {
        super((byte) 12);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
