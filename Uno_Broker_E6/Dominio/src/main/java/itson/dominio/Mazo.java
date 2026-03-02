
package itson.dominio;

import java.util.Stack;

public class Mazo {
    private Stack<Carta> cartas;

    public Mazo() {
        this.cartas = new Stack<>();
    }

    public Carta tomarCarta() {
        if (estaVacio()) {
            throw new IllegalStateException("El mazo está vacío.");
        }
        return cartas.pop();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public Stack<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(Stack<Carta> cartas) {
        this.cartas = cartas;
    }
}
