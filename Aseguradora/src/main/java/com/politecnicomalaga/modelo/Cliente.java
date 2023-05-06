package com.politecnicomalaga.modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cliente {

    private String dni;
    private String idPolicy;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private LinkedList<Incidencia> incidents;

    //Constructor
    public Cliente(String dni, String idPolicy, String name, String lastName, String address, String email, String phoneNumber) {
        this.dni = dni;
        this.idPolicy = idPolicy;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;

        this.incidents = new LinkedList<>();
    }

    public Cliente(Cliente client) {
        this.dni = client.dni;
        this.idPolicy = client.idPolicy;
        this.name = client.name;
        this.lastName = client.lastName;
        this.address = client.address;
        this.email = client.email;
        this.phoneNumber = client.phoneNumber;

        this.incidents = new LinkedList<>();
    }

    //Setters
    public void setClientInfo(Cliente client) {
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

        for (Incidencia incident : this.incidents) {

            if (incident.isActive) {

                isRemovable = false;
                break;
            }

        }

        return isRemovable;

    }

    public void addIncident(Incidencia newIncident) {

        boolean isIn = false;

        for (Incidencia incident : this.incidents) {
            if (incident.getId().equals(newIncident.getId())) {
                isIn = true;
            }
        }

        if (!isIn) {
            this.incidents.add(newIncident);
        }

    }

    public void closeIncident(String incidentId) {

        for (Incidencia incident : this.incidents) {
            if (incident.getId().equals(incidentId)) {
                incident.close();
            }
        }

    }

    public Incidencia[] listIncident(int filter) {

        LinkedList<Incidencia> filteredIncidents = new LinkedList<>();
        Incidencia[] incidents;

        /*
        filter == -1 -> Just Closed
        filter == 0 -> All
        filter == 1 -> Just Opened
         */

        if (filter == -1) {

            for (Incidencia incident : this.incidents) {

                if (!incident.isActive) {

                    filteredIncidents.add(incident);

                }
            }

        } else if (filter == 0) {

            filteredIncidents.addAll(this.incidents);

        } else if (filter == 1) {

            for (Incidencia incident : this.incidents) {

                if (incident.isActive) {

                    filteredIncidents.add(incident);

                }
            }

        }

        incidents = new Incidencia[filteredIncidents.size()];

        for (int i = 0; i < filteredIncidents.size(); i++) {

            incidents[i] = filteredIncidents.get(i);

        }

        return incidents;

    }
    @Override
    public String toString() {
        return String.format("DNI: %10s;" +
                        "Póliza: %10s;" +
                        "Nombre: %10s;" +
                        "Apellidos: %10s;" +
                        "Dirección: %10s;" +
                        "Email: %10s;" +
                        "Teléfono: %10s",
                this.dni,
                this.idPolicy,
                this.name,
                this.lastName,
                this.address,
                this.email,
                this.phoneNumber);
    }
}
