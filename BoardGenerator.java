/*
 * This class implements feature #1 by first creating a board that keeps
 * the player's score. This class also generates a random word, keeps track
 * of the current word and keeps track of past words. Within the class, there
 * are accessor methods that enable the private instance variables to be
 * accessed outside of the class and validity methods that are used to test is
 * a board and word is generated correctly.
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Feature 1: Generates a Board
public class BoardGenerator {
    protected final int MAXTIME = 80; // The max time for the game
    protected static final int POINTS = 200; // Points awarded per character
    protected static final int ALPHABET = 26; // # of letters in the alphabet
    protected static final int MAXLENGTH = 6; // max word length

    // protected instance variables (for subclass inheritance)
    protected int score; // Maintains the amount of points a player has
    protected ArrayList<String> currentWord; // Tracks the created word
    protected ArrayList<String> randomWord; // Generates the random word used
    protected ArrayList<String> words; // Tracks past valid submitted words
    protected Stopwatch timer; // Tracks the time
    protected int wordCount; // Tracks the number of words submitted

    // Generates & Initializes instance variables for the board;
    public BoardGenerator() {
        wordCount = 0;
        score = 0;
        currentWord = new ArrayList<String>();
        randomWord = randomWord();
        timer = new Stopwatch();
        words = new ArrayList<String>();
    }

    // Generates a random word; A private Helper Method
    private ArrayList<String> randomWord() {
        String possibleLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        randomWord = new ArrayList<String>();
        boolean[] usedNumbers = new boolean[ALPHABET];

        // prevents the creation of duplicate words
        while (randomWord.size() != MAXLENGTH) {
            int random = (int) (Math.random() * ALPHABET);
            if (!usedNumbers[random]) {
                char letter = possibleLetters.charAt(random);
                randomWord.add(String.valueOf(letter));
                usedNumbers[random] = true;
            }
        }
        // Checks validity of word & calls itself if the word is invalid;
        if (isRandomWordValid(randomWord.toString())) {
            return randomWord;
        }
        return randomWord();

    }

    // The following are get methods:

    // Returns the score
    public int getScore() {
        return score;
    }

    // Returns the time
    public int getTime() {
        return MAXTIME - (int) timer.elapsedTime();
    }

    // Returns the current word
    public String getCurrentWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentWord.size(); i++) {
            sb.append(currentWord.get(i));
        }
        return sb.toString();
    }

    // Returns the current word
    public String getRandomWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < randomWord.size(); i++) {
            sb.append(randomWord.get(i));
        }
        return sb.toString();
    }


    // The following are methods that check validity

    // Checks if the created word is an actual word
    public static boolean isAnagram(String word) throws FileNotFoundException {
        word = word.toLowerCase(); // checks input regardless of capitalization
        File dictionary = new File("words.txt");
        Scanner scan = new Scanner(dictionary);
        while (scan.hasNextLine()) {
            if (scan.nextLine().equals(word))
                return true;
        }
        return false;
    }

    // Checks for duplicates within a string
    public static boolean duplicates(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if there is at least one vowel in randomWord & no duplicate letters
    public static boolean isRandomWordValid(String s) {
        boolean vowels = s.contains("A") || s.contains("I")
                || s.contains("E") || s.contains("O") || s.contains("U");
        if (vowels && duplicates(s)) {
            return true;
        }
        return false;
    }

    // Test that ensures that the board created is valid
    public boolean isBoardValid() {
        String word = this.getRandomWord();
        if (isRandomWordValid(word)
                && word.length() == MAXLENGTH && this.getCurrentWord().length() == 0
                && this.getScore() == 0 && this.getTime() == MAXTIME) {
            return true;
        }
        return false;
    }

    // Other Methods:

    // Returns the amount of points depending on length & if the word is an anagram
    public int points(String word) throws FileNotFoundException {
        if (!words.contains(word)) {
            if (isAnagram(word)) {
                wordCount++;
                words.add(word);
                return word.length() * POINTS;
            }
        }
        StdOut.println("Invalid Entry");
        return 0;
    }

    // Overrides the toString method
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("randomWord: " + getRandomWord() + ", ");
        sb.append("score: " + getScore() + ", ");
        sb.append("time: " + getTime() + ", ");
        sb.append("currentWord: " + getCurrentWord());
        return sb.toString();
    }

    // Tests all of the methods
    public static void main(String[] args) throws FileNotFoundException {
        BoardGenerator board1 = new BoardGenerator(); // Tests Constructor
        StdOut.println(board1); // Tests the toString method & randomWord method

        // Tests the get methods (all are also in toString method)
        String word = board1.getRandomWord();
        StdOut.println("word: " + word);
        String current = board1.getCurrentWord();
        StdOut.println("current: " + current);
        int time = board1.getTime();
        StdOut.println("time: " + time);
        int score = board1.getScore();
        StdOut.println("Score: " + score);

        // Tests the isBoardValid method
        if (board1.isBoardValid())
            StdOut.println("Board is Valid : true");
        else
            StdOut.println("Board is Valid : false");

        // Tests the isRandomWordValid method
        if (isRandomWordValid(word))
            StdOut.println("randomWord is Valid : true");
        else
            StdOut.println("randomWord is Valid : false");

        // Tests the duplicates method
        String s = "SSSSS";
        if (duplicates(s)) {
            StdOut.println("s has duplicates");
        }
        else {
            StdOut.println("s has no duplicates");
        }

        // Tests the isAnagrams method
        current = "TRYOUT"; // This is an example anagram of YOUTTR
        if (isAnagram(current)) // Checks if this a valid anagram
            StdOut.println("TRYOUT is an anagram");
        else
            StdOut.println("TRYOUT is not an anagram");

        // Tests the points method
        StdOut.println("points awarded for current: " + board1.points(current));
    }
}
