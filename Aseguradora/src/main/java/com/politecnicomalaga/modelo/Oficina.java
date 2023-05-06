package com.politecnicomalaga.modelo;

import java.util.LinkedList;

public class Oficina {
    private int id;
    private String name;
    private String address;
    private String city;
    private String postcode;
    private String phoneNumber;
    private String email;
    private LinkedList<Cliente> clients;

    //Constructor
    public Oficina(int id, String name, String address, String city, String postcode, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.clients = new LinkedList<>();
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    ///Getters


    //Methods
    public void addClient(Cliente newClient) {

        boolean isIn = false;

        for (Cliente client : this.clients) {

            if (client.getDni().equals(newClient.getDni())) {
                isIn = true;
            }

        }

        if (!isIn) {

            this.clients.add(newClient);

        }

    }

    public void removeClient(String dni) {

        this.clients.removeIf(client -> client.getDni().equals(dni) && client.isRemovable());

    }

    public void updateClient(String dni, Cliente newClientInfo) {

        for (Cliente client : this.clients) {

            if (client.getDni().equals(dni)) {

                client.setClientInfo(newClientInfo);

            }

        }

    }

    public Cliente searchClientByDNI(String dni) {

        for (Cliente client : this.clients) {

            if (client.getDni().equals(dni)) {

                return client;

            }

        }

        return null;

    }

    public Cliente[] searchClientByLastName(String LastName) {

        LinkedList<Cliente> filteredClients = new LinkedList<>();
        Cliente[] clients;

        for (Cliente client : this.clients) {

            if (client.getLastName().contains(LastName)) {

                filteredClients.add(client);

            }

        }

        clients = new Cliente[filteredClients.size()];

        for (int i = 0; i < filteredClients.size(); i++) {

            clients[i] = filteredClients.get(i);

        }

        return clients;

    }

    public Cliente[] listClients() {

        Cliente[] clients = new Cliente[this.clients.size()];

        for (int i = 0; i < this.clients.size(); i++) {

            clients[i] = this.clients.get(i);

        }

        return clients;

    }

    @Override
    public String toString() {
        return String.format("Código: %10s;" +
                        "Nombre: %10s;" +
                        "Dirección: %10s;" +
                        "Ciudad: %10s;" +
                        "CP: %10s;" +
                        "Teléfono: %10s;" +
                        "Email: %10s",
                this.id,
                this.name,
                this.address,
                this.city,
                this.postcode,
                this.phoneNumber,
                this.email);
    }
}
