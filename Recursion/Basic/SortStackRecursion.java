package Basic;

import java.util.*;

public class SortStackRecursion {
    public static void solve(Stack<Integer> st){
        // Base Condition 
        if(st.size() == 1)return;

        // Hypothesis
        int temp = st.pop();
        solve(st);

        // induction

        insert(st, temp);
    }

    public static void insert(Stack<Integer> st, int temp){
        // base case
        if(st.size() == 0 || st.peek() <= temp){
            st.push(temp);
            return;
        }

        // Hypothesis
        int x = st.pop();
        insert(st, temp);

        // induction
        st.push(x);
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();

        st.push(5);
        st.push(3);
        st.push(1);
        st.push(4);
        st.push(2);
        st.push(5);

        solve(st);

        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
    }
}
