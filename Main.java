import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    // ---------------- FAST INPUT ----------------
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }

    // ---------------- FAST OUTPUT ----------------
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    // ---------------- UTILITY FUNCTIONS ----------------
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    // Modular Exponentiation (a^b % mod)
    static long modPow(long a, long b, long mod) {
        long res = 1;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }

    // Check if prime
    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0 && n != 2) return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Sort array quickly
    static void sort(int[] arr) { Arrays.sort(arr); }
    static void sort(long[] arr) { Arrays.sort(arr); }

    // Reverse array
    static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Prefix sum
    static long[] prefixSum(int[] arr) {
        long[] pref = new long[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            pref[i + 1] = pref[i] + arr[i];
        }
        return pref;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main (String[] args) throws java.lang.Exception {
        FastReader sc = new FastReader();

        int t = sc.nextInt(); 
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

            long sum = 0;
            for (int x : arr) sum += x;

            out.println("Sum = " + sum);
            out.println("GCD(12, 18) = " + gcd(12, 18));
            out.println("LCM(12, 18) = " + lcm(12, 18));
            out.println("2^10 mod 1e9+7 = " + modPow(2, 10, 1000000007));
        }
        out.flush();
    }
}
