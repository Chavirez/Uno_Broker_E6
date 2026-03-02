
package itson.dominio.tiposCarta.accionesCarta;

import itson.dominio.Partida;
import itson.dominio.enums.Color;
import itson.dominio.enums.Direccion;
import itson.dominio.tiposCarta.CartaAccion;

public class Reversa extends CartaAccion {
    public Reversa(String id, Color color) {
        super(id, color, false);
    }

    @Override
    public void ejecutar(Partida partida) {
        // Lógica para invertir la dirección en el estado de la partida
        Direccion actual = partida.getDireccion();
        partida.setDireccion(actual == Direccion.HORARIO ? 
                             Direccion.ANTIHORARIO : Direccion.HORARIO);
    }
}