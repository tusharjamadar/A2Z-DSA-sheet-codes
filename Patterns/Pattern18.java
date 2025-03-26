package Patterns;

public class Pattern18 {
    // E
    // D E
    // C D E
    // B C D E
    // A B C D E
    public static void main(String[] args) {
        int n = 5;

        for (int i = 0; i < n; i++) {

            char ch = (char) ('A' + n - i - 1);

            for (int j = 0; j <= i; j++) {
                System.out.print(ch + " ");
                ch++;
            }

            System.out.println();
        }
    }
}
