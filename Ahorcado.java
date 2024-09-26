import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        Random random = new Random();
        
        // Obtener la lista de palabras posibles para el juego
        String[] palabras = obtenerPalabras();
        // Elegir una palabra aleatoria de la lista
        String palabra = palabras[random.nextInt(palabras.length)];
        
        // Comenzar el juego con la palabra seleccionada
        jugar(t, palabra);
        t.close();
    }

    // Método para obtener la lista de palabras
    private static String[] obtenerPalabras() {
        return new String[] {
            "manzana", "platano", "naranja", "fresa", "uva", "mango", "piña", "kiwi", "melon",
            "sandia", "pera", "durazno", "cereza", "papaya", "limon", "frambuesa", "arandano", "maracuya",
            "granada", "coco", "sol", "luz", "mar", "cielo", "flor", "río", "nube", "árbol", "hoja", "pájaro",
            "viento", "estrella", "noche", "día", "montaña", "camino", "agua", "tierra", "fuego", "aire", "piedra",
            "arena", "playa", "ciudad", "puente", "campo", "bosque", "valle", "lago", "isla", "costa", 
            "barco", "puerto", "gaviota", "desierto", "cactus", "huracán", "tornado", "lluvia", "nieve", 
            "frío", "calor", "verano", "invierno", "otoño", "primavera", "jardín", "semilla", "fruta", "flor", 
            "cereza", "naranja", "limón", "manzana", "pera", "uva", "melón", "sándalo", "musgo", "bambú", 
            "pino", "roble", "sauce", "ciprés", "cedro", "helecho", "margarita", "rosa", "tulipán", "girasol", 
            "lavanda", "cactus", "tormenta", "bruma", "rocío", "escarcha", "trigo", "maíz", "cebada", "algodón", 
            "pasto", "mulch", "brote", "hongo", "seta", "pino", "baya", "fresno", "mangle", "marisma", 
            "pantano", "juncal", "charca", "fresno", "roble", "aliso", "arce", "álamo", "sauce", "viña", 
            "alga", "coral", "concha", "perla", "duna", "olas", "marino", "medusa", "ballena", "delfín", 
            "tiburón", "pez", "ostra", "caracol", "langosta", "albatros", "pingüino", "pelícano", "fragata", 
            "pato", "cisne", "ganso", "búho", "águila", "halcón", "lince", "zorro", "lobo", "ciervo", 
            "oso", "ratón", "zorrillo", "mapache", "jaguar", "pantera", "león", "tigre", "elefante", 
            "jirafa", "camello", "cebra", "cabra", "oveja", "vaca", "cerdo", "gallina", "gallo", 
            "perro", "gato", "caballo", "burro", "murciélago", "luciérnaga", "mariposa", "polilla", 
            "escarabajo", "hormiga", "araña", "avispa", "abeja", "libélula", "saltamontes", "grillo", 
            "cigarra", "oruga", "gusano", "sanguijuela", "pulga", "piojo", "cucaracha", "mosquito", 
            "mosca", "ciempies", "caracol", "babosa", "lombriz", "foca", "lobo", "foca", "morsa", 
            "tortuga", "iguana", "serpiente", "cocodrilo", "lagarto", "camaleón"
        };
    }

    // Método para jugar el juego
    private static void jugar(Scanner t, String palabra) {
        boolean[] letrasAdivinadas = new boolean[palabra.length()]; // Arreglo para las letras adivinadas
        int contador = 0; // Contador de intentos fallidos

        // Bucle principal del juego que permite hasta 6 intentos
        while (contador < 6) {
            mostrarMonigote(contador); // Mostrar el estado del monigote
            mostrarEstadoJuego(palabra, letrasAdivinadas); // Mostrar la palabra y las letras adivinadas
            System.out.print("Adivina una letra (SOLO UNA LETRA Y MINUSCULA!!): ");
            char letra = t.nextLine().charAt(0); // Leer la letra introducida por el usuario

            // Comprobar si la letra está en la palabra
            if (comprobarLetra(palabra, letra, letrasAdivinadas)) {
                System.out.println("Correcto!"); // Si la letra está, mostrar mensaje de acierto
            } else {
                contador++; // Incrementar contador si la letra no está
                System.out.println("Incorrecto. Te quedan " + (6 - contador) + " intentos."); // Mensaje de error
            }

            // Comprobar si se adivinaron todas las letras
            if (comprobarVictoria(letrasAdivinadas)) {
                System.out.println("Felicidades! adivinaste la palabra: " + palabra);
                return; // Salir del juego si se adivina la palabra
            }
        }

        // Mostrar el monigote completo y el mensaje de pérdida
        mostrarMonigote(6);
        System.out.println("Perdiste... :( La palabra era: " + palabra);
    }

    // Método para mostrar el monigote según el número de intentos fallidos
    private static void mostrarMonigote(int contador) {
        System.out.println("  ******");
        System.out.println("  *    *");
        if (contador >= 1) System.out.println("  *    *"); // Cabeza
        else System.out.println("  *");

        if (contador >= 2) {
            if (contador >= 3) System.out.println("  *   ***"); // Cuerpo completo con brazos
            else System.out.println("  *   **"); // Cuerpo sin un brazo
        } else System.out.println("  *");

        if (contador >= 4) {
            if (contador >= 5) System.out.println("  *   * *"); // Piernas completas
            else System.out.println("  *   *"); // Solo una pierna
        } else System.out.println("  *");

        System.out.println("  *");
        System.out.println("*******");
        System.out.println();
    }

    // Método para mostrar el estado actual del juego
    private static void mostrarEstadoJuego(String palabra, boolean[] letrasAdivinadas) {
        StringBuilder resultado = new StringBuilder(); // StringBuilder para construir el estado
        for (int i = 0; i < palabra.length(); i++) {
            if (letrasAdivinadas[i]) {
                resultado.append(palabra.charAt(i)); // Agregar la letra si fue adivinada
            } else {
                resultado.append('_'); // Mostrar un guion bajo si no fue adivinada
            }
            resultado.append(' '); // Espacio entre letras
        }
        System.out.println("Palabra: " + resultado); // Mostrar el estado de la palabra
    }

    // Método para comprobar si la letra adivinada está en la palabra
    private static boolean comprobarLetra(String palabra, char letra, boolean[] letrasAdivinadas) {
        boolean acierto = false; // Variable para verificar si se acertó
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                letrasAdivinadas[i] = true; // Marcar la letra como adivinada
                acierto = true; // Se encontró la letra
            }
        }
        return acierto; // Retornar si hubo acierto o no
    }


    // Método para comprobar si se ha adivinado la palabra
    private static boolean comprobarVictoria(boolean[] letrasAdivinadas) {
        for (boolean adivinada : letrasAdivinadas) {
            if (!adivinada) {
                return false; // Si alguna letra no fue adivinada, retornar falso
            }
        }
        return true;
    }
}