//MUST ADD TO GRADLE/MAVEN GSON AND JAXB LIBS

package com.politecnicomalaga.com.controller;

import com.google.gson.Gson;
import com.politecnicomalaga.com.model.Equipo;

import java.io.*;
import java.util.Scanner;

public class ControladorFicheros {

    //TEXT
    public static boolean writeText(String text, String path) {

        //ALERTA: Creo que si intentamos guardar el fichero en la misma ruta que el proyecto, no se almacena nada

        //Utilizando un FileWriter ayudado por
        //un printWriter, escribimos el texto
        //en el fichero indicado y en caso
        //de error, devolvemos falso, si no, true.
        FileWriter fileWriter = null;
        PrintWriter printWriter;
        boolean correct;

        try {
            fileWriter = new FileWriter(path, false);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.flush();
            fileWriter.close();
            fileWriter = null;
            correct = true;
        } catch (IOException e) {
            correct = false;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    correct = false;
                }
            }
        }

        return correct;
    }

    public static String readText(String path) throws IOException {

        //Utilizando un FileReader ayudado por
        //un Scanner, leemos el texto
        //del fichero indicado y en caso
        //de error, elevamos la excepción
        //si no, devolvemos el resultado de la lectura.

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


    //CSV
    public static boolean ControladorFicherosCSV(String sNombreFichero, Equipo e) {
        //VER MÉTODO writeText
        return ControladorFicheros.writeText(e.toString(), sNombreFichero);

    }

    public static Equipo leerEquipoCSV(String sNombreFichero) {
        //VER MÉTODO readText

        //Creamos el objeto utilizando el
        //constructor que acepta como parámetro
        //un string en formato csv

        Equipo equipo;
        String csv;

        try {
            csv = ControladorFicheros.readText(sNombreFichero);
            equipo = new Equipo(csv);
        } catch (IOException e) {
            return null;
        }

        return equipo;

    }
}
