package com.politecnicomalaga.modelo;

public class IncidenciaUrgente extends Incidencia {
    private int maxDays;

    public IncidenciaUrgente(String date, String timeHours, String policyId, int policyNumber, String ownVehicle, String thirdPartyVehicle, String description, int maxDays) throws IllegalArgumentException {

        super(date, timeHours, policyId, policyNumber, ownVehicle, thirdPartyVehicle, description);

        if (maxDays <= 0) {

            throw new IllegalArgumentException("Los días máximos deben ser mayor a 0");

        }

        this.maxDays = maxDays;

    }

    @Override
    public String toString() {
        return String.format("Fecha: %10s;" +
                        "Hora: %10s;" +
                        "Identificador: %10s;" +
                        "Matrícula Própia: %10s;" +
                        "Matrícula Ajena: %10s;" +
                        "Descripción: %20s;" +
                        "Días Máximos: %10s",
                super.date,
                super.timeHours,
                super.id,
                super.ownVehicle,
                super.thirdPartyVehicle,
                super.description,
                this.maxDays);
    }

}
