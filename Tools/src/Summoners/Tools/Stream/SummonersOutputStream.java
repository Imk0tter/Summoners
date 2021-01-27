package Summoners.Tools.Stream;

import java.io.IOException;
import java.io.OutputStream;

public class SummonersOutputStream {
    OutputStream outputStream;

    public SummonersOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public synchronized void write(byte[] buffer) throws IOException {
        this.outputStream.write(buffer);

    }

    public synchronized void write(byte[] buffer, int offset, int length) throws IOException {
        this.outputStream.write(buffer, offset, length);
    }

    public synchronized void write(int b) throws IOException {
        this.outputStream.write(b);
    }

    public synchronized void writeByte(int b) throws IOException {
        write(b);
    }

    public synchronized void writeShort(int s) throws IOException {
        writeByte(s >> 8 & 0xFF);
        writeByte(s & 0xFF);
    }

    public synchronized void writeInt(int i) throws IOException {
        writeShort(i >> 16 & 0xFFFF);
        writeShort(i & 0xFFFF);

    }

    public synchronized void writeLong(long l) throws IOException {

        writeInt((int) (l >> 32 & 0xFFFFFFFF));
        writeInt((int) (l & 0xFFFFFFFF));

    }

    public synchronized void close() throws IOException {
        this.outputStream.close();
    }

    public synchronized void flush() throws IOException {
        this.outputStream.flush();
    }

    public synchronized void writeLine(String s) throws IOException {
        for (int i = 0; i < s.length(); ++i) {
            writeByte((byte) s.charAt(i));
        }
        writeByte('\r');
        writeByte('\n');
        this.outputStream.flush();
    }

}
