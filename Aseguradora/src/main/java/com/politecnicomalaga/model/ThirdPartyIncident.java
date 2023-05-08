package com.politecnicomalaga.model;

public class ThirdPartyIncident extends Incident {

    private String thirdPartyDriverDni;

    public ThirdPartyIncident(Incident incident, String thirdPartyDriverDni) {
        super(incident.date,
                incident.timeHours,
                incident.policyId,
                incident.policyNumber,
                incident.ownVehicle,
                incident.thirdPartyVehicle,
                incident.description);

        this.thirdPartyDriverDni = thirdPartyDriverDni;

    }

    public ThirdPartyIncident(String data) {
        super(data);

        //IMPORT CSV
        String[] attributes = data.split(";");

        if (attributes[7].equals("Incidencia Ajena")) {
            this.thirdPartyDriverDni = attributes[8];
        }

    }

    @Override
    public String toString() {
        return String.format("Fecha: %10s#" +
                        "Hora: %10s#" +
                        "Identificador: %10s#" +
                        "Vehículo Própio: %10s#" +
                        "Vehículo Ajeno: %10s#" +
                        "Descripción: %20s#" +
                        "Conductor Ajeno (DNI): %10s\n",
                super.date,
                super.timeHours,
                super.id,
                super.ownVehicle,
                super.thirdPartyVehicle,
                super.description,
                this.thirdPartyDriverDni);
    }

    @Override
    public String toCSV() {

        String csv;

        csv = String.format("Incidencia;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "Incidencia Ajena;" +
                        "%s\n",
                super.date,
                super.timeHours,
                super.id,
                super.ownVehicle,
                super.thirdPartyVehicle,
                super.description,
                this.thirdPartyDriverDni);

        return csv;
    }
}
