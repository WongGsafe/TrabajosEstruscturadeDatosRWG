import java.util.ArrayList;
import java.util.List;

public class NodoArbolBMas {
    private final boolean esHoja;
    private List<Integer> llaves;
    private List<NodoArbolBMas> hijos; 
    NodoArbolBMas siguiente;         

    
    Integer clavePromovida;           
    NodoArbolBMas nuevoDerecho;      

    public NodoArbolBMas(boolean esHoja) {
        this.esHoja = esHoja;
        this.llaves = new ArrayList<>();
        this.hijos  = new ArrayList<>();
        this.siguiente = null;
    }

    public boolean esHoja() { return esHoja; }
    public List<Integer> getLlaves() { return llaves; }
    public List<NodoArbolBMas> getHijos() { return hijos; }

    public void setLlaves(List<Integer> llaves) { this.llaves = llaves; }
    public void setHijos(List<NodoArbolBMas> hijos) { this.hijos = hijos; }

    
    void insertarEnHoja(int clave) {
        int i = 0;
        while (i < llaves.size() && llaves.get(i) < clave) i++;
        if (i < llaves.size() && llaves.get(i) == clave) return; 
        llaves.add(i, clave);
    }

    void dividir(int orden) {
        int maxClaves = orden - 1;
        if (llaves.size() <= maxClaves) return;

        int mitad = (orden / 2);

        if (esHoja) {
            NodoArbolBMas derecho = new NodoArbolBMas(true);
            for (int i = mitad; i < llaves.size(); i++) {
                derecho.llaves.add(llaves.get(i));
            }
            while (llaves.size() > mitad) llaves.remove(llaves.size() - 1);
            derecho.siguiente = this.siguiente;
            this.siguiente = derecho;

            this.clavePromovida = derecho.llaves.get(0);
            this.nuevoDerecho = derecho;

        } else {
            int idxMediana = mitad;
            Integer mediana = llaves.get(idxMediana);

            NodoArbolBMas derecho = new NodoArbolBMas(false);

            for (int i = idxMediana + 1; i < llaves.size(); i++) {
                derecho.llaves.add(llaves.get(i));
            }
            for (int i = idxMediana + 1; i < hijos.size(); i++) {
                derecho.hijos.add(hijos.get(i));
            }

            while (llaves.size() > idxMediana) llaves.remove(llaves.size() - 1);
            while (hijos.size() > idxMediana + 1) hijos.remove(hijos.size() - 1);

            this.clavePromovida = mediana;
            this.nuevoDerecho = derecho;
        }
    }
}
