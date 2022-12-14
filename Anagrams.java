/*
  * This file contains the second and third feature of our project called
  * Anagrams.
  *
  * The second feature (most of the code) involves the code necessary for GUI
  * (Graphical User Interface) to emerge through the use of JButtons to allow
  *  user-interactions in Anagrams (selecting letters) and printing the current
  * word (through the use of a JLabel).
  *
  * The third feature builds/add on to the game by adding a shuffle JButton
  * button similar to the iconic Pigeon-game Anagrams (shuffle method, JButton
  * in construction). It shuffles the position of the x coordinates of the
  * letter's JButton by either reverse their order, shifting it to the right by
  * one, and ... left by one through a void method.
  */

 import javax.swing.JButton;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.WindowConstants;
 import java.awt.Color;
 import java.awt.Font;
 import java.io.FileNotFoundException;
 import java.util.ArrayList;
 import java.util.Objects;

 public class Anagrams extends JPanel {

     // magic number that is the distance: the start to middle of all square-like
     // shapes
     private static final int CENTER = 15;
     // magic number that is the distance: the start to the end of all
     // square-like shapes
     private static final int SEPERATION = 85;
     // magic number for all the square length
     private static final int SQUARELENGTH = 70;
     // magic number for the number of tiles (both random / current word)
     private static final int TILES = 6;
     // magic number for the height of the random and current letter tiles
     private static final int HEIGHTRANDOMLETTER = 550;
     private static final int HEIGHTOFCURRENTWORDBOXES = 450;
     private static final int HEIGHTOFCURRENTWORD = 435;
     // magic number for the display centering, length, and height
     private static final int DISPLAYCENTER = 135;
     private static final int DISPLAYLENGTH = 250;
     private static final int DISPLAYHEIGHT = 50;
     // magic number for the timer centering & letter dimension (length x height)
     private static final int TIMERCENTER = 430;
     private static final int LETTERDIMENSIONS = 100;

     // private variables that hold the font
     private static final Font PLAYERFONT = new
             Font("Montserrat", Font.ITALIC, 30);
     private static final Font DISPLAYFONT = new
             Font("Arial", Font.PLAIN, 30);
     private static final Font OUTPUTFONT = new
             Font("Times New Roman", Font.BOLD, 15);

     private BoardGenerator board;
     // private instance variable that holds the Arraylists, timer, xCord array
     private ArrayList<JButton> buttons = new ArrayList<JButton>();
     private ArrayList<JLabel> letterLabel = new ArrayList<JLabel>();
     private int[] xCord;

     // private instance variables for all the JLabels used
     private JLabel scoreKeeperLabel = new JLabel("");
     private JLabel wordsLabel = new JLabel("");

     // this is the constructor that takes in a newBoard from board generator
     // (feature 1, which holds values we need) and a name (simply for player
     // name). Through the use of Java.swing, it makes a GUI for Anagrams to
     // play on. Finally, the boolean testing is implying to disallow or allow
     // debugging to be shown at the end (usually set to false).

     public Anagrams(BoardGenerator newBoard, String name, boolean testing) {
         // assigns board to newBoard
         board = newBoard;
         // creates labels that will be needed
         JLabel playerLabel = new JLabel("");
         JLabel timerLabel = new JLabel("");
         JLabel lettersLabel0 = new JLabel("");
         JLabel lettersLabel1 = new JLabel("");
         JLabel lettersLabel2 = new JLabel("");
         JLabel lettersLabel3 = new JLabel("");
         JLabel lettersLabel4 = new JLabel("");
         JLabel lettersLabel5 = new JLabel("");

         // basic JFrame set up (creating, setting canvas size, etc.)
         // 540 represents half the length of a laptop screen, and 680 the height
         JFrame frame = new JFrame();
         frame.setSize(540, 680);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         // creating and setting up the game panel (which is added to JFrame)
         JPanel panel = new JPanel();
         frame.add(panel);
         panel.setLayout(null);
         panel.setBackground(Color.lightGray);

         // obtains the x-cords of the buttons, labels, etc. w/ method below
         fillXCord(CENTER, SEPERATION);

         // setting up the font, writing, and moving the JLabels (Txt on JPanel)
         // 50 represents the y-height for playerLabel on the frame
         playerLabel.setFont(PLAYERFONT);
         playerLabel.setText("Player:   " + name);
         playerLabel.setBounds(DISPLAYCENTER, 50, DISPLAYLENGTH, DISPLAYHEIGHT);
         panel.add(playerLabel);
         // 100 represents the y-height for wordLabel on the frame
         wordsLabel.setFont(DISPLAYFONT);
         wordsLabel.setText("Words:  " + board.wordCount);
         wordsLabel.setBounds(DISPLAYCENTER, 100, DISPLAYLENGTH, DISPLAYHEIGHT);
         panel.add(wordsLabel);
         // 150 represents the y-height for scoreKeeperLabel on the frame
         scoreKeeperLabel.setFont(DISPLAYFONT);
         scoreKeeperLabel.setText("Score:   " + board.score);
         scoreKeeperLabel.setBounds(DISPLAYCENTER, 150,
                                    DISPLAYLENGTH, DISPLAYHEIGHT);
         panel.add(scoreKeeperLabel);

         // the 15 is the height, 100 is the length, and 50 is width of the label
         timerLabel.setFont(OUTPUTFONT);
         timerLabel.setText("Time: " + board.MAXTIME);
         timerLabel.setBounds(TIMERCENTER, 15, 100, 50);
         panel.add(timerLabel);

         // first adds the JLabel to the list, then writes & adds on JPanel
         letterLabel.add(lettersLabel0);
         letterLabel.add(lettersLabel1);
         letterLabel.add(lettersLabel2);
         letterLabel.add(lettersLabel3);
         letterLabel.add(lettersLabel4);
         letterLabel.add(lettersLabel5);
         // letters placement is different, so deviations to xCords needed
         for (int i = 0; i < TILES; i++) {
             letterLabel.get(i).setFont(DISPLAYFONT);
             letterLabel.get(i).setText("");
             letterLabel.get(i).setBounds(xCord[i] + 25, HEIGHTOFCURRENTWORD,
                                          LETTERDIMENSIONS, LETTERDIMENSIONS);
             panel.add(letterLabel.get(i));
         }
         // creates 8 buttons & puts text on them (randomLetter, enter, shuffle)
         JButton button0 = new JButton(board.randomWord.get(0));
         JButton button1 = new JButton(board.randomWord.get(1));
         JButton button2 = new JButton(board.randomWord.get(2));
         JButton button3 = new JButton(board.randomWord.get(3));
         JButton button4 = new JButton(board.randomWord.get(4));
         JButton button5 = new JButton(board.randomWord.get(5));
         JButton enter = new JButton("Enter");
         JButton shuffle = new JButton("S");

         // sets up the Action Listener and ties it with their appropriate method
         button0.addActionListener(e -> buttonPressed(0));
         button1.addActionListener(e -> buttonPressed(1));
         button2.addActionListener(e -> buttonPressed(2));
         button3.addActionListener(e -> buttonPressed(3));
         button4.addActionListener(e -> buttonPressed(4));
         button5.addActionListener(e -> buttonPressed(5));
         enter.addActionListener(e -> StdOut.println(enterPressed()));
         shuffle.addActionListener(e -> shuffle());

         // adds & sets up the background & bounds of these two buttons
         // (150,300) is the center, 200 is the length, 40 is the width
         // (25, 15) is the center, 45 is the length, 45 is the width
         enter.setBackground(Color.white);
         shuffle.setBackground(Color.white);
         enter.setBounds(150, 300, 200, 40);
         shuffle.setBounds(25, 15, 45, 45);
         panel.add(enter);
         panel.add(shuffle);

         // Makes an arraylist of JButtons to avoid repeated code
         buttons.add(button0);
         buttons.add(button1);
         buttons.add(button2);
         buttons.add(button3);
         buttons.add(button4);
         buttons.add(button5);
         // square panels for empty slots (6) on te JFrame for aesthetics
         JPanel square0 = new JPanel();
         JPanel square1 = new JPanel();
         JPanel square2 = new JPanel();
         JPanel square3 = new JPanel();
         JPanel square4 = new JPanel();
         JPanel square5 = new JPanel();
         // makes an array list to hold it and avoid continuously repeating code
         ArrayList<JPanel> squares = new ArrayList<JPanel>();
         squares.add(square0);
         squares.add(square1);
         squares.add(square2);
         squares.add(square3);
         squares.add(square4);
         squares.add(square5);

         // light purple and woody color
         Color woody = Color.getHSBColor(186, 140, 99);
         Color purply = Color.getHSBColor(97, 64, 81);
         // once again setting up JButtons and empty canvas for visual reasons
         for (int i = 0; i < TILES; i++) {
             buttons.get(i).setBackground(woody);
             buttons.get(i).setFont(DISPLAYFONT);
             buttons.get(i).setBounds(xCord[i], HEIGHTRANDOMLETTER,
                                      SQUARELENGTH, SQUARELENGTH);
             panel.add(buttons.get(i));

             squares.get(i).setLayout(null);
             squares.get(i).setBounds(xCord[i], HEIGHTOFCURRENTWORDBOXES,
                                      SQUARELENGTH, SQUARELENGTH);
             squares.get(i).setBackground(purply);
             panel.add(squares.get(i));
         }
         // Basic JFrame necessity that lets the panels be seen
         frame.setVisible(true);
         // time variable list needed to maintain and print game time
         ArrayList<Integer> times = new ArrayList<Integer>();
         // Game Loop that simply runs for a given time & label to showcase it
         while (true) {
             int time = (int) board.timer.elapsedTime();
             if (!times.contains(board.MAXTIME - time))
                 timerLabel.setText("Time:  " + (board.MAXTIME - time));
             times.add(board.MAXTIME - time);
             if (time == board.MAXTIME) {

                 // these are the test that must be done for debugging purposes:
                 if (testing) {
                     // test the X methods produce the values : y = 85x + 15 
                     // (0<x<6). Clone will be used to test shuffle
                     fillXCord(CENTER, SEPERATION);
                     int[] clone = new int[TILES];
                     for (int i = 0; i < TILES; i++) {
                         clone[i] = xCord[i];
                         StdOut.print(xCord[i] + " ");
                     }
                     StdOut.println();
                     // test the shuffle by comparing its clone to the now 
                     // shuffled xCord by making a similarity test (should be
                     // 0)
                     shuffle();
                     int similarity = 0;
                     for (int i = 0; i < TILES; i++) {
                         if (xCord[i] == clone[i]) similarity++;
                         else StdOut.println("Not equal at element: " + i);
                     }
                     StdOut.println("Similarity test should be ZERO: " +
                                            similarity);

                     // testing the button is printing the exact letter as seen.
                     // To test,  click all letters in correct sequence
                     String store = "Label showcased when pressed: ";
                     StdOut.println("First letter: " + board.randomWord.get(0));
                     StdOut.println(store + lettersLabel0.getText());

                     StdOut.println("Second letter: " + 
                                            board.randomWord.get(1));
                     StdOut.println(store + lettersLabel1.getText());

                     StdOut.println("Third letter: " + board.randomWord.get(2));
                     StdOut.println(store + lettersLabel2.getText());

                     StdOut.println("Fourth letter: " + 
                                            board.randomWord.get(3));
                     StdOut.println(store + lettersLabel3.getText());

                     StdOut.println("Fifth letter: " + board.randomWord.get(4));
                     StdOut.println(store + lettersLabel4.getText());

                     StdOut.println("Sixth letter: " + board.randomWord.get(5));
                     StdOut.println(store + lettersLabel5.getText());

                     // test that is current word is observed. It will say this 
                     // not a word, followed up by the current. In a real game, 
                     // if current word is a genuine word, it will not say the 
                     // first message
                     System.out.println("When enter is pressed: ");
                     StdOut.println(enterPressed());
                 }
                 // final games stats and exits
                 else {
                     System.out.println(name + "'s " + "final stats were: ");
                     System.out.println("Score: " + board.score);
                     System.out.println("Word count: " + board.wordCount);
                 }
                 System.exit(0);
             }
         }
     }

     // makes the x Cord for all things that need it
     // returns an int array after giving it the center
     // and separation values
     public void fillXCord(int midPoint, int distance) {
         xCord = new int[TILES];

         // fill both arrays with calculated maneuvers
         for (int i = 0; i < TILES; i++)
             xCord[i] = midPoint + (i * distance);
     }

     // shuffles the xCords w/ distinct processes and then moves
     // the JButtons to the correct spot
     public void shuffle() {
         // create the array for xCord and its clone
         int[] shadowClone = new int[TILES];
         // makes a copy since some shuffles require it
         for (int i = 0; i < TILES; i++)
             shadowClone[i] = xCord[i];
         // there's three different shuffles, so a random decides
         int random = (int) (Math.random() * 3);
         // shuffle for loop
         for (int i = 0; i < 3; i++) {
             // reverse-order shuffle
             if (random == 0) {
                 int temp = xCord[i];
                 xCord[i] = xCord[TILES - 1 - i];
                 xCord[TILES - 1 - i] = temp;
             }
             // slide left by one (shadow clone needed for one iteration)
             else if (random == 1) {
                 if (i == 2) {
                     xCord[i] = shadowClone[i + 1];
                     xCord[i + 3] = shadowClone[0];
                 }
                 else {
                     xCord[i] = xCord[i + 1];
                     xCord[i + 3] = xCord[i + 4];
                 }
             }
             // slide right by one (shadow clone needed for one iteration)
             else {
                 if (i == 2) {
                     xCord[TILES - 1 - i] = shadowClone[TILES - 2 - i];
                     xCord[TILES - 4 - i] = shadowClone[TILES - 1];
                 }
                 else {
                     xCord[TILES - 1 - i] = xCord[TILES - 2 - i];
                     xCord[TILES - 4 - i] = xCord[TILES - 5 - i];
                 }
             }
         }
         // sets the JButtons with new xCords
         for (int i = 0; i < TILES; i++) {
             buttons.get(i).setBounds(xCord[i], HEIGHTRANDOMLETTER,
                                      SQUARELENGTH, SQUARELENGTH);
         }
     }

     // method that details what happens when a button is pressed
     // just requires the number (position) of the JButton
     public void buttonPressed(int buttonNumber) {
         // local variables needed for the removing letters
         int length = board.currentWord.size();

         // if loop simply adds the correct letter when a JButton is pressed
         if (!board.currentWord.contains(board.randomWord.get(buttonNumber))) {
             board.currentWord.add(board.randomWord.get(buttonNumber));
         }
         // if loop deletes the most recent letter if that button was pressed
         else if (Objects.equals(board.currentWord.get(length - 1),
                                 board.randomWord.get(buttonNumber))) {
             board.currentWord.remove(board.randomWord.get(buttonNumber));
         }
         // makes the JLabel fit the currentWord arraylist
         int newlength = board.currentWord.toArray().length;
         for (int i = 0; i < TILES; i++) {
             if (i < newlength) letterLabel.get(i).setText(board.currentWord.
                                                                   get(i));
             else letterLabel.get(i).setText("");
         }
     }

     // this is method used when enter is pressed, it released a string
     // (it would be void if testing was not needed)
     public String enterPressed() {
         String word = board.getCurrentWord();
         try {
             board.score += board.points(word); // assigns points
         }
         catch (Exception error) {
             StdOut.print("FILE NOT FOUND");
         }
         scoreKeeperLabel.setText("Score:  " + board.score);
         wordsLabel.setText("Words:   " + board.wordCount);
         board.currentWord.clear();
         for (int i = 0; i < TILES; i++) {
             letterLabel.get(i).setText("");
         }
         String result = "Enter was clicked and submitted: " + word;
         return result;
     }

     // Testing if game is playable, buttons works, and so forth.
     public static void main(String[] args) throws FileNotFoundException {
         BoardGenerator board1 = new BoardGenerator();
         String name = args[0];
         boolean testing = Boolean.parseBoolean(args[1]);
         Anagrams game = new Anagrams(board1, name, testing);
         // the debugging code is in the game loop for reasons already explained
     }
 }
