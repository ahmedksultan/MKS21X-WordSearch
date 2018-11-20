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
               /*
               if (args.length == 2) {
                    int row = Integer.parseInt(args[0]);
                    int col = Integer.parseInt(args[1]);
                    WordSearch output = new WordSearch(row, col);
                    System.out.println(output);
                    System.exit(1);
               }
               */
               int row = Integer.parseInt(args[0]);
               int col = Integer.parseInt(args[1]);
               int seed = (int)(Math.random() * 10000);
               String key = "nokey";
               if (args.length == 4) {
                    seed = Integer.parseInt(args[3]);
               }
               if (args.length == 5 && args[4].equals("key")) {
                    System.out.println("\n--- ANSWER KEY ---\n");
                    seed = Integer.parseInt(args[3]);
                    key = "key";
               }
               WordSearch output = new WordSearch(row, col, args[2], seed, key);
               System.out.println(output);
               System.exit(1);
          }
          catch (NumberFormatException e) {
               System.out.println("Error! Wrong inputs have been detected. \nPlease use this format: java WordSearch [# of rows] [# of columns] [filename] [# seed]* [key]*\n(* denotes optional)\n");
               System.exit(1);
          }
          catch (ArrayIndexOutOfBoundsException e) {
               System.out.println("Error! Your word search must have at least one row. \nPlease use this format: java WordSearch [# of rows] [# of columns] [filename] [# seed]* [key]*\n(* denotes optional)\n");
               System.exit(1);
          }
          catch (NegativeArraySizeException e) {
               System.out.println("Error! Your array rows/columns are negative. \nPlease use this format: java WordSearch [# of rows] [# of columns] [filename] [# seed]* [key]*\n(* denotes optional)\n");
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

     /*

     //CONSTRUCTOR 1 - rows, cols (2args) [[[blank array]]]
     public WordSearch(int rows, int cols) {
          if (rows < 0 || cols < 0) {
               throw new IllegalArgumentException("Given dimensions are out of bounds!");
          }
          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = ' ';
               }
          }
     }

     */

     public WordSearch(int rows, int cols, String fileName, int randSeed, String key) {
          //Tejas helped me finding a solution for this
          seed = randSeed;
          randgen = new Random(seed);
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
                    data[a][b] = ' ';
               }
          }
          if (key.equals("key")) {
               addAllWordskey();
          }
          else {
               addAllWords();
          }
     }

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
          System.out.println(" ");
          return result + "\nWords: " + wordsAdded + " (seed: " + seed + ")\n";
     }

     private boolean addWord(String word, int row, int col, int rowInc, int colInc) {
          if (word.length() <= data.length || word.length() <= data[0].length) {
               int rResult = row + (word.length() * rowInc);
               int cResult = col + (word.length() * colInc);
               if ((rResult < data.length && (rResult >= 0) && (cResult < data[0].length) && (cResult >= 0))) {
                    if (rowInc == 0 && colInc == 0) {
                         return false;
                    }
                    for (int i = 0; i < word.length(); i++) {
                         if (row + (i * rowInc) >= data.length ||
                             col + (i * colInc) >= data[row].length ||
                             ((word.charAt(i) != data[row+(i*rowInc)][col+(i*colInc)]) &&
                             (data[row+(i*rowInc)][col+(i*colInc)] != ' '))) {
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
               //idx - random index that chooses a word out of the wordsToAdd ArrayList
               int idx = Math.abs(randgen.nextInt() % wordsToAdd.size());
               String word = wordsToAdd.get(idx);
               while (wordsToAdd.contains(word)) {
                    //complete signifies when the word has been added
                    //tries keeps track of how many tries the function has done
                    boolean complete = false;
                    int tries = 0;
                    int rowInc = randgen.nextInt() % 2;
                    int colInc = randgen.nextInt() % 2;
                    //originally had 50 tries, thanks to Tejas' recommendation did data[0].length * data.length
                    while (tries < (data[0].length * data.length) && !(complete)) {
                         int row = randgen.nextInt(data.length);
                         int col = randgen.nextInt(data[0].length);
                         if (addWord(word, row, col, rowInc, colInc)) {
                              wordsToAdd.remove(word);
                              wordsAdded.add(word);
                              complete = true;
                              /* debug stuff!
                              System.out.println("\nAdded: " + word + "\nRow/col: " + row + " " + col + "\nrowInc/colInc: " + rowInc + " " + colInc );
                              */
                         }
                         tries++;
                    }
                    wordsToAdd.remove(word);
               }
          }
          for (int x = 0; x < data.length; x++) {
               for (int y = 0; y < data[x].length; y++) {
                    if (data[x][y] == ' ') {
                         //Tejas helped me with this
                         data[x][y] = (char)(Math.abs((randgen.nextInt()) % 26) + 'A');
                    }
               }
          }
     }

     private void addAllWordskey() {
          while (wordsToAdd.size() > 0) {
               //Alma, Ali, and Tejas helped me with this part
               //idx - random index that chooses a word out of the wordsToAdd ArrayList
               int idx = Math.abs(randgen.nextInt() % wordsToAdd.size());
               String word = wordsToAdd.get(idx);
               while (wordsToAdd.contains(word)) {
                    //complete signifies when the word has been added
                    //tries keeps track of how many tries the function has done
                    boolean complete = false;
                    int tries = 0;
                    int rowInc = randgen.nextInt() % 2;
                    int colInc = randgen.nextInt() % 2;
                    //originally had 50 tries, thanks to Tejas' recommendation did data[0].length * data.length
                    while (tries < (data[0].length * data.length) && !(complete)) {
                         int row = randgen.nextInt(data.length);
                         int col = randgen.nextInt(data[0].length);
                         if (addWord(word, row, col, rowInc, colInc)) {
                              wordsToAdd.remove(word);
                              wordsAdded.add(word);
                              complete = true;
                              /* debug stuff!
                              System.out.println("\nAdded: " + word + "\nRow/col: " + row + " " + col + "\nrowInc/colInc: " + rowInc + " " + colInc );
                              */
                         }
                         tries++;
                    }
                    wordsToAdd.remove(word);
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
                   (data[row][col+i] != ' '))) {
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
                    (data[row+i][col] != ' '))) {
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
                    (data[row+i][col+i] != ' '))) {
                         return false;
               }
          }
          for (int i = 0; i < word.length(); i++) {
               data[row+i][col+i] = word.charAt(i);
          }
          return true;
     }
}
