package jvm.bytecode.decompiler.test;

import jvm.bytecode.decompiler.decompile.Decompiler;

import java.io.IOException;

public class DecompilerTest {
    public static void main(String[] args) throws IOException {
        String path = "/Users/hyp/code/note/jvm/target/classes/jvm/bytecode/lab1/JvmBytecoeLab1.class";
        Decompiler decompiler = new Decompiler(path);
        decompiler.decompile(System.out);
    }
}
