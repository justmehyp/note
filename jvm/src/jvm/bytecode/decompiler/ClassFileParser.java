package jvm.bytecode.decompiler;

import jvm.bytecode.decompiler.attributeinfo.AttributeInfo;
import jvm.bytecode.decompiler.cpinfo.CpInfo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClassFileParser {

    private String path;
    private DataInputStreamReader reader;

    public ClassFileParser(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new NullPointerException("path cannot be null");
        }

        this.path = path;
        try {
            DataInputStream is = new DataInputStream(new FileInputStream(path));
            this.reader = new DataInputStreamReader(is);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("class file not found", e);
        }
    }

    public ClassFile parse() {
        try {
            short count;
            return ClassFile.builder()
                    .magic(reader.u4())
                    .minor(reader.u2())
                    .major(reader.u2())
                    .cpCount(count = reader.u2())
                    .cpInfo(cpInfoArray(count))
                    .accessFlags(reader.u2())
                    .thisClassNameIndex(reader.u2())
                    .superClassNameIndex(reader.u2())
                    .interfaceCount(count = reader.u2())
                    .interfaceNameIndexes(reader.u2Array(count))
                    .fieldCount(count = reader.u2())
                    .fieldInfo(fieldInfoArray(count))
                    .methodCount(count = reader.u2())
                    .methodInfo(methodInfoArray(count))
                    .attributeCount(count = reader.u2())
                    .attributeInfo(attributeInfoArray(count))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("parse class file failed", e);
        } finally {
            closeClassFile();
        }
    }

    private CpInfo[] cpInfoArray(short count) throws IOException {
        if (count == 0) {
            return new CpInfo[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        CpInfo[] result = new CpInfo[icount];
        for (int i = 1; i < icount; i++) { // 居然忘了从 1 开始
            result[i] = cpInfo();
        }
        return result;
    }

    private CpInfo cpInfo() throws IOException {
        return CpInfo.build(reader);
    }

    private FieldInfo[] fieldInfoArray(short count) throws IOException {
        if (count == 0) {
            return new FieldInfo[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        FieldInfo[] result = new FieldInfo[icount];
        for (int i = 0; i < icount; i++) {
            result[i] = fieldInfo();
        }
        return result;
    }

    private FieldInfo fieldInfo() throws IOException {
        return FieldInfo.build(reader);
    }

    private MethodInfo[] methodInfoArray(short count) throws IOException {
        if (count == 0) {
            return new MethodInfo[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        MethodInfo[] result = new MethodInfo[icount];
        for (int i = 0; i < icount; i++) {
            result[i] = methodInfo();
        }
        return result;
    }

    private MethodInfo methodInfo() throws IOException {
        return MethodInfo.build(reader);
    }

    private AttributeInfo[] attributeInfoArray(short count) throws IOException {
        if (count == 0) {
            return new AttributeInfo[0];
        }

        int icount = NumberUtil.toUnsignedShort(count);
        AttributeInfo[] result = new AttributeInfo[icount];
        for (int i = 0; i < icount; i++) {
            result[i] = attributeInfo();
        }
        return result;
    }

    private AttributeInfo attributeInfo() throws IOException {
        return AttributeInfo.build(reader);
    }

    private void closeClassFile() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
