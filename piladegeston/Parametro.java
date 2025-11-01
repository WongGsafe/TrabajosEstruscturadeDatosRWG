public class Parametro {
    public final TipoDato tipo;
    public final String nombre;

    public Parametro(TipoDato tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return tipo + " " + nombre;
    }
}
