

public class CheckPrime {
    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 7;

        if (isPrime(n)) {
            System.out.println("The number " + n + " is a prime number.");
        } else {
            System.out.println("The number " + n + " is not a prime number.");
        }
    }
}
