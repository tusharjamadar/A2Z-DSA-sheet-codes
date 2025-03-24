package Patterns;

public class Pattern14 {
    /*
     * A
     * ABC
     * ABCDE
     * ABCDEFG
     * ABCDEFGHI
     */
    public static void main(String[] args) {
        int n = 5;

        for (int i = 0; i < n; i++) {
            for (char ch = 'A'; ch <= 'A' + i + i; ch++) {
                System.out.print(ch);

            }

            System.out.println();
        }
    }
}
