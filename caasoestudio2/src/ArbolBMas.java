import java.util.List;

public class ArbolBMas {
    private final int orden;
    private NodoArbolBMas raiz;

    public ArbolBMas(int orden) {
        if (orden < 3) throw new IllegalArgumentException("El orden debe ser >= 3");
        this.orden = orden;
        this.raiz = new NodoArbolBMas(true);     }

    public void insertar(int llave) {
        if (raiz.getLlaves().size() == orden - 1) {
            NodoArbolBMas nuevaRaiz = new NodoArbolBMas(false);
            nuevaRaiz.getHijos().add(raiz);
            dividirHijo(nuevaRaiz, 0);
            raiz = nuevaRaiz;
        }
        insertarNoLleno(raiz, llave);
    }

    public boolean buscar(int llave) {
        return buscarNodo(raiz, llave);
    }

    public void imprimirArbol() {
        imprimirNodo(raiz, "", true);
    }

    public void recorrerDesde(int claveInicio, int n) {
        if (n <= 0) return;
        NodoArbolBMas hoja = buscarHoja(raiz, claveInicio);
        List<Integer> ks = hoja.getLlaves();
        int i = 0;
        while (i < ks.size() && ks.get(i) < claveInicio) i++;

        int restantes = n;
        NodoArbolBMas actual = hoja;
        while (actual != null && restantes > 0) {
            ks = actual.getLlaves();
            while (i < ks.size() && restantes > 0) {
                System.out.println(ks.get(i));
                i++; restantes--;
            }
            if (restantes > 0) { actual = actual.siguiente; i = 0; }
        }
    }


    private void insertarNoLleno(NodoArbolBMas nodo, int llave) {
        if (nodo.esHoja()) {
            nodo.insertarEnHoja(llave);
            if (nodo.getLlaves().size() > orden - 1) {
                nodo.dividir(orden);
  
            }
        } else {
            int i = 0;
            List<Integer> ks = nodo.getLlaves();
            while (i < ks.size() && llave >= ks.get(i)) i++;
            NodoArbolBMas hijo = nodo.getHijos().get(i);

            if (hijo.getLlaves().size() == orden - 1) {
                dividirHijo(nodo, i);
                if (llave >= nodo.getLlaves().get(i)) i++;
            }
            insertarNoLleno(nodo.getHijos().get(i), llave);
        }
    }

    private void dividirHijo(NodoArbolBMas padre, int indice) {
        NodoArbolBMas hijo = padre.getHijos().get(indice);
        hijo.dividir(orden);

        Integer promovida = hijo.clavePromovida;
        NodoArbolBMas derecho = hijo.nuevoDerecho;

        // Insertar promovida y puntero derecho en el padre
        int pos = 0;
        List<Integer> ks = padre.getLlaves();
        while (pos < ks.size() && promovida > ks.get(pos)) pos++;

        padre.getLlaves().add(pos, promovida);
        padre.getHijos().add(pos + 1, derecho);
    }


    private boolean buscarNodo(NodoArbolBMas nodo, int llave) {
        int i = 0;
        List<Integer> ks = nodo.getLlaves();
        while (i < ks.size() && llave > ks.get(i)) i++;

        if (nodo.esHoja()) {
            return (i < ks.size() && llave == ks.get(i));
        } else {
            return buscarNodo(nodo.getHijos().get(i), llave);
        }
    }

    private NodoArbolBMas buscarHoja(NodoArbolBMas nodo, int clave) {
        while (!nodo.esHoja()) {
            int i = 0;
            List<Integer> ks = nodo.getLlaves();
            while (i < ks.size() && clave >= ks.get(i)) i++;
            nodo = nodo.getHijos().get(i);
        }
        return nodo;
    }


    private void imprimirNodo(NodoArbolBMas nodo, String indentacion, boolean esUltimo) {
        System.out.println(indentacion + "+- " + (nodo.esHoja() ? "Hoja" : "Interno") + " " + nodo.getLlaves());
        String pref = indentacion + (esUltimo ? "   " : "|  ");
        if (!nodo.esHoja()) {
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                imprimirNodo(nodo.getHijos().get(i), pref, i == nodo.getHijos().size() - 1);
            }
        }
    }
}
