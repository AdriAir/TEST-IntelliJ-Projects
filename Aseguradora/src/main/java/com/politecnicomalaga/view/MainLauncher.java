import com.politecnicomalaga.controller.FileController;
import com.politecnicomalaga.model.Client;
import com.politecnicomalaga.model.Incident;
import com.politecnicomalaga.model.Office;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

public class MainLauncher {

    //Varaibles
    boolean exit = false;
    int menu = 0;

    //Oficina
    static String officeCode;
    static String officeName;
    static String officeAddress;
    static String officeCity;
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
    Office office;
    Client client;
    Incident incident;
    MainLauncher mainLauncher = new MainLauncher();

    public static void main(String[] args) {


        //Primer Log In
        do {
            exit = mainLauncher.firstLogMenu(scanner);
        } while (!exit);

        do {

        } while (!exit);

    }

    public boolean firstLogMenu(Scanner scanner) {

        boolean exit;

        System.out.print("---------------------\n" +
                "ASEGURADORA DE COCHES\n" +
                "---------------------\n" +
                "1. CREAR OFICINA\n" +
                "2. IMPORTAR OFICINA\n" +
                "---------------------\n" +
                "Su opción: ");
        try {
            menu = scanner.nextInt();
            scanner = fflush(scanner);

            if (menu < 1 || menu > 2) {
                System.out.println("Introduzca un valor válido...");
            } else {
                exit = true;
            }

        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Porfavor, introduzca un número...");
            exit = false;
        } finally {
            return exit;
        }
    }

    public boolean createOffice() {
        try {

            System.out.print("Introduzca el código de la oficina: ");
            officeCode = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca el nombre de la oficina: ");
            officeName = scanner.nextLine();
            scanner = fflush(scanner);


            System.out.print("Introduzca la dirección de la oficina: ");
            officeAddress = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca la ciudad de la oficina: ");
            officeCity = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca el CP de la oficina: ");
            officePostCode = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca el teléfono de la oficina: ");
            officePhoneNumber = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca el email de la oficina: ");
            officeEmail = scanner.nextLine();
            scanner = fflush(scanner);

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
    }

    public boolean importOfficeMenu() {

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
            scanner = fflush(scanner);

            if (menu < 1 || menu > 3) {

                System.out.println("Introduzca un valor válido...");

            } else {

                exit = true;

            }

        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Porfavor, introduzca un número...");
        }

    }


    public boolean importFromCsv() {
        try {
            System.out.print("Introuduce la ruta del CSV: ");
            path = scanner.nextLine();
            scanner = fflush(scanner);
            csv = FileController.readText(path);
            office = new Office(csv);
            System.out.println("Se ha importado la oficina...");
            exit = true;
        } catch (IOException ioException) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public boolean importFromJson() {
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
    }

    public boolean importFromXml() {
        try {
            System.out.print("Introuduce la ruta del XML: ");
            path = scanner.nextLine();
            scanner = fflush(scanner);
            xml = FileController.readText(path);
            office = (Office) FileController.readXML(xml, office);
            System.out.println("Se ha importado la oficina...");
            exit = true;
        } catch (IOException ioException) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public boolean exportOfficeMenu() {
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
            scanner = fflush(scanner);

            if (menu < 1 || menu > 3) {

                System.out.println("Introduzca un valor válido...");

            } else {

                exit = true;

            }

        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Porfavor, introduzca un número...");
        }
    }

    public boolean exportToCsv() {
        try {
            System.out.print("Introuduce la ruta del nuevo archivo: ");
            path = scanner.nextLine();
            scanner = fflush(scanner);
            csv = office.toCSV();
            FileController.writeText(csv, path);
            System.out.println("Se ha exportado la oficina...");
            exit = true;
        } catch (IOException ioException) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public boolean exportToJson() {
        try {
            System.out.print("Introuduce la ruta del nuevo archivo: ");
            path = scanner.nextLine();
            scanner = fflush(scanner);
            FileController.writeJson(office, path);
            System.out.println("Se ha exportado la oficina...");
            exit = true;
        } catch (IOException ioException) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public boolean exportToXml() {
        try {
            System.out.print("Introuduce la ruta del nuevo archivo: ");
            path = scanner.nextLine();
            scanner = fflush(scanner);
            FileController.writeXML(office, path);
            System.out.println("Se ha exportado la oficina...");
            exit = true;
        } catch (JAXBException e) {
            System.out.println("Error con el XML");
        }
    }

    public boolean mainMenuLoop() {
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
            scanner = fflush(scanner);

            if (menu < 1 || menu > 3) {

                System.out.println("Introduzca un valor válido...");

            } else {

                exit = true;

            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Porfavor, introduzca un número...");
        }
    }

    public boolean createClient() {
        try {

            System.out.print("Introduzca el DNI del cliente: ");
            clientDni = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca el nombre del cliente: ");
            clientName = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca los apellidos del cliente: ");
            clientLastName = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca la dirección postal del cliente: ");
            clientAddress = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca el teléfono del cliente:  ");
            clientPhoneNumber = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca el email del cliente: ");
            clientEmail = scanner.nextLine();
            scanner = fflush(scanner);

            System.out.print("Introduzca código de la póliza del cliente: ");
            clientIdPolicy = scanner.nextLine();
            scanner = fflush(scanner);

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
    }

    public boolean clientsMenuLoop() {
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
            scanner = fflush(scanner);

            if (menu < 1 || menu > 3) {

                System.out.println("Introduzca un valor válido...");

            } else {

                exit = true;

            }

        } catch (InputMismatchException inputMismatchException) {

            System.out.println("Porfavor, introduzca un número...");

        }
    }

    public boolean listClients() {
        try {

            //Listar clientes
            System.out.println("Seleccione un cliente");

            for (int i = 0; i < office.listClients().length; i++) {

                System.out.println(i + 1 + ". " + office.listClients()[i].toString());

            }

            menu = scanner.nextInt();
            scanner = fflush(scanner);

            if (menu < 1 || menu > office.listClients().length + 1) {

                System.out.println("Selecciona una opción válida...");

            } else {

                client = office.listClients()[menu - 1];
                exit = true;

            }

        } catch (InputMismatchException inputMismatchException) {

            System.out.println("Introduzca un valor válido...");

        }
    }

    public boolean searchClientsByDni() {
        try {

            System.out.println("Introduzca el DNI del cliente: ");
            clientDni = scanner.nextLine();
            scanner = fflush(scanner);

            if (office.searchClientByDNI(clientDni) != null) {

                client = office.searchClientByDNI(clientDni);
                exit = true;

            } else {

                System.out.println("No se ha enontrado el cliente...");

            }

        } catch (InputMismatchException inputMismatchException) {

            System.out.println("Introduzca un valor válido...");

        }
    }

    public boolean searchClientByLastName() {
        try {

            System.out.println("Introduzca el/los apellidos a filtrar: ");
            clientLastName = scanner.nextLine();
            scanner = fflush(scanner);

            if (office.searchClientByLastName(clientLastName) != null) {

                System.out.println("Seleccione un cliente: ");
                for (int i = 0; i < office.searchClientByLastName(clientLastName).length; i++) {

                    System.out.println(i + 1 + ". " + office.searchClientByLastName(clientLastName)[i]);

                }

                menu = scanner.nextInt();
                scanner = fflush(scanner);

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
    }

    public boolean selectedClientMenuLoop() {
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
            scanner = fflush(scanner);

            if (menu < 1 || menu > 5) {

                System.out.println("Introduzca un valor válido...");

            } else {

                exit = true;

            }

        } catch (InputMismatchException inputMismatchException) {

            System.out.println("Porfavor, introduzca un número...");

        }
    }


    public static Scanner fflush(Scanner scanner) {
        scanner = new Scanner(System.in);
        return scanner;
    }


}