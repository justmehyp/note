package jvm.bytecode.decompiler.decompile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Method {
    public String accessLevel;
    public boolean isStatic;
    public boolean isFinal;
    public String returnType;
    public String name;
    public String[] params;
    public String exceptions;
    public String[] statements;

    public SourceFile sourceFile;

    private static final String encoding = "UTF-8";

    public void toBytes(ByteArrayOutputStream baos) {
        try {
            accessLevel(baos); isStatic(baos); isFinal(baos); returnType(baos); name(baos);
            leftBracket(baos); params(baos); rightBracket(baos); blank(baos); exceptions(baos);
            leftBrace(baos); newLine(baos);
            statements(baos);
            rightBrace(baos);
            newLine(baos);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception", e);
        }
    }

    private void newLine(ByteArrayOutputStream baos) throws IOException {
        baos.write(0x0A); // \n
    }

    private void leftBrace(ByteArrayOutputStream baos) throws IOException {
        baos.write(("{").getBytes(encoding)); // {
    }

    private void rightBrace(ByteArrayOutputStream baos) throws IOException {
        baos.write(("}").getBytes(encoding)); // }
    }

    private void leftBracket(ByteArrayOutputStream baos) throws IOException {
        baos.write(("(").getBytes(encoding)); // (
    }

    private void rightBracket(ByteArrayOutputStream baos) throws IOException {
        baos.write((")").getBytes(encoding)); // )
    }

    private void blank(ByteArrayOutputStream baos) throws IOException {
        baos.write((" ").getBytes(encoding)); // <空格>
    }

    private void statementEnd(ByteArrayOutputStream baos) throws IOException {
        baos.write((";").getBytes(encoding)); // <空格>
    }

    //========
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

    private void returnType(ByteArrayOutputStream baos) throws IOException {
        if (!"<init>".equals(name)) {
            baos.write((returnType + " ").getBytes(encoding));
        }
    }

    private void name(ByteArrayOutputStream baos) throws IOException {
        if ("<init>".equals(name)) {
            baos.write((sourceFile.name).getBytes(encoding));
        }
        else {
            baos.write((name).getBytes(encoding));
        }
    }

    private void params(ByteArrayOutputStream baos) throws IOException {
        if (params != null && params.length != 0) {
            String firstParam = params[0];
            baos.write((firstParam).getBytes(encoding));
            for (int i = 1; i < params.length; i++) {
                String param = params[i];
                baos.write((", " + param).getBytes(encoding));
            }
        }
    }

    private void exceptions(ByteArrayOutputStream baos) throws IOException {
        //todo
    }

    private void statements(ByteArrayOutputStream baos) throws IOException {
        //todo
    }
}
