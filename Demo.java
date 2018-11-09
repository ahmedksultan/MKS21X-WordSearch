public class Demo {
     public static void main(String[] args) {
          System.out.println("\n--- BEGINNING OF TEST ---\n");

          System.out.println("Initializing new WordSearch (5x5 square)...");
          WordSearch fivesq = new WordSearch(5, 5);
          System.out.println(fivesq);
          System.out.println("Testing addWordHorizontal: placing ZERO horizontal in index (0,0)");
          fivesq.addWordHorizontal("ZERO", 0, 0);
          System.out.println(fivesq);
          System.out.println("Testing addWordVertical: placing ZEBRA vertically in index (0,0)");
          fivesq.addWordVertical("ZEBRA", 0, 0);
          System.out.println(fivesq);
          System.out.println("Testing addWordHorizontal [[error]]: placing PIG horizontally in index (1,0)");
          System.out.println("***SHOULD RESULT IN NO CHANGE b/c there is a pre-existing, non 'P' character!***");
          fivesq.addWordHorizontal("PIG", 1, 0);
          System.out.println(fivesq);
          System.out.println("Testing addWordVertical [[error]]: placing MATH vertically in index (0, 1)");
          System.out.println("***SHOULD RESULT IN NO CHANGE b/c there is a pre-existing, non 'M' character!***");
          fivesq.addWordVertical("MATH", 0, 1);
          System.out.println(fivesq);
          System.out.println("Testing addWordHorizontal & addWordVertical on words that are too big...expect no change");
          fivesq.addWordVertical("ELEPHANTS", 0, 1);
          fivesq.addWordHorizontal("ELEPHANTS", 1, 0);
          fivesq.addWordHorizontal("BROSKIDOODLE", 2, 0);
          fivesq.addWordVertical("RASTAFARIANISM", 0, 2);
          System.out.println(fivesq);
          System.out.println("Testing a few more addWordHorizontals & addWordVerticals (BREAD, RICE, EERIE, DRY, AERO, ZEROS, EONS)...");
          fivesq.addWordVertical("EERIE", 0, 1);
          fivesq.addWordHorizontal("BREAD", 2, 0);
          fivesq.addWordHorizontal("RICE", 3, 0);
          fivesq.addWordVertical("DRY", 2, 4);
          fivesq.addWordHorizontal("AERO", 4, 0);
          fivesq.addWordHorizontal("ZEROS", 0, 0);
          fivesq.addWordHorizontal("EONS", 1, 1);
          System.out.println(fivesq);

          System.out.println("////////////////////////////////////////////////////\n");

          System.out.println("Initializing new WordSearch (7x7 square)...");
          WordSearch sevensq = new WordSearch(7, 7);
          System.out.println(sevensq);
          System.out.println("Testing addWordHorizontal: placing SUPER horizontal in index (0,0)");
          sevensq.addWordHorizontal("SUPER", 0, 0);
          System.out.println(sevensq);
          System.out.println("Testing addWordVertical: placing SMASH vertically in index (0,0)");
          sevensq.addWordVertical("SMASH", 0, 0);
          System.out.println(sevensq);
          System.out.println("Testing addWordDiagonal: placing MARTH and SHEIK diagonally @ (1,0) & (0,0) respectively");
          sevensq.addWordDiagonal("SHEIK", 0, 0);
          sevensq.addWordDiagonal("MARTH", 1, 0);
          System.out.println(sevensq);
          System.out.println("Testing addWordDiagonal: placing IKE @ (3,3)");
          sevensq.addWordDiagonal("IKE", 3, 3);
          System.out.println(sevensq);
          System.out.println("Testing addWordDiagonal [[error]]: placing NESS @ (4, 0)...expect no change");
          sevensq.addWordDiagonal("NESS", 4, 0);
          System.out.println(sevensq);
          System.out.println("Testing addWordDiagonal [[error]]: placing RIDLEY @ (0, 4)...expect no change");
          sevensq.addWordDiagonal("RIDLEY", 0, 4);
          System.out.println(sevensq);

          System.out.println("--- END OF TEST ---\n");
     }
}
