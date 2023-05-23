package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Moto;

import java.util.Comparator;

public class ComparadorCilindrada implements Comparator<Moto> {
    @Override
    public int compare(Moto o1, Moto o2) {
        return o1.getCilindrada() - o2.getCilindrada();
    }
}
