import java.util.Scanner;

public class numeroSecreto {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numSecreto;
        int intentos = 0;
        boolean haAcertado = false;

        numSecreto = (int) (Math.random() * 100);
        
        //Aqui le pedimos al usuario que meta el numero
        System.out.println("Intenta adivinar el numero secreto! (pista: esta entre el 0 y el 100)");


        while (!haAcertado) { //Usamos while para que siga pidiendo el numero hasta que acierte 
            try {
                System.out.println("Introduce tu numero o pulsa 's' para reiniciar la partida ");
                String valorEntrada = input.next();

                if (valorEntrada.equals("s")) { //Para reiniciar el juego
                    
                    numSecreto = (int) (Math.random() * 100); //Con esto sacamos un nuevo numero aleatorio al reiniciarlo
                    intentos = 0; // Y reiniciamos los intentos
                    System.out.println("Empiezas de nuevo (RECUERDA: esta entre el 0 y el 100)");
                } else {

                    int numElegido = Integer.parseInt(valorEntrada);
                    if (numElegido >= 0 && numElegido <= 100) { //Para que solo puedan meter numeros entre 0 y 100
                        intentos = intentos + 1; //Con esto conseguimos que le vaya sumando el numero de intentos

                        if (numElegido < numSecreto) { //Comprueba si el numero que nos dan es menor al numero secreto
                            System.out.println("El numero secreto es mayor");
                            System.out.println("Llevas " + intentos + " intento/s"); //Y se le suma los intentos
                        } else if (numElegido > numSecreto) { //Comprueba si el numero que nos dan es mayor al numero secreto
                            System.out.println("El numero secreto es menor");
                            System.out.println("Llevas " + intentos + " intento/s");
                        } else {  //Si no es mayor o menor ha acertado
                            System.out.println("¡¡Has acertado!! El numero secreto era " + numSecreto);
                            System.out.println("Lo has conseguido en " + intentos + " intento/s");

                            haAcertado = true; // Cuando lo acierte para parar el bucle
                        }
                    }
                }
                //Para que le salga este mensaje de error si no introduce un numero valido
            } catch (Exception e) { 
                System.out.println("El valor introducido no es valido");
                
            }
        }
        input.close();
    }

}