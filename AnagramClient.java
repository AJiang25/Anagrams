/*
 * This is the client. It is where the game is actually played by combining
 * BoardGenerator and Anagrams
 * */

import javax.swing.JFrame;
import java.io.FileNotFoundException;

public class AnagramClient {
    // The Test Client
    public static void main(String[] args) throws FileNotFoundException {
        BoardGenerator Board1 = new BoardGenerator();
        JFrame jr = new JFrame();
        String name = args[0];
        Anagrams Test1 = new Anagrams(Board1, name);
        jr.add(Test1);

    }
}
