

import java.util.ArrayList;
import java.util.Collections;

public class PrintAllDivisors {
    public static void main(String[] args) {
        int n = 36;

        // for (int i = 1; i <= n; i++) {
        // if (n % i == 0)
        // System.out.print(i + " ");
        // }
        ArrayList<Integer> res = new ArrayList<>();

        int sqrtN = (int) Math.sqrt(n);

        for (int i = 1; i <= sqrtN; ++i) {
            if (n % i == 0) {
                res.add(i);

                if (i != n / i) {
                    res.add(n / i);
                }
            }
        }

        Collections.sort(res);

        System.out.println(res);

    }
}
