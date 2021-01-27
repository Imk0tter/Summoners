package Summoners.Tools.File;

import java.io.*;

public class SummonersFile extends File {
    final String fileName;

    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;

    int FLAGS;

    public SummonersFile(String fileName) throws FileNotFoundException {
        super(fileName);
        this.fileName = fileName;

        //fileInputStream = new FileInputStream(this);
        //fileOutputStream = new FileOutputStream(this);

        FLAGS = 0;

    }

    public synchronized void canRead(boolean canRead) throws IOException {
            if (canRead) {
                FLAGS |= FileFlags.CAN_READ;
                if (fileInputStream != null) fileInputStream.close();
                fileInputStream = new FileInputStream(this);
            } else {
                FLAGS &= ~FileFlags.CAN_READ;
                if (fileInputStream != null) fileInputStream.close();
                fileInputStream = null;
            }
        }

    public synchronized void canWrite(boolean canWrite) throws IOException {
        if (canWrite) {
            FLAGS |= FileFlags.CAN_WRITE;
            if (fileOutputStream != null) fileOutputStream.close();
            fileOutputStream = new FileOutputStream(this);
        } else {
            FLAGS &= ~FileFlags.CAN_WRITE;
            if (fileOutputStream != null) fileOutputStream.close();
            fileOutputStream = null;
        }
    }

    public synchronized byte readByte() throws IOException {
        if (READABLE())
           return (byte)this.read();
        throw new IOException("D34DB33F");
    }

    public synchronized byte[] readNBytes(int length) throws IOException {
        if (READABLE())
            return fileInputStream.readNBytes(length);
        throw new IOException("D34DB33F");
    }

    public synchronized int readNBytes(byte[] buffer, int offset, int length) throws IOException {
        if (READABLE())
            return fileInputStream.readNBytes(buffer, offset, length);
        throw new IOException("D34DB33F");
    }

    public synchronized byte[] readAllBytes() throws IOException {
        if (READABLE())
            return fileInputStream.readAllBytes();
        throw new IOException("D34DB33F");
    }

    public synchronized int read(byte[] buffer, int offset, int length) throws IOException {
        if (READABLE())
            return fileInputStream.read(buffer, offset, length);
        throw new IOException("D34DB33F");

    }

    public synchronized void skipNBytes(long n) throws IOException {
        if (READABLE())
            fileInputStream.skipNBytes(n);
        throw new IOException("D34DB33F");
    }

    public synchronized int read() throws IOException {
        if (READABLE())
            return fileInputStream.read();
        throw new IOException("D34DB33F");
    }
    public synchronized int read(byte[] buffer) throws IOException {
        if (READABLE())
            return fileInputStream.read(buffer);
        throw new IOException("D34DB33F");
    }

    public synchronized int readShort() throws IOException {
        if (READABLE())
            return readByte() << 8 | readByte();
        throw new IOException("D34DB33F");
    }

    public synchronized int readInt() throws IOException {
        if (READABLE())
            return readShort() << 16 | readShort();
        throw new IOException("D34DB33F");
    }

    public synchronized long readLong() throws IOException {
        if (READABLE())
            return readInt() << 32 | readInt();
        throw new IOException("D34DB33F");
    }

    public synchronized String readLine() throws IOException {
        String line = "";
        char currentByte;
        if (READABLE()){
            while ((fileInputStream.available() > 0) && ((currentByte = (char)readByte()) != 0x0a)) {
                line += currentByte;
            }
            return line;
        }
        throw new IOException("D34DB33F");
    }

    public synchronized boolean READABLE() throws IOException {
        if ((FLAGS & FileFlags.CAN_READ) != 0) {
            if (fileInputStream.available() > 0)
                return true;
            throw new IOException("Cannot read byte from FileInputStream for file '" + fileName + "': available() <= 0");
        }
        throw new IOException("Cannot read byte from FileInputStream for file '" + fileName + "': (FLAGS & FileFlags.CAN_READ) == 0");
    }

    public synchronized void write(byte[] buffer) throws IOException {
        if (WRITABLE()) {
            fileOutputStream.write(buffer);
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");
    }

    public synchronized void write(byte[] buffer, int offset, int length) throws IOException {
        if (WRITABLE()) {
            fileOutputStream.write(buffer, offset, length);
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");
    }

    public synchronized void write(int b) throws IOException {
        if (WRITABLE()) {
            fileOutputStream.write(b);
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");
    }

    public synchronized void writeByte(int b) throws IOException {
        if (WRITABLE()) {
            write(b);
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");
    }

    public synchronized void writeShort(int s) throws IOException {
        if (WRITABLE()) {
            writeByte(s >> 8 & 0xFF);
            writeByte(s & 0xFF);
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");
    }

    public synchronized void writeInt(int i) throws IOException {
        if (WRITABLE()) {
            writeShort(i >> 16 & 0xFFFF);
            writeShort(i & 0xFFFF);
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");
    }
    public synchronized void writeLong(long l) throws IOException {
        if (WRITABLE()) {
            writeInt((int)(l >> 32 & 0xFFFFFFFF));
            writeInt((int)(l & 0xFFFFFFFF));
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");
    }
    public synchronized void writeLine(String s) throws IOException {
        if (WRITABLE()) {
            for (int i = 0; i < s.length(); ++i) {
                writeByte((byte) s.charAt(i));
            }
            writeByte('\r');
            writeByte('\n');
            fileOutputStream.flush();
        }
        throw new IOException("D34DB33F");

    }
    public synchronized boolean WRITABLE() throws IOException {
        if ((FLAGS & FileFlags.CAN_WRITE) != 0)
            if (fileOutputStream != null)
                return true;
            else
                throw new IOException("Cannot write to FileOutputStream for file '" + fileName + "': fileOutPutStream == NULL");
        throw new IOException("Cannot write to FileOutputStream for file '" + fileName + "': (FLAGS & FileFlags.CAN_WRITE) == 0");
    }
}
