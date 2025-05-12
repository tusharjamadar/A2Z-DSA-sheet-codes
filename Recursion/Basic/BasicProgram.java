package Basic;
import java.util.Arrays;
import java.util.Collections;

public class BasicProgram {
    private static void printName(String name, int n) {
        if (n == 0)
            return;
        System.out.println(name);
        printName(name, n - 1);
    }

    private static void print1ToN(int n) {
        if (n == 0)
            return;
        print1ToN(n - 1);
        System.out.println(n);
    }

    private static void printNTo1(int n) {
        if (n == 0)
            return;
        System.out.println(n);
        printNTo1(n - 1);
    }

    private static int getSum(int n) {
        if (n == 0)
            return 0;

        return n + getSum(n - 1);
    }

    private static int getFactorial(int n) {
        if (n == 0)
            return 1;

        return n * getFactorial(n - 1);
    }

    private static void reverseArray(int[] arr, int i, int j) {
        if (i >= j)
            return;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        reverseArray(arr, i + 1, j - 1);

    }

    private static boolean checkPalindrom(String str, int i, int j) {
        if (i >= j)
            return true;

        if (str.charAt(i) != str.charAt(j))
            return false;

        return checkPalindrom(str, i + 1, j - 1);

    }

    private static int getFibonacci(int n) {
        if (n <= 1)
            return n;

        int a = getFibonacci(n - 1);
        int b = getFibonacci(n - 2);

        return a + b;
    }

    public static void main(String[] args) {
        String name = "Tushar";
        int n = 6;
        // printName(name, n);
        // print1ToN(n);
        // printNTo1(n);
        // System.out.println(getSum(n)); // sum = n * (n+1) / 2
        // System.out.println(getFactorial(5));

        // Reverse a array
        // int[] arr = new int[] { 1, 2, 3, 4, 5 };

        // reverseArray(arr, 0, arr.length - 1);

        // System.out.println(Arrays.toString(arr));

        // Check String is palindrom or not
        // String str = "aba";

        // boolean res = checkPalindrom(str, 0, str.length() - 1);

        // if (res) {
        // System.out.println("Given string is palindrom");
        // } else {
        // System.out.println("Given string is not palindrom");
        // }

        // get nth Fibonacci Number
        // 0 1 1 2 3 5 8
        System.out.println(getFibonacci(n));
    }
}
