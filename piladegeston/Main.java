import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PilaDinamica pila = new PilaDinamica();

        // Regla 1: insertar main() como base
        List<Parametro> parametrosMain = new ArrayList<>();
        parametrosMain.add(new Parametro(TipoDato.STRING, "args"));
        Llamada main = new Llamada(TipoDato.VOID, "main", parametrosMain);
        pila.push(main);
        
        System.out.println("Se ha creado la funcion base 'main()'.");
        System.out.println("Recuerda:");
        System.out.println(" - La funcion main() siempre estara en la base de la pila.");
        System.out.println(" - Puedes agregar nuevas funciones o finalizar las activas.\n");

        int opcion = -1;

        while (opcion != 0) {
            System.out.println("==============================================");
            System.out.println("                   MENU                      ");
            System.out.println("==============================================");
            System.out.println("1) Llamar una nueva funcion (push)");
            System.out.println("2) Finalizar la funcion actual (pop)");
            System.out.println("3) Ver la funcion actual (peek)");
            System.out.println("4) Mostrar toda la pila de llamadas");
            System.out.println("0) Salir del simulador");
            System.out.println("==============================================");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Entrada invalida. Ingrese un numero del menu.\n");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- NUEVA LLAMADA DE FUNCION ---");
                    System.out.println("Ejemplo: int sumar(int a, int b)\n");

                    System.out.println("Tipos de retorno disponibles: VOID, INT, DOUBLE, STRING, BOOLEAN");
                    System.out.print("Ingrese el tipo de retorno de la funcion: ");
                    String tipoTexto = sc.nextLine().trim().toUpperCase();

                    TipoDato tipo;
                    try {
                        tipo = TipoDato.valueOf(tipoTexto);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo no reconocido. Se asignara VOID por defecto.");
                        tipo = TipoDato.VOID;
                    }

                    System.out.print("Ingrese el nombre de la funcion (ejemplo: sumar, mostrarMenu, etc): ");
                    String nombre = sc.nextLine().trim();

                    System.out.print("Ingrese la cantidad de parametros: ");
                    int cantidad = 0;
                    try {
                        cantidad = Integer.parseInt(sc.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Valor invalido. Se asumira que no tiene parametros.");
                    }

                    List<Parametro> parametros = new ArrayList<>();
                    for (int i = 0; i < cantidad; i++) {
                        System.out.println("\nParametro #" + (i + 1));
                        System.out.print("Tipo del parametro (VOID, INT, DOUBLE, STRING, BOOLEAN): ");
                        String tipoParam = sc.nextLine().trim().toUpperCase();

                        TipoDato tipoParametro;
                        try {
                            tipoParametro = TipoDato.valueOf(tipoParam);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tipo no valido. Se asignara STRING por defecto.");
                            tipoParametro = TipoDato.STRING;
                        }

                        System.out.print("Nombre del parametro: ");
                        String nombreParam = sc.nextLine().trim();

                        parametros.add(new Parametro(tipoParametro, nombreParam));
                    }

                    Llamada nueva = new Llamada(tipo, nombre, parametros);
                    pila.push(nueva);
                    System.out.println("\n>>> Funcion llamada con exito <<<");
                    System.out.println("Nueva funcion activa: " + nueva + "\n");
                    break;

                case 2:
                    System.out.println("\n--- FINALIZAR FUNCION ACTUAL ---");
                    if (pila.size() == 1) {
                        System.out.println("No se puede finalizar la funcion main().");
                        break;
                    }
                    Llamada eliminada = pila.pop();
                    if (eliminada != null) {
                        System.out.println("Funcion finalizada correctamente: " + eliminada);
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("\n--- FUNCION ACTUAL ---");
                    Llamada actual = pila.peek();
                    if (actual != null) {
                        System.out.println("Funcion en ejecucion: " + actual);
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println("\n--- PILA DE LLAMADAS ACTUAL ---");
                    pila.mostrarPila();
                    System.out.println();
                    break;

                case 0:
                    System.out.println("\nSaliendo del simulador de pila de llamadas...");
                    System.out.println("Gracias por utilizar el programa.");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente nuevamente.\n");
                    break;
            }
        }

        sc.close();
    }
}
