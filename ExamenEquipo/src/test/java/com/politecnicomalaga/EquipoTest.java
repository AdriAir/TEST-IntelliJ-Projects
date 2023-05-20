package com.politecnicomalaga;

import com.politecnicomalaga.model.Equipo;
import com.politecnicomalaga.model.Jugador;
import com.politecnicomalaga.model.Portero;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EquipoTest {

    private static Equipo equipoCSV;
    private static Equipo equipo;
    private static Portero portero;
    private static Jugador jugador;

    @org.junit.Before
    public void setUp() throws Exception {
        equipo = new Equipo("CP Sant Lluís", 14, "Menorca", "cp@basquet.com");
    }

    @org.junit.Test
    public void isActivo() {
        assertFalse(equipo.isActivo());
        for (int i = 0; i < 6; i++) {
            equipo.addJugador(new Jugador("Adrián",
                    "Borio Muñoz",
                    String.valueOf(i),
                    "gmail.com",
                    "38472783",
                    2002,
                    79,
                    3));
        }
        assertFalse(equipo.isActivo());
        equipo.addPortero(new Portero("Adrián",
                "Borio Muñoz",
                "127823J",
                "gmail.com",
                "38472783",
                2002,
                79,
                3,
                0));

        assertTrue(equipo.isActivo());
    }

    @org.junit.Test
    public void testToString() {
        for (int i = 0; i < 6; i++) {
            equipo.addJugador(new Jugador("Adrián",
                    "Borio Muñoz",
                    String.valueOf(i),
                    "gmail.com",
                    "38472783",
                    2002,
                    79,
                    3));
        }
        equipo.addPortero(new Portero("Adrián",
                "Borio Muñoz",
                "127823J",
                "gmail.com",
                "38472783",
                2002,
                79,
                3,
                0));
        equipoCSV = new Equipo(equipo.toString());
        assertEquals(equipoCSV.toString(), equipo.toString());
    }

    @org.junit.Test
    public void eliminaPortero() {
        equipo.addPortero(new Portero("Adrián",
                "Borio Muñoz",
                "127823J",
                "gmail.com",
                "38472783",
                2018,
                79,
                3,
                0));
        equipo.addPortero(new Portero("Adrián",
                "Borio 2",
                "12712413J",
                "gmail.com",
                "38472783",
                2009,
                79,
                3,
                0));

        equipo.eliminaPortero("127823J");

        assertEquals(1, equipo.menoresEdad().size());
    }

    @org.junit.Test
    public void eliminaJugador() {
        for (int i = 0; i < 4; i++) {
            equipo.addJugador(new Jugador("Adrián",
                    "Borio Muñoz",
                    String.valueOf(i),
                    "gmail.com",
                    "38472783",
                    2015,
                    79,
                    3));
        }
        equipo.eliminaJugador("3");
        assertEquals(3, equipo.menoresEdad().size());

    }

    @org.junit.Test
    public void menoresEdad() {

        ArrayList<Jugador> jugadores;

        equipo.addJugador(new Jugador("Concha",
                "Fresca Dos",
                "2834",
                "gmail.com",
                "38472783",
                2015,
                79,
                3));
        equipo.addJugador(new Jugador("Adrián",
                "Borio Muñoz",
                "18727",
                "gmail.com",
                "38472783",
                2015,
                79,
                3));
        equipo.addJugador(new Jugador("Adrián",
                "Muñoz Borio",
                "93832",
                "gmail.com",
                "38472783",
                2015,
                79,
                3));
        equipo.addJugador(new Jugador("Borio",
                "Muñoz Muñoz",
                "0928372",
                "gmail.com",
                "38472783",
                2015,
                79,
                3));

        equipo.addJugador(new Jugador("Muñoz",
                "Borio Adrian",
                "293",
                "gmail.com",
                "38472783",
                2001,
                79,
                3));
        equipo.addPortero(new Portero("Adrián",
                "Borio Borio",
                "127823J",
                "gmail.com",
                "38472783",
                2018,
                79,
                3,
                0));
        equipo.addPortero(new Portero("Borio",
                "Borio Borio",
                "12712413J",
                "gmail.com",
                "38472783",
                2002,
                79,
                3,
                0));

        assertEquals(5, equipo.menoresEdad().size());

        jugadores = equipo.menoresEdad();

        for (int i = 0; i < jugadores.size(); i++){
            System.out.println(jugadores.get(i).toString());
        }
    }

    @org.junit.Test
    public void jugadoresTitulares() {
        ArrayList<Jugador> jugadores;

        equipo.addJugador(new Jugador("Concha",
                "Fresca Dos",
                "2834",
                "gmail.com",
                "38472783",
                2015,
                79,
                9));
        equipo.addJugador(new Jugador("Adrián",
                "Borio Muñoz",
                "18727",
                "gmail.com",
                "38472783",
                2015,
                79,
                10));
        equipo.addJugador(new Jugador("Adrián",
                "Muñoz Borio",
                "93832",
                "gmail.com",
                "38472783",
                2015,
                79,
                2));
        equipo.addJugador(new Jugador("Borio",
                "Muñoz Muñoz",
                "0928372",
                "gmail.com",
                "38472783",
                2015,
                79,
                6));

        equipo.addJugador(new Jugador("Muñoz",
                "Borio Adrian",
                "293",
                "gmail.com",
                "38472783",
                2001,
                79,
                2));
        equipo.addPortero(new Portero("Adrián",
                "Borio Borio",
                "127823J",
                "gmail.com",
                "38472783",
                2018,
                79,
                3,
                4));
        equipo.addPortero(new Portero("Borio",
                "Borio Borio",
                "12712413J",
                "gmail.com",
                "38472783",
                2002,
                79,
                3,
                0));

        jugadores = equipo.jugadoresTitulares();

        for (int i = 0; i < jugadores.size(); i++){
            System.out.println(jugadores.get(i).toString());
        }

    }
}