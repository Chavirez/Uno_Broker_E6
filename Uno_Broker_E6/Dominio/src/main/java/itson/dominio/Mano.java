
package itson.dominio;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private List<Carta> cartas;

    public Mano() {
        this.cartas = new ArrayList<>();
    }

    public Carta obtenerCarta(int indice) {
        if (indice < 0 || indice >= cartas.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        return cartas.get(indice);
    }

    public void agregarCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public Carta removerCarta(int indice) {
        if (indice < 0 || indice >= cartas.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        return cartas.remove(indice);
    }

    public int cantidadCartas() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}