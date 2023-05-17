package com.politecnicomalaga.com;

import com.politecnicomalaga.com.model.Client;
import com.politecnicomalaga.com.model.Incident;
import com.politecnicomalaga.com.model.Office;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int menu = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("1.IMPORTAR/n2.EXPORTAR");
        menu = scanner.nextInt();

        Office office = null;

        if (menu == 1) {
            //IMPORTAR
            try {
                office = new Office(FileController.readText("C:\\Users\\Abori\\Desktop\\datos.csv"));
                System.out.println(office.toString());
                System.out.println(office.listClients()[0].toString());


            } catch (IOException ioException) {
                System.out.println("MARICON, HAZLO MEJOR");
            }

        } else if (menu == 2) {
            //EXPORTAR
            office = new Office("1234", "Clincca", "Malaga", "Malaga", "1320", "q234", "aborio@gmail");
            office.addClient(new Client("31231", "aaaaa", "Adri", "Borio", "Malaga", "aborio@gmail.com", "823842"));
            office.searchClientByDNI("31231").addIncident(new Incident("23213", "23", "aaaaa", 1, "asdadsa", "adadsa", "asads"));

        }


    }
}