
package itson.dominio.tiposCarta.accionesCarta;

import itson.dominio.Partida;
import itson.dominio.enums.Color;
import itson.dominio.tiposCarta.CartaAccion;

public class Toma2 extends CartaAccion {
    public Toma2(String id, Color color) {
        super(id, color, false);
    }

    @Override
    public void ejecutar(Partida partida) {
        // Accedemos a la restricción de la partida para acumular cartas
        partida.getRestriccion().acumularToma(2);
    }
}