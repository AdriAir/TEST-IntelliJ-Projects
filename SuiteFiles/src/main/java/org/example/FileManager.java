package org.example;

import java.io.*;
import java.util.Scanner;

public class FileManager {

    private String path;
    private String fileName;
    private final String filePath;
    private PrintWriter printWriter;
    private Scanner scanner;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public FileManager(String path, String fileName) {

        this.path = path;
        this.fileName = fileName;
        this.filePath = this.path + "\\" + this.fileName;

    }

    public boolean writeText(String content) {

        boolean correct = true;

        try {

            this.printWriter = new PrintWriter(new FileWriter(this.filePath));
            this.printWriter.print(content);

        } catch (IOException ioException) {

            correct = false;

        } finally {

            if (this.printWriter != null) {

                this.printWriter.close();

            }
        }

        return correct;
    }

    public String readText() throws FileNotFoundException {

        StringBuilder content = new StringBuilder();

        try {

            this.scanner = new Scanner(new FileReader(this.filePath));

            do {

                content.append(this.scanner.nextLine());

            } while (this.scanner.hasNextLine());

        } finally {

            if (this.scanner != null) {

                this.scanner.close();

            }


        }

        return content.toString();

    }

    public boolean writeBinary(byte[] buffer) {

        boolean correct = true;

        try {

            this.objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.filePath));
            this.objectOutputStream.write(buffer);

            if (this.objectOutputStream != null) {

                this.objectOutputStream.close();

            }

        } catch (IOException ioException) {

            correct = false;

        }

        return correct;

    }

    public byte[] readBinary(int byteNumber) throws IOException {

        byte[] bytes = new byte[byteNumber];

        try {

            this.objectInputStream = new ObjectInputStream(new FileInputStream(this.filePath));

            for (byte bin : bytes) {

                bytes[bin] = this.objectInputStream.readByte();

            }

        } finally {

            if (this.objectInputStream != null) {

                this.objectInputStream.close();

            }
        }

        return bytes;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
