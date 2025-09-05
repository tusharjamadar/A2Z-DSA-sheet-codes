package String.Hard;

public class RabinKarpDoubleHashing {
    static int MOD1 = (int) 1e9+7;
    static int MOD2 = (int) 1e9+33;

    public long[] hashValue(String str, long RADIX1, long RADIX2, int m){
        long ans[] = new long[2], factor1 = 1, factor2 = 1;

        for(int i = m-1; i >= 0; i--){
            ans[0] += ((str.charAt(i) - 'a') * factor1) % MOD1;
            factor1 = (factor1 * RADIX1) % MOD1;
            ans[1] += ((str.charAt(i) - 'a') * factor2) % MOD2;
            factor1 = (factor2 * RADIX2) % MOD2;
        }

        return ans;
    }

    public int rabinKarp(String str, String pattern){
        int n = str.length(), m = pattern.length();

        if(n < m)return -1;
        
        long RADIX1 = 26, MAX_WEIGHT1 = 1;
        long RADIX2 = 27, MAX_WEIGHT2 = 1;

        for(long i = 1; i<=m; i++){
            MAX_WEIGHT1 = (MAX_WEIGHT1 * RADIX1) % MOD1;
            MAX_WEIGHT2 = (MAX_WEIGHT2 * RADIX2) % MOD2;
        }

        long[] patHash = hashValue(pattern, RADIX1, RADIX2, m), strHash = new long[2];

        for(int i = 0; i <= n - m; i++){
            if(i==0){
                strHash = hashValue(str, RADIX1, RADIX2, m);
            }else{
                strHash[0] = ((strHash[0] * RADIX1) % MOD1 - ((str.charAt(i-1) - 'a') * MAX_WEIGHT1) % MOD1 + (str.charAt(i + m -1) - 'a') + MOD1) % MOD1;
                strHash[1] = ((strHash[1] * RADIX2) % MOD2 - ((str.charAt(i-1) - 'a') * MAX_WEIGHT2) % MOD2 + (str.charAt(i + m -1) - 'a') + MOD2) % MOD2;
            }

            if(strHash[0] == patHash[0] && strHash[1] == patHash[1]){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RabinKarpAlgo sol = new RabinKarpAlgo();

        String str1 = "tusharjamadar", pat1 = "arj";

        System.out.println(sol.rabinKarp(str1, pat1));
        
    }
}
