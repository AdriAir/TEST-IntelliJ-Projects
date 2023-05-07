package com.politecnicomalaga.modelo;

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

    public ThirdPartyIncident(int type, String data) {
        super(type, data);
        /*
        type == 1 -> CSV
        type == 2 -> JSON
        type == 3 -> XML
         */

        if (type == 1) {

            //IMPORT CSV
            String[] attributes = data.split(";");

            if (attributes[0].equals("Incidencia")) {
                this.thirdPartyDriverDni = attributes[7];
            }

        } else if (type == 2) {

            //IMPORT JSON

        } else if (type == 3) {

            //IMPORT XML

        } else {
            throw new IllegalArgumentException("El tipo no es válido...");
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
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%20s;" +
                        "%10s\n",
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
