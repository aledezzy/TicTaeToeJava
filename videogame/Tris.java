package videogame;

import java.lang.Thread;
import java.io.IOException;
import java.util.Scanner;
import java.lang.InterruptedException;
import java.util.Random;

/**
 * La classe Tris serve per creare l'ambiente di gioco
 * 
 * @version 1.1
 * @author Alessandro De Zuani
 * @author Matteo Trolese
 * @author Giordano Calizzano
 * @author Daniele Novello
 * @author Mattia Tonazzo
 * @since 0.1
 */
public class Tris {

    private Griglia griglia, grigliaRef; 
    // griglia è la griglia di gioco, mentre grigliaRef è, come da nome, una griglia di riferimento, 
    //utilizzata nel metodo inserisci per controllare che la posizione inserita non sia già stata utilizzata in precedenza
    private boolean turno = true;   // descrive il turno se true è il turno di X, mentre se è false è il turno di O
    private Thread gameThread;  // è il Thread del gioco
    private boolean isGameRunning;  // descrive se il gioco è avviato o meno
    public String giocatore1 = "", giocatore2 = ""; // sono i nomi dei due giocatori
    private String starting = "", notStarting = ""; // sono i nomi del giocatore che inizia e di quello che non inizia
    private Scanner in = new Scanner(System.in); // Scanner
    private Random gen = new Random(); // Generatore di numeri casuali

    /**
     * Costruise una griglia di dimensioni 3x3
     */
    public Tris() {
        griglia = new Griglia();
        grigliaRef = new Griglia();
    }

    /**
     * Mostra di chi e' il turno, richiede l'inserimento della posizione e, in base
     * al numero, inserisce X o O
     * 
     * @exception e
     */
    public void inserisci() {
        if (turno) {
            System.out.println("Turno di: " + starting);
        } else {
            System.out.println("Turno di: " + notStarting);
        }
        System.out.println("Inserire la posizione");
        String a = in.next();
        searchStart: for (int i = 0; i < grigliaRef.griglia.length; i++) {
            for (int j = 0; j < grigliaRef.griglia[0].length; j++) {
                if (grigliaRef.griglia[i][j].equals(a)) {
                    if (turno && !griglia.griglia[i][j].equals("X") && !griglia.griglia[i][j].equals("O")) {
                        griglia.insert("X", i, j);
                        break;
                    } else if (!griglia.griglia[i][j].equals("X") && !griglia.griglia[i][j].equals("O")) {
                        griglia.insert("O", i, j);
                        break;
                    } else if (griglia.griglia[i][j].equals("X") || griglia.griglia[i][j].equals("O")) {
                        System.out.println("Casella occupata, non si bara! Riprova:");
                        threadSleep(3000);
                        turno = !turno;
                    }
                } else if (Integer.parseInt(a) > griglia.DIMENSIONE * griglia.DIMENSIONE || Integer.parseInt(a) < 1) {
                    turno = !turno;
                    System.out.println("Casella inesistente, reinserire");
                    threadSleep(3000);
                    break searchStart;
                }
            }
        }
    }

    /**
     * Verifica se in una riga, colonna, diagonale o diagonale inversa sono
     * presenti tre caratteri dello stesso tipo
     * 
     * @return boolean
     */
    public boolean verifica() {
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

    /**
     * Verifica se si ha pareggiato
     * 
     * @return isPareggio
     */
    public boolean pareggio() {
        boolean isPareggio = false;
        for (int i = 0; i < griglia.griglia.length; i++) {
            for (int j = 0; j < griglia.griglia[0].length; j++) {
                if (griglia.griglia[i][j].equals("X") || griglia.griglia[i][j].equals("O")) {
                    isPareggio = true;
                } else {
                    isPareggio = false;
                    break;
                }
            }
        }
        return isPareggio;
    }

    /**
     * Genera un numero casuale e, in base al numero (0/1), assegna il primo
     * giocatore
     */
    public void random() {
        System.out.println("Random tra player 0 e 1. Chi uscira'?");
        threadSleep(2000);
        int casuale = gen.nextInt(2);
        if (casuale == 0) {
            starting = giocatore1;
            notStarting = giocatore2;
        } else {
            starting = giocatore2;
            notStarting = giocatore1;
        }
        System.out.println("E' uscito " + casuale + " quindi iniziera' " + starting + "!");
        threadSleep(2000);
    }

    /**
     * Avvia il gameloop
     */
    public void run() {
        isGameRunning = true;
        gameThread = new Thread(this::processGameLoop);
        random();
        gameThread.start();
    }

    /**
     * Ferma il programma
     */
    private void stop() {
        isGameRunning = false;
        System.out.println("Vuoi fare un'altra partita? Y / N");
        String ris = in.next();
        if (ris.equalsIgnoreCase("y")) {
            griglia = new Griglia();
            isGameRunning = true;
            random();
        }
    }

    /**
     * Finche' il gioco gira: mostra la griglia, richiede l'inserimento di un numero
     * e aggiorna lo stato della partita
     */
    private void processGameLoop() {
        while (isGameRunning) {
            render();
            inserisci();
            update();
        }
    }

    /**
     * Verifica se la partita e' finita
     */
    private void update() {
        if (!pareggio()) {
            if (verifica()) {
                render();
                if (turno) {
                    System.out.println("Ha vinto: " + starting);
                } else {
                    System.out.println("Ha vinto: " + notStarting);
                }
                stop();
            }
        } else {
            render();
            System.out.println("Pareggio");
            stop();
            in.close();
        }
        turno = !turno;
    }

    /**
     * Pulisce la console e stampa la griglia
     */
    private void render() {
        clearConsole();
        System.out.println(griglia.toString());
    }

    /**
     * Pulisce la console
     * 
     * @exception e
     */
    private void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Non si riesce a cancellare la console");
            System.out.println(e.getStackTrace());
        }
    }

    private void threadSleep(long time){
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}