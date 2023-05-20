package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Jugador;

import java.util.Comparator;

public class ComparaGoles implements Comparator<Jugador> {
    @Override
    public int compare(Jugador j1, Jugador j2) {
        return j2.getGoles() - j1.getGoles();
    }
}
