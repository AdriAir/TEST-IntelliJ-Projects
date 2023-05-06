package com.politecnicomalaga;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainLauncher {
    public static void main(String[] args) {

        //Varaibles
        boolean exit = false;

        //Objects
        Scanner scanner = new Scanner(System.in);

        System.out.print("""
                ---------------------
                ASEGURADORA DE COCHES
                ---------------------
                1. CREAR OFICINA
                2. IMPORTAR OFICINA
                ---------------------
                Su opción:\s""");

        if (read(0, scanner) != null) {

            System.out.println();

        } else {

            System.out.println();

        }

        do {


        } while (!exit);

    }

    public static Object read(int type, Scanner scanner) {

        /*
        type == -1 -> STRING
        type == 0 -> INT
        type == 1 -> FLOAT

        return null if error
         */

        if (type == -1) {

            String string;

            try {
                string = scanner.nextLine();
            } catch (InputMismatchException inputMismatchException) {
                return null;
            }

            return string;

        } else if (type == 0) {

            int number;

            try {
                number = scanner.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                return null;
            }

            return number;

        } else if (type == 1) {

            float number;

            try {
                number = scanner.nextFloat();
            } catch (InputMismatchException inputMismatchException) {
                return null;
            }

            return number;

        } else {
            return null;
        }

    }
}