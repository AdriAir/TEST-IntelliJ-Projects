package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Flota;
import com.politecnicomalaga.model.Vehiculo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladorVehiculos {

    public static void grabarAFichero(String fileName, Flota miFlota) throws IOException {

        FileWriter fileWriter = null;
        PrintWriter printWriter;
        ArrayList<Vehiculo> vehiculos = new ArrayList<>(miFlota.getVehiculos().values());

        try {
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);

            //Escribimos cada vehiculo
            for (Vehiculo vehiculo : vehiculos) {
                printWriter.print(vehiculo.toString());
            }

            printWriter.flush();
            fileWriter.close();
            fileWriter = null;
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }


    public static int leerDeFichero(String fileName, Flota miFlota) throws IOException {

        StringBuilder text = new StringBuilder();
        FileReader fileReader = null;
        Scanner scanner;
        String[] vehiculos;
        String[] attr;
        int lineas = 0;

        try {
            fileReader = new FileReader(fileName);
            scanner = new Scanner(fileReader);

            while (scanner.hasNext()) {
                text.append(scanner.nextLine());
            }

            fileReader.close();
            fileReader = null;
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }

        //Ahora, una vez leido, creamos los objetos
        vehiculos = text.toString().split("VEHICULO:");
        for (String vehiculo : vehiculos) {
            try {
                attr = vehiculo.split(";");

                //Creamos y añadimos los vehiculos
                //El tipo es siempre el penultimo atributo
                Vehiculo v = miFlota.crearVehiculo(attr, attr[6]);
                miFlota.putVehiculo(v);
                lineas++;
            } catch (Exception e) {
                //Si hay escepción, no se ha añade una linea
            }
        }
        return lineas;
    }
}
