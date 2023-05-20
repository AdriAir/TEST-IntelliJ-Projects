package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Jugador;

import java.util.Comparator;

public class ComparaJugador implements Comparator<Jugador> {
    @Override
    public int compare(Jugador j1, Jugador j2) {

        String fullName1 = j1.getApellidos() + " " + j1.getNombre();
        String fullName2 = j2.getApellidos() + " " + j2.getNombre();

        return fullName1.compareTo(fullName2);
    }
}
