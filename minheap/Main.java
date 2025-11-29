package minheap;

import java.util.Scanner;
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Programa de prueba para MinHeap");
        System.out.print("Ingrese la capacidad max del monticulo: ");
        int capacidad = leerEnteroPositivo();
        MinH heap = new MinH(capacidad);
        menu(heap);
        System.out.println("Programa finalizado.");
    }

    public static void menu(MinH heap) {
        int opcion;
        do {
            System.out.println("menu");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Eliminar min");
            System.out.println("3. Ver min");
            System.out.println("4. Heapify arreglo");
            System.out.println("5. Imprimir monticulo");
            System.out.println("6. Salir");
            System.out.print("seleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el valor a insertar: ");
                    int valor = leerEntero();
                    try {
                        heap.insertar(valor);
                        System.out.println("Insertado.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        int minimo = heap.eliminarMin();
                        System.out.println("Eliminado: " + minimo);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        int min = heap.peek();
                        System.out.println("Min actual: " + min);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Cantidad de elementos del arreglo: ");
                    int n = leerEnteroPositivo();
                    int[] arr = new int[n];
                    for (int i = 0; i < n; i++) {
                        System.out.print("Elemento [" + i + "]: ");
                        arr[i] = leerEntero();
                    }
                    heap.heapify(arr);
                    System.out.println("Heapify realizado.");
                    break;

                case 5:
                    heap.imprimirHeap();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Opción inv.");
            }

        } while (opcion != 6);
    }

    private static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un entero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static int leerEnteroPositivo() {
        int num;
        do {
            num = leerEntero();
            if (num <= 0) System.out.print("tiene que ser mayor a 0: ");
        } while (num <= 0);
        return num;
    }
}
