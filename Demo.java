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
          fivesq.addWordVertical("MATH", 1, 0);
          System.out.println(fivesq);
          System.out.println("\n--- END OF TEST ---\n");
     }
}
