package com.politecnicomalaga.model;

public class Coche extends Vehiculo {
    protected int pasajeros;
    public Coche(String marca, String modelo, int kilometraje, String matricula, float precio, String tipo, boolean disponible, int pasajeros) {
        super(marca, modelo, kilometraje, matricula, precio, tipo, disponible);

        //Control de errores de pasajeros entre 2 y 7, en otro caso, por defecto 5
        if (pasajeros >= 2 && pasajeros <= 7 ){
            this.pasajeros = pasajeros;
        } else {
            this.pasajeros = 5;
        }

    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        //Control de errores de pasajeros entre 2 y 7, en otro caso,no hace nada
        if (pasajeros >= 2 && pasajeros <= 7 ){
            this.pasajeros = pasajeros;
        }
    }

    @Override
    public float facturaAlquiler(int kms) {

        float precioTotal = 0;

        //Calculamos el precio final según las condiciones

        //controlamos ambos en caso de que cambien las restricciones
        if (this.pasajeros >= 6 && this.pasajeros <= 7) {
            precioTotal = (float) (this.precio * kms * 1.10);
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
                this.pasajeros));

        return csv.toString();

    }
}
