package com.politecnicomalaga;

import com.politecnicomalaga.modelo.Oficina;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainLauncher {
    public static void main(String[] args) {

        //Varaibles
        boolean exit = false;
        int menu = 0;

        //Oficina
        String officeCode;
        String officeName;
        String officeAddress;
        String officeCity;
        String officePostCode;
        String officeEmail;
        String officePhoneNumber;

        //Cliente

        //Incidencia

        //Objects
        Scanner scanner = new Scanner(System.in);
        Oficina office;

        //First Start
        do {
            System.out.print("""
                    ---------------------
                    ASEGURADORA DE COCHES
                    ---------------------
                    1. CREAR OFICINA
                    2. IMPORTAR OFICINA
                    ---------------------
                    Su opción:\s""");

            try {
                menu = scanner.nextInt();

                if (menu < 1 || menu > 2) {

                    System.out.println("Introduzca un valor válido...");

                } else {

                    exit = true;

                }

            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Porfavor, introduzca un número...");
            }
        } while (!exit);

        exit = false;

        if (menu == 1) {
            //Crear Oficina
            do {
                try {

                    System.out.print("Introduzca el código de la oficina: ");
                    officeCode = scanner.nextLine();

                    System.out.print("Introduzca el nombre de la oficina: ");
                    officeName = scanner.nextLine();

                    System.out.print("Introduzca la dirección de la oficina: ");
                    officeAddress = scanner.nextLine();

                    System.out.print("Introduzca la ciudad de la oficina: ");
                    officeCity = scanner.nextLine();

                    System.out.print("Introduzca el CP de la oficina: ");
                    officePostCode = scanner.nextLine();

                    System.out.print("Introduzca el teléfono de la oficina: ");
                    officePhoneNumber = scanner.nextLine();

                    System.out.print("Introduzca el email de la oficina: ");
                    officeEmail = scanner.nextLine();

                    office = new Oficina(officeCode,
                            officeName,
                            officeAddress,
                            officeCity,
                            officePostCode,
                            officePhoneNumber,
                            officeEmail);

                    exit = true;

                } catch (InputMismatchException inputMismatchException) {

                    System.out.println("Porfavor, introduzca valores válido...");

                }
            } while (!exit);

            exit = false;

            do {
                System.out.print("""
                        ---------------------
                        ASEGURADORA DE COCHES
                        ---------------------
                        1. ALTA CLIENTE
                        2. BUSCAR CLIENTE
                        3. LISTAR CLIENTES
                                            
                        4. SALIR
                        ---------------------
                        Su opción:\s""");

                try {
                    menu = scanner.nextInt();

                    if (menu < 1 || menu > 4) {

                        System.out.println("Introduzca un valor válido...");

                    } else {

                        exit = true;

                    }
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Porfavor, introduzca un número...");
                }
            } while (!exit);

        } else if (menu == 2) {
            //Importar Oficina
            do {
                System.out.print("""
                        ---------------------
                        IMPORTAR OFICINA
                        ---------------------
                        1. DESDE CSV
                        2. DESDE JSON
                        3. DESDE XML
                        ---------------------
                        Su opción:\s""");

                try {
                    menu = scanner.nextInt();

                    if (menu < 1 || menu > 3) {

                        System.out.println("Introduzca un valor válido...");

                    } else {

                        exit = true;

                    }

                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Porfavor, introduzca un número...");
                }
            } while (!exit);

            exit = false;

            if (menu == 1) {
                //Importar desde CSV
            } else if (menu == 2) {
                //Importar desde JSON
            } else if (menu == 3) {
                //Importar desde XML
            }
        }
    }

}