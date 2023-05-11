package com.politecnicomalaga.com.model;

public class Portero extends Jugador {
    protected int golesEncajados;

    //CONSTRUCTORS
    public Portero(String nombre, String apellidos, String dni, String email, String telefono, int nacimiento, int dorsal, int goles, int golesEncajados) throws IllegalArgumentException {
        super(nombre, apellidos, dni, email, telefono, nacimiento, dorsal, goles);

        //Control de los datos
        if (golesEncajados < 0) {
            throw new IllegalArgumentException("golesEncajados must be greater/equal than 0");
        } else {
            this.golesEncajados = golesEncajados;
        }

    }

    public Portero(String sCadenaCSV) throws IllegalArgumentException {
        //Realizamos la importación del jugador, normalmente
        super(sCadenaCSV);


        //Dividimos los atributos de la sentencia
        String[] attributes = sCadenaCSV.split(";");

        //El primer atributo debe ser PORTERO:
        if (attributes[0].equals("PORTERO")) {
            //Asignamos los atributos de la clase portero,
            //pues los del padre, al estar en el mismo orden,
            // los realiza el constructor del mismo
            //Control de los datos
            if (Integer.parseInt(attributes[9]) < 0) {
                throw new IllegalArgumentException("golesEncajados must be greater/equal than 0");
            } else {
                this.golesEncajados = Integer.parseInt(attributes[9]);
            }
        } else {
            //Si no, salimos, pues no existe la sentencia de PORTERO:
            return;
        }
    }

    //GETTERS && SETTERS

    public int getGolesEncajados() {
        return golesEncajados;
    }

    public void setGolesEncajados(int golesEncajados) throws IllegalArgumentException {
        //Elevamos excepción si introducen valor inválido para golesEncajados
        if (golesEncajados < 0) {
            throw new IllegalArgumentException("golesEncajados must be greater/equal than 0");
        } else {
            this.golesEncajados += golesEncajados;
        }
    }

    //TO STRING CSV
    @Override
    public String toString() {
        //No lo he conseguido con ":"
        return String.format("PORTERO;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%d;" +
                        "%d;" +
                        "%d\n",
                super.nombre,
                super.apellidos,
                super.dni,
                super.email,
                super.telefono,
                super.nacimiento,
                super.dorsal,
                super.goles,
                this.golesEncajados);
    }
}
