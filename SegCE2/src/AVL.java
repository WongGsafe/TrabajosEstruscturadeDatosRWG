package SegCE2.src;
import java.util.Scanner;

public class AVL {

    private NodoAVL raiz;

    public AVL() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // Buscar un nodo
    public NodoAVL buscar(int llave) {
        NodoAVL actual = raiz;
        while (actual != null && actual.getLlave() != llave) {
            if (llave < actual.getLlave()) {
                actual = actual.getHijoIzquierdo();
            } else {
                actual = actual.getHijoDerecho();
            }
        }
        return actual;
    }

    // Insertar un valor
    public void insertar(int llave) {
        raiz = insertar(raiz, llave);
    }

    private NodoAVL insertar(NodoAVL nodo, int llave) {
        if (nodo == null) return new NodoAVL(llave);

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(insertar(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(insertar(nodo.getHijoDerecho(), llave));
        } else {
            return nodo; // Valor repetido
        }

        nodo.actualizarAltura();
        return balancearNodo(nodo);
    }

    // Eliminar un nodo
    public void eliminar(int llave) {
        raiz = eliminar(raiz, llave);
    }

    private NodoAVL eliminar(NodoAVL nodo, int llave) {
        if (nodo == null) return null;

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(eliminar(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(eliminar(nodo.getHijoDerecho(), llave));
        } else {
            if (nodo.getHijoIzquierdo() == null) return nodo.getHijoDerecho();
            if (nodo.getHijoDerecho() == null) return nodo.getHijoIzquierdo();

            NodoAVL sucesor = getSucesor(nodo);
            sucesor.setHijoIzquierdo(nodo.getHijoIzquierdo());
            nodo = sucesor;
        }

        nodo.actualizarAltura();
        return balancearNodo(nodo);
    }

    // Balance general
    private NodoAVL balancearNodo(NodoAVL nodo) {
        int bf = nodo.balancear();

        if (bf > 1) {
            if (nodo.getHijoIzquierdo().balancear() >= 0) {
                return nodo.rotarDerecha(nodo);  // LL
            } else {
                return nodo.rotarIzquierdaDerecha(nodo); // LR
            }
        }

        if (bf < -1) {
            if (nodo.getHijoDerecho().balancear() <= 0) {
                return nodo.rotarIzquierda(nodo); // RR
            } else {
                return nodo.rotarDerechaIzquierda(nodo); // RL
            }
        }

        return nodo;
    }

    // Sucesor del nodo eliminado
    private NodoAVL getSucesor(NodoAVL nodo) {
        NodoAVL padre = nodo;
        NodoAVL sucesor = nodo;
        NodoAVL actual = nodo.getHijoDerecho();

        while (actual != null) {
            padre = sucesor;
            sucesor = actual;
            actual = actual.getHijoIzquierdo();
        }

        if (sucesor != nodo.getHijoDerecho()) {
            padre.setHijoIzquierdo(sucesor.getHijoDerecho());
            sucesor.setHijoDerecho(nodo.getHijoDerecho());
        }

        return sucesor;
    }

    // Impresion en orden
    public void mostrarEnOrden() {
        enOrden(raiz);
        System.out.println();
    }

    private void enOrden(NodoAVL nodo) {
        if (nodo != null) {
            enOrden(nodo.getHijoIzquierdo());
            System.out.print(nodo.getLlave() + " ");
            enOrden(nodo.getHijoDerecho());
        }
    }

    // Menu principal
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AVL arbol = new AVL();
        int opcion;

        do {
            System.out.println("\nMenu AVL");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Eliminar");
            System.out.println("4. Mostrar en orden");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Digite la llave: ");
                    arbol.insertar(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Digite la llave: ");
                    NodoAVL n = arbol.buscar(sc.nextInt());
                    if (n != null) System.out.println("Encontrado");
                    else System.out.println("No encontrado");
                    break;

                case 3:
                    System.out.print("Digite la llave: ");
                    arbol.eliminar(sc.nextInt());
                    break;

                case 4:
                    arbol.mostrarEnOrden();
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 5);

        sc.close();
    }
}
