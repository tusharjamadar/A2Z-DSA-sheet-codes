

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpression {

    static void printCond(Collection<Integer> C, Predicate<Integer> p) {
        for (Integer x : C) {
            if (p.test(x))
                System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {
        // class myPredicateEven implements Predicate<Integer> {
        // public boolean test(Integer x) {
        // return x % 2 == 0;
        // }
        // }

        // Predicate<Integer> myPreEven = new Predicate<Integer>() {
        // public boolean test(Integer x) {
        // return x % 2 == 0;
        // }
        // };

        List<Integer> al = new ArrayList<>(Arrays.asList(10, 5, 20, 7, 30));

        // printCond(al, new myPredicateEven());
        // printCond(al, myPreEven);

        // with lambda function
        printCond(al, x -> x % 2 == 0);
    }
}
