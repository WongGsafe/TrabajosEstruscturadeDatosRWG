public class Main {
    public static void main(String[] args) {
        ArbolBMas arbol = new ArbolBMas(4);

        // Demo rápida (mismas claves que tu ejemplo)
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.insertar(6);
        arbol.insertar(15);
        arbol.insertar(30);
        arbol.insertar(25);

        System.out.println("Árbol:");
        arbol.imprimirArbol();

        System.out.println("\nBuscar 15: " + arbol.buscar(15));
        System.out.println("Buscar 99: " + arbol.buscar(99));

        System.out.println("\nRecorrer desde 6 (5 elementos):");
        arbol.recorrerDesde(6, 5);
    }
}
