package com.politecnicomalaga.modelo;

public class IncidenciaAjena extends Incidencia {

    private String ThirdPartyDriverDni;

    public IncidenciaAjena(String date, String timeHours, String policyId, int policyNumber, String ownVehicle, String thirdPartyVehicle, String description, String ThirdPartyDriverDni) {
        super(date, timeHours, policyId, policyNumber, ownVehicle, thirdPartyVehicle, description);

        this.ThirdPartyDriverDni = ThirdPartyDriverDni;

    }

    @Override
    public String toString() {
        return String.format("Fecha: %10s;" +
                        "Hora: %10s;" +
                        "Identificador: %10s;" +
                        "Matrícula Própia: %10s;" +
                        "Matrícula Ajena: %10s;" +
                        "Descripción: %20s;" +
                        "DNI Conductor Ajeno: %10s",
                super.date,
                super.timeHours,
                super.id,
                super.ownVehicle,
                super.thirdPartyVehicle,
                super.description,
                this.thirdPartyVehicle);
    }
}
