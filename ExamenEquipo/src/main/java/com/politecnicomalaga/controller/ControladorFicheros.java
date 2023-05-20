package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Equipo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ControladorFicheros {

    //TEXT
    public static boolean grabarEquipoCSV(String sNombreFichero, Equipo e) {

        FileWriter fileWriter = null;
        PrintWriter printWriter;
        boolean correcto;

        try {
            fileWriter = new FileWriter(sNombreFichero);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(e.toString());
            printWriter.flush();
            fileWriter.close();
            fileWriter = null;
            correcto = true;
        } catch (IOException ex) {
           correcto = false;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    correcto = false;
                }
            }
        }
        return correcto;
    }

    public static Equipo leerEquipoCSV(String sNombreFichero) throws IOException {

        StringBuilder text = new StringBuilder();
        FileReader fileReader = null;
        Scanner scanner;

        try {
            fileReader = new FileReader(sNombreFichero);
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

        return new Equipo(text.toString());

    }

}
