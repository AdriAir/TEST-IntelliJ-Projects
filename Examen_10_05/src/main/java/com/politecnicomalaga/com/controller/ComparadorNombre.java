package com.politecnicomalaga.com.controller;

import com.politecnicomalaga.com.model.Jugador;

import java.util.Comparator;

public class ComparadorNombre implements Comparator<Jugador> {
    //Comparamos los nombres y apellidos de dos jugadores
    @Override
    public int compare(Jugador o1, Jugador o2) {
        return (o1.getNombre()+o1.getApellidos()).compareTo(o2.getNombre() + o2.getApellidos());
    }
}
