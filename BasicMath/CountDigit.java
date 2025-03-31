package BasicMath;

import java.util.*;

public class CountDigit {

    public static int digitCount(int n) {
        // int cnt = 0;
        // while (n > 0) {
        // n /= 10;
        // cnt++;
        // }
        // return cnt;

        int cnt = (int) (Math.log10(n) + 1);
        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number: ");
        int n = sc.nextInt();

        int res = digitCount(n);

        System.out.println("Digit Count of number " + n + " is: " + res);

    }
}
