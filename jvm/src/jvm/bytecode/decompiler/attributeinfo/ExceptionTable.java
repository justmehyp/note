package jvm.bytecode.decompiler.attributeinfo;

import jvm.bytecode.decompiler.DataInputStreamReader;

import java.io.IOException;

public class ExceptionTable {
    public short startPC;
    public short endPC;
    public short handlerPC;
    public short catchType;

    public static ExceptionTable build(DataInputStreamReader reader) throws IOException {
        ExceptionTable exceptionTable = new ExceptionTable();
        exceptionTable.startPC = reader.u2();
        exceptionTable.endPC = reader.u2();
        exceptionTable.handlerPC = reader.u2();
        exceptionTable.catchType = reader.u2();
        return exceptionTable;
    }
}
