package SegCE2.src;
public class NodoAVL {

    // Atributos
    private int llave;
    private NodoAVL hijoIzquierdo;
    private NodoAVL hijoDerecho;
    private int altura;

    // Constructor
    public NodoAVL(int llave) {
        this.llave = llave;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.altura = 1; // Una hoja tiene altura 1
    }

    // Getters
    public int getLlave() {
        return llave;
    }

    public NodoAVL getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoAVL getHijoDerecho() {
        return hijoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    // Setters
    public void setHijoIzquierdo(NodoAVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(NodoAVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    // Altura de un nodo
    public int evaluarAltura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.getAltura();
    }

    // Factor de balance
    public int evaluarBalance(NodoAVL nodo) {
        if (nodo == null) return 0;
        return evaluarAltura(nodo.getHijoIzquierdo()) - evaluarAltura(nodo.getHijoDerecho());
    }

    public int balancear() {
        return evaluarBalance(this);
    }

    // Actualiza la altura del nodo
    public void actualizarAltura() {
        this.altura = Math.max(evaluarAltura(hijoIzquierdo), evaluarAltura(hijoDerecho)) + 1;
    }

    // Rotacion simple izquierda
    public NodoAVL rotarIzquierda(NodoAVL nodo) {
        if (nodo == null || nodo.getHijoDerecho() == null) return nodo;

        NodoAVL nuevaRaiz = nodo.getHijoDerecho();
        NodoAVL sub = nuevaRaiz.getHijoIzquierdo();

        nuevaRaiz.setHijoIzquierdo(nodo);
        nodo.setHijoDerecho(sub);

        nodo.actualizarAltura();
        nuevaRaiz.actualizarAltura();

        return nuevaRaiz;
    }

    // Rotacion simple derecha
    public NodoAVL rotarDerecha(NodoAVL nodo) {
        if (nodo == null || nodo.getHijoIzquierdo() == null) return nodo;

        NodoAVL nuevaRaiz = nodo.getHijoIzquierdo();
        NodoAVL sub = nuevaRaiz.getHijoDerecho();

        nuevaRaiz.setHijoDerecho(nodo);
        nodo.setHijoIzquierdo(sub);

        nodo.actualizarAltura();
        nuevaRaiz.actualizarAltura();

        return nuevaRaiz;
    }

    // Rotacion doble izquierda-derecha
    public NodoAVL rotarIzquierdaDerecha(NodoAVL nodo) {
        nodo.setHijoIzquierdo(rotarIzquierda(nodo.getHijoIzquierdo()));
        return rotarDerecha(nodo);
    }

    // Rotacion doble derecha-izquierda
    public NodoAVL rotarDerechaIzquierda(NodoAVL nodo) {
        nodo.setHijoDerecho(rotarDerecha(nodo.getHijoDerecho()));
        return rotarIzquierda(nodo);
    }
}
