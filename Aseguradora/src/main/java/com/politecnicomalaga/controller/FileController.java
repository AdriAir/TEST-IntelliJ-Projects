//MUST ADD TO GRADLE/MAVEN GSON AND JAXB LIBS

package com.politecnicomalaga.controller;

import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.Scanner;

public class FileController {

    //TEXT
    public static void writeText(String text, String path) throws IOException {

        FileWriter fileWriter = null;
        PrintWriter printWriter;

        try {
            fileWriter = new FileWriter(path);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.flush();
            fileWriter.close();
            fileWriter = null;
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    public static String readText(String path) throws IOException {

        StringBuilder text = new StringBuilder();
        FileReader fileReader = null;
        Scanner scanner;

        try {
            fileReader = new FileReader(path);
            scanner = new Scanner(fileReader);

            while (scanner.hasNext()) {
                text.append(scanner.nextLine()).append("\n");
            }

            fileReader.close();
            fileReader = null;
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
        return text.toString();
    }

    //XML
    public static void writeXML(Object object, String path) throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller();
        marshaller.marshal(object, new File(path));
    }

    public static Object readXML(String xml, Object object) {
        return new Gson().fromJson(xml, object.getClass());
    }

    //JSON
    public static void writeJSON(Object object, String path) throws IOException {
        writeText(new Gson().toJson(object), path);
    }

    public static Object readJson(String json, Object object) {
        return new Gson().fromJson(json, object.getClass());
    }

    //BINARY
    public static void writeBinary(String text, String path) throws IOException {

        byte[] binaryData = text.getBytes();

        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(binaryData);
            fileOutputStream.close();
            fileOutputStream.flush();
        }
    }

    public static String readBinary(String path) throws IOException {

        StringBuilder text = new StringBuilder();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(path);
            byte[] data = new byte[1];

            while (fileInputStream.read(data) != -1) {
                text.append((char) (data[0]));
                System.out.print(" " + (char) data[0] + "-" + data[0] + " ");
            }

            System.out.println();
            fileInputStream.close();
            fileInputStream = null;

        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return text.toString();
    }
}
