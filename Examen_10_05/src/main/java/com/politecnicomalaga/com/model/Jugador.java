package com.politecnicomalaga.com.model;

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

    //CONSTRUCTORS
    public Jugador(String nombre, String apellidos, String dni, String email, String telefono, int nacimiento, int dorsal, int goles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni.toLowerCase();
        this.email = email;
        this.telefono = telefono;
        this.nacimiento = nacimiento;

        //Control de los datos
        if (goles < 0) {
            this.goles = 0;
        } else {
            this.goles = goles;
        }
        if (dorsal < 1 || dorsal > 99) {
            this.dorsal = 100;
        } else {
            this.dorsal = dorsal;
        }
    }

    public Jugador(String sCadenaCSV) {
        //Importamos los datos de un csv y los transformamos en objetos
        //Dividimos los atributos de la sentencia
        String[] attributes = sCadenaCSV.split(";");

        //El primer atributo debe ser JUGADOR O PORTERO:
        if (attributes[0].equals("JUGADOR")) {
            //En ese caseo, asignamos los atributos al objeto.
            this.nombre = attributes[1];
            this.apellidos = attributes[2];
            this.dni = attributes[3];
            this.email = attributes[4];
            this.telefono = attributes[5];
            this.nacimiento = Integer.parseInt(attributes[6]);
            //Control de los datos
            if (Integer.parseInt(attributes[8]) < 0) {
                this.goles = 0;
            } else {
                this.goles = Integer.parseInt(attributes[8]);
            }
            if (Integer.parseInt(attributes[7]) < 1 || Integer.parseInt(attributes[7]) > 99) {
                this.dorsal = 100;
            } else {
                this.dorsal = Integer.parseInt(attributes[7]);
            }
        } else if (attributes[0].equals("PORTERO")) {
            //En ese caseo, asignamos los atributos al objeto.
            this.nombre = attributes[1];
            this.apellidos = attributes[2];
            this.dni = attributes[3];
            this.email = attributes[4];
            this.telefono = attributes[5];
            this.nacimiento = Integer.parseInt(attributes[6]);
            //Control de los datos
            if (Integer.parseInt(attributes[8]) < 0) {
                this.goles = 0;
            } else {
                this.goles = Integer.parseInt(attributes[8]);
            }
            if (Integer.parseInt(attributes[7]) < 1 || Integer.parseInt(attributes[7]) > 99) {
                this.dorsal = 100;
            } else {
                this.dorsal = Integer.parseInt(attributes[7]);
            }
        }
        else {
            //Si no, salimos, pues no existe la sentencia de JUGADOR:
            //Puede ser un PORTERO:
            return;
        }
    }

    //GETTERS && SETTERS
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
        this.dni = dni.toLowerCase();
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
        //SOLO SUMA GOLES SI ES MAYOR A CERO
        if (goles > 0) {
            this.goles += goles;
        }
    }

    //METODOS CLASE
    protected boolean mayorEdad() {
        //Método que resta el año actual con el año de nacimiento del jugador
        //Si la resta dá un valor mayor a 17, entonces es mayor de edad.
        return YearMonth.now().getYear() - this.nacimiento > 17;
    }

    //TO STRING CSV
    @Override
    public String toString() {
        //No lo he conseguido con ":"
        return String.format("JUGADOR;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%d;" +
                        "%d;" +
                        "%d\n",
                this.nombre,
                this.apellidos,
                this.dni,
                this.email,
                this.telefono,
                this.nacimiento,
                this.dorsal,
                this.goles);
    }


}
