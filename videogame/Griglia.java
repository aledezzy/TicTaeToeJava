package videogame;
/**
 * Griglia del gioco del tris
 */
public class Griglia {
public String[][] griglia;
public final int DIMENSIONE = 3;
/**
 * Griglia
 * @param x
 * @param y
 */

public Griglia(){
    griglia = new String[DIMENSIONE][DIMENSIONE];
    int popola = 1;
    for (int i = 0; i < griglia.length; i++) {    
        for (int j = 0; j < griglia[0].length; j++) {
            griglia[i][j] = String.valueOf(popola);
            popola++;
        }
    }
}
/**
 * Restiruisce una stringa che ha la forma di una griglia, partendo dall'array bidimensionale
 * @return stringa
 */
public String toString () {
        String stringa = "";
        stringa = stringa + "\n-------------\n";
        for (int i = 0; i < griglia.length; i++) {
            stringa = stringa + "| ";
            for (int j = 0; j < griglia[0].length; j++) {
                stringa = stringa + griglia[i][j] + " " + "| ";
            }
            stringa = stringa + "\n-------------\n";
        }
        
        return stringa;
    }
/***
 * Inserisce la mossa del giocatore nella griglia
 * @param segno
 * @param i
 * @param j
 */
public void insert(String segno, int i, int j) {
    griglia[i][j] = segno; 
}
/**
 * Restiruisce la riga richiesta
 * @param n
 * @return riga
 */
public String[] row (int n) {
    String[] riga = new String[3];
    for (int i = 0; i < griglia[n].length; i++) {
        riga[i] = griglia[n][i];
    }
    return riga;
}
/***
 * Restituisce una colonna
 * @param n
 * @return colonna
 */
public String[] column (int n) {
    String[] colonna = new String[3];
    for (int i = 0; i < griglia.length; i++) {
        colonna[i] = griglia[i][n];
    }
    return colonna;
}
/***
 * Restituisce una diagonale
 * @return diagonale
 */
public String[] diag () {
    String[] diagonale = new String[3];
    for (int i = 0; i < griglia.length; i++) {
        diagonale[i] =  griglia[i][i];
    }
    return diagonale;
}
/***
 * Restituisce una diagonale inversa
 * @return diagonaleInversa
 */
public String[] invDiag () {
    String[] diagonaleInversa = new String[3];
    for (int i = 0; i < griglia.length; i++) {
        diagonaleInversa[i] = griglia[i][griglia[0].length - i - 1];
    }
    return diagonaleInversa;
}
}