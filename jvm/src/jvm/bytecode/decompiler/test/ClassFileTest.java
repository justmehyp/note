package jvm.bytecode.decompiler.test;

import jvm.bytecode.decompiler.ClassFile;
import jvm.bytecode.decompiler.ClassFileParser;

public class ClassFileTest {
    public static void main(String[] args) {
        String path = "/Users/hyp/code/note/jvm/target/classes/jvm/bytecode/lab1/JvmBytecoeLab1.class";
        ClassFileParser parser = new ClassFileParser(path);
        ClassFile classFile = parser.parse();
        System.out.println(classFile);
    }
}
