package videogame;
/**
 * TestTris per testare i metodi del tris
 */
public class TestTris {
    /***
     * Main per il test dei metodi
     * @param args
     */

     
    public static void main(String[] args) {
        //tris in orizzontale
        Griglia griglia = new Griglia();
        griglia.insert("X", 0, 0);
        griglia.insert("X", 0, 1);
        griglia.insert("X", 0, 2);
        System.out.println(griglia.toString());
        System.out.println("orizzontale : " + verifica(griglia));
    
        //tris in verticale
        griglia = new Griglia();
        griglia.insert("X", 0, 0);
        griglia.insert("X", 1, 0);
        griglia.insert("X", 2, 0);
        System.out.println(griglia.toString());
        System.out.println("verticale : " + verifica(griglia));

        //tris diagonale 
        griglia = new Griglia();
        griglia.insert("X", 0, 0);
        griglia.insert("X", 1, 1);
        griglia.insert("X", 2, 2);

        System.out.println(griglia.toString());
        System.out.println("diagonale: " + verifica(griglia));
        
        //tris diagonale inversa
        griglia = new Griglia();
        griglia.insert("X", 0, 2);
        griglia.insert("X", 1, 1);
        griglia.insert("X", 2, 0);

        System.out.println(griglia.toString());
        System.out.println("diagonale inversa :" + verifica(griglia));
    }
    

    private static boolean verifica(Griglia griglia) {
        for (int i = 0; i < 3; i++) {
            if (griglia.row(i)[0].equals(griglia.row(i)[1]) && griglia.row(i)[0].equals(griglia.row(i)[2])) {
                return true;
            } else if (griglia.column(i)[0].equals(griglia.column(i)[1])
                    && griglia.column(i)[0].equals(griglia.column(i)[2])) {
                return true;
            }
        }
        if (griglia.diag()[0].equals(griglia.diag()[1]) && griglia.diag()[0].equals(griglia.diag()[2])) {
            return true;
        } else if (griglia.invDiag()[0].equals(griglia.invDiag()[1])
                && griglia.invDiag()[0].equals(griglia.invDiag()[2])) {
            return true;
        } else {
            return false;
        }
    }

    

    

}
