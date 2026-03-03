/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.ensamblador;

import itson.dominio.*;
import itson.dominio.enums.Color;
import itson.dominio.enums.ValorNumero;
import itson.dominio.tiposCarta.CartaNumero;
import itson.dominio.tiposCarta.accionesCarta.Reversa;
import itson.dominio.tiposCarta.accionesCarta.Toma2;
import itson.dominio.tiposCarta.accionesCarta.Toma4;
import itson.mvc.ControladorPartida;
import itson.mvc.VistaMesaJuego;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author santi
 */
public class Ensamblador {

    public static void main(String[] args) {
        // 1. Creas el Modelo
        Mazo m = crearMazoUNO();
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("J1", "Santi", "avatar1.png"));
        jugadores.add(new Jugador("J2", "Ana", "avatar2.png"));
        jugadores.add(new Jugador("J3", "Carlos", "avatar3.png"));
        jugadores.add(new Jugador("J4", "María", "avatar4.png"));
        Partida partida = new Partida("1",jugadores, m);

        // 2. Creas el Controlador (le pasas el modelo para que pueda modificarlo)
        ControladorPartida controlador = new ControladorPartida(partida);

        // 3. Creas la Vista (le pasas el controlador para enviar los clics del usuario)
        VistaMesaJuego vista = new VistaMesaJuego(controlador, "1");

        // 4. ¡La magia del Observer! Suscribes la vista al modelo
        partida.agregarObservador(vista);
    }
    
    private static Mazo crearMazoUNO() {
            Stack<Carta> pilaCartas = new Stack<>();
            int idContador = 1;

            // Colores básicos del juego
            Color[] coloresBasicos = {Color.ROJO, Color.AMARILLO, Color.VERDE, Color.AZUL};
            ValorNumero[] valores = ValorNumero.values();

            for (Color c : coloresBasicos) {
                // Un solo 0 por cada color
                pilaCartas.push(new CartaNumero("C" + idContador++, c, ValorNumero.CERO));

                // Dos cartas de cada número del 1 al 9 por color
                for (int i = 1; i <= 9; i++) {
                    pilaCartas.push(new CartaNumero("C" + idContador++, c, valores[i]));
                    pilaCartas.push(new CartaNumero("C" + idContador++, c, valores[i]));
                }

//                // Dos cartas de acción de cada tipo por color
//                pilaCartas.push(new Salto("C" + idContador++, c));
//                pilaCartas.push(new Salto("C" + idContador++, c));

                pilaCartas.push(new Reversa("C" + idContador++, c));
                pilaCartas.push(new Reversa("C" + idContador++, c));

                pilaCartas.push(new Toma2("C" + idContador++, c));
                pilaCartas.push(new Toma2("C" + idContador++, c));
            }

            // 4 Comodines de Cambio de Color y 4 Toma 4 (estos son de color NEGRO por defecto)
            for (int i = 0; i < 4; i++) {
//                pilaCartas.push(new CambioColor("C" + idContador++));
                pilaCartas.push(new Toma4("C" + idContador++));
            }

            // ¡Barajar las cartas! Usamos Collections.shuffle para revolver el Stack
            Collections.shuffle(pilaCartas);

            // Crear el objeto Mazo y asignarle la pila de cartas
            Mazo mazoCompleto = new Mazo();
            mazoCompleto.setCartas(pilaCartas);

            return mazoCompleto;
        }
}
