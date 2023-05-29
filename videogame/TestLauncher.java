package videogame;
import java.util.Scanner;
/**
 * TestLauncher
 */
/**
 * @deprecated
 */
public class TestLauncher {
    public static void main(String[] args) {
        // Stampa scritta di gioco
        System.out.println("Tris");
        //Stampa una riga per estetica
        System.out.println("-----------");
        // Crea lo scanner per l'input dei giocatori
        Scanner in = new Scanner(System.in);
        // Prova del launcher
        Tris tris = new Tris();
        // Prende in inputi nomi dei giocatori
        System.out.println("Inserire il nome del Giocatore 1");
        tris.giocatore1 = in.nextLine();
        System.out.println("Inserire il nome del Giocatore 2");
        tris.giocatore2 = in.nextLine();
        // Prova a runnare il gioco
        tris.run();
       
    }
}