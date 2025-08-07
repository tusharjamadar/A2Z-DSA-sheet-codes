package Graph.DisjointSet;

import java.util.*;

// ✅ Problem Summary:
// You're given a list of accounts where the first element is the name and the rest are emails.
// If two accounts share any common email, they belong to the same person.
// Merge such accounts and return each account with the name and sorted list of emails.

// ✅ Brute Force Approach (Explanation Only):
// For each account, check all other accounts to find a common email.
// If found, merge the accounts. This leads to O(n^2 * m) time complexity
// where n = number of accounts, m = number of emails per account.
// This is inefficient for large datasets.

// ✅ Optimized Approach using Disjoint Set:
// Treat each account as a node. If two accounts share an email, union them.
// Then, group all emails by their ultimate parent.
// Finally, sort emails and return the grouped accounts with names.

// Disjoint Set class using Union By Size and Path Compression
class DisjointSet {
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            size.add(1);
            parent.add(i);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) return node;

        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;

        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

public class AccountsMerge {

    // Main method to merge accounts
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mapMailNode = new HashMap<>();

        // Step 1: Union accounts with shared emails
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mapMailNode.containsKey(mail)) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        // Step 2: Group emails by ultimate parent
        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : mapMailNode.entrySet()) {
            String mail = entry.getKey();
            int node = ds.findUPar(entry.getValue());
            mergedMail[node].add(mail);
        }

        // Step 3: Create final answer with sorted emails and names
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0) continue;
            Collections.sort(mergedMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0)); // add name
            temp.addAll(mergedMail[i]);       // add emails
            ans.add(temp);
        }

        return ans;
    }

    // ✅ Dry Run Example:
    // Input:
    // [["John","johnsmith@mail.com","john_newyork@mail.com"],
    //  ["John","johnsmith@mail.com","john00@mail.com"],
    //  ["Mary","mary@mail.com"],
    //  ["John","johnnybravo@mail.com"]]
    //
    // 1st and 2nd John's share "johnsmith@mail.com" → merged.
    // Final Output:
    // [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
    //  ["Mary","mary@mail.com"],
    //  ["John","johnnybravo@mail.com"]]

    // ✅ Time Complexity:
    // O(N * M * α(N)) — where N = number of accounts, M = max emails per account, α is inverse Ackermann (almost constant)
    // Sorting takes O(E log E), where E is total number of unique emails.
    // Overall: O(N * M * α(N) + E log E)

    // ✅ Space Complexity:
    // O(N + E) — for Disjoint Set, hashmap, and mergedMail

    // ✅ main() Method for Testing
    public static void main(String[] args) {
        AccountsMerge sol = new AccountsMerge();

        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        input.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        input.add(Arrays.asList("Mary", "mary@mail.com"));
        input.add(Arrays.asList("John", "johnnybravo@mail.com"));

        List<List<String>> result = sol.accountsMerge(input);

        for (List<String> acc : result) {
            System.out.println(acc);
        }
    }
}
