package com.politecnicomalaga.model;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected int kilometraje;
    protected String matricula;
    protected float precio;
    protected String tipo;
    protected boolean disponible;

    public Vehiculo(String marca, String modelo, int kilometraje, String matricula, float precio, String tipo, boolean disponible) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.precio = precio;
        this.tipo = tipo;
        this.disponible = disponible;

        //Control de errores del kilometraje, debe ser positivo o 0, si no, se asigna 0
        if (kilometraje < 0) {
            this.kilometraje = 0;
        } else {
            this.kilometraje = kilometraje;
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        //Sumamos al kilometraje solo si nos dan un valor positivo
        if (kilometraje > 0) {
            this.kilometraje += kilometraje;
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    //Método abstracto que @Overridean los hijos
    public abstract float facturaAlquiler(int kms);

    @Override
    public String toString() {
        //Realizamos el formateo en csv y lo devolvemos como String
        StringBuilder csv;

        csv = new StringBuilder(String.format("VEHICULO:" +
                        "%s;" +
                        "%s;" +
                        "%d;" +
                        "%s;" +
                        "%f;" +
                        "%b;" +
                        "%s",
                this.marca,
                this.modelo,
                this.kilometraje,
                this.matricula,
                this.precio,
                this.disponible,
                this.tipo));

        return csv.toString();

    }

}
