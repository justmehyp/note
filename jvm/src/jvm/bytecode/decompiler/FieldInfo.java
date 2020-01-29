package jvm.bytecode.decompiler;

import jvm.bytecode.decompiler.attributeinfo.AttributeInfo;
import java.io.IOException;

public class FieldInfo {
    public short accessFlags;
    public short nameIndex;
    public short descriptorIndex;
    public short attributeCount;
    public AttributeInfo[] attributes;

    public static FieldInfo build(DataInputStreamReader reader) throws IOException {
        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.accessFlags = reader.u2();
        fieldInfo.nameIndex = reader.u2();
        fieldInfo.descriptorIndex = reader.u2();
        fieldInfo.attributeCount = reader.u2();
        fieldInfo.attributes = buildAttributeInfos(reader, fieldInfo.attributeCount);
        return fieldInfo;
    }

    private static AttributeInfo[] buildAttributeInfos(DataInputStreamReader reader, short count) throws IOException {
        if (count == 0) {
            return new AttributeInfo[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        AttributeInfo[] attributeInfos = new AttributeInfo[icount];
        for (int i = 0; i < icount; i++) {
            attributeInfos[i] = AttributeInfo.build(reader);
        }
        return attributeInfos;
    }
}
