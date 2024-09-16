import java.util.Scanner;
import java.util.Arrays;

class TwoDArrays {
   static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      int[][] myTable = new int[4][7];

      fillInRows(myTable);
      System.out.println("Elements in table:");
      print2DArray(myTable);

      fillInCols(myTable);
      System.out.println("Elements in table:");
      print2DArray(myTable);
   }

   static void print2DArray(final int[][] array) {
      System.out.println("[");
      for (final int[] row : array) {
         System.out.println("\t" + Arrays.toString(row));
      }
      System.out.println("]");
   }

   static void fillInRows(int[][] table) {
      for (int row = 0; row < table.length; ++row) {
         for (int col = 0; col < table[0].length; ++col) {
            System.out.println("Please enter an integer");
            table[row][col] = scanner.nextInt();
         }
      }
   }

   static void fillInCols(int[][] table) {
      for (int col = 0; col < table[0].length; ++col) {
         for (int row = 0; row < table.length; ++row) {
            System.out.println("Please enter an integer");
            table[row][col] = scanner.nextInt();
         }
      }
   }
}
