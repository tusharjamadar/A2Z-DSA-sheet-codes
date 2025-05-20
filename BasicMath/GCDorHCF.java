/*
✅ Problem:
Find the **Greatest Common Divisor (GCD)** or **Highest Common Factor (HCF)** of two numbers.
The GCD of two numbers is the largest number that divides both of them without leaving a remainder.

🧠 Intuition:
The idea is to repeatedly reduce the larger number by the remainder when divided by the smaller number.
This process is based on the **Euclidean algorithm**, which is efficient and works by the property:
- gcd(a, b) = gcd(a % b, b), where a > b.

⚙️ Algorithm:
1. Initialize two numbers, `n1` and `n2`.
2. Apply the Euclidean algorithm until one of the numbers becomes zero.
3. The remaining non-zero number will be the GCD.
4. If `n1` becomes zero, `n2` is the GCD, and vice versa.

⏱ Time Complexity:
- O(log(min(n1, n2))), where n1 and n2 are the two numbers. The number of iterations is proportional to the number of digits in the smaller number.

📦 Space Complexity:
- O(1), since we're using a constant amount of extra space.
*/

public class GCDorHCF {
    public static void main(String[] args) {
        int n1 = 10, n2 = 7;
        int a = n1, b = n2;
        int gcd = 1;

        // Euclidean Algorithm to find GCD
        while (n1 > 0 && n2 > 0) {
            if (n1 > n2) {
                n1 = n1 % n2;
            } else {
                n2 = n2 % n1;
            }
        }

        // The remaining non-zero number will be the GCD
        if (n1 == 0) {
            gcd = n2;
        } else {
            gcd = n1;
        }

        // Output the GCD of the two numbers
        System.out.println("GCD of (" + a + ", " + b + ") is " + gcd);
    }
}
