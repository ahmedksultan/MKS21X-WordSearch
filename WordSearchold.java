import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearchold {
     public static void main(String[] args) {
          try {
               if (args.length == 2) {
                    int row = Integer.parseInt(args[0]);
                    int col = Integer.parseInt(args[1]);
                    WordSearch test = new WordSearch(row, col);
                    System.out.println(test);
                    System.exit(1);
               }
               if (args.length == 3) {
                    int row = Integer.parseInt(args[0]);
                    int col = Integer.parseInt(args[1]);
                    WordSearch test = new WordSearch(row, col, args[2]);
                    test.addAllWords();
                    System.exit(1);
               }
              if (args.length == 4) {
                    int row = Integer.parseInt(args[0]);
                    int col = Integer.parseInt(args[1]);
                    int seed = Integer.parseInt(args[3]);
                    WordSearch test = new WordSearch(row, col, args[2], seed);
                    test.addAllWords();
                    System.exit(1);
               }
          }
          catch (NumberFormatException e) {
               System.out.println("Error!");
               System.exit(1);
          }
     }
     private char[][]data;
     private int seed; //random seed used to produce this WordSearch
     private Random randgen; //random object to unify random calls
     private ArrayList<String> wordsToAdd; //all words get added, indicating they haven't been added
     private ArrayList<String> wordsAdded; //words that have been successfully added

     //CONSTRUCTOR 1 - rows, cols
     public WordSearch(int rows, int cols) {
          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
          }
     }
     //CONSTRUCTOR 2 - rows, cols, fileName
     public WordSearch(int rows, int cols, String fileName) {
          if (rows < 0 || cols < 0) {
               throw new IllegalArgumentException("Given dimensions are out of bounds!");
          }
          wordsToAdd = new ArrayList<String>();
          wordsAdded = new ArrayList<String>();
          try {
               File words = new File(fileName);
               Scanner in = new Scanner(words);
               while(in.hasNext()) {
                    wordsToAdd.add(in.nextLine());
               }
          }
          catch (FileNotFoundException e) {
               System.out.println("File not found: " + fileName);
               System.exit(1);
          }
          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
          }
          randgen = new Random();
          seed = randgen.nextInt();
     }
     //CONSTRUCTOR 3 - rows, cols, fileName, randSeed
     public WordSearch(int rows, int cols, String fileName, int randSeed) {
          if (rows < 0 || cols < 0) {
               throw new IllegalArgumentException("Given dimensions are out of bounds!");
          }
          wordsToAdd = new ArrayList<String>();
          wordsAdded = new ArrayList<String>();
          try {
               File words = new File(fileName);
               Scanner in = new Scanner(words);
               while(in.hasNext()) {
                    wordsToAdd.add(in.nextLine());
               }
          }
          catch (FileNotFoundException e) {
               System.out.println("File not found: " + fileName);
               System.exit(1);
          }

          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
          }
          randgen = new Random(randSeed);
          seed = randSeed;

     }
     /**Set all values in the WordSearch to underscores'_'*/
     private void clear() {
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
          }
     }
     /**Each row is a new line, there is a space between each letter
      *@return a String with each character separated by spaces, and rows
      *separated by newlines.
     */
     public String toString() {
          String result = "";
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    if (b == 0) {
                         result = result + ("| ");
                    }
                    result = result + (data[a][b] + " ");
               }
               result = result + "|\n";
          }
          return result + "\nWords: " + wordsAdded + "\n";
     }
     /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added in the direction rowIncrement,colIncrement
     *Words must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
     *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
     *@return true when: the word is added successfully.
     *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
     *        OR there are overlapping letters that do not match
     */
     private boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement) {
          if (word.length() <= data.length || word.length() <= data[0].length) {
               return true;
          }
          else {
               System.out.println("ERROR - This word is too big! : " + word);
               return false;
          }
     }
     /*[rowIncrement,colIncrement] examples:
     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
     *[ 1,0] would add downwards because (row+1), with no col change
     *[ 0,-1] would add towards the left because (col - 1), with no row change
     */
     private void addAllWords() {
          //need to add formatting at some point
          while (wordsToAdd.size() > 0) {
               //Alma and Ali helped me with this
               int idx = Math.abs(randgen.nextInt() % wordsToAdd.size());
               String word = wordsToAdd.get(idx);
               int rowInc = randgen.nextInt() % 2;
               int colInc = randgen.nextInt() % 2;
               while (wordsToAdd.contains(word)) {
                    boolean complete = false;
                    int tries = 0;
                    //tried to use for loop for this part but that didn't really work so started using while loop (thanks Alma!)
                    while (tries < 100 && !complete) {
                         int rRow = randgen.nextInt(data.length);
                         int rCol = randgen.nextInt(data[0].length);
                         if (addWord(word, rRow, rCol, rowInc, colInc)) {
                              wordsToAdd.remove(word);
                              wordsAdded.add(word);
                              complete = true;
                         }
                         else {
                              tries = 100;
                              complete = true;
                         }
                         tries++;
                    }
               }
          }
      }

      /**Attempts to add a given word to the specified position of the WordGrid.
      *The word is added from left to right, must fit on the WordGrid, and must
      *have a corresponding letter to match any letters that it overlaps.
      *
      *@param word is any text to be added to the word grid.
      *@param row is the vertical location of where you want the word to start.
      *@param col is the horizontal location of where you want the word to start.
      *@return true when the word is added successfully. When the word doesn't fit,
      * or there are overlapping letters that do not match, then false is returned
      * and the board is NOT modified.
      */
      public boolean addWordHorizontal(String word,int row, int col) {
          for (int i = 0; i < word.length(); i++) {
               /* ifstatement is checking to make sure...
                    > word isn't too big;
                    > letters of word can overlap
                    > current spot is empty
               */
               if (col + i >= data[row].length ||
                    ((word.charAt(i) != data[row][col+i]) &&
                    (data[row][col+i] != '_'))) {
                         return false;
               }
          }
          for (int i = 0; i < word.length(); i++) {
               data[row][col+i] = word.charAt(i);
          }
          return true;
      }
      /**Attempts to add a given word to the specified position of the WordGrid.
       *The word is added from top to bottom, must fit on the WordGrid, and must
       *have a corresponding letter to match any letters that it overlaps.
       *
       *@param word is any text to be added to the word grid.
       *@param row is the vertical location of where you want the word to start.
       *@param col is the horizontal location of where you want the word to start.
       *@return true when the word is added successfully. When the word doesn't fit,
       *or there are overlapping letters that do not match, then false is returned.
       *and the board is NOT modified.
     */
     public boolean addWordVertical(String word, int row, int col) {
          for (int i = 0; i < word.length(); i++) {
               if (row + i >= data.length ||
                    ((word.charAt(i) != data[row+i][col]) &&
                    (data[row+i][col] != '_'))) {
                         return false;
               }
          }
          for (int i = 0; i < word.length(); i++) {
               data[row+i][col] = word.charAt(i);
          }
          return true;
     }
     public boolean addWordDiagonal(String word, int row, int col) {
          for (int i = 0; i < word.length(); i++) {
               if (row + i >= data.length ||
                    col + i >= data[row].length ||
                    ((word.charAt(i) != data[row+i][col+i]) &&
                    (data[row+i][col+i] != '_'))) {
                         return false;
               }
          }
          for (int i = 0; i < word.length(); i++) {
               data[row+i][col+i] = word.charAt(i);
          }
          return true;
     }
}
