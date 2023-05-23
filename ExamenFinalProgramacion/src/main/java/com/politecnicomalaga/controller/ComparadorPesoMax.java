package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Camioneta;

import java.util.Comparator;

public class ComparadorPesoMax implements Comparator<Camioneta> {
    @Override
    public int compare(Camioneta o1, Camioneta o2) {
        return o1.getPesoMaximo() - o2.getPesoMaximo();
    }
}
