import java.util.*; //random, scanner, arrayList
import java.io.*; //file, filenotfoundexception

/*
main stuff (taking args, running methods, outputting results)
constructors
     > 1 - just rows and columns, so empty WS
     > 2 - randomly generated (no seed)
     > 3 - randomly generated (w/ seed)
toString methods
legacy add methods (horizontal, diagonal, vertical)
*/

public class WordSearch {
     public static void main(String[] args) {
          try {
               if (args.length == 2) {
                    int row = Integer.parseInt(args[0]);
                    int col = Integer.parseInt(args[1]);
                    WordSearch output = new WordSearch(row, col);
                    System.out.println(output);
                    System.exit(1);
               }
               if (args.length == 3) {
                    int row = Integer.parseInt(args[0]);
                    int col = Integer.parseInt(args[1]);
                    WordSearch output = new WordSearch(row, col, args[2]);
                    /*
                    System.out.println(output);
                    System.out.println(output.wordsToAdd);
                    System.out.println(output.randgen);
                    */
                    /*
                    output.addWord("epic", 0, 0, 1, 1);
                    output.addWord("apple", 1, 0, 0, 1);
                    output.addWord("zoomzoom", 7, 9, 0, -1);
                    */

                    System.out.println(output);
                    System.exit(1);
               }
          }
          catch (NumberFormatException e) {
               System.out.println("Error!");
               System.exit(1);
          }
          //if 2 args, create blank
          //if 3 args, generate random WS
          //if 4 args, generate random WS with seed!
          //if 5 args, generate random WS with seed with key
     }

     private char[][] data;
     private int seed;
     private Random randgen;
     private ArrayList<String> wordsToAdd;
     private ArrayList<String> wordsAdded;

     //CONSTRUCTOR 1 - rows, cols (2args)
     public WordSearch(int rows, int cols) {
          if (rows < 0 || cols < 0) {
               throw new IllegalArgumentException("Given dimensions are out of bounds!");
          }
          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
          }
     }

     //CONSTRUCTOR 2 - rows, cols, fileName (3args)
     public WordSearch(int rows, int cols, String fileName) {
          randgen = new Random();
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
          addAllWords();
          //seed = ???
     }

     //CONSTRUCTOR 3 - rows, cols, fileName, randSeed (4args)

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

     private boolean addWord(String word, int row, int col, int rowInc, int colInc) {
          if (word.length() <= data.length || word.length() <= data[0].length) {
               int rResult = row + (word.length() * rowInc);
               int cResult = col + (word.length() * colInc);
               if ((rResult < data.length && (rResult >= 0) && (cResult < data[0].length) && (cResult >= 0))) {
                    for (int i = 0; i < word.length(); i++) {
                         if (row + (i * rowInc) >= data.length ||
                             col + (i * colInc) >= data[row].length ||
                             ((word.charAt(i) != data[row+(i*rowInc)][col+(i*colInc)]) &&
                             (data[row+(i*rowInc)][col+(i*colInc)] != '_'))) {
                                   return false;
                         }
                    }
                    for (int i = 0; i < word.length(); i++) {
                         data[row+(i*rowInc)][col+(i*colInc)] = word.charAt(i);
                    }
                    return true;
               }
               else {
                    return false;
               }
          }
          else {
               return false;
          }
     }

     private void addAllWords() {
          while (wordsToAdd.size() > 0) {
               //Alma, Ali, and Tejas helped me with this part
               int idx = Math.abs(randgen.nextInt() % wordsToAdd.size());
               String word = wordsToAdd.get(idx);
               while (wordsToAdd.contains(word)) {
                    boolean complete = false;
                    int tries = 0;
                    int rowInc = randgen.nextInt() % 2;
                    int colInc = randgen.nextInt() % 2;
                    while (tries < 100 && !(complete)) {
                         int row = randgen.nextInt(data.length);
                         int col = randgen.nextInt(data[0].length);
                         if (addWord(word, row, col, rowInc, colInc)) {
                              wordsToAdd.remove(word);
                              wordsAdded.add(word);
                              complete = true;
                         }
                         else {
                              tries++;
                         }
                    }
               }
          }
     }






     //LEGACY FUNCTIONS (addHorizontal/addVertical/addDiagonal)
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
