package com.politecnicomalaga.modelo;

import java.util.LinkedList;

public class Client {

    private String dni;
    private String idPolicy;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private LinkedList<Incident> incidents;

    //Constructor
    public Client(String dni, String idPolicy, String name, String lastName, String address, String email, String phoneNumber) {
        this.dni = dni;
        this.idPolicy = idPolicy;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;

        this.incidents = new LinkedList<>();
    }

    public Client(Client client) {
        this.dni = client.dni;
        this.idPolicy = client.idPolicy;
        this.name = client.name;
        this.lastName = client.lastName;
        this.address = client.address;
        this.email = client.email;
        this.phoneNumber = client.phoneNumber;

        this.incidents = new LinkedList<>();
    }

    public Client(int type, String data) {

        /*
        type == 1 -> CSV
        type == 2 -> JSON
        type == 3 -> XML
         */

        if (type == 1) {

            //IMPORT CSV
            String[] lines = data.split("\n");
            String[] attributes = data.split(";");

            if (attributes[0].equals("Cliente")) {

                this.dni = attributes[1];
                this.idPolicy = attributes[2];
                this.name = attributes[3];
                this.lastName = attributes[4];
                this.address = attributes[5];
                this.email = attributes[6];
                this.phoneNumber = attributes[7];

            } else {

                return;

            }

            //Incidencias

            incidents = new LinkedList<>();

            for (int i = 1; i < lines.length; i++) {

                Incident incident = new Incident(1, lines[i]);
                this.incidents.add(incident);

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
    public void setClientInfo(Client client) {
        this.dni = client.dni;
        this.idPolicy = client.idPolicy;
        this.name = client.name;
        this.lastName = client.lastName;
        this.address = client.address;
        this.email = client.email;
        this.phoneNumber = client.phoneNumber;
    }

    //Getters
    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    //Methods
    public boolean isRemovable() {

        boolean isRemovable = true;

        for (Incident incident : this.incidents) {

            if (incident.isActive) {

                isRemovable = false;
                break;
            }

        }

        return isRemovable;

    }

    public void addIncident(Incident newIncident) {

        boolean isIn = false;

        for (Incident incident : this.incidents) {
            if (incident.getId().equals(newIncident.getId())) {
                isIn = true;
                break;
            }
        }

        if (!isIn) {
            this.incidents.add(newIncident);
        }

    }

    public void closeIncident(String incidentId) {

        for (Incident incident : this.incidents) {
            if (incident.getId().equals(incidentId)) {
                incident.close();
            }
        }

    }

    public Incident[] listIncident(int filter) {

        LinkedList<Incident> filteredIncidents = new LinkedList<>();
        Incident[] incidents;

        /*
        filter == -1 -> Just Closed
        filter == 0 -> All
        filter == 1 -> Just Opened
         */

        if (filter == -1) {

            for (Incident incident : this.incidents) {

                if (!incident.isActive) {

                    filteredIncidents.add(incident);

                }
            }

        } else if (filter == 0) {

            filteredIncidents.addAll(this.incidents);

        } else if (filter == 1) {

            for (Incident incident : this.incidents) {

                if (incident.isActive) {

                    filteredIncidents.add(incident);

                }
            }

        }

        incidents = new Incident[filteredIncidents.size()];

        for (int i = 0; i < filteredIncidents.size(); i++) {

            incidents[i] = filteredIncidents.get(i);

        }

        return incidents;

    }

    @Override
    public String toString() {
        return String.format("DNI: %10s#" +
                        "ID Póliza: %10s#" +
                        "Nombre: %10s#" +
                        "Apellidos: %10s#" +
                        "Dirección Postal: %10s#" +
                        "Email: %10s#" +
                        "Teléfono: %10s\n",
                this.dni,
                this.idPolicy,
                this.name,
                this.lastName,
                this.address,
                this.email,
                this.phoneNumber);
    }

    public String toCSV() {

        StringBuilder csv;

        csv = new StringBuilder(String.format("Cliente;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s;" +
                        "%10s\n",
                this.dni,
                this.idPolicy,
                this.name,
                this.lastName,
                this.address,
                this.email,
                this.phoneNumber));

        for (Incident incident : incidents) {

            csv.append(incident.toCSV());

        }

        return csv.toString();
    }
}
