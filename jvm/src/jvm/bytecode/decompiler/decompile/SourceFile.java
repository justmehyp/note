package jvm.bytecode.decompiler.decompile;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SourceFile {
    public String packageName;
    public String[] imports;
    public String accessLevel;
    public boolean isStatic;
    public boolean isFinal;
    public String type;
    public String name;
    public Field[] fields;
    public Method[] methods;

    private static final String encoding = "UTF-8";

    public byte[] toBytes() {
        return toBytes(new ByteArrayOutputStream());
    }

    // todo: ACC_ABSTRACT
    public byte[] toBytes(ByteArrayOutputStream baos) {
        try {
            if (packageName != null) {
                packageName(baos);
                imports(baos);
                accessLevel(baos); isStatic(baos); isFinal(baos); type(baos); name(baos); leftBrace(baos); newLine(baos);

                fields(baos);
                newLine(baos);

                methods(baos);

                rightBrace(baos);
                newLine(baos);
            }
            return baos.toByteArray();
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

    private void packageName(ByteArrayOutputStream baos) throws IOException {
        baos.write(("package " + packageName + ";").getBytes(encoding));
        newLine(baos);
    }

    private void imports(ByteArrayOutputStream baos) throws IOException {
        if (imports != null) {
            for (String ip : imports) {
                baos.write(("import " + ip + ";").getBytes(encoding));
                newLine(baos);
            }
        }
        newLine(baos);
    }

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
        baos.write((name + " ").getBytes(encoding));
    }

    private void fields(ByteArrayOutputStream baos) throws IOException {
        if (fields != null) {
            for (Field field : fields) {
                field.toBytes(baos);
            }
        }
    }

    private void methods(ByteArrayOutputStream baos) throws IOException {
        if (methods != null) {
            for (Method method : methods) {
                method.toBytes(baos);
                newLine(baos);
            }
        }
    }
}

