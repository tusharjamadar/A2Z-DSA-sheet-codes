
import java.util.ArrayList;
import java.util.List;

public class toArrayMethodDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(15);
        list.add(20);

        Integer[] arr = list.toArray(new Integer[0]);

        for (Integer x : arr) {
            System.out.println(x);
        }

    }
}
