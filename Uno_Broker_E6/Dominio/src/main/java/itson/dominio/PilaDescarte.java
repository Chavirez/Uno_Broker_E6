
package itson.dominio;

import java.util.Stack;

public class PilaDescarte {
    private Stack<Carta> cartas;

    public PilaDescarte() {
        this.cartas = new Stack<>();
    }

    public void ponerCarta(Carta carta) {
        this.cartas.push(carta);
    }

    public Carta obtenerCima() {
        if (this.cartas.isEmpty()) {
            return null;
        }
        return this.cartas.peek();
    }

    public Stack<Carta> getCartas() {
        return cartas;
    }
}