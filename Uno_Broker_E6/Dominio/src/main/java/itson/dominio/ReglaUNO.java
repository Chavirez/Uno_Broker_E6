
package itson.dominio;

import itson.dominio.enums.Color;
import itson.dominio.tiposCarta.CartaAccion;
import itson.dominio.tiposCarta.CartaNumero;


public class ReglaUNO {

    public boolean esJugadaValida(Carta cartaALanzar, Carta cartaCima, Color colorActivo) {
        // 1. Si es un comodín (Toma 4 o Cambio de Color), siempre es válido lanzarlo
        if (cartaALanzar.esComodin()) {
            return true;
        }

        // 2. Si los colores coinciden (usando el color activo de la partida)
        if (cartaALanzar.getColor() == colorActivo) {
            return true;
        }

        // 3. Si son cartas de número y el valor coincide
        if (cartaALanzar instanceof CartaNumero && cartaCima instanceof CartaNumero) {
            return ((CartaNumero) cartaALanzar).getValor() == ((CartaNumero) cartaCima).getValor();
        }

        // 4. Si son cartas de acción y el tipo de acción coincide (ej. Salto sobre Salto)
        if (cartaALanzar instanceof CartaAccion && cartaCima instanceof CartaAccion) {
            return cartaALanzar.getClass().equals(cartaCima.getClass());
        }

        return false;
    }

    public void aplicarEfecto(Partida partida, Carta carta) {
        // Primero, si la carta es de acción, ejecutamos su lógica interna
        if (carta instanceof CartaAccion) {
            ((CartaAccion) carta).ejecutar(partida);
        }

        // Actualizamos el estado de la partida con la nueva carta
        partida.setColorActual(carta.getColor());
        
        if (carta instanceof CartaNumero) {
            partida.setValorActual(((CartaNumero) carta).getValor());
        } else {
            // Si es acción, el valor numérico suele quedar nulo o ignorado
            partida.setValorActual(null);
        }
    }
}