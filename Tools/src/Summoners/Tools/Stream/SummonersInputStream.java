package Summoners.Tools.Stream;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class SummonersInputStream {
    InputStream inputStream;

    public SummonersInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public synchronized byte readByte() throws IOException {
        return (byte) this.read();
    }

    public synchronized byte[] readNBytes(int length) throws IOException {
        return inputStream.readNBytes(length);
    }

    public synchronized int readNBytes(byte[] buffer, int offset, int length) throws IOException {
        return inputStream.readNBytes(buffer, offset, length);
    }

    public synchronized byte[] readAllBytes() throws IOException {
        return inputStream.readAllBytes();
    }

    public synchronized int read(byte[] buffer, int offset, int length) throws IOException {
        return inputStream.read(buffer, offset, length);
    }

    public synchronized void skipNBytes(long n) throws IOException {

        inputStream.skipNBytes(n);

    }

    public synchronized int read() throws IOException {

        return inputStream.read();

    }

    public synchronized int read(byte[] buffer) throws IOException {

        return inputStream.read(buffer);

    }

    public synchronized int readShort() throws IOException {

        return readByte() << 8 | readByte();

    }

    public synchronized int readInt() throws IOException {

        return readShort() << 16 | readShort();
    }

    public synchronized long readLong() throws IOException {

        return readInt() << 32 | readInt();

    }

    public synchronized String readLine() throws IOException {
        String line = "";
        char currentByte;
        while (inputStream.available() > 0 && ((currentByte = (char) readByte()) != 0x0a)) {
            line += currentByte;
        }
        return line;
    }

    public synchronized void close() throws IOException {
        this.inputStream.close();
    }
    public synchronized int available() throws IOException {
        return this.inputStream.available();
    }

}
