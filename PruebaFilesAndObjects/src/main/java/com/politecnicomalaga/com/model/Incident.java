package com.politecnicomalaga.com.model;

public class Incident {
    public static int policyNumber = 0;
    protected String date;
    protected String timeHours;
    protected String id;
    protected String ownVehicle;
    protected String thirdPartyVehicle;
    protected String description;

    protected boolean isActive;
    protected String policyId;


    //Constructor
    public Incident(String date, String timeHours, String policyId, int policyNumber, String ownVehicle, String thirdPartyVehicle, String description) {
        this.date = date;
        this.timeHours = timeHours;
        this.ownVehicle = ownVehicle;
        this.thirdPartyVehicle = thirdPartyVehicle;
        this.description = description;
        this.policyId = policyId;
        Incident.policyNumber++;

        this.id = this.policyId + "-" + Incident.policyNumber;

        this.isActive = true;
    }

    public Incident(String csv) {
        //IMPORT CSV
        String[] attributes = csv.split(";");
        if (attributes[0].equals("Incidencia")) {
            this.date = attributes[1];
            this.timeHours = attributes[2];
            this.id = attributes[3];
            this.ownVehicle = attributes[4];
            this.thirdPartyVehicle = attributes[5];
            this.description = attributes[6];
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
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s\n",
                this.date,
                this.timeHours,
                this.id,
                this.ownVehicle,
                this.thirdPartyVehicle,
                this.description);
        return csv;
    }
}
