package itson.dominio.tiposCarta;

import itson.dominio.Carta;
import itson.dominio.Partida;
import itson.dominio.enums.Color;

public abstract class CartaAccion extends Carta {
    public CartaAccion(String id, Color color, boolean esComodin) {
        super(id, color, esComodin);
    }

    public abstract void ejecutar(Partida partida);
}