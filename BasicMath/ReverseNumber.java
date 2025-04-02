
import java.util.Scanner;

public class ReverseNumber {

    public static int getReverseNumber(int n) {
        int rev = 0;

        while (n > 0) {
            int last = n % 10;
            rev = rev * 10 + last;
            n = n / 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number: ");
        int n = sc.nextInt();

        int res = getReverseNumber(n);

        System.out.println("Reverse of number " + n + " is: " + res);

    }
}
