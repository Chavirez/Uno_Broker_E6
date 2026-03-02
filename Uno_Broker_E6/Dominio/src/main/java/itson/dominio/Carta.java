package itson.dominio;

import itson.dominio.enums.Color;

public abstract class Carta {
    private final String id;
    private final Color color;
    private final boolean comodin;

    public Carta(String id, Color color, boolean comodin) {
        this.id = id;
        this.color = color;
        this.comodin = comodin;
    }

    public boolean esComodin() {
        return comodin;
    }

    public String getId() { return id; }
    public Color getColor() { return color; }
}