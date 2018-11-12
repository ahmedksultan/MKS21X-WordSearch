import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{

     private char[][]data;
     private int seed; //random seed used to produce this WordSearch
     private Random randgen; //random object to unify random calls
     private ArrayList<String> wordsToAdd; //all words get added, indicating they haven't been added
     private ArrayList<String> wordsAdded; //words that have been successfully added

     /**Initialize the grid to the size specified
      *and fill all of the positions with '_'
      *@param row is the starting height of the WordSearch
      *@param col is the starting width of the WordSearch
     */
     //CONSTRUCTOR 1 - rows, cols
     public WordSearch(int rows,int cols) {
          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
          }
     }
     //CONSTRUCTOR 2 - rows, cols, fileName
     public WordSearch(int rows, int cols, String fileName) {

     }
     //CONSTRUCTOR 3 - rows, cols, fileName, randSeed
     public WordSearch(int rows, int cols, String fileName, int randSeed) {

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
          return result + "\nWords: " + wordsAdded;
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

    private boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement){
         /* different cases
         down (1, 0) DONE
         right (0, 1) DONE
         up (-1, 0) DONE
         left (0, -1) DONE
         down & right (1, 1) DONE
         up & left(-1, -1)
         down & left(1, -1)
         up & right (-1, 1)
         */
         //DOWN
         if (rowIncrement == 1 && colIncrement == 0) {
              return addWordVertical(word, row, col);
         }
         //RIGHT
         if (rowIncrement == 0 && colIncrement == 1) {
              return addWordHorizontal(word, row, col);
         }
         //DOWN & RIGHT
         if (rowIncrement == 1 && colIncrement == 1) {
              return addWordDiagonal(word, row, col);
         }
         //UP
         if (rowIncrement == -1 && colIncrement == 0) {
              String word2 = wordFlip(word);
              return addWordVertical(word2, row, col);
         }
         //LEFT
         if (rowIncrement == 0 && colIncrement == -1) {
              String word2 = wordFlip(word);
              return addWordHorizontal(word2, row, col);
         }
         //UP & LEFT
         if (rowIncrement == -1 && colIncrement == -1) {
              String word2 = wordFlip(word);
              return addWordDiagonal(word2, row, col);
         }
         return false;
    }
    /*[rowIncrement,colIncrement] examples:
     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
     *[ 1,0] would add downwards because (row+1), with no col change
     *[ 0,-1] would add towards the left because (col - 1), with no row change
     */
     public static String wordFlip(String word) {
          String result = "";
          for (int i = 0; i < word.length(); i++) {
               result = word.substring(i, i+1) + result;
          }
          return result;
     }
}
