
package itson.dominio.tiposCarta.accionesCarta;

import itson.dominio.Partida;
import itson.dominio.enums.Color;
import itson.dominio.tiposCarta.CartaAccion;

public class Toma4 extends CartaAccion {
    public Toma4(String id) {
        super(id, Color.NEGRO, true); // Es comodín por definición
    }

    @Override
    public void ejecutar(Partida partida) {
        partida.getRestriccion().acumularToma(4);
        // Aquí la partida también debería solicitar al jugador elegir un color
    }
}
