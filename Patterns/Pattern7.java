package Patterns;

public class Pattern7 {
    /*
     *
     ***
     *****
     *******
     *********
     * pyramid
     * 
     */

    public static void main(String[] args) {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < (n - i); j++) {
                System.err.print(" ");
            }
            for (int j = 0; j < (2 * i - 1); j++) {
                System.err.print("*");
            }
            System.err.println();
        }
    }
}
