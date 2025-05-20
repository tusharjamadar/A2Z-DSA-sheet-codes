/*
✅ Problem:
Check whether a given number is an **Armstrong number**.

A number is called an **Armstrong number** if the sum of its own digits each raised to the power of the number of digits equals the number itself.
For example:
153 = 1³ + 5³ + 3³ = 1 + 125 + 27 = 153 → Armstrong number

🧠 Intuition:
We decompose the number into its digits, raise each digit to the power equal to the number of digits, and sum them up.
If the final sum is equal to the original number, then it's an Armstrong number.

⚙️ Algorithm:
1. Convert the number to a string to find the number of digits (`k`).
2. Initialize a variable `sum` to 0.
3. Extract each digit from the number using modulo and division.
4. For each digit, raise it to the power `k` and add it to `sum`.
5. After processing all digits, compare `sum` with the original number.

⏱ Time Complexity:
- O(k), where k is the number of digits in the number.

📦 Space Complexity:
- O(1)
*/

public class ArmstrongNumber {

    public static boolean isArmstrong(int num) {
        int k = String.valueOf(num).length(); // Count of digits
        int sum = 0;
        int n = num;

        while (n > 0) {
            int ld = n % 10; // Last digit
            sum += Math.pow(ld, k); // Add digit^k to sum
            n /= 10; // Remove last digit
        }

        return sum == num;
    }

    public static void main(String[] args) {
        int number = 153;
        if (isArmstrong(number)) {
            System.out.println(number + " is an Armstrong number.");
        } else {
            System.out.println(number + " is not an Armstrong number.");
        }
    }
}
