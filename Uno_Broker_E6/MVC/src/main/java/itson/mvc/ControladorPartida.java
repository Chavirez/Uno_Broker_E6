package itson.mvc;

import itson.dominio.Partida;

public class ControladorPartida {
    private Partida partida;

    public ControladorPartida(Partida partida) {
        this.partida = partida;
    }

    // La vista llama a este método cuando el jugador hace clic en una de sus cartas
    public void intentarJugarCarta(String idJugador, int indiceCarta) {
        try {
            partida.jugarCarta(idJugador, indiceCarta);
        } catch (Exception e) {
            // Aquí podrías avisarle a la vista que muestre un popup de error
            System.out.println("Error al jugar: " + e.getMessage());
        }
    }

    // La vista llama a este método cuando hacen clic en el mazo para robar
    public void intentarRobarCarta(String idJugador) {
        try {
            partida.robarCarta(idJugador);
        } catch (Exception e) {
            System.out.println("Error al robar: " + e.getMessage());
        }
    }
}