package jvm.bytecode.decompiler.attributeinfo;

import jvm.bytecode.decompiler.DataInputStreamReader;
import java.io.IOException;

public class AttributeInfo {
    public short attributeNameIndex;
    public int attributeLength;
    public byte[] data;

    public static AttributeInfo build(DataInputStreamReader reader) throws IOException {
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.attributeNameIndex = reader.u2();
        attributeInfo.attributeLength = reader.u4();
        attributeInfo.data = reader.u1Array(attributeInfo.attributeLength);
        return attributeInfo;
    }
}
