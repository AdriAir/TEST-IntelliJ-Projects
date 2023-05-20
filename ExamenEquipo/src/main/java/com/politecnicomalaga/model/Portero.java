package com.politecnicomalaga.model;

public class Portero extends Jugador {

    protected int golesEncajados;

    public Portero(String nombre, String apellidos, String dni, String email, String telefono, int nacimiento, int dorsal, int goles, int golesEncajados) {
        super(nombre, apellidos, dni, email, telefono, nacimiento, dorsal, goles);

        if (golesEncajados < 0) {
            this.golesEncajados = 0;
        } else {
            this.golesEncajados = golesEncajados;
        }

    }

    public Portero(String sCadenaCSV) {
        super(sCadenaCSV);

        sCadenaCSV = sCadenaCSV.replaceAll("\n", "");

        String[] attributes = sCadenaCSV.split(":")[1].split(";");
        this.golesEncajados = Integer.parseInt(attributes[8]);

    }

    public int getGolesEncajados() {
        return golesEncajados;
    }

    public void setGolesEncajados(int golesEncajados) {
        if (golesEncajados > 0) {
            this.golesEncajados += golesEncajados;
        }
    }

    @Override
    public String toString() {

        StringBuilder sCsv;

        sCsv = new StringBuilder(String.format("PORTERO:%s;%s;%s;%s;%s;%d;%d;%d;%d\n",
                super.nombre,
                super.apellidos,
                super.dni,
                super.email,
                super.telefono,
                super.nacimiento,
                super.dorsal,
                super.goles,
                this.golesEncajados));

        return sCsv.toString();

    }
}
