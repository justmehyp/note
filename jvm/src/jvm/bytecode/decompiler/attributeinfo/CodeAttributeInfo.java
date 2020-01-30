package jvm.bytecode.decompiler.attributeinfo;

import jvm.bytecode.decompiler.DataInputStreamReader;
import jvm.bytecode.decompiler.NumberUtil;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class CodeAttributeInfo extends AttributeInfo {

    public short maxStack;
    public short maxLocals;
    public int codeLength;
    public byte[] code;
    public short exceptionTableLength;
    public ExceptionTable[] exceptionTables;
    public short attributeCount;
    public AttributeInfo[] attributes;

    public static CodeAttributeInfo from(AttributeInfo attributeInfo) {
        try (DataInputStreamReader reader = new DataInputStreamReader(new DataInputStream(new ByteArrayInputStream(attributeInfo.data)))) {
            CodeAttributeInfo info = new CodeAttributeInfo();
            info.attributeNameIndex = attributeInfo.attributeNameIndex;
            info.attributeLength = attributeInfo.attributeLength;
            info.data = attributeInfo.data;

            info.maxStack = reader.u2();
            info.maxLocals = reader.u2();
            info.codeLength = reader.u4();
            info.code = reader.u1Array(info.codeLength);
            info.exceptionTableLength = reader.u2();
            info.exceptionTables = buildExceptionTables(reader, info.exceptionTableLength);
            info.attributeCount = reader.u2();
            info.attributes = buildAttributes(reader, info.attributeCount);
            return info;
        } catch (Exception e) {
            throw new RuntimeException("Malformed CodeAttributeInfo", e);
        }
    }

    private static ExceptionTable[] buildExceptionTables(DataInputStreamReader reader, short count) throws IOException {
        if (count == 0) {
            return new ExceptionTable[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        ExceptionTable[] result = new ExceptionTable[icount];
        for (int i = 0; i < icount; i++) {
            result[i] = ExceptionTable.build(reader);
        }
        return result;
    }

    private static AttributeInfo[] buildAttributes(DataInputStreamReader reader, short count) throws IOException {
        if (count == 0) {
            return new AttributeInfo[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        AttributeInfo[] attributes = new AttributeInfo[icount];
        for (int i = 0; i < icount; i++) {
            attributes[i] = AttributeInfo.build(reader);
        }
        return attributes;
    }
}
