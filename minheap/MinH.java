package minheap;

public class MinH {

    private int[] heap;
    private int size;
    private int capacity;

    public MinH(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    private int padre(int i) {
        return (i - 1) / 2;
    }

    private int hijoIzq(int i) {
        return 2 * i + 1;
    }

    private int hijoDer(int i) {
        return 2 * i + 2;
    }

    public boolean estaVacio() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void insertar(int valor) {
        if (size == capacity) {
            throw new IllegalStateException("El montículo está lleno.");
        }
        heap[size] = valor;
        upHeapify(size);
        size++;
    }

    private void upHeapify(int i) {
        while (i > 0 && heap[i] < heap[padre(i)]) {
            intercambiar(i, padre(i));
            i = padre(i);
        }
    }

    public int peek() {
        if (estaVacio()) {
            throw new IllegalStateException("El montículo está vacío.");
        }
        return heap[0];
    }

    public int eliminarMin() {
        if (estaVacio()) {
            throw new IllegalStateException("El montículo está vacío.");
        }
        int minimo = heap[0];
        heap[0] = heap[size - 1];
        size--;
        downHeapify(0);
        return minimo;
    }

    private void downHeapify(int i) {
        while (true) {
            int izq = hijoIzq(i);
            int der = hijoDer(i);
            int masPeq = i;

            if (izq < size && heap[izq] < heap[masPeq]) masPeq = izq;
            if (der < size && heap[der] < heap[masPeq]) masPeq = der;

            if (masPeq == i) break;

            intercambiar(i, masPeq);
            i = masPeq;
        }
    }

    public void heapify(int[] arreglo) {
        if (arreglo.length > capacity) {
            capacity = arreglo.length;
            heap = new int[capacity];
        }

        for (int i = 0; i < arreglo.length; i++) {
            heap[i] = arreglo[i];
        }
        size = arreglo.length;

        for (int i = (size / 2) - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    private void intercambiar(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void imprimirHeap() {
        System.out.print("Contenido del montículo: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
