package com.politecnicomalaga;

import com.politecnicomalaga.model.Portero;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PorteroTest {

    private static Portero porteroCSV;
    private static Portero portero;
    private static String nombre;
    private static String apellidos;
    private static String dni;
    private static String email;
    private static String telefono;
    private static int nacimiento;
    private static int dorsal;
    private static int goles;
    private static int golesEncajados;
    @org.junit.Before
    public void setUp() throws Exception {
        nombre = "Adrián";
        apellidos = "Borio Munoz";
        dni = "41414141J";
        email = "aborio2004@gmail.com";
        telefono = "182302040";
        nacimiento = 2004;
        dorsal = 45;
        goles = -12;
        golesEncajados = 12;

        portero = new Portero(nombre, apellidos, dni, email, telefono, nacimiento, dorsal, goles, golesEncajados);

    }

    @Test
    public void getterSetters(){
        assertEquals(portero.getNombre(), nombre);
        assertEquals(portero.getApellidos(), apellidos);
        assertEquals(portero.getDni(), dni);
        assertEquals(portero.getDorsal(), dorsal);
        assertEquals(portero.getEmail(), email);
        assertEquals(portero.getTelefono(), telefono);
        assertEquals(portero.getNacimiento(), nacimiento);
        assertEquals(portero.getGoles(), 0);

        nombre = "Antonio";
        apellidos = "Cervantes";
        dni = "31313131J";
        email = "acerjur@gmail.com";
        telefono = "32224353";
        nacimiento = 2007;
        dorsal = -1;
        goles = 18;
        golesEncajados = 2;

        portero.setNombre(nombre);
        portero.setApellidos(apellidos);
        portero.setDni(dni);
        portero.setEmail(email);
        portero.setTelefono(telefono);
        portero.setNacimiento(nacimiento);
        portero.setDorsal(dorsal);
        portero.setGoles(goles);
        portero.setGolesEncajados(golesEncajados);

        assertEquals(portero.getNombre(), nombre);
        assertEquals(portero.getApellidos(), apellidos);
        assertEquals(portero.getDni(), dni);
        assertEquals(portero.getDorsal(), 100);
        assertEquals(portero.getEmail(), email);
        assertEquals(portero.getTelefono(), telefono);
        assertEquals(portero.getNacimiento(), nacimiento);
        assertEquals(portero.getGoles(), goles);
        assertEquals(portero.getGolesEncajados(), golesEncajados + 12);

    }

    @Test
    public void testToString() {
        porteroCSV = new Portero(portero.toString());
        assertEquals(portero.getNombre(), porteroCSV.getNombre());
        assertEquals(portero.getApellidos(), porteroCSV.getApellidos());
        assertEquals(portero.getDni(), porteroCSV.getDni());
        assertEquals(portero.getDorsal(), porteroCSV.getDorsal());
        assertEquals(portero.getEmail(), porteroCSV.getEmail());
        assertEquals(portero.getTelefono(), porteroCSV.getTelefono());
        assertEquals(portero.getNacimiento(), porteroCSV.getNacimiento());
        assertEquals(portero.getGoles(), porteroCSV.getGoles());
        assertEquals(portero.getGolesEncajados(), porteroCSV.getGolesEncajados());
    }
}