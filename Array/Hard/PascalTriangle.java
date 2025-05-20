/*
✅ Problem:
Print Pascal's Triangle and solve three types of subproblems:
1. Find the element at a specific row and column (1-indexed).
2. Print the entire nth row.
3. Print the entire Pascal's triangle up to row n.

🧠 Intuition:
- Each element in Pascal’s Triangle is a binomial coefficient: nCr = n! / (r! * (n-r)!)
- The triangle follows this property: T[i][j] = T[i-1][j-1] + T[i-1][j]

⚙️ Algorithm:
1. **findnCr**: Efficient O(r) method to compute nCr using the iterative product method.
2. **printNthRow2**: Uses previously computed values to build a row in O(n).
3. **getPascalTriangle**: Builds the triangle by generating each row using printNthRow2.

⏱ Time Complexities:
- findnCr: O(r)
- printNthRow2: O(n)
- getPascalTriangle: O(n^2)

📦 Space Complexity:
- O(1) for individual operations (excluding output storage).
*/

package Array.Hard;
import java.util.*;

public class PascalTriangle {

    // ✅ Type 1: Find the element at row (n), column (r)
    public static long findnCr(int n, int r) {
        long res = 1;
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return res;
    }

    // ✅ Type 2: Print nth row of Pascal’s triangle using repeated nCr calls (O(n*r))
    public static void printNthRow1(int n) {
        for (int c = 1; c <= n; c++) {
            long res = findnCr(n - 1, c - 1);
            System.out.print(res + " ");
        }
        System.out.println();
    }

    // ✅ Type 2 Optimized: Return nth row of Pascal’s triangle using iterative formula (O(n))
    public static List<Integer> printNthRow2(int n) {
        List<Integer> ans = new ArrayList<>();
        int res = 1;
        ans.add(res);
        for (int i = 1; i < n; i++) {
            res = res * (n - i);
            res = res / i;
            ans.add(res);
        }
        return ans;
    }

    // ✅ Type 3: Generate the entire Pascal Triangle up to row n
    public static List<List<Integer>> getPascalTriangle(int n) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add(printNthRow2(i));
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 6, r = 3;

        // 🔹 Type 1: Find a specific element
        long ele = findnCr(n - 1, r - 1);
        System.out.println("Element at row " + n + " and column " + r + " is: " + ele);

        // 🔹 Type 2: Print entire nth row
        List<Integer> nthRow = printNthRow2(n);
        System.out.println("Row " + n + ": " + nthRow);

        // 🔹 Type 3: Print Pascal's triangle
        List<List<Integer>> triangle = getPascalTriangle(n);
        System.out.println("Pascal's Triangle up to row " + n + ":");
        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
    }
}
