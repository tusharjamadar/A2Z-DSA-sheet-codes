

/*
 * Generics in Java
 * -> Write once, use for any non-premitive type
 * -> Java Collections extensively use Generics
 * -> Generic Class/Interface and Methods
 * -> Type Safety
 */

public class GenericsDemo {

    public static <T> int count(T arr[], T x) {
        int res = 0;

        for (T ele : arr) {
            if (ele.equals(x)) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Integer arr[] = { 10, 20, 30, 40, 10, 30 };

        System.out.println(count(arr, 10));
    }
}
