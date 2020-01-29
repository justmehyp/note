package jvm.bytecode.decompiler;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

public class DataInputStreamReader implements Closeable {
    private DataInputStream dis;

    public DataInputStreamReader(DataInputStream dataInputStream) {
        this.dis = dataInputStream;
    }

    public byte u1() throws IOException {
        return dis.readByte();
    }

    public short u2() throws IOException {
        return dis.readShort();
    }

    public int u4() throws IOException {
        return dis.readInt();
    }

    public byte[] u1Array(int count) throws IOException {
        byte[] result = new byte[count];
        for (int i = 0; i < count; i++) {
            result[i] = u1();
        }
        return result;
    }

    public short[] u2Array(int count) throws IOException {
        short[] result = new short[count];
        for (int i = 0; i < count; i++) {
            result[i] = u2();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        dis.close();
    }
}
