package MustDo500;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://leetcode.com/problems/word-break/description/
 */
/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.

 */
public class A29_WordBreak {

    /*
    The key idea is to check every possible prefix of the given string in the dictionary of words.
     If the prefix is found in the dictionary of words, run the recursive function for the rest of the string
      and at any point if the whole string is found, simply return True.
     */
    /*
    TC : O( 2 ^ n)
    SC : O(n)
    My submission : https://leetcode.com/problems/word-break/submissions/1280456691/
     */

    public boolean wordBreak_Recursive(String s, List<String> wordDict) {
        if(s == null || s.isEmpty()) return true;

        for(int i = 1 ; i <= s.length(); i++){
            String prefix = s.substring(0,i); // 0 ...i-1
            String suffix = s.substring(i); // i to n

            /*
            s = "apple"
            prefix = a
            suffix = pple

            prefix = ap
            suffix = ple ....
             */
            //If the recursive call for suffix returns true, we return true, otherwise we try next prefix.
            if(wordDict.contains(prefix) && wordBreak_Recursive(suffix,wordDict)){
                return true;
            }
        }
        return false;
    }
    /*
       My submission : https://leetcode.com/problems/word-break/submissions/1280462720/
       https://chatgpt.com/c/3fb4cd4a-92c2-4ff7-8c00-2343936b2a50
       TC : O(n * n)
       SC : O(n) for hashset, dp array
     */
    public boolean wordBreak_DP_BU(String s, List<String> wordDict) {

        // Lets take wordDict to Set for faster retrieval
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n+1];

        dp[0] = true; // empty string is breakable
        for(int i = 1 ; i <= n; i++){   // ----------------------------O(n)
            for(int j = 0; j < i ; j++){ // prefix 0 to i-1  // ----------------------------O(i)
                if (dp[j] && wordSet.contains(s.substring(j, i))) { // j to i-1.   {0  .... j ..... i-1} //-------O(k)
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        A29_WordBreak ob = new A29_WordBreak();
        System.out.println("***************** Recursion ***********************");
        System.out.println(ob.wordBreak_Recursive("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
        System.out.println(ob.wordBreak_Recursive("leetcode", Arrays.asList("leet","code")));
        System.out.println(ob.wordBreak_Recursive("applepenapple", Arrays.asList("apple","pen")));
        System.out.println(ob.wordBreak_Recursive("a", Arrays.asList("a","b")));

        System.out.println("***************** DP - Bottom up ***********************");
        System.out.println(ob.wordBreak_DP_BU("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
        System.out.println(ob.wordBreak_DP_BU("leetcode", Arrays.asList("leet","code")));
        System.out.println(ob.wordBreak_DP_BU("applepenapple", Arrays.asList("apple","pen")));
        System.out.println(ob.wordBreak_DP_BU("a", Arrays.asList("a","b")));


    }
}
