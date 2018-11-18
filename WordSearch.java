import java.util.*; //random, scanner, arrayList
import java.io.*; //file, filenotfoundexception

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
          data = new char[rows][cols];
          for (int a = 0; a < data.length; a++) {
               for (int b = 0; b < data[a].length; b++) {
                    data[a][b] = '_';
               }
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
          return result + "\nWords: " + wordsAdded + "\n";
     }
     
}
