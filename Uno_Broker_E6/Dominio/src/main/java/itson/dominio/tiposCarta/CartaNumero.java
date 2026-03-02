package itson.dominio.tiposCarta;

import itson.dominio.Carta;
import itson.dominio.enums.Color;
import itson.dominio.enums.ValorNumero;

public class CartaNumero extends Carta {
    private final ValorNumero valor;

    public CartaNumero(String id, Color color, ValorNumero valor) {
        super(id, color, false);
        this.valor = valor;
    }

    public ValorNumero getValor() { return valor; }
}