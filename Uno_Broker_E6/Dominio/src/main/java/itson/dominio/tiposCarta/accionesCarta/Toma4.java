
package itson.dominio.tiposCarta.accionesCarta;

import itson.dominio.Partida;
import itson.dominio.enums.Color;
import itson.dominio.tiposCarta.CartaAccion;

public class Toma4 extends CartaAccion {
    public Toma4(String id) {
        super(id, Color.NEGRO, true); 
    }
    @Override
    public void ejecutar(Partida partida) {
        partida.getRestriccionTurno().acumularToma(4);
    }
}
