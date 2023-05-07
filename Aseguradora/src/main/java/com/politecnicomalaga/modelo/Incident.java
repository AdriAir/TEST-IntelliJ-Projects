package com.politecnicomalaga.modelo;

public class Incident {
    protected String date;
    protected String timeHours;
    protected String id;
    protected String ownVehicle;
    protected String thirdPartyVehicle;
    protected String description;

    protected boolean isActive;
    protected String policyId;
    protected int policyNumber;

    //Constructor
    public Incident(String date, String timeHours, String policyId, int policyNumber, String ownVehicle, String thirdPartyVehicle, String description) {
        this.date = date;
        this.timeHours = timeHours;
        this.ownVehicle = ownVehicle;
        this.thirdPartyVehicle = thirdPartyVehicle;
        this.description = description;
        this.policyId = policyId;
        this.policyNumber = policyNumber;

        this.id = this.policyId + "-" + this.policyNumber;

        this.isActive = true;
    }

    public Incident(int type, String data) {

        /*
        type == 1 -> CSV
        type == 2 -> JSON
        type == 3 -> XML
         */

        if (type == 1) {

            //IMPORT CSV
            String[] attributes = data.split(";");

            if (attributes[0].equals("Incidencia")) {
                this.date = attributes[1];
                this.timeHours = attributes[2];
                this.id = attributes[3];
                this.ownVehicle = attributes[4];
                this.thirdPartyVehicle = attributes[5];
                this.description = attributes[6];
            }

        } else if (type == 2) {

            //IMPORT JSON

        } else if (type == 3) {

            //IMPORT XML

        } else {
            throw new IllegalArgumentException("El tipo no es válido...");
        }

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
        return String.format("%10s#" +
                        "%10s#" +
                        "%10s#" +
                        "%10s#" +
                        "%10s#" +
                        "%20s\n",
                this.date,
                this.timeHours,
                this.id,
                this.ownVehicle,
                this.thirdPartyVehicle,
                this.description);
    }

    public String toCSV() {

        String csv;

        csv = String.format("Incidencia;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%20s\n",
                this.date,
                this.timeHours,
                this.id,
                this.ownVehicle,
                this.thirdPartyVehicle,
                this.description);

        return csv;
    }

    public String toJSON() {

        return "";

    }

    public String toXML() {
        return "";
    }

}
