
package itson.dominio;

import itson.dominio.enums.Direccion;

public class Turno {
    private int numero;
    private String jugadorActualId;

    public Turno(int numeroInicial, String jugadorActualIdInicial) {
        this.numero = numeroInicial;
        this.jugadorActualId = jugadorActualIdInicial;
    }

    public void avanzar(int totalJugadores, Direccion direccion) {
        if (direccion == Direccion.HORARIO) {
            this.numero = (this.numero + 1) % totalJugadores;
        } else {
            this.numero = (this.numero - 1 + totalJugadores) % totalJugadores;
        }
    }

    public void saltarSiguiente(int totalJugadores, Direccion direccion) {
        if (direccion == Direccion.HORARIO) {
            this.numero = (this.numero + 2) % totalJugadores;
        } else {
            this.numero = (this.numero - 2 + totalJugadores) % totalJugadores;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getJugadorActualId() {
        return jugadorActualId;
    }

    public void setJugadorActualId(String jugadorActualId) {
        this.jugadorActualId = jugadorActualId;
    }
}