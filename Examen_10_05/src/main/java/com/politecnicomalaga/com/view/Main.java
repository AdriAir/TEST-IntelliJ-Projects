package com.politecnicomalaga.com.view;

import com.politecnicomalaga.com.controller.ControladorFicheros;
import com.politecnicomalaga.com.model.Equipo;
import com.politecnicomalaga.com.model.Jugador;
import com.politecnicomalaga.com.model.Portero;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //MAIN DE PRUEBA CSVs
        String path = "C:\\Users\\Abori\\Desktop\\equipo.csv";

        Main.exportarCsv(path);
        Main.importarCsv(path);


    }

    public static boolean exportarCsv(String path) {
        Equipo equipo = new Equipo("Sant Lluís",
                "Menorca, Sant Lluís",
                "cc@gmail.com");
        Jugador jugador = new Jugador("Adrián",
                "Borio Muñoz",
                "41928285J",
                "aborio2004@gmail.com",
                "123123323",
                2004,
                12,
                3);
        equipo.addJugador(jugador);

        Portero portero = new Portero("Manuel",
                "Pons Vidal",
                "312423J",
                "ppvidal@gmail.com",
                "48283625",
                2009,
                11,
                0,
                1);

        equipo.addJugador(portero);
        return ControladorFicheros.writeText(equipo.toString(), path);
    }

    public static boolean importarCsv(String path){

        try {
            Equipo equipo = new Equipo(ControladorFicheros.readText(path));
            System.out.println("Contenido en el equipo:");
            System.out.println(equipo.toString());
            return true;
        } catch (IOException e) {
            System.out.println("Error con la lectura del fichero");
            return false;
        }
    }
}