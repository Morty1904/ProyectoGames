import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        Random random = new Random();
        // puse una lista de frutas porque son palabras típicas de este juego
        String[] Palabras = { "manzana", "platano", "naranja", "fresa", "uva", "mango", "piña", "kiwi", "melon",
                "sandia", "pera", "durazno", "cereza", "papaya", "limon", "frambuesa", "arandano", "maracuya",
                "granada", "coco", "sol", "luz", "mar", "cielo", "flor", "río", "nube", "árbol", "hoja", "pájaro",
                "viento", "estrella", "noche", "día", "montaña", "camino", "agua", "tierra", "fuego", "aire", "piedra",
                "arena", "playa",
                "ciudad", "puente", "campo", "bosque", "valle", "lago", "isla", "costa", "barco", "puerto", "gaviota",
                "desierto", "cactus", "huracán", "tornado", "lluvia", "nieve", "nieve", "frío", "calor", "verano",
                "invierno", "otoño", "primavera", "jardín", "semilla", "fruta", "flor", "cereza", "naranja", "limón",
                "manzana", "pera", "uva", "melón", "sándalo", "musgo", "bambú", "pino", "roble", "sauce", "ciprés",
                "cedro", "helecho",
                "margarita", "rosa", "tulipán", "girasol", "lavanda", "cactus", "tormenta", "bruma", "rocío",
                "escarcha", "trigo", "maíz", "cebada", "algodón", "pasto", "mulch", "brote", "hongo", "seta", "pino",
                "baya", "fresno", "mangle", "marisma", "pantano", "juncal", "charca", "fresno", "roble", "aliso",
                "arce", "álamo", "sauce", "viña", "alga", "coral", "concha", "perla", "duna", "olas", "marino",
                "medusa", "ballena", "delfín", "tiburón", "pez", "ostra", "caracol", "langosta", "albatros",
                "pingüino", "pelícano", "fragata", "pato", "cisne", "ganso", "búho", "águila", "halcón", "lince",
                "zorro", "lobo", "ciervo", "oso", "ratón", "zorrillo", "mapache", "jaguar", "pantera", "león", "tigre",
                "elefante", "jirafa", "camello", "cebra", "cabra", "oveja", "vaca", "cerdo", "gallina", "gallo",
                "perro", "gato", "caballo", "burro", "murciélago", "luciérnaga", "mariposa", "polilla", "escarabajo",
                "hormiga", "araña", "avispa", "abeja", "libélula", "saltamontes", "grillo", "cigarra", "oruga",
                "gusano", "sanguijuela", "pulga", "piojo", "cucaracha", "mosquito", "mosca", "ciempies", "caracol",
                "babosa", "lombriz", "foca", "lobo", "foca", "morsa", "tortuga", "iguana", "serpiente", "cocodrilo",
                "lagarto", "camaleón" };
        String palabra = Palabras[random.nextInt(220)];
        boolean[] letrasAdivinadas = new boolean[palabra.length()];
        int contador = 0;
        while (contador < 6) {
            // monigote ;-;
            System.out.println("  ******");
            System.out.println("  *    *");
            if (contador >= 1) {
                System.out.println("  *    *"); // Cabeza
            } else {
                System.out.println("  *");
            }
            if (contador >= 2) {
                if (contador >= 3) {
                    System.out.println("  *   ***"); // Cuerpo completo con brazos
                } else {
                    System.out.println("  *   **"); // Cuerpo sin un brazo
                }
            } else {
                System.out.println("  *");
            }
            if (contador >= 4) {
                if (contador >= 5) {
                    System.out.println("  *   * *"); // Piernas completas
                } else {
                    System.out.println("  *   *"); // Solo una pierna
                }
            } else {
                System.out.println("  *");
            }
            System.out.println("  *");
            System.out.println("*******");
            System.out.println();
            // Añadí esto que te muestra lo adivinado y lo que falta por adivinar como "-"
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < palabra.length(); i++) {
                if (letrasAdivinadas[i]) {
                    resultado.append(palabra.charAt(i));
                } else {
                    resultado.append('_');
                }
                resultado.append(' ');
            }
            System.out.println("Palabra: " + resultado);
            System.out.print("Adivina una letra(SOLO UNA LETRA Y MINUSCULA!!): ");
            char letra = t.nextLine().charAt(0);
            boolean acierto = false;
            // Comprobacion de si la letra está en la palabra a adivinar
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra) {
                    letrasAdivinadas[i] = true;
                    acierto = true;
                }
            }
            if (acierto) {
                System.out.println("Correcto!");
            }
            if (!acierto) {
                contador++;
                System.out.println("Incorrecto. Te quedan " + (6 - contador) + " intentos.");
            }
            // Comprobacion de si se adivinaron todas las letras para terminar el juego
            boolean todasAdivinadas = true;
            for (boolean adivinada : letrasAdivinadas) {
                if (!adivinada) {
                    todasAdivinadas = false;
                    break;
                }
            }
            if (todasAdivinadas) {
                // si se acertaron todas sale este mensaje
                System.out.println("Felicidades! adivinaste la palabra: " + palabra);
                break;
            }
            if (contador == 6) {
                // Aqui muestras junto al mensaje de que perdiste al monigote completado!
                System.out.println("  ******");
                System.out.println("  *    *");
                System.out.println("  *    *");
                System.out.println("  *   ***");
                System.out.println("  *   * *");
                System.out.println("  *");
                System.out.println("*******");
                System.out.println("Perdiste... :( La palabra era: " + palabra);
            }
        }
        t.close();
    }
}