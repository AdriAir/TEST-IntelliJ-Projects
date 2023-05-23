package com.politecnicomalaga.model;

public class Autocaravana extends Vehiculo {
    protected int camasDisponibles;

    public Autocaravana(String marca, String modelo, int kilometraje, String matricula, float precio, String tipo, boolean disponible, int camasDisponibles) throws IllegalArgumentException {
        super(marca, modelo, kilometraje, matricula, precio, tipo, disponible);

        //Control de errores de camasDispoibles, si no está entre 1 y 6, eleva excepción
        if (camasDisponibles >= 1 && camasDisponibles <= 6){
            this.camasDisponibles = camasDisponibles;
        } else {
            throw new IllegalArgumentException("Valor inválido para camasDisponibles");
        }

    }

    public int getCamasDisponibles() {
        return camasDisponibles;
    }

    public void setCamasDisponibles(int camasDisponibles) throws IllegalArgumentException {
        //Control de errores de camasDispoibles, si no está entre 1 y 6, eleva excepción
        if (camasDisponibles >= 1 && camasDisponibles <= 6){
            this.camasDisponibles = camasDisponibles;
        } else {
            throw new IllegalArgumentException("Valor inválido para camasDisponibles");
        }
    }

    @Override
    public float facturaAlquiler(int kms) {

        float precioTotal = 0;

        //Calculamos el precio final según las condiciones
        if (this.camasDisponibles >= 5) {
            precioTotal = (float) (this.precio * kms * 1.15);
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
                this.camasDisponibles));

        return csv.toString();

    }

}
