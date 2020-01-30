package jvm.bytecode.decompiler.attributeinfo;

import jvm.bytecode.decompiler.DataInputStreamReader;
import jvm.bytecode.decompiler.NumberUtil;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class LocalVariableTableAttributeInfo extends AttributeInfo {
    public short localVariableTableCount;
    public LocalVariableTable[] localVariableTables;

    public static LocalVariableTableAttributeInfo from(AttributeInfo attributeInfo) {
        try (DataInputStreamReader reader = new DataInputStreamReader(new DataInputStream(new ByteArrayInputStream(attributeInfo.data)))) {
            LocalVariableTableAttributeInfo info = new LocalVariableTableAttributeInfo();
            info.attributeNameIndex = attributeInfo.attributeNameIndex;
            info.attributeLength = attributeInfo.attributeLength;
            info.data = attributeInfo.data;

            info.localVariableTableCount = reader.u2();
            info.localVariableTables = buildLocalVariableTables(reader, info.localVariableTableCount);
            return info;
        } catch (Exception e) {
            throw new RuntimeException("Malformed CodeAttributeInfo", e);
        }
    }

    private static LocalVariableTable[] buildLocalVariableTables(DataInputStreamReader reader, short count) {
        if (count == 0) {
            return new LocalVariableTable[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        LocalVariableTable[] localVariableTables = new LocalVariableTable[icount];
        for (int i = 0; i < icount; i++) {
            localVariableTables[i] = LocalVariableTable.build(reader);
        }
        return localVariableTables;
    }
}
