package Patterns;

public class Pattern10 {

    /*
    *
    **
    ***
    ****
    *****
    ****
    ***
    **
    *
     */

    public static void main(String[] args) {
        int n = 5;

        for (int i = 0; i <= 2 * n - 1; i++) {
            int stars = i;

            if (i > n)
                stars = 2 * n - i;

            for (int j = 1; j <= stars; j++) {
                System.err.print("*");
            }

            System.err.println();
        }
    }

}
