/*
 * This is the client. It is where the game is actually played by combining
 * BoardGenerator and Anagrams
 * */

import javax.swing.JFrame;
import java.io.FileNotFoundException;

public class AnagramClient {
    // The Test Client
    public static void main(String[] args) throws FileNotFoundException {
        BoardGenerator board1 = new BoardGenerator();
        JFrame jr = new JFrame();
        String name = args[0];
        Anagrams test1 = new Anagrams(board1, name, false);
        jr.add(test1);

    }
}
