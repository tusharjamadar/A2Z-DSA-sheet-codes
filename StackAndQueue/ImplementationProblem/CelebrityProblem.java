import java.util.Stack;

public class CelebrityProblem {

    /*
     * ✅ Problem:
     * A celebrity is someone who:
     *   - Is known by everyone else (mat[i][celebrity] == 1 for all i != celebrity)
     *   - Knows no one else (mat[celebrity][i] == 0 for all i != celebrity)
     * Goal: Return the index of the celebrity if exists, otherwise return -1.
     */

    // ----------------------------------------------------------------
    // 💡 Solution 1: Two-Pointer Optimized Approach (O(1) Space)
    // ----------------------------------------------------------------

    /*
     * 💡 Intuition:
     * We can eliminate non-celebrities one by one. If person A knows B,
     * then A cannot be a celebrity. We keep updating the candidate accordingly.
     *
     * ⏱️ Time Complexity: O(n)
     * 🗃️ Space Complexity: O(1)
     */
    public int findCelebrityOptimized(int[][] mat) {
        int n = mat.length;
        int candidate = 0;

        // Step 1: Find the potential celebrity
        for (int i = 1; i < n; i++) {
            if (mat[candidate][i] == 1) {
                // Candidate knows i => not a celebrity
                candidate = i;
            }
        }

        // Step 2: Verify candidate
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;

            // Celebrity shouldn't know anyone and should be known by everyone
            if (mat[candidate][i] == 1 || mat[i][candidate] == 0) {
                return -1;
            }
        }

        return candidate;
    }

    // ----------------------------------------------------------------
    // 💡 Solution 2: Stack-Based Elimination Approach
    // ----------------------------------------------------------------

    /*
     * 💡 Intuition:
     * Push all people in a stack. Pop two at a time and eliminate one based
     * on who knows whom. The last remaining person is the candidate.
     *
     * ⏱️ Time Complexity: O(n)
     * 🗃️ Space Complexity: O(n)
     */
    public int findCelebrityStack(int[][] mat) {
        int n = mat.length;
        Stack<Integer> stack = new Stack<>();

        // Step 1: Push everyone into the stack
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        // Step 2: Eliminate using pairwise comparison
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();

            if (mat[a][b] == 1) {
                // a knows b => a can't be a celebrity
                stack.push(b);
            } else {
                // a doesn't know b => b can't be a celebrity
                stack.push(a);
            }
        }

        // No candidate left
        if (stack.isEmpty()) return -1;

        int candidate = stack.pop();

        // Step 3: Final verification
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;

            if (mat[candidate][i] == 1 || mat[i][candidate] == 0) {
                return -1;
            }
        }

        return candidate;
    }

    // ----------------------------------------------------------------
    // 🔍 Test the implementations
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        CelebrityProblem cp = new CelebrityProblem();

        int[][] mat1 = {
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };

        int[][] mat2 = {
            {1, 1},
            {1, 1}
        };

        int[][] mat3 = {
            {1}
        };

        System.out.println("Optimized Result:");
        System.out.println("Celebrity in mat1: " + cp.findCelebrityOptimized(mat1)); // Output: 1
        System.out.println("Celebrity in mat2: " + cp.findCelebrityOptimized(mat2)); // Output: -1
        System.out.println("Celebrity in mat3: " + cp.findCelebrityOptimized(mat3)); // Output: 0

        System.out.println("\nStack-Based Result:");
        System.out.println("Celebrity in mat1: " + cp.findCelebrityStack(mat1)); // Output: 1
        System.out.println("Celebrity in mat2: " + cp.findCelebrityStack(mat2)); // Output: -1
        System.out.println("Celebrity in mat3: " + cp.findCelebrityStack(mat3)); // Output: 0
    }
}
