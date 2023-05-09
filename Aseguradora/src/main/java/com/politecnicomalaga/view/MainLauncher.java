package com.politecnicomalaga.view;

import com.politecnicomalaga.controller.FileController;
import com.politecnicomalaga.model.Client;
import com.politecnicomalaga.model.Incident;
import com.politecnicomalaga.model.Office;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

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
        String clientDni;
        String clientName;
        String clientLastName;
        String clientAddress;
        String clientPhoneNumber;
        String clientEmail;
        String clientIdPolicy;

        //Incidencia
        String incidentDate;
        String incidentTimeHours;
        String incidentOwnVehicle;
        String incidentThirdPartyVehicle;
        String incidentDescription;
        String incidentPolicyId;
        int incidentPolicyNumber;
        int incidentMaxDays;
        String incidentThirdPartyDriverDni;

        //Files
        String path;
        String csv;
        String json;
        String xml;

        //Objects
        Scanner scanner = new Scanner(System.in);
        Office office = null;
        Client client;
        Incident incident;

        //First Start
        do {
            System.out.print("---------------------\n" +
                    "ASEGURADORA DE COCHES\n" +
                    "---------------------\n" +
                    "1. CREAR OFICINA\n" +
                    "2. IMPORTAR OFICINA\n" +
                    "---------------------\n" +
                    "Su opción: ");
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
            //Create Office
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

                    office = new Office(officeCode,
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

        } else {
            //Import Office
            do {
                System.out.print("---------------------\n" +
                        "IMPORTAR OFICINA\n" +
                        "---------------------\n" +
                        "1. DESDE CSV\n" +
                        "2. DESDE JSON\n" +
                        "3. DESDE XML\n" +
                        "---------------------\n" +
                        "Su opción: ");
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
                //Import from CSV
                do {
                    try {
                        System.out.print("Introuduce la ruta del CSV: ");
                        path = scanner.nextLine();
                        csv = FileController.readText(path);
                        office = new Office(csv);
                        System.out.println("Se ha importado la oficina...");
                        exit = true;
                    } catch (IOException ioException) {
                        System.out.println("No se ha encontrado el archivo");
                    }
                } while (!exit);
                exit = false;
            } else if (menu == 2) {
                //Import from JSON
                do {
                    try {
                        System.out.print("Introuduce la ruta del JSON: ");
                        path = scanner.nextLine();
                        json = FileController.readText(path);
                        office = (Office) FileController.readJson(json, office);
                        System.out.println("Se ha importado la oficina...");
                        exit = true;
                    } catch (IOException ioException) {
                        System.out.println("No se ha encontrado el archivo");
                    }
                } while (!exit);
                exit = false;
            } else {
                //Import from XML
                do {
                    try {
                        System.out.print("Introuduce la ruta del XML: ");
                        path = scanner.nextLine();
                        xml = FileController.readText(path);
                        office = (Office) FileController.readXML(xml, office);
                        System.out.println("Se ha importado la oficina...");
                        exit = true;
                    } catch (IOException ioException) {
                        System.out.println("No se ha encontrado el archivo");
                    }
                } while (!exit);
                exit = false;
            }
        }

        //Loop del programa
        do {
            //Office Created
            do {
                System.out.print("---------------------\n" +
                        "ASEGURADORA DE COCHES\n" +
                        "---------------------\n" +
                        "1. ALTA CLIENTE\n" +
                        "2. VER CLIENTES\n\n" +
                        "3. GUARDAR Y SALIR\n" +
                        "---------------------\n" +
                        "Su opción: ");

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
                //Create Client
                do {
                    try {

                        System.out.print("Introduzca el DNI del cliente: ");
                        clientDni = scanner.nextLine();

                        System.out.print("Introduzca el nombre del cliente: ");
                        clientName = scanner.nextLine();

                        System.out.print("Introduzca los apellidos del cliente: ");
                        clientLastName = scanner.nextLine();

                        System.out.print("Introduzca la dirección postal del cliente: ");
                        clientAddress = scanner.nextLine();

                        System.out.print("Introduzca el teléfono del cliente:  ");
                        clientPhoneNumber = scanner.nextLine();

                        System.out.print("Introduzca el email del cliente: ");
                        clientEmail = scanner.nextLine();

                        System.out.print("Introduzca código de la póliza del cliente: ");
                        clientIdPolicy = scanner.nextLine();

                        client = new Client(clientDni,
                                clientIdPolicy,
                                clientName,
                                clientLastName,
                                clientAddress,
                                clientEmail,
                                clientPhoneNumber);

                        office.addClient(client);

                        exit = true;

                    } catch (InputMismatchException inputMismatchException) {

                        System.out.println("Porfavor, introduzca valores válido...");

                    } catch (IllegalArgumentException illegalArgumentException) {

                        System.out.println("El DNI del cliente ya está asignado a otro cliente...");

                    }
                } while (!exit);

            } else if (menu == 2) {

                //VER CLIENTES
                do {

                    System.out.print("---------------------\n" +
                            "GESTOR DE CLIENTES\n" +
                            "---------------------\n" +
                            "1. LISTAR CLIENTES\n" +
                            "2. BUSCAR CLIENTE POR DNI\n" +
                            "3. BUSCAR CLIENTES POR APELLIDOS\n" +
                            "---------------------\n" +
                            "Su opción: ");

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

                    do {

                        try {

                            //Listar clientes
                            System.out.println("Seleccione un cliente");

                            for (int i = 0; i < office.listClients().length; i++) {

                                System.out.println(i + 1 + ". " + office.listClients()[i].toString());

                            }

                            menu = scanner.nextInt();

                            if (menu < 1 || menu > office.listClients().length + 1) {

                                System.out.println("Selecciona una opción válida...");

                            } else {

                                client = office.listClients()[menu - 1];
                                exit = true;

                            }

                        } catch (InputMismatchException inputMismatchException) {

                            System.out.println("Introduzca un valor válido...");

                        }
                    } while (!exit);

                    exit = false;

                } else if (menu == 2) {

                    //Buscar Cliente por DNI
                    do {

                        try {

                            System.out.println("Introduzca el DNI del cliente: ");
                            clientDni = scanner.nextLine();

                            if (office.searchClientByDNI(clientDni) != null) {

                                client = office.searchClientByDNI(clientDni);
                                exit = true;

                            } else {

                                System.out.println("No se ha enontrado el cliente...");

                            }

                        } catch (InputMismatchException inputMismatchException) {

                            System.out.println("Introduzca un valor válido...");

                        }

                    } while (!exit);

                    exit = false;

                } else {

                    //Buscar clientes por Apellidos
                    do {

                        try {

                            System.out.println("Introduzca el/los apellidos a filtrar: ");
                            clientLastName = scanner.nextLine();

                            if (office.searchClientByLastName(clientLastName) != null) {

                                System.out.println("Seleccione un cliente: ");
                                for (int i = 0; i < office.searchClientByLastName(clientLastName).length; i++) {

                                    System.out.println(i + 1 + ". " + office.searchClientByLastName(clientLastName)[i]);

                                }

                                menu = scanner.nextInt();

                                if (menu < 1 | menu > office.searchClientByLastName(clientLastName).length + 1) {

                                    System.out.println("Selecciona una opción válida...");

                                } else {

                                    client = office.searchClientByLastName(clientLastName)[menu - 1];
                                    exit = true;

                                }

                            } else {

                                System.out.println("No se ha encontrado ningún cliente...");

                            }

                        } catch (InputMismatchException inputMismatchException) {

                            System.out.println("Introduzca un valor válido...");

                        }
                    } while (!exit);
                }

                //GESTIONAR CLIENTE
                do {

                    System.out.print("---------------------\n" +
                            "GESTOR DEL CLIENTE\n" +
                            "---------------------\n" +
                            "1. ALTA INCIDENCIA\n" +
                            "2. CERRAR INCIDENCIA\n" +
                            "3. ACTUALIZAR CLIENTE\n" +
                            "4. ELIMINAR CLIENTE\n\n" +
                            "5. VOLVER ATRÁS\n" +
                            "---------------------\n" +
                            "Su opción: ");

                    try {

                        menu = scanner.nextInt();

                        if (menu < 1 || menu > 5) {

                            System.out.println("Introduzca un valor válido...");

                        } else {

                            exit = true;

                        }

                    } catch (InputMismatchException inputMismatchException) {

                        System.out.println("Porfavor, introduzca un número...");

                    }
                } while (!exit);

                exit = false;

            } else {

                //Exportar Oficina
                do {
                    System.out.print("---------------------\n" +
                            "GUARDAR OFICINA\n" +
                            "---------------------\n" +
                            "1. GUARDAR EN CSV\n" +
                            "2. GUARDAR EN JSON\n" +
                            "3. GUARDAR EN XML\n" +
                            "---------------------\n" +
                            "Su opción: ");

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

                //Export Office
                do {
                    System.out.print("---------------------\n" +
                            "EXPORTAR OFICINA\n" +
                            "---------------------\n" +
                            "1. EXPORTAR A CSV\n" +
                            "2. EXPORTAR A JSON\n" +
                            "3. EXPORTAR A XML\n" +
                            "---------------------\n" +
                            "Su opción: ");

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
                    //Export to CSV
                    do {
                        try {
                            System.out.print("Introuduce la ruta del nuevo archivo: ");
                            path = scanner.nextLine();
                            csv = office.toCSV();
                            FileController.writeText(csv, path);
                            System.out.println("Se ha exportado la oficina...");
                            exit = true;
                        } catch (IOException ioException) {
                            System.out.println("No se ha encontrado el archivo");
                        }
                    } while (!exit);
                    exit = false;
                } else if (menu == 2) {
                    //Export to JSON
                    do {
                        try {
                            System.out.print("Introuduce la ruta del nuevo archivo: ");
                            path = scanner.nextLine();
                            FileController.writeJson(office, path);
                            System.out.println("Se ha exportado la oficina...");
                            exit = true;
                        } catch (IOException ioException) {
                            System.out.println("No se ha encontrado el archivo");
                        }
                    } while (!exit);
                    exit = false;
                } else {
                    //Export to XML
                    do {
                        try {
                            System.out.print("Introuduce la ruta del nuevo archivo: ");
                            path = scanner.nextLine();
                            FileController.writeXML(office, path);
                            System.out.println("Se ha exportado la oficina...");
                            exit = true;
                        } catch (JAXBException e) {
                            System.out.println("Error con el XML");
                        }
                    } while (!exit);
                    exit = false;
                }
            }

        } while (!exit);

    }

}