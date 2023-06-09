package com.politecnicomalaga.model;

import java.util.ArrayList;


public class Clinic {

    //Estados
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String cif;
    private ArrayList<Patient> patients;


    //Comportamiento

    //Constructor
    public Clinic(String name, String address, String phoneNumber, String email, String cif) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cif = cif;
        this.patients = new ArrayList<>();
    }

    public Clinic(String csv) {
        String[] lines = csv.split("\n");
        //Me vendrá una línea mínimo para clinica
        String[] attributes = lines[0].split(";");
        if (attributes[0].equals("Clinica")) {
            this.name = attributes[1];
            this.address = attributes[2];
            this.phoneNumber = attributes[3];
            this.email = attributes[4];
            this.cif = attributes[5];
        } else {
            return;
        }

        //Después de 0 a n tratamientos
        this.patients = new ArrayList<>();

        String[] patients = csv.split("Paciente");
        String patient;

        for (int i = 1; i < patients.length; i++) {
            patient = "Paciente" + patients[i];
            Patient newPatient = new Patient(patient);
            this.patients.add(newPatient);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getCif() {
        return cif;
    }


    public void setCif(String cif) {
        this.cif = cif;
    }

    //CRUD de Pacientes
    public boolean addPatient(Patient patient) {

        if (this.searchPatients(patient.getDni(), Patient.patientAttributes.DNI) == null) {
            patients.add(patient);
            return true;
        }
        return false;

    }

    public boolean removePatient(String dni) {
        Patient[] patients = this.searchPatients(dni, Patient.patientAttributes.DNI);
        if (patients != null && patients.length == 1) {
            if (patients[0].isAllPaid()) {
                return this.patients.remove(patients[0]);
            }
        }
        return false;
    }


    public Patient[] searchPatients(String field, Patient.patientAttributes patientAttributes) {
        ArrayList<Patient> result = new ArrayList<>();

        for (Patient patients : patients) {
            if (patients.compare(field, patientAttributes)) {
                result.add(patients);
            }
        }

        if (result.size() > 0) {
            Patient[] patient = new Patient[result.size()];
            return result.toArray(patient);
        }
        return null;
    }

    public Patient getPatient(String dni) {

        for (Patient patient : patients) {
            if (patient.compare(dni, Patient.patientAttributes.DNI)) {
                return patient;
            }
        }

        return null;
    }


    public Patient[] getPatients() {
        if (this.patients.size() == 0) return null;
        Patient[] patients = new Patient[this.patients.size()];
        return this.patients.toArray(patients);
    }

    public boolean updatePatient(String dni, String field, Patient.patientAttributes patientAttributes) {
        Patient patients = this.getPatient(dni);
        if (patients != null) {
            patients.setValue(field, patientAttributes);
            return true;
        }
        return false;
    }


    public String toCSV() {
        StringBuilder result = new StringBuilder(String.format("Clinica;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s\n",
                this.name,
                this.address,
                this.phoneNumber,
                this.email,
                this.cif));
        for (Patient patient : this.patients) {
            result.append(patient.toCSV());
        }
        return result.toString();
    }
}