
package itson.observadorpartida;

import itson.dominio.Partida;

public interface IObservadorPartida {
    /**
     * Este método será llamado automáticamente cada vez que 
     * el estado del juego cambie (alguien juega, roba o termina turno).
     */
    void actualizarEstado(Partida partida);
}
