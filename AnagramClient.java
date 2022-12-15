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
        boolean testing = Boolean.parseBoolean(args[1]);
        Anagrams Test1 = new Anagrams(Board1, name, testing);
        jr.add(Test1);

    }
}
