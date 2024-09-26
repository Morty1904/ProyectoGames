import java.util.Random;
import java.util.Scanner;

public class Sudoku {
    int[][] solucion; // Matriz que contiene la solución del Sudoku
    int[][] tablero; // Matriz que representa el tablero del Sudoku en juego

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario
        GenerarSudoku generar = new GenerarSudoku(); // Instancia de la clase que genera el Sudoku
        int[][] tableroSudoku = generar.getTablero(); // Obtiene el tablero inicial
        int[][] solucion = generar.getSolucion(); // Obtiene la solución del Sudoku

        // Bucle principal para la interacción con el usuario
        while (true) {
            imprimirTablero(tableroSudoku); // Imprime el tablero actual
            System.out.println("Ingrese fila, columna y número (ejemplo: 0 1 5) o -1 para rendirse:");
            String entrada = scanner.nextLine(); // Lee la entrada del usuario

            // Si el usuario se rinde, muestra la solución y termina el juego
            if (entrada.equals("-1")) {
                System.out.println("Solución:");
                imprimirTablero(solucion);
                break;
            }

            String[] partes = entrada.split(" "); // Divide la entrada en partes
            int f = Integer.parseInt(partes[0]); // Fila ingresada por el usuario
            int c = Integer.parseInt(partes[1]); // Columna ingresada por el usuario
            int num = Integer.parseInt(partes[2]); // Número ingresado por el usuario

            // Valida si el movimiento es permitido
            if (esValido(tableroSudoku, f, c, num)) {
                tableroSudoku[f][c] = num; // Coloca el número en el tablero
                // Verifica si el tablero está completo
                if (estaCompleto(tableroSudoku)) {
                    System.out.println("¡Felicidades! Has completado el Sudoku.");
                    imprimirTablero(tableroSudoku); // Imprime el tablero final
                    break;
                }
            } else {
                System.out.println("Movimiento inválido. Intenta de nuevo."); // Mensaje de error
            }
        }
    }

    // Imprime el tablero de Sudoku en un formato legible
    public static void imprimirTablero(int[][] tablero) {
        for (int f = 0; f < 9; f++) {
            // Imprime líneas divisorias cada 3 filas
            if (f % 3 == 0 && f != 0) {
                System.out.println("------+-------+------");
            }
            for (int c = 0; c < 9; c++) {
                // Imprime líneas divisorias cada 3 columnas
                if (c % 3 == 0 && c != 0) {
                    System.out.print(" | ");
                }
                // Imprime el número o un punto si la casilla está vacía
                System.out.print(tablero[f][c] == 0 ? "." : tablero[f][c]);
                if (c != 8) {
                    System.out.print(" "); // Espacio entre columnas
                }
            }
            System.out.println(); // Salto de línea al final de la fila
        }
    }

    // Verifica si un número puede ser colocado en una posición específica
    public static boolean esValido(int[][] tablero, int f, int c, int num) {
        // Verifica la fila
        for (int x = 0; x < 9; x++) {
            if (tablero[f][x] == num) {
                return false; // Número ya existe en la fila
            }
        }
        // Verifica la columna
        for (int x = 0; x < 9; x++) {
            if (tablero[x][c] == num) {
                return false; // Número ya existe en la columna
            }
        }
        // Verifica el cuadrante 3x3
        int fInicio = f - f % 3, cInicio = c - c % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i + fInicio][j + cInicio] == num) {
                    return false; // Número ya existe en el cuadrante
                }
            }
        }
        return true; // El número es válido
    }

    // Verifica si el tablero está completo
    public static boolean estaCompleto(int[][] tablero) {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (tablero[f][c] == 0) {
                    return false; // Hay al menos una casilla vacía
                }
            }
        }
        return true; // El tablero está completo
    }
}

class GenerarSudoku {
    private int[][] solucion; // Matriz que contiene la solución del Sudoku
    private int[][] tablero; // Matriz que representa el tablero inicial del Sudoku

    public GenerarSudoku() {
        solucion = new int[9][9]; // Inicializa la matriz de solución
        tablero = new int[9][9]; // Inicializa la matriz del tablero
        generarSudoku(); // Genera un Sudoku válido
        eliminarNumeros(); // Elimina algunos números para crear el tablero jugable
    }

    // Genera un Sudoku completo
    private void generarSudoku() {
        llenarTablero(solucion); // Llena la matriz de solución
        // Copia la solución al tablero inicial
        for (int f = 0; f < 9; f++) {
            System.arraycopy(solucion[f], 0, tablero[f], 0, 9);
        }
    }

    // Llena el tablero de manera recursiva
    private boolean llenarTablero(int[][] tablero) {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                // Busca una casilla vacía
                if (tablero[f][c] == 0) {
                    Random random = new Random(); // Generador de números aleatorios
                    // Intenta colocar un número del 1 al 9
                    for (int num = 1; num <= 9; num++) {
                        int n = random.nextInt(9) + 1; // Número aleatorio entre 1 y 9
                        if (esValido(tablero, f, c, n)) {
                            tablero[f][c] = n; // Coloca el número en la casilla
                            // Recursivamente intenta llenar el resto del tablero
                            if (llenarTablero(tablero)) {
                                return true; // Si se llena con éxito, retorna verdadero
                            }
                            tablero[f][c] = 0; // Deshace el movimiento si no funciona
                        }
                    }
                    return false; // Si no se pudo colocar un número, retorna falso
                }
            }
        }
        return true; // El tablero está lleno
    }

    // Verifica si un número puede ser colocado en una posición específica (método
    // redundante)
    private boolean esValido(int[][] tablero, int f, int c, int num) {
        for (int x = 0; x < 9; x++) {
            if (tablero[f][x] == num) {
                return false; // Número ya existe en la fila
            }
        }
        for (int x = 0; x < 9; x++) {
            if (tablero[x][c] == num) {
                return false; // Número ya existe en la columna
            }
        }
        int fInicio = f - f % 3, cInicio = c - c % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i + fInicio][j + cInicio] == num) {
                    return false; // Número ya existe en el cuadrante
                }
            }
        }
        return true; // El número es válido
    }

    // Elimina números aleatorios del tablero para crear un Sudoku jugable
    private void eliminarNumeros() {
        Random random = new Random(); // Generador de números aleatorios
        int cont = 40; // Cantidad de números a eliminar
        while (cont != 0) {
            int f = random.nextInt(9); // Fila aleatoria
            int c = random.nextInt(9); // Columna aleatoria
            if (tablero[f][c] != 0) {
                tablero[f][c] = 0; // Elimina el número
                cont--; // Disminuye el contador
            }
        }
    }

    // Método que retorna el tablero actual
    public int[][] getTablero() {
        return tablero;
    }

    // Método que retorna la solución del Sudoku
    public int[][] getSolucion() {
        return solucion;
    }
}