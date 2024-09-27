import java.util.Scanner;

public class Conecta4 {
    // Constantes para definir el tamaño del tablero
    private static final int F = 6; // Número de filas
    private static final int C = 7; // Número de columnas
    private static char[][] tablero = new char[F][C]; // Matriz que representa el tablero de juego
    private static char jugadorActual = 'X'; // El jugador que tiene el turno actual ('X' o 'O')

    public static void main(String[] args) {
        inicializarTablero(); // Inicializa el tablero vacío
        boolean juegoTerminado = false; // flag para saber si el juego ha terminado
        Scanner scanner = new Scanner(System.in);

        // Bucle principal del juego
        while (!juegoTerminado) {
            mostrarTablero(); // Muestra el tablero en la consola
            System.out.println("Turno del jugador " + jugadorActual);
            System.out.println("Elige una columna (1-7): ");
            int columna = scanner.nextInt() - 1; // Obtiene la columna elegida por el jugador

            // Verifica que la columna elegida es válida
            if (columna >= 0 && columna < C) {
                if (colocarFicha(columna)) { // Intenta colocar la ficha en la columna elegida
                    if (hayGanador()) { // Verifica si el jugador actual ha ganado
                        mostrarTablero();
                        System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                        juegoTerminado = true; // Termina el juego si hay un ganador
                    } else if (tableroLleno()) { // Verifica si el tablero está lleno y no hay más movimientos
                        mostrarTablero();
                        System.out.println("¡Empate! El tablero está lleno.");
                        juegoTerminado = true; // Termina el juego si hay empate
                    } else {
                        cambiarTurno(); // Cambia al siguiente jugador
                    }
                } else {
                    System.out.println("Columna llena. Elige otra.");
                }
            } else {
                System.out.println("Columna no válida. Intenta de nuevo.");
            }
        }
        System.out.println("¿Quieres jugar otra vez? (s/n): ");
        char respuesta = scanner.next().charAt(0); // Permite reiniciar el juego si el jugador lo desea
        if (respuesta == 's') {
            reiniciarJuego(); // Reinicia el juego
        }
        scanner.close();
    }

    // Inicializa el tablero llenando todas las posiciones con el caracter '-'
    private static void inicializarTablero() {
        for (int i = 0; i < F; i++) {
            for (int j = 0; j < C; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    // Muestra el tablero de juego en la consola
    private static void mostrarTablero() {
        for (int i = 0; i < F; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println(); // Nueva línea para separar las filas del tablero
        }
        System.out.println("1 2 3 4 5 6 7"); // Muestra los números de las columnas
    }

    // Coloca la ficha del jugador actual en la columna elegida, si es posible
    private static boolean colocarFicha(int columna) {
        for (int i = F - 1; i >= 0; i--) {
            if (tablero[i][columna] == '-') { // Encuentra la primera posición vacía en la columna
                tablero[i][columna] = jugadorActual; // Coloca la ficha del jugador
                return true; // Indica que la ficha se colocó correctamente
            }
        }
        return false; // Si la columna está llena, devuelve false
    }

    // Cambia el turno al otro jugador
    private static void cambiarTurno() {
        if (jugadorActual == 'X') {
            jugadorActual = 'O';
        } else {
            jugadorActual = 'X';
        }
    }

    /*
     * El código verifica si las fichas en cuatro posiciones consecutivas en la
     * misma fila, columna o diagonal
     * son iguales al jugadorActual. Si todas son iguales, significa que hay 4
     * fichas consecutivas del jugador actual
     * en una fila, y entonces retorna true.
     */

    private static boolean hayGanador() {
        // Verificación horizontal
        for (int i = 0; i < F; i++) {
            for (int j = 0; j < C - 3; j++) {
                if (tablero[i][j] == jugadorActual && tablero[i][j + 1] == jugadorActual
                        && tablero[i][j + 2] == jugadorActual && tablero[i][j + 3] == jugadorActual) {
                    return true; // Hay 4 en línea horizontal
                }
            }
        }

        // Verificación vertical
        for (int i = 0; i < F - 3; i++) {
            for (int j = 0; j < C; j++) {
                if (tablero[i][j] == jugadorActual && tablero[i + 1][j] == jugadorActual
                        && tablero[i + 2][j] == jugadorActual && tablero[i + 3][j] == jugadorActual) {
                    return true; // Hay 4 en línea vertical
                }
            }
        }

        /*
         * Aquí se busca una secuencia diagonal donde la ficha del jugador esté en la
         * posición (i, j),
         * y las siguientes fichas esten en posiciones que avanzan tanto en las filas
         * como en columnas (i+1, j+1, etc.).
         * Si se encuentran 4 en esta dirección, el jugador ha ganado con una diagonal
         * de izquierda a derecha.
         */

        // (de izquierda a derecha)
        for (int i = 0; i < F - 3; i++) {
            for (int j = 0; j < C - 3; j++) {
                if (tablero[i][j] == jugadorActual && tablero[i + 1][j + 1] == jugadorActual
                        && tablero[i + 2][j + 2] == jugadorActual && tablero[i + 3][j + 3] == jugadorActual) {
                    return true; // Hay 4 en línea diagonal (de izquierda a derecha)
                }
            }
        }

        // (de derecha a izquierda)
        for (int i = 0; i < F - 3; i++) {
            for (int j = 3; j < C; j++) {
                if (tablero[i][j] == jugadorActual && tablero[i + 1][j - 1] == jugadorActual
                        && tablero[i + 2][j - 2] == jugadorActual && tablero[i + 3][j - 3] == jugadorActual) {
                    return true; // Hay 4 en línea diagonal (de derecha a izquierda)
                }
            }
        }

        return false; // Si no se encuentran 4 en línea, no hay ganador
    }

    // Verifica si el tablero está lleno
    private static boolean tableroLleno() {
        for (int i = 0; i < C; i++) {
            if (tablero[0][i] == '-') {
                return false; // Hay al menos una columna con espacio disponible
            }
        }
        return true; // Todas las posiciones están llenas
    }

    // Reinicia el tablero y vuelve a empezar el juego
    private static void reiniciarJuego() {
        inicializarTablero(); // Vuelve a vaciar el tablero
        jugadorActual = 'X'; // Restablece el jugador inicial
        main(null); // Reinicia el juego desde el inicio
    }
}