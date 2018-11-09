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

          System.out.println("--- END OF TEST ---\n");
     }
}
