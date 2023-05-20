package com.politecnicomalaga.model;

import com.politecnicomalaga.controller.ComparaGoles;
import com.politecnicomalaga.controller.ComparaJugador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Equipo {

    private Map<String, Jugador> jugadores;
    private Portero[] porteros;
    private String nombre;
    private int puntuacion;
    private String ciudad;
    private String email;

    public Equipo(String nombre, int puntuacion, String ciudad, String email) {
        this.nombre = nombre;
        if (puntuacion < 0) {
            this.puntuacion = 0;
        } else {
            this.puntuacion = puntuacion;
        }
        this.ciudad = ciudad;
        this.email = email;

        this.jugadores = new HashMap<>();
        this.porteros = new Portero[2];

    }

    public Equipo(String sCadenaCSV) {
        String[] lines = sCadenaCSV.split("\n");
        String header;
        String[] attributes;
        Portero portero;
        Jugador jugador;

        this.jugadores = new HashMap<>();
        this.porteros = new Portero[2];

        for (int i = 0; i < lines.length; i++) {

            lines[i] = lines[i].replaceAll("\n", "");
            header = lines[i].split(":")[0];
            attributes = lines[i].split(":")[1].split(";");

            switch (header) {
                case "EQUIPO" -> {
                    this.nombre = attributes[0];
                    this.puntuacion = Integer.parseInt(attributes[1]);
                    this.ciudad = attributes[2];
                    this.email = attributes[3];
                }
                case "JUGADOR" -> {
                    jugador = new Jugador(lines[i]);
                    this.jugadores.put(jugador.getDni(), jugador);
                }
                case "PORTERO" -> {
                    portero = new Portero(lines[i]);
                    for (int j = 0; j < this.porteros.length; j++) {

                        if (this.porteros[j] == null) {
                            this.porteros[j] = portero;
                            break;
                        }
                    }
                }
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        if (puntuacion > 0) {
            this.puntuacion += puntuacion;
        }
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        int porterosNum = 0;
        int jugadoresNum = 0;
        ArrayList<Jugador> jugadores = new ArrayList<>(this.jugadores.values());

        for (Portero p : this.porteros) {
            if (p != null) {
                porterosNum++;
            }
        }
        for (Jugador j : jugadores) {
            if (j != null) {
                jugadoresNum++;
            }
        }

        return porterosNum >= 1 && jugadoresNum >= 5;

    }

    @Override
    public String toString() {
        StringBuilder sCsv;
        ArrayList<Jugador> jugadores = new ArrayList<>(this.jugadores.values());

        sCsv = new StringBuilder(String.format("EQUIPO:%s;%d;%s;%s\n",
                this.nombre,
                this.puntuacion,
                this.ciudad,
                this.email));

        for (Portero p : this.porteros) {
            if (p != null) {
                sCsv.append(p.toString());
            }
        }
        for (Jugador j : jugadores) {
            if (j != null) {
                sCsv.append(j.toString());
            }
        }

        return sCsv.toString();

    }

    public boolean addPortero(Portero p) {
        for (int i = 0; i < this.porteros.length; i++) {

            if (this.porteros[i] == null) {
                this.porteros[i] = p;
                return true;
            }
        }
        return false;
    }

    public boolean addJugador(Jugador j) {
        if (!this.jugadores.containsKey(j.getDni())) {
            this.jugadores.put(j.getDni(), j);
            return true;
        }
        return false;
    }

    public boolean eliminaPortero(String dni) {
        for (int i = 0; i < this.porteros.length; i++) {
            if (this.porteros[i] != null) {
                if (this.porteros[i].getDni().equals(dni)) {
                    this.porteros[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eliminaJugador(String dni) {
        if (this.jugadores.containsKey(dni)) {
            this.jugadores.remove(dni);
            return true;
        }
        return false;
    }

    public ArrayList<Jugador> menoresEdad() {

        ArrayList<Jugador> menores = new ArrayList<>();

        ArrayList<Jugador> jugadores = new ArrayList<>(this.jugadores.values());

        for (Portero p : this.porteros) {
            if (p != null) {
                if (!p.mayorEdad()) {
                    menores.add(p);
                }
            }
        }

        for (Jugador j : jugadores) {
            if (j != null) {
                if (!j.mayorEdad()) {
                    menores.add(j);
                }
            }
        }

        menores.sort(new ComparaJugador());

        return menores;
    }

    public ArrayList<Jugador> jugadoresTitulares() {
        ArrayList<Jugador> titulares = new ArrayList<>();

        ArrayList<Jugador> jugadores = new ArrayList<>(this.jugadores.values());

        if (this.porteros[0] != null && this.porteros[1] != null) {
            if (porteros[0].getGolesEncajados() < porteros[1].getGoles()) {
                titulares.add(0, porteros[0]);
            } else {
                titulares.add(0, porteros[1]);
            }
        } else if (this.porteros[0] != null) {
            titulares.add(0, porteros[0]);
        } else if (this.porteros[1] != null) {
            titulares.add(0, porteros[1]);
        }

        jugadores.sort(new ComparaGoles());

        for (int i = 0; i < jugadores.size() || i < 5; i++) {
            titulares.add(jugadores.get(i));
        }

        return titulares;

    }

}
