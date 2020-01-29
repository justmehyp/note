package jvm.bytecode.decompiler;

import jvm.bytecode.decompiler.attributeinfo.AttributeInfo;

import java.io.IOException;

public class MethodInfo {
    public short accessFlags;
    public short nameIndex;
    public short descriptorIndex;
    public short attributeCount;
    public AttributeInfo[] attributes;

    public static MethodInfo build(DataInputStreamReader reader) throws IOException {
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.accessFlags = reader.u2();
        methodInfo.nameIndex = reader.u2();
        methodInfo.descriptorIndex = reader.u2();
        methodInfo.attributeCount = reader.u2();
        methodInfo.attributes = buildAttributeInfos(reader, methodInfo.attributeCount);
        return methodInfo;
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
