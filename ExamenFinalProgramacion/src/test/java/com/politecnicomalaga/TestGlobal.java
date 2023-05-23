package com.politecnicomalaga;

import com.politecnicomalaga.controller.ControladorVehiculos;
import com.politecnicomalaga.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGlobal {

    private static Flota flota;
    private static Moto moto;
    private static Coche coche;
    private static Camioneta camioneta;
    private static Autocaravana autocaravana;

    @Before
    public void setUp() throws Exception {
        flota = new Flota("Adrián Borio", "41414141J", "aborio@gmail.com");
        coche = new Coche("Seat", "S224", 5000, "A12345", 9.5f, "Coche", true, 5);
        moto = new Moto("Seat", "S224", 200, "B12345", 7f, "Moto", false, 600);
        autocaravana = new Autocaravana("Seat", "S224", 6000, "C12345", 12f, "Autocaravana", true, 4);
        camioneta = new Camioneta("Seat", "S224", 500, "D12345", 8.5f, "Camioneta", true, 5100);
    }

    @Test
    public void TestFlota() {
        assertEquals("Adrián Borio", flota.getNombreGerente());
        assertEquals("41414141J", flota.getDniGerente());
        assertEquals("aborio@gmail.com", flota.getEmailGerente());

        flota.putVehiculo(coche);
        flota.putVehiculo(moto);
        flota.putVehiculo(autocaravana);
        flota.putVehiculo(camioneta);

        assertEquals(1, flota.cochesDisponibles().length);
        assertEquals(0, flota.motosDisponibles().length);
        assertEquals(1, flota.autocaravanasDisponibles().length);
        assertEquals(1, flota.camionetasDisponibles().length);

        assertTrue(flota.getVehiculos().containsKey("A12345"));
        assertTrue(flota.getVehiculos().containsKey("B12345"));
        assertTrue(flota.getVehiculos().containsKey("C12345"));
        assertTrue(flota.getVehiculos().containsKey("D12345"));

        assertEquals("Adrián Borio;41414141J;aborio@gmail.com", flota.toString());

        try {
            //Graba los floats con "," en vez de ".", pero funciona
            ControladorVehiculos.grabarAFichero("C:\\Users\\Abori\\Desktop\\flota.csv", flota);
        } catch (IOException e) {
            System.out.println("Error de escritura...");
        }
        try {
            assertEquals(4, ControladorVehiculos.leerDeFichero("C:\\Users\\Abori\\Desktop\\flota2.csv", flota));
            assertEquals(4, ControladorVehiculos.leerDeFichero("C:\\Users\\Abori\\Desktop\\flota.csv", flota));

        } catch (IOException e) {
            System.out.println("Error de lectura...");
        }

        assertTrue(flota.getVehiculos().containsKey("A12345"));
        assertTrue(flota.getVehiculos().containsKey("B12345"));
        assertTrue(flota.getVehiculos().containsKey("C12345"));
        assertTrue(flota.getVehiculos().containsKey("D12345"));
        assertTrue(flota.getVehiculos().containsKey("E12345"));
        assertTrue(flota.getVehiculos().containsKey("F12345"));
        assertTrue(flota.getVehiculos().containsKey("G12345"));
        assertTrue(flota.getVehiculos().containsKey("H12345"));

        flota = new Flota("Adrián Borio", "41414141J", "aborio@gmail.com");
        coche = new Coche("Seat", "S224", 5000, "A12345", 9.5f, "Coche", true, 5);
        moto = new Moto("Seat", "S224", 200, "B12345", 7f, "Moto", false, 600);
        autocaravana = new Autocaravana("Seat", "S224", 6000, "C12345", 12f, "Autocaravana", true, 4);
        camioneta = new Camioneta("Seat", "S224", 500, "D12345", 8.5f, "Camioneta", true, 5100);

        flota.putVehiculo(coche);
        flota.putVehiculo(moto);
        flota.putVehiculo(autocaravana);
        flota.putVehiculo(camioneta);
        flota.putVehiculo(coche);
        flota.putVehiculo(moto);
        flota.putVehiculo(autocaravana);
        flota.putVehiculo(camioneta);

        assertEquals(4, flota.getVehiculos().size());

        assertEquals(552.5f, flota.facturarVehiculo("D12345", 50), 0f);

    }

    @Test
    public void TestVehiculos() {
        coche.setPasajeros(1);
        assertEquals(5, coche.getPasajeros());
        coche.setPasajeros(8);
        assertEquals(5, coche.getPasajeros());
        coche.setPasajeros(3);
        assertEquals(3, coche.getPasajeros());
        coche.setPasajeros(4);
        assertEquals(475f, coche.facturaAlquiler(50), 0f);
        coche.setPasajeros(7);
        assertEquals(522.5f, coche.facturaAlquiler(50), 0f);

        coche.setKilometraje(-10);
        assertEquals(5000, coche.getKilometraje());
        coche.setKilometraje(100);
        assertEquals(5100, coche.getKilometraje());


        moto.setCilindrada(200);
        assertEquals(200, moto.getCilindrada());
        moto.setCilindrada(1000);
        assertEquals(1000, moto.getCilindrada());

        moto.setCilindrada(400);
        assertEquals(350f, moto.facturaAlquiler(50), 0f);
        moto.setCilindrada(700);
        assertEquals(420f, moto.facturaAlquiler(50), 0f);

        autocaravana.setCamasDisponibles(5);
        assertEquals(5, autocaravana.getCamasDisponibles());

        camioneta.setPesoMaximo(3500);
        assertEquals(3500, camioneta.getPesoMaximo());
    }
}