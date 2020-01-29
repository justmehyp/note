package jvm.bytecode.decompiler.cpinfo;

import jvm.bytecode.decompiler.DataInputStreamReader;

import java.io.IOException;

public abstract class CpInfo {
    public byte tag;

    public CpInfo(byte tag) {
        this.tag = tag;
    }

    public static CpInfo build(DataInputStreamReader reader) throws IOException {
        byte tag = reader.u1();
        switch (tag) {
            case 1:
                short count = reader.u2();
                return new CpInfo_UTF8(count, reader.u1Array(count));
            case 3:
                return new CpInfo_Integer(reader.u1Array(4));
            case 4:
                return new CpInfo_Float(reader.u1Array(4));
            case 5:
                return new CpInfo_Long(reader.u1Array(8));
            case 6:
                return new CpInfo_Double(reader.u1Array(8));
            case 7:
                return new CpInfo_Class(reader.u2());
            case 8:
                return new CpInfo_String(reader.u2());
            case 9:
                return new CpInfo_FieldRef(reader.u2(), reader.u2());
            case 10:
                return new CpInfo_MethodRef(reader.u2(), reader.u2());
            case 11:
                return new CpInfo_InterfaceMethodRef(reader.u2(), reader.u2());
            case 12:
                return new CpInfo_NameAndTypeRef(reader.u2(), reader.u2());
            default:
                throw new RuntimeException("Unknown CpInfo tag: [" + tag + "]");
        }
    }
}
