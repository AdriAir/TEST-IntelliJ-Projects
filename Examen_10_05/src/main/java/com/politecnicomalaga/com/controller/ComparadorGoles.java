package com.politecnicomalaga.com.controller;

import com.politecnicomalaga.com.model.Jugador;

import java.util.Comparator;

public class ComparadorGoles implements Comparator<Jugador> {
    //Comparamos los goles
    @Override
    public int compare(Jugador o1, Jugador o2) {
        return Integer.compare(o1.getGoles(), o2.getGoles());
    }
}
