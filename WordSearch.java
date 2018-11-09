public class WordSearch{
     private char[][]data;
     /**Initialize the grid to the size specified
      *and fill all of the positions with '_'
      *@param row is the starting height of the WordSearch
      *@param col is the starting width of the WordSearch
     */
     public WordSearch(int rows,int cols) {
          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
          }
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
                    result = result + (data[a][b] + " ");
               }
               result = result + "\n";
          }
          return result;
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
}
