package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Autocaravana;

import java.util.Comparator;

public class ComparadorCamas implements Comparator<Autocaravana> {
    @Override
    public int compare(Autocaravana o1, Autocaravana o2) {
        return o1.getCamasDisponibles() - o2.getCamasDisponibles();
    }
}
