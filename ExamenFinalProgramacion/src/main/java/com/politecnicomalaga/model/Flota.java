package com.politecnicomalaga.model;

import com.politecnicomalaga.controller.ComparadorCamas;
import com.politecnicomalaga.controller.ComparadorCilindrada;
import com.politecnicomalaga.controller.ComparadorPesoMax;
import com.politecnicomalaga.controller.ComparadorPrecio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Flota {
    protected String nombreGerente;
    protected String dniGerente;
    protected String emailGerente;
    protected Map<String, Vehiculo> vehiculos;

    public Flota(String nombreGerente, String dniGerente, String emailGerente) {
        this.nombreGerente = nombreGerente;
        this.dniGerente = dniGerente;
        this.emailGerente = emailGerente;

        //Inicializamos el HashMap vacio
        this.vehiculos = new HashMap<>();
    }

    public String getNombreGerente() {
        return nombreGerente;
    }

    public void setNombreGerente(String nombreGerente) {
        this.nombreGerente = nombreGerente;
    }

    public String getDniGerente() {
        return dniGerente;
    }

    public void setDniGerente(String dniGerente) {
        this.dniGerente = dniGerente;
    }

    public String getEmailGerente() {
        return emailGerente;
    }

    public void setEmailGerente(String emailGerente) {
        this.emailGerente = emailGerente;
    }

    @Override
    public String toString() {
        //Realizamos el formateo en csv y lo devolvemos como String
        StringBuilder csv;

        csv = new StringBuilder(String.format("%s;" +
                        "%s;" +
                        "%s",
                this.nombreGerente,
                this.dniGerente,
                this.emailGerente));

        return csv.toString();

    }

    public Moto[] motosDisponibles() {
        //Devuelve un array con las motos en el sistema que estén disponibles
        //Orden Cilindrada, MAS A MENOS
        ArrayList<Vehiculo> vehiculos = new ArrayList<>(this.vehiculos.values());
        ArrayList<Moto> motos = new ArrayList<>();
        Moto[] motosFinal;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getClass().equals(Moto.class) && vehiculo.isDisponible()) {
                motos.add((Moto) vehiculo);
            }
        }

        //Ordenamos
        motos.sort(new ComparadorCilindrada());
        motosFinal = new Moto[motos.size()];

        return motos.toArray(motosFinal);
    }

    public Coche[] cochesDisponibles() {
        //Devuelve un array con los coches en el sistema que estén disponibles
        //Orden Precio, MENOS A MAS

        ArrayList<Vehiculo> vehiculos = new ArrayList<>(this.vehiculos.values());
        ArrayList<Coche> coches = new ArrayList<>();
        Coche[] cochesFinal;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getClass().equals(Coche.class) && vehiculo.isDisponible()) {
                coches.add((Coche) vehiculo);
            }
        }

        //Ordenamos
        coches.sort(new ComparadorPrecio());
        cochesFinal = new Coche[coches.size()];

        return coches.toArray(cochesFinal);
    }

    public Camioneta[] camionetasDisponibles() {
        //Devuelve un array con las camionetas en el sistema que estén disponibles
        //Orden PesoMaximo, MAS A MENOS
        ArrayList<Vehiculo> vehiculos = new ArrayList<>(this.vehiculos.values());
        ArrayList<Camioneta> camionetas = new ArrayList<>();
        Camioneta[] camionetasFinal;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getClass().equals(Camioneta.class) && vehiculo.isDisponible()) {
                camionetas.add((Camioneta) vehiculo);
            }
        }

        //Ordenamos
        camionetas.sort(new ComparadorPesoMax());
        camionetasFinal = new Camioneta[camionetas.size()];

        return camionetas.toArray(camionetasFinal);
    }

    public Autocaravana[] autocaravanasDisponibles() {
        //Devuelve un array con las motos en el sistema que estén disponibles
        //Orden CamasDisponibles, MAS A MENOS
        ArrayList<Vehiculo> vehiculos = new ArrayList<>(this.vehiculos.values());
        ArrayList<Autocaravana> autocaravanas = new ArrayList<>();
        Autocaravana[] autocaravanasFinal;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getClass().equals(Autocaravana.class) && vehiculo.isDisponible()) {
                autocaravanas.add((Autocaravana) vehiculo);
            }
        }

        //Ordenamos
        autocaravanas.sort(new ComparadorCamas());
        autocaravanasFinal = new Autocaravana[autocaravanas.size()];

        return autocaravanas.toArray(autocaravanasFinal);
    }

    public Vehiculo crearVehiculo(String[] datosAtributos, String tipo) {

        //Controlamos que pasan 8 atributos, si no, retornamos NULL
        if (datosAtributos.length == 8) {
            String marca = datosAtributos[0];
            String modelo = datosAtributos[1];
            int kilometraje = Integer.parseInt(datosAtributos[2]);
            String matricula = datosAtributos[3];
            float precio = Float.parseFloat(datosAtributos[4].replaceAll(",","."));
            boolean disponible = Boolean.parseBoolean(datosAtributos[5]);
            String tipoVehiculo = datosAtributos[6];
            int especial = Integer.parseInt(datosAtributos[7]);

            //Creamos los vehiculos según el tipo y lo retornamos, si algo está mal, retornamos NULL
            if (tipo.equalsIgnoreCase("moto")) {
                return new Moto(marca, modelo, kilometraje, matricula, precio, tipoVehiculo, disponible, especial);
            } else if (tipo.equalsIgnoreCase("coche")) {
                return new Coche(marca, modelo, kilometraje, matricula, precio, tipoVehiculo, disponible, especial);
            } else if (tipo.equalsIgnoreCase("camioneta")) {
                return new Camioneta(marca, modelo, kilometraje, matricula, precio, tipoVehiculo, disponible, especial);
            } else if (tipo.equalsIgnoreCase("autocaravana")) {
                return new Autocaravana(marca, modelo, kilometraje, matricula, precio, tipoVehiculo, disponible, especial);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void putVehiculo(Vehiculo v) {

        //Comprobamos si existe o no ese vehiculo (matricula)
        if (this.vehiculos.containsKey(v.getMatricula())) {
            //si existe lo borramos y añadimos al nuevo vehiculo
            this.vehiculos.remove(v.getMatricula());
            this.vehiculos.put(v.getMatricula(), v);
        } else {
            //Si no existe, lo añadimos sin más
            this.vehiculos.put(v.getMatricula(), v);
        }
    }

    public float facturarVehiculo(String matricula, int kms) throws NullPointerException {
        //Obtenemos el vehiculo del mapa
        Vehiculo v = this.vehiculos.get(matricula);

        //Si v es nullo, es que no se ha encontrado en el mapa, si no, procedemos con la factura
        if (v != null) {
            //Añadimos los nuevos kms al coche
            v.setKilometraje(kms);
            //Lo marcamos como disponible
            v.setDisponible(true);
            //Calculamos la factura de los kms realizados en este alquiler y los devolvemos
            return v.facturaAlquiler(kms);
        } else {
            //De otra forma, elevamos excepción
            throw new NullPointerException("El vehiculo no existe");
        }
    }

    public boolean alquilarVehiculo(String matricula) {
        //Obtenemos el vehiculo del mapa
        Vehiculo v = this.vehiculos.get(matricula);

        //Si v es nullo, es que no se ha encontrado en el mapa, si no, procedemos a alquilar
        if (v != null) {
            //Lo marcamos como NO disponible y retornamos TRUE
            v.setDisponible(false);
            return true;
        } else {
            //De otra forma, return false
            return false;
        }
    }

    public Map<String, Vehiculo> getVehiculos() {
        return vehiculos;
    }
}
