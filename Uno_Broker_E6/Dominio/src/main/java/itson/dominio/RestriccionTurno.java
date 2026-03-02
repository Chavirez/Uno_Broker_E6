
package itson.dominio;

public class RestriccionTurno {
    private int cartasPorTomarPendientes;
    private int saltosPendientes;

    public RestriccionTurno() {
        this.cartasPorTomarPendientes = 0;
        this.saltosPendientes = 0;
    }

    public boolean hayRestriccion() {
        return cartasPorTomarPendientes > 0 || saltosPendientes > 0;
    }

    public void acumularToma(int n) {
        this.cartasPorTomarPendientes += n;
    }

    public void acumularSalto() {
        this.saltosPendientes += 1;
    }

    public void consumirEnTurno() {
        this.cartasPorTomarPendientes = 0;
        this.saltosPendientes = 0;
    }

    public int getCartasPorTomarPendientes() {
        return cartasPorTomarPendientes;
    }

    public int getSaltosPendientes() {
        return saltosPendientes;
    }
}
