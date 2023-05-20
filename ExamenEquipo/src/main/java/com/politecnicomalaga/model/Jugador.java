package com.politecnicomalaga.model;

import java.time.YearMonth;

public class Jugador {
    protected String nombre;
    protected String apellidos;
    protected String dni;
    protected String email;
    protected String telefono;
    protected int nacimiento;
    protected int dorsal;
    protected int goles;

    public Jugador(String nombre, String apellidos, String dni, String email, String telefono, int nacimiento, int dorsal, int goles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.nacimiento = nacimiento;

        if (dorsal < 1 || dorsal > 99) {
            this.dorsal = 100;
        } else {
            this.dorsal = dorsal;
        }

        if (goles < 0) {
            this.goles = 0;
        } else {
            this.goles = goles;
        }
    }

    public Jugador(String sCadenaCSV) {

        //Esto es solo por si se creara un jugador a partir de otro, sin importarlo desde un equipo entero
        sCadenaCSV = sCadenaCSV.replaceAll("\n", "");

        String[] attributes = sCadenaCSV.split(":")[1].split(";");

            this.nombre = attributes[0];
            this.apellidos = attributes[1];
            this.dni = attributes[2];
            this.email = attributes[3];
            this.telefono = attributes[4];
            this.nacimiento = Integer.parseInt(attributes[5]);
            this.dorsal = Integer.parseInt(attributes[6]);
            this.goles = Integer.parseInt(attributes[7]);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        if (dorsal < 1 || dorsal > 99) {
            this.dorsal = 100;
        } else {
            this.dorsal = dorsal;
        }
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        if (goles > 0) {
            this.goles += goles;
        }
    }

    @Override
    public String toString() {
        StringBuilder sCsv;

        sCsv = new StringBuilder(String.format("JUGADOR:%s;%s;%s;%s;%s;%d;%d;%d\n",
                this.nombre,
                this.apellidos,
                this.dni,
                this.email,
                this.telefono,
                this.nacimiento,
                this.dorsal,
                this.goles));

        return sCsv.toString();

    }

    public boolean mayorEdad() {
        return YearMonth.now().getYear() - this.getNacimiento() > 17;
    }
}
