package jvm.bytecode.decompiler.attributeinfo;

import jvm.bytecode.decompiler.DataInputStreamReader;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ConstantValueAttributeInfo extends AttributeInfo {
    public short index;

    public static ConstantValueAttributeInfo from(AttributeInfo attributeInfo) {
        try (DataInputStreamReader reader = new DataInputStreamReader(new DataInputStream(new ByteArrayInputStream(attributeInfo.data)))) {
            ConstantValueAttributeInfo info = new ConstantValueAttributeInfo();
            info.attributeNameIndex = attributeInfo.attributeNameIndex;
            info.attributeLength = attributeInfo.attributeLength;
            info.data = attributeInfo.data;

            info.index = reader.u2();
            return info;
        } catch (Exception e) {
            throw new RuntimeException("Malformed CodeAttributeInfo", e);
        }
    }
}
