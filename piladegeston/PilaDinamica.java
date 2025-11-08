import java.util.ArrayList;

public class PilaDinamica {
    private ArrayList<Llamada> pila;

    public PilaDinamica() {
        pila = new ArrayList<>();
    }

    public void push(Llamada llamada) {
        pila.add(llamada);
    }

    public Llamada pop() {
        if (pila.isEmpty()) {
            System.out.println("No se puede hacer POP, la pila esta vacia");
            return null;
        }
        return pila.remove(pila.size() - 1);
    }

    public Llamada peek() {
        if (pila.isEmpty()) {
            System.out.println("La pila esta vacia");
            return null;
        }
        return pila.get(pila.size() - 1);
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }

    public int size() {
        return pila.size();
    }

    public void mostrarPila() {
        if (pila.isEmpty()) {
            System.out.println("La pila esta vacia");
            return;
        }

        System.out.println("Contenido de la pila de llamadas (de arriba hacia abajo)");
        for (int i = pila.size() - 1; i >= 0; i--) {
            Llamada l = pila.get(i);
            System.out.println("Funcion: " + l.nombre +
                    " | Retorno: " + l.retorno +
                    " | Parametros: " + l.params);
        }
        System.out.println("-------------------------------------------");
    }
}
