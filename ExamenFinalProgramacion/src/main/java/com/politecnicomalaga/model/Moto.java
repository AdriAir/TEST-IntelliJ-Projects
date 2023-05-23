package com.politecnicomalaga.model;

public class Moto extends Vehiculo {

    protected int cilindrada;

    public Moto(String marca, String modelo, int kilometraje, String matricula, float precio, String tipo, boolean disponible, int cilindrada) {
        super(marca, modelo, kilometraje, matricula, precio, tipo, disponible);

        this.cilindrada = cilindrada;

    }
    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public float facturaAlquiler(int kms) {

        float precioTotal = 0;

        //Calculamos el precio final según las condiciones
        if (this.cilindrada >= 500) {
            precioTotal = (float) (this.precio * kms * 1.20);
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
                this.cilindrada));

        return csv.toString();

    }
}
