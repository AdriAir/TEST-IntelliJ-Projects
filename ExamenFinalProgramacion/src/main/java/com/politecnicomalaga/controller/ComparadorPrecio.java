package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Coche;

import java.util.Comparator;

public class ComparadorPrecio implements Comparator<Coche> {
    @Override
    public int compare(Coche o1, Coche o2) {
        return (int) (o1.getPrecio() - o2.getPrecio());
    }
}
