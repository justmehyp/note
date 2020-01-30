package jvm.bytecode.decompiler.decompile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Field {
    public String accessLevel;
    public boolean isStatic;
    public boolean isFinal;
    public String type;
    public String name;
    public String initValue;

    public SourceFile sourceFile;

    private static final String encoding = "UTF-8";

    public void toBytes(ByteArrayOutputStream baos) {
        try {
            accessLevel(baos);
            isStatic(baos);
            isFinal(baos);
            type(baos);
            name(baos);
            defaultValue(baos);
            statementEnd(baos);
            newLine(baos);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception", e);
        }
    }

    private void newLine(ByteArrayOutputStream baos) throws IOException {
        baos.write(0x0A); // \n
    }

    private void equal(ByteArrayOutputStream baos) throws IOException {
        baos.write(("=").getBytes(encoding)); // =
    }

    private void blank(ByteArrayOutputStream baos) throws IOException {
        baos.write((" ").getBytes(encoding)); // <空格>
    }

    private void statementEnd(ByteArrayOutputStream baos) throws IOException {
        baos.write((";").getBytes(encoding)); // <空格>
    }

    //=====
    private void accessLevel(ByteArrayOutputStream baos) throws IOException {
        if (accessLevel != null) {
            baos.write((accessLevel + " ").getBytes(encoding));
        }
    }

    private void isStatic(ByteArrayOutputStream baos) throws IOException {
        if (isStatic) {
            baos.write(("static ").getBytes(encoding));
        }
    }

    private void isFinal(ByteArrayOutputStream baos) throws IOException {
        if (isFinal) {
            baos.write(("final ").getBytes(encoding));
        }
    }

    private void type(ByteArrayOutputStream baos) throws IOException {
        baos.write((type + " ").getBytes(encoding));
    }

    private void name(ByteArrayOutputStream baos) throws IOException {
        baos.write((name).getBytes(encoding));
    }

    private void defaultValue(ByteArrayOutputStream baos) throws IOException {
        if (initValue != null) {
            blank(baos);
            equal(baos);
            blank(baos);
            baos.write((initValue).getBytes(encoding));
        }
    }
}
