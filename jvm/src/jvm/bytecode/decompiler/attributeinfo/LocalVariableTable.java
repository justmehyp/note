package jvm.bytecode.decompiler.attributeinfo;

import jvm.bytecode.decompiler.DataInputStreamReader;

public class LocalVariableTable {
    public short startPC;
    public short length;
    public short nameIndex;
    public short descriptorIndex;
    public short index;

    public static LocalVariableTable build(DataInputStreamReader reader) {
        try {
            LocalVariableTable info = new LocalVariableTable();

            info.startPC = reader.u2();
            info.length = reader.u2();
            info.nameIndex = reader.u2();
            info.descriptorIndex = reader.u2();
            info.index = reader.u2();
            return info;
        } catch (Exception e) {
            throw new RuntimeException("Malformed CodeAttributeInfo", e);
        }
    }
}
