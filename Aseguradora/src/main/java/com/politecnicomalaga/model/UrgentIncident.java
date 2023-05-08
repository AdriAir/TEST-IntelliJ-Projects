package com.politecnicomalaga.model;

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

    public UrgentIncident(String csv) {
        super(csv);

        //IMPORT CSV
        String[] attributes = csv.split(";");

        if (attributes[7].equals("Incidencia Urgente")) {
            this.maxDays = Integer.parseInt(attributes[8]);
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
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "Incidencia Urgente;" +
                        "%s\n",
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
