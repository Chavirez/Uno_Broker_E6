
package itson.dominio;

import itson.dominio.enums.TipoJugada;

public class Penalizacion {
    private int cantidadCartas;
    private String motivo;

    public Penalizacion(int cantidadCartas, String motivo) {
        this.cantidadCartas = cantidadCartas;
        this.motivo = motivo;
    }

    public void aplicar(Partida partida, String idJugador) {
        Jugador jugadorPenalizado = null;
        
        // Asume que agregaste public List<Jugador> getJugadores() en Partida
        for (Jugador j : partida.getJugadores()) {
            if (j.getId().equals(idJugador)) {
                jugadorPenalizado = j;
                break;
            }
        }

        if (jugadorPenalizado != null) {
            for (int i = 0; i < cantidadCartas; i++) {
                if (!partida.getMazo().estaVacio()) {
                    Carta carta = partida.getMazo().tomarCarta();
                    jugadorPenalizado.getMano().agregarCarta(carta);
                }
            }
            
            Jugada jugada = new Jugada(TipoJugada.ROBAR_CARTA, System.currentTimeMillis(), "Sistema", "Penalización a " + idJugador + " por " + motivo + ": robó " + cantidadCartas + " cartas.");
            partida.getJugadas().add(jugada);
        }
    }

    public int getCantidadCartas() { return cantidadCartas; }
    public String getMotivo() { return motivo; }
}