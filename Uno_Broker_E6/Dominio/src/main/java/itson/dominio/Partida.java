/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.dominio;

import itson.dominio.Interfaces.ObservadorPartida;
import itson.dominio.enums.*;
import itson.dominio.tiposCarta.accionesCarta.*;
import itson.dominio.tiposCarta.*;
import java.util.ArrayList;
import java.util.List;

public class Partida {
    private String id;
    private EstadoPartida estado;
    private Color colorActual;
    private ValorNumero valorActual;
    private Direccion direccion;
    private int indiceTurno;

    private Mazo mazo;
    private PilaDescarte pilaDescarte;
    private List<Jugador> jugadores;
    private Turno turno;
    private RestriccionTurno restriccionTurno;
    private ReglaUNO reglaUNO;
    private List<Jugada> jugadas;
    private List<ObservadorPartida> observadores;

    public Partida(String id, List<Jugador> jugadores, Mazo mazo) {
        this.id = id;
        this.jugadores = jugadores;
        this.mazo = mazo;
        this.pilaDescarte = new PilaDescarte();
        this.turno = new Turno(0, jugadores.get(0).getId());
        this.restriccionTurno = new RestriccionTurno();
        this.reglaUNO = new ReglaUNO();
        this.jugadas = new ArrayList<>();
        this.estado = EstadoPartida.EN_CURSO;
        this.direccion = Direccion.HORARIO;
        this.indiceTurno = 0;
        this.observadores = new ArrayList<>();
    }

    public Jugador obtenerJugadorActual() {
        return jugadores.get(indiceTurno);
    }

    public void agregarObservador(ObservadorPartida observador) {
            this.observadores.add(observador);
            observador.actualizarEstado(this); 
        }

    public void removerObservador(ObservadorPartida observador) {
        this.observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (ObservadorPartida obs : observadores) {
            obs.actualizarEstado(this);
        }
    }
    
    public void jugarCarta(String idJugador, int indiceCarta) {
        Jugador jugador = obtenerJugadorActual();

        if (!jugador.getId().equals(idJugador)) {
            throw new IllegalStateException("No es el turno del jugador.");
        }

        Carta carta = jugador.getMano().obtenerCarta(indiceCarta);
        Carta cima = pilaDescarte.obtenerCima();

        if (!reglaUNO.esJugadaValida(carta, cima, this.colorActual)) {
            throw new IllegalArgumentException("Jugada no válida según las reglas de UNO.");
        }

        if (restriccionTurno.hayRestriccion() && !(carta instanceof Toma2 || carta instanceof Toma4)) {
            throw new IllegalStateException("Existe una restricción activa. Debes robar cartas.");
        }

        reglaUNO.aplicarEfecto(this, carta);

        jugador.getMano().removerCarta(indiceCarta);
        pilaDescarte.ponerCarta(carta);

        Jugada jugada = new Jugada(TipoJugada.JUGAR_CARTA, System.currentTimeMillis(), idJugador, "Lanzó carta: " + carta.getId());
        this.jugadas.add(jugada);

        if (jugador.getMano().cantidadCartas() == 0) {
            terminarPartida();
        } else {
            turno.avanzar(jugadores.size(), direccion);
            this.indiceTurno = turno.getNumero();
        }
        
        notificarObservadores();
        
    }

    public void robarCarta(String idJugador) {
        Jugador jugador = obtenerJugadorActual();

        if (!jugador.getId().equals(idJugador)) {
            throw new IllegalStateException("No es el turno del jugador.");
        }

        int cantidadARobar = 1;
        if (restriccionTurno.hayRestriccion()) {
            cantidadARobar = restriccionTurno.getCartasPorTomarPendientes();
        }

        for (int i = 0; i < cantidadARobar; i++) {
            if (!mazo.estaVacio()) {
                Carta robada = mazo.tomarCarta();
                jugador.getMano().agregarCarta(robada);
            }
        }

        if (restriccionTurno.hayRestriccion()) {
            restriccionTurno.consumirEnTurno();
        }

        Jugada jugada = new Jugada(TipoJugada.ROBAR_CARTA, System.currentTimeMillis(), idJugador, "Robó " + cantidadARobar + " carta(s)");
        this.jugadas.add(jugada);

        turno.avanzar(jugadores.size(), direccion);
        this.indiceTurno = turno.getNumero();
        
        notificarObservadores();
    }

    public void elegirColor(String idJugador, Color color) {
        Jugador jugador = obtenerJugadorActual();

        if (!jugador.getId().equals(idJugador)) {
            throw new IllegalStateException("No es el turno del jugador.");
        }

        this.colorActual = color;

        Jugada jugada = new Jugada(TipoJugada.ELEGIR_COLOR, System.currentTimeMillis(), idJugador, "Eligió el color " + color.name());
        this.jugadas.add(jugada);
        
    }

    public void terminarPartida() {
        this.estado = EstadoPartida.FINALIZADA;
        
        Jugada jugada = new Jugada(TipoJugada.FIN_PARTIDA, System.currentTimeMillis(), "Sistema", "La partida ha concluido.");
        this.jugadas.add(jugada);
    }

    public String getId() { return id; }
    public EstadoPartida getEstado() { return estado; }
    public Color getColorActual() { return colorActual; }
    public void setColorActual(Color colorActual) { this.colorActual = colorActual; }
    public ValorNumero getValorActual() { return valorActual; }
    public void setValorActual(ValorNumero valorActual) { this.valorActual = valorActual; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public int getIndiceTurno() { return indiceTurno; }
    public Turno getTurno() { return turno; }
    public RestriccionTurno getRestriccionTurno() { return restriccionTurno; }
    public PilaDescarte getPilaDescarte() { return pilaDescarte; }
    public Mazo getMazo() { return mazo; }
    public List<Jugada> getJugadas() { return jugadas; }
    public List<Jugador> getJugadores() { return jugadores; }
}