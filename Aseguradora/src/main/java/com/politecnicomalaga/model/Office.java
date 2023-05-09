package com.politecnicomalaga.model;

import com.google.gson.Gson;

import java.util.LinkedList;

public class Office {
    private String id;
    private String name;
    private String address;
    private String city;
    private String postcode;
    private String phoneNumber;
    private String email;
    private LinkedList<Client> clients;

    //Constructor
    public Office(String id, String name, String address, String city, String postcode, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.clients = new LinkedList<>();
    }
    public Office(String data) {

        //IMPORT CSV
        String[] lines = data.split("\n");
        String[] attributes = lines[0].split(";");

        if (attributes[0].equals("Oficina")) {

            this.id = attributes[1];
            this.name = attributes[2];
            this.address = attributes[3];
            this.city = attributes[4];
            this.postcode = attributes[5];
            this.phoneNumber = attributes[6];
            this.email = attributes[7];

        } else {

            return;

        }

        //Clientes

        this.clients = new LinkedList<>();

        String[] csvClients = data.split("Cliente");
        String csvClient;

        for (String sClient : csvClients) {

            csvClient = "Cliente" + sClient;
            Client client = new Client(csvClient);
            clients.add(client);

        }

    }

    //Setters
    public void setId(String id) {
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
    public void addClient(Client newClient) throws IllegalArgumentException {

        boolean isIn = false;

        for (Client client : this.clients) {

            if (client.getDni().equals(newClient.getDni())) {
                isIn = true;
                break;
            }

        }

        if (!isIn) {

            this.clients.add(newClient);

        } else {

            throw new IllegalArgumentException("El cliente ya existe");

        }

    }

    public void removeClient(String dni) {

        this.clients.removeIf(client -> client.getDni().equals(dni) && client.isRemovable());

    }

    public void updateClient(String dni, Client newClientInfo) {

        for (Client client : this.clients) {

            if (client.getDni().equals(dni)) {

                client.setClientInfo(newClientInfo);

            }

        }

    }

    public Client searchClientByDNI(String dni) {

        for (Client client : this.clients) {

            if (client.getDni().equalsIgnoreCase(dni)) {

                return client;

            }

        }

        return null;

    }

    public Client[] searchClientByLastName(String LastName) {

        LinkedList<Client> filteredClients = new LinkedList<>();
        Client[] clients;

        for (Client client : this.clients) {

            if (client.getLastName().toLowerCase().contains(LastName.toLowerCase())) {

                filteredClients.add(client);

            }

        }

        clients = new Client[filteredClients.size()];

        for (int i = 0; i < filteredClients.size(); i++) {

            clients[i] = filteredClients.get(i);

        }

        return clients;

    }

    public Client[] listClients() {

        Client[] clients = new Client[this.clients.size()];

        for (int i = 0; i < this.clients.size(); i++) {

            clients[i] = this.clients.get(i);

        }

        return clients;

    }

    @Override
    public String toString() {
        return String.format("Código: %10s#" +
                        "Nombre: %10s#" +
                        "Dirección Postal: %10s#" +
                        "Ciudad: %10s#" +
                        "CP: %10s#" +
                        "Teléfono: %10s#" +
                        "Email: %10s\n",
                this.id,
                this.name,
                this.address,
                this.city,
                this.postcode,
                this.phoneNumber,
                this.email);
    }

    public String toCSV() {

        StringBuilder csv;

        csv = new StringBuilder(String.format("Oficina;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s\n",
                this.id,
                this.name,
                this.address,
                this.city,
                this.postcode,
                this.phoneNumber,
                this.email));

        for (Client client : clients) {

            csv.append(client.toCSV());

        }

        return csv.toString();
    }

}
