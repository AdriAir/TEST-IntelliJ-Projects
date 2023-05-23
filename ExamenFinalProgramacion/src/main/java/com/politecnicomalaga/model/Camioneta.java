package com.politecnicomalaga.model;

public class Camioneta extends Vehiculo {
    protected int pesoMaximo;

    public Camioneta(String marca, String modelo, int kilometraje, String matricula, float precio, String tipo, boolean disponible, int pesoMaximo) throws IllegalArgumentException {
        super(marca, modelo, kilometraje, matricula, precio, tipo, disponible);

        //Control de errores de pesoMaximo, debe ser un valor entre 3500 y 7500, si no, eleva excepción
        if (pesoMaximo >= 3500 && pesoMaximo <= 7500) {
            this.pesoMaximo = pesoMaximo;
        } else {
            throw new IllegalArgumentException("Valor inválido para pesoMaximo");
        }
    }

    public int getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(int pesoMaximo) throws IllegalArgumentException {
        //Control de errores de pesoMaximo, debe ser un valor entre 3500 y 7500, si no, eleva excepción
        if (pesoMaximo >= 3500 && pesoMaximo <= 7500) {
            this.pesoMaximo = pesoMaximo;
        } else {
            throw new IllegalArgumentException("Valor inválido para pesoMaximo");
        }
    }

    @Override
    public float facturaAlquiler(int kms) {

        float precioTotal = 0;

        //Calculamos el precio final según las condiciones
        if (this.pesoMaximo > 5000) {
            precioTotal = (float) (this.precio * kms * 1.30);
        } else {
            precioTotal = this.precio * kms;
        }
        return precioTotal;
    }
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
                        "%s;" +
                        "%d",
                this.marca,
                this.modelo,
                this.kilometraje,
                this.matricula,
                this.precio,
                this.disponible,
                this.tipo,
                this.pesoMaximo));

        return csv.toString();

    }
}
