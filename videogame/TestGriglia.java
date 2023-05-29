package videogame;
import java.util.Arrays;
/**
 * TestGriglia per testare i metodi della griglia
 */
public class TestGriglia {
    /**
     * Main per il test della griglia
     * @param args
     */
    public static void main(String[] args) {
        Griglia griglia = new Griglia();
        // Stampa la griglia di gioco
        System.out.println(griglia.toString());
        System.out.println("------------------");
        // Stampa le righe della griglia di gioco
        System.out.println(Arrays.toString(griglia.row(0)));
        System.out.println(Arrays.toString(griglia.row(1)));
        System.out.println(Arrays.toString(griglia.row(2)));
        System.out.println("------------------");
        // Stampa le colonne della griglia di gioco
        System.out.println(Arrays.toString(griglia.column(0)));
        System.out.println(Arrays.toString(griglia.column(1)));
        System.out.println(Arrays.toString(griglia.column(2)));
        System.out.println("------------------");
        // Stampa la diagonale della griglia di gioco
        System.out.println(Arrays.toString(griglia.diag()));
        System.out.println("------------------");
        // Stampa la diagonale inversa della griglia di gioco
        System.out.println(Arrays.toString(griglia.invDiag()));
    }
}