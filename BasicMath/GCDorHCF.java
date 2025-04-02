
public class GCDorHCF {
    public static void main(String[] args) {
        int n1 = 10, n2 = 7;
        int minVal = Math.min(n2, n1);
        int gcd = 1;

        // for (int i = 2; i <= minVal; i++) {
        // if (n1 % i == 0 && n2 % i == 0)
        // gcd = i;
        // }
        // for (int i = minVal; i >= 2; i--) {
        // if (n1 % i == 0 && n2 % i == 0) {
        // gcd = i;
        // break;
        // }
        // }

        int a = n1, b = n2;

        // Euclidean Algorithm

        // gcd(a, b) -> gcd(a%b, b) where a > b;

        while (n1 > 0 && n2 > 0) {
            if (n1 > n2) {
                n1 = n1 % n2;
            } else {
                n2 = n2 % n1;
            }
        }

        if (n1 == 0) {
            gcd = n2;
        } else {
            gcd = n1;
        }

        System.out.println("GCD of (" + a + ", " + b + ") is " + gcd);
    }
}
