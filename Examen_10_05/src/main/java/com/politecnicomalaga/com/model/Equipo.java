package com.politecnicomalaga.com.model;

import com.politecnicomalaga.com.controller.ComparadorGoles;
import com.politecnicomalaga.com.controller.ComparadorNombre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Equipo {

    protected HashMap<String, Jugador> jugadores;
    protected Portero[] porteros;
    protected String nombre;
    protected int puntuacion;
    protected String ciudad;
    protected String email;

    public Equipo(String nombre, String ciudad, String email) throws IllegalArgumentException {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.email = email;
        this.puntuacion = 0;

        this.porteros = new Portero[2];
        this.jugadores = new HashMap<>();
    }

    public Equipo(String sCadenaCSV) {
        //Importamos los datos de un csv y los transformamos en objetos

        //Inicializamos las collecciones de la clase
        this.porteros = new Portero[2];
        this.jugadores = new HashMap<>();

        //Dividimos las sentencias
        String[] lines = sCadenaCSV.split("\n");

        //Dividimos los atributos
        String[] attributes = lines[0].split(";");

        //Creamos las colecciones necesarias:
        HashMap<String, Jugador> jugadores = new HashMap<>();
        Portero[] porteros = new Portero[2];

        //Equipo
        //El primer atributo de la primer línea debe ser "EQUIPO:"
        if (attributes[0].contains("EQUIPO")) {
            this.nombre = attributes[1];
            this.puntuacion = Integer.parseInt(attributes[2]);
            this.ciudad = attributes[3];
            this.email = attributes[4];
        } else {
            return;
        }
        //Jugadores
        //Podemos encontrar Porteros o Jugadores

        //PORTEROS
        //RECORREMOS EL BUCLE, DIVIDIMOS LOS ATRIBUTOS DE CADA LÍNEA A PARTIR DE LA 1
        //SEGÚN EL CONTENIDO DEL ATRIBUTO 0, CREAMOS UN PORTERO O UN JUGADOR

        //Dividimos las sentencias
        //Dividimos los atributos
        for (int i = 1; i < lines.length; i++) {
            attributes = lines[i].split(";");

            if (attributes[0].equals("PORTERO")) {
                Portero p = new Portero(Arrays.toString(attributes));
            } else if (attributes[0].equals("JUGADOR")) {
                Jugador j = new Jugador(Arrays.toString(attributes));
            } else {
                return;
            }
        }
    }

    //GETTERS && SETTERS

    public boolean esActivo() {
        //Devuelve true si hay al menos un portero y 5 jugadores o más
        return (this.porteros[0] != null || this.porteros[1] != null) && this.jugadores.size() >= 5;
    }

    //METODOS
    public boolean addPortero(Portero p) {
        //Comprobar que hay sitio en el Array y añadir portero en caso contrario devolver false.
        //No revisa si ya existe!
        if (this.porteros[0] == null) {
            this.porteros[0] = p;
            return true;
        } else if (this.porteros[1] == null) {
            this.porteros[1] = p;
            return true;
        } else {
            return false;
        }
    }

    public boolean addJugador(Jugador j) {
        //Comprobar que el jugador no existe ya en el mapa y añadirlo si es cierto.
        if (this.jugadores.containsKey(j.getDni())) {
            return false;
        } else {
            this.jugadores.put(j.getDni(), j);
            return true;
        }
    }

    public boolean eliminaPortero(String dni) {
        //Comprobar que el portero existe, eliminarlo.
        //Como no comprobamos que hay duplicados en porteros, comprobamos siempre
        //las dos posiciones del array.

        boolean done = false;

        if (this.porteros[0].getDni().equalsIgnoreCase(dni)) {
            this.porteros[0] = null;
            done = true;
        }
        if (this.porteros[1].getDni().equalsIgnoreCase(dni)) {
            this.porteros[1] = null;
            done = true;
        }

        return done;

    }

    public boolean eliminaJugador(String dni) {
        //Comprobar que el jugador existe y lo eliminamos.

        if (this.jugadores.containsKey(dni)) {
            this.jugadores.remove(dni);
            return true;
        }

        return false;

    }

    public ArrayList<Jugador> menoresEdad() {

        //Creamos una lista con los jugadores y la recorremos. Los menores los añadimos a una nueva lista.
        //Luego recorremos los porteros no NULL y añadimos a la  misma lista los menores de edad

        Jugador[] jugadores = new Jugador[this.jugadores.size()];

        //Esto puede producir una excepción ClassCastException
        jugadores = this.jugadores.values().toArray(jugadores);

        ArrayList<Jugador> menoresDeEdad = new ArrayList<>();


        for (Jugador j : jugadores) {
            if (!j.mayorEdad()) {
                menoresDeEdad.add(j);
            }
        }
        for (Portero p : this.porteros) {
            if (p != null && !p.mayorEdad()) {
                menoresDeEdad.add(p);
            }
        }

        //Los ordenamos
        menoresDeEdad.sort(new ComparadorNombre());

        return menoresDeEdad;
    }

    public ArrayList<Jugador> jugadoresTitulares() {

        //Creamos varias collecciones para poder tratar los datos y ordenarlos cómodamente.

        ArrayList<Jugador> titulares = new ArrayList<>();
        ArrayList<Jugador> jugadoresOrdenar = new ArrayList<>();
        Jugador[] jugadores = new Jugador[this.jugadores.size()];
        if (!this.jugadores.isEmpty()) {
            jugadores = this.jugadores.values().toArray(jugadores);
            jugadoresOrdenar.addAll(List.of(jugadores));
        }
        int max;

        //PORTEROS - Revismos si hay nullos y añadimos a la posición 0 el que tenga menos goles encajados.
        if (porteros[0] != null && porteros[1] != null) {
            if (porteros[0].getGolesEncajados() < porteros[1].getGolesEncajados()) {
                titulares.set(0, porteros[0]);
            } else {
                titulares.set(0, porteros[1]);
            }
        } else {
            if (porteros[0] != null) {
                titulares.set(0, porteros[0]);
            } else if (porteros[1] != null) {
                titulares.set(0, porteros[1]);
            }
        }


        //JUGADORES - Los ordenamos por goles y añadimos 5 (o menos si la lista es menor)
        //Esto para evitar problemas, aunque el equipo no esté activo, podrían llamar a la función.

        jugadoresOrdenar.sort(new ComparadorGoles());

        if (jugadoresOrdenar.size() > 5) {
            max = 5;
        } else {
            max = jugadoresOrdenar.size();
        }

        for (int i = 0; i < max; i++) {
            titulares.add(jugadoresOrdenar.get(i));
        }

        return titulares;

    }

    //TO STRING CSV
    @Override
    public String toString() {
        Jugador[] jugadores = new Jugador[this.jugadores.size()];
        if (!this.jugadores.isEmpty()) {
            jugadores = this.jugadores.values().toArray(jugadores);
        }

        //Creamos la sentencia de los datos del Equipo
        StringBuilder csv = new StringBuilder(String.format("EQUIPO;" +
                        "%s;" +
                        "%d;" +
                        "%s;" +
                        "%s\n",
                this.nombre,
                this.puntuacion,
                this.ciudad,
                this.email
        ));

        //Añadimos las sentencias de los porteros
        for (Portero p : this.porteros) {
            if (p != null) {
                csv.append(p);
            }
        }

        //Añadimos las sentencias de todos los jugadores
        if (jugadores != null) {
            for (Jugador j : jugadores) {
                csv.append(j.toString());
            }
        }

        return csv.toString();
    }
}
