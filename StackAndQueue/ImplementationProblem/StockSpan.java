import java.util.Stack;

/*
 * 901. Online Stock Span
 *
 * ✅ Problem:
 * Implement the StockSpanner class that computes the **stock span** for each day.
 * The span of the stock's price today is the number of consecutive previous days
 * where the price was less than or equal to today's price.
 *
 * 💡 Intuition: (Previous Greater Element to current element index - current index)
 * Use a stack to maintain prices along with their indices (or spans).
 * For each new price:
 *   - Pop all previous prices from the stack that are ≤ current price.
 *   - The span is the distance between the current day and the last day with a greater price.
 *   - Push the current price and its index onto the stack.
 *
 * 🧠 Algorithm:
 * 1. Maintain a stack of pairs (price, index).
 * 2. For each new price:
 *    a. Increment index.
 *    b. Pop from stack until top price is > current price.
 *    c. Calculate span = current index - top's index (or -1 if stack is empty).
 *    d. Push (price, index) to stack.
 *    e. Return span.
 *
 * ⏱️ Time Complexity: Amortized O(1) per operation (since each element is pushed and popped once).
 * 🗃️ Space Complexity: O(n), where n is the number of calls to next().
 */

// Helper class to store price and its index
class Pair {
    int price;
    int index;

    Pair(int price, int index) {
        this.price = price;
        this.index = index;
    }
}

class StockSpan {
    Stack<Pair> st;  // Stack to store (price, index) pairs
    int ind;         // Current day index

    public StockSpan() {
        st = new Stack<>();
        ind = -1; // Start from -1 to represent "before first day"
    }

    public int next(int price) {
        ind++;  // Move to next day

        // Remove all prices from the stack that are less than or equal to current price
        while (!st.isEmpty() && st.peek().price <= price) {
            st.pop();
        }

        // Calculate span: distance to previous greater price
        int span = ind - (st.isEmpty() ? -1 : st.peek().index);

        // Push the current price and its index
        st.push(new Pair(price, ind));

        return span;
    }
}

// 🔍 Example usage
class Main {
    public static void main(String[] args) {
        StockSpan stockSpanner = new StockSpan();

        System.out.println(stockSpanner.next(100)); // Output: 1
        System.out.println(stockSpanner.next(80));  // Output: 1
        System.out.println(stockSpanner.next(60));  // Output: 1
        System.out.println(stockSpanner.next(70));  // Output: 2
        System.out.println(stockSpanner.next(60));  // Output: 1
        System.out.println(stockSpanner.next(75));  // Output: 4
        System.out.println(stockSpanner.next(85));  // Output: 6
    }
}
