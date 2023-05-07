package com.politecnicomalaga.modelo;

public class UrgentIncident extends Incident {
    private int maxDays;

    public UrgentIncident(Incident incident, int maxDays) throws IllegalArgumentException {

        super(incident.date,
                incident.timeHours,
                incident.policyId,
                incident.policyNumber,
                incident.ownVehicle,
                incident.thirdPartyVehicle,
                incident.description);

        if (maxDays <= 0) {

            throw new IllegalArgumentException("Los días máximos deben ser mayor a 0");

        }

        this.maxDays = maxDays;

    }

    public UrgentIncident(int type, String data) {
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
                this.maxDays = Integer.parseInt(attributes[7]);
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
                        "Días Máximos: %10s\n",
                super.date,
                super.timeHours,
                super.id,
                super.ownVehicle,
                super.thirdPartyVehicle,
                super.description,
                this.maxDays);
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
                this.maxDays);

        return csv;
    }
}
