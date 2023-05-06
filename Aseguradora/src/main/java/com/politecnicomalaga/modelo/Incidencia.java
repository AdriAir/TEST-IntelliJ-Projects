package com.politecnicomalaga.modelo;

public class Incidencia {
    protected String date;
    protected String timeHours;
    protected String id;
    protected String ownVehicle;
    protected String thirdPartyVehicle;
    protected String description;

    protected boolean isActive;

    //Constructor
    public Incidencia(String date, String timeHours, String policyId, int policyNumber, String ownVehicle, String thirdPartyVehicle, String description) {
        this.date = date;
        this.timeHours = timeHours;
        this.ownVehicle = ownVehicle;
        this.thirdPartyVehicle = thirdPartyVehicle;
        this.description = description;

        this.id = policyId + "-" + policyNumber;

        this.isActive = true;
    }

    //Setters

    //Getter
    public String getId() {
        return id;
    }

    //Methods
    public void close() {

        this.isActive = false;

    }

    public void open() {

        this.isActive = true;

    }

    @Override
    public String toString() {
        return String.format("Fecha: %10s;" +
                        "Hora: %10s;" +
                        "Identificador: %10s;" +
                        "Matrícula Própia: %10s;" +
                        "Matrícula Ajena: %10s;" +
                        "Descripción: %20s",
                this.date,
                this.timeHours,
                this.id,
                this.ownVehicle,
                this.thirdPartyVehicle,
                this.description);
    }

}
