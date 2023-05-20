package com.politecnicomalaga;

import com.politecnicomalaga.model.Equipo;
import com.politecnicomalaga.model.Jugador;
import com.politecnicomalaga.model.Portero;
import org.junit.Test;

import static org.junit.Assert.*;

public class JugadorTest {

    private static Jugador jugadorCSV;
    private static Jugador jugador;
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

        jugador = new Jugador(nombre, apellidos, dni, email, telefono, nacimiento, dorsal, goles);

    }

    @Test
    public void getterSetters(){
        assertEquals(jugador.getNombre(), nombre);
        assertEquals(jugador.getApellidos(), apellidos);
        assertEquals(jugador.getDni(), dni);
        assertEquals(jugador.getDorsal(), dorsal);
        assertEquals(jugador.getEmail(), email);
        assertEquals(jugador.getTelefono(), telefono);
        assertEquals(jugador.getNacimiento(), nacimiento);
        assertEquals(jugador.getGoles(), 0);

        nombre = "Antonio";
        apellidos = "Cervantes";
        dni = "31313131J";
        email = "acerjur@gmail.com";
        telefono = "32224353";
        nacimiento = 2007;
        dorsal = -1;
        goles = 18;
        golesEncajados = 2;

        jugador.setNombre(nombre);
        jugador.setApellidos(apellidos);
        jugador.setDni(dni);
        jugador.setEmail(email);
        jugador.setTelefono(telefono);
        jugador.setNacimiento(nacimiento);
        jugador.setDorsal(dorsal);
        jugador.setGoles(goles);

        assertEquals(jugador.getNombre(), nombre);
        assertEquals(jugador.getApellidos(), apellidos);
        assertEquals(jugador.getDni(), dni);
        assertEquals(jugador.getDorsal(), 100);
        assertEquals(jugador.getEmail(), email);
        assertEquals(jugador.getTelefono(), telefono);
        assertEquals(jugador.getNacimiento(), nacimiento);
        assertEquals(jugador.getGoles(), goles);

    }

    @Test
    public void testToString() {
        jugadorCSV = new Jugador(jugador.toString());
        assertEquals(jugador.getNombre(), jugadorCSV.getNombre());
        assertEquals(jugador.getApellidos(), jugadorCSV.getApellidos());
        assertEquals(jugador.getDni(), jugadorCSV.getDni());
        assertEquals(jugador.getDorsal(), jugadorCSV.getDorsal());
        assertEquals(jugador.getEmail(), jugadorCSV.getEmail());
        assertEquals(jugador.getTelefono(), jugadorCSV.getTelefono());
        assertEquals(jugador.getNacimiento(), jugadorCSV.getNacimiento());
        assertEquals(jugador.getGoles(), jugadorCSV.getGoles());
    }

    @Test
    public void mayorEdad() {
        assertTrue(jugador.mayorEdad());
        jugador.setNacimiento(2008);
        assertFalse(jugador.mayorEdad());
    }
}