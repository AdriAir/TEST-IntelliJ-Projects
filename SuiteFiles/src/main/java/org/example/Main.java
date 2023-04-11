package org.example;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        Scanner myScanner = new Scanner(System.in);
        FileManager fileManager;
        String content;
        String path;
        String fileName;

        boolean exit;
        char menu;

        do {
            exit = false;
            System.out.println("""
                    Menú Suite Ficheros
                    a. Escribir texto en pantalla y escribir a disco en modo texto
                    b. Escribir texto en pantalla y escribir a disco en modo binario
                    c. Leer texto de disco y mostrar en pantalla en modo texto
                    d. Leer texto de disco y mostrar en pantalla en modo binario
                    e. Escribir 4 números int y escribirlos en fichero binario
                    f. Leer en binario 4 números int desde fichero en disco
                    
                    0. Salir
                    """);

            menu = myScanner.nextLine().charAt(0);
            myScanner = main.fflush(myScanner);

            if (menu == 'a') {

                System.out.println("Escribe el texto a escribir a disco (texto)");
                content = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                System.out.println("Escribe la ruta del archivo");
                path = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                System.out.println("Escribe el nombre del archivo");
                fileName = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                fileManager = new FileManager(path, fileName);
                if (fileManager.writeText(content)) {

                    System.out.println("Se ha completado la operación!");

                } else {

                    System.out.println("No se ha encontrado el archivo '" + fileName + "' en '" + path + "'");

                }

            } else if (menu == 'b') {

                System.out.println("Escribe el texto a escribir a disco (binario)");
                content = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                System.out.println("Escribe la ruta del archivo");
                path = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                System.out.println("Escribe el nombre del archivo");
                fileName = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                fileManager = new FileManager(path, fileName);
                if (fileManager.writeBinary(content.getBytes(StandardCharsets.UTF_8))) {

                    System.out.println("Se ha completado la operación!");

                } else {

                    System.out.println("No se ha encontrado el archivo '" + fileName + "' en '" + path + "'");

                }

            } else if (menu == 'c') {

                System.out.println("Escribe la ruta del archivo a leer (texto)");
                path = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                System.out.println("Escribe el nombre del archivo");
                fileName = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                fileManager = new FileManager(path, fileName);

                try {

                    System.out.println(fileManager.readText());
                    System.out.println("Se ha completado la operación!");

                } catch (FileNotFoundException e) {

                    System.out.println("No se ha encontrado el archivo '" + fileManager.getFileName() + "' en '" + fileManager.getPath() + "'");

                }

            } else if (menu == 'd') {

                System.out.println("Escribe la ruta del archivo a leer (binario)");
                path = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                System.out.println("Escribe el nombre del archivo");
                fileName = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                fileManager = new FileManager(path, fileName);

                try {

                    System.out.println(Arrays.toString(fileManager.readText().getBytes()));
                    System.out.println("Se ha completado la operación!");

                } catch (FileNotFoundException e) {

                    System.out.println("No se ha encontrado el archivo '" + fileManager.getFileName() + "' en '" + fileManager.getPath() + "'");

                }

            } else if (menu == 'e') {

                System.out.println();

            } else if (menu == 'f') {

                System.out.println("Escribe la ruta del archivo a leer (binario)");
                path = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                System.out.println("Escribe el nombre del archivo");
                fileName = myScanner.nextLine();
                myScanner = main.fflush(myScanner);

                fileManager = new FileManager(path, fileName);

                try {

                    System.out.println(Arrays.toString(fileManager.readText().getBytes()));
                    System.out.println("Se ha completado la operación!");

                } catch (FileNotFoundException e) {

                    System.out.println("No se ha encontrado el archivo '" + fileManager.getFileName() + "' en '" + fileManager.getPath() + "'");

                }

            } else if (menu == '0') {

                exit = true;

            } else {

                System.out.println("No se ha introducido un valor válido...");

            }
        } while (!exit);

    }

    public Scanner fflush(Scanner scanner) {

        scanner = new Scanner(System.in);

        return scanner;

    }

}