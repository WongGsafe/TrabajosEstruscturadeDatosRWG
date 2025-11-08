import java.util.List;

public class Llamada {
    public final TipoDato retorno;
    public final String nombre;
    public final List<Parametro> params;

    public Llamada(TipoDato retorno, String nombre, List<Parametro> params) {
        this.retorno = retorno;
        this.nombre = nombre;
        this.params = params;
    }

    @Override
    public String toString() {
        return nombre + ":" + retorno + "(#" + params.size() + ")";
    }
}
