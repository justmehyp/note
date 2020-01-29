package jvm.bytecode.decompiler.cpinfo;

public class CpInfo_InterfaceMethodRef extends CpInfo {
    public short classInfoIndex;
    public short nameAndTypeIndex;

    public CpInfo_InterfaceMethodRef(short classInfoIndex, short nameAndTypeIndex) {
        super((byte) 11);
        this.classInfoIndex = classInfoIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
