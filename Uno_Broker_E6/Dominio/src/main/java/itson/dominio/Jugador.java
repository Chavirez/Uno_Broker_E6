
package itson.dominio;

public class Jugador {
    private String id;
    private String nombre;
    private String avatar;
    private boolean listo;
    private Mano mano;

    public Jugador(String id, String nombre, String avatar) {
        this.id = id;
        this.nombre = nombre;
        this.avatar = avatar;
        this.listo = false;
        this.mano = new Mano();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public boolean isListo() { return listo; }
    public void setListo(boolean listo) { this.listo = listo; }

    public Mano getMano() { return mano; }
}