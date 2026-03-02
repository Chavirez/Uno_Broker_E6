
package itson.dominio;

import itson.dominio.enums.TipoJugada;

public class Jugada {
    private final TipoJugada tipo;
    private final long timestamp;
    private final String idJugador;
    private final String descripcion;

    public Jugada(TipoJugada tipo, long timestamp, String idJugador, String descripcion) {
        this.tipo = tipo;
        this.timestamp = timestamp;
        this.idJugador = idJugador;
        this.descripcion = descripcion;
    }

    public TipoJugada getTipo() { return tipo; }
    public long getTimestamp() { return timestamp; }
    public String getIdJugador() { return idJugador; }
    public String getDescripcion() { return descripcion; }
}