package MustDo500;
import java.util.ArrayList;
import java.util.List;
public class A7_LongestPalindromicSubstring {

    // TC: O(n)
    public boolean isPali(String s){
        int n = s.length();
        int start = 0;
        int end = n-1;
        while(start < end){
            if(s.charAt(start) != s.charAt(end))return false;
            start++;
            end--;
        }
        return true;
    }

    //TC: O(n^3)
    public List<String> getAllSubstrings(String s){
        int n = s.length();
        List<String> allSubStrings = new ArrayList<>();
        for(int i = 0 ; i < n; i++ ){
            for(int j = i ; j < n; j++){
                allSubStrings.add(s.substring(i,j+1));
            }
        }
        return allSubStrings;
    }

    /*
    TC : O(n^3)
    SC: O(1) ... O(n) for result
     */
    public String longestPalindrome_BruteForce(String s) {

        List<String> allSubStrings = getAllSubstrings(s);
        int maxLen = Integer.MIN_VALUE;
        String longestPali = null;

        for(String str: allSubStrings){
            if(isPali(str) && maxLen < str.length()){
                maxLen = str.length();
                //System.out.println("MaxLen: "+maxLen);
                longestPali = str;
            }
        }

        return longestPali;
    }
    //**********************************************************************************************************
    /*
    Bottom up Approach- DP

    Idea:
        baab

        consider i = 0 , j = 3 ==> for s[i][j] to be a palindrome the recurrence can be derived as follows:
                                                s[i+1][j-1] should be palindrome &&
                                                s[i] == s[j]
        There are 3 substrings taken for comparison here, hence min requirement is length 3. length 1 and 2 should be handled as basecases.
     */
    /*
    Reference : https://www.geeksforgeeks.org/longest-palindromic-substring/
    My submission : https://leetcode.com/problems/longest-palindromic-substring/submissions/1267051510/
    TC : O(n * n)
    SC : O(n * n)
     */

    public String longestPalindrome_DP_BU(String s){

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int maxLen = 1;
        // length 1
        for(int i = 0 ; i < n; i++){
            dp[i][i] = true;
        }
        //length 2
        int start = 0;
        for(int i = 0; i < n-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // from length 3
        for(int k = 3; k <= n; k++){
            for(int i = 0 ; i <= n-k; i++){

                // fix endpoint in this substring window
                int j = i + k -1;

                if(dp[i+1][j-1] && (s.charAt(i) == s.charAt(j)) ){
                    dp[i][j] = true;
                    if(k > maxLen){
                        start = i;
                        maxLen = k;
                    }
                }
            }
        }
        System.out.println(maxLen);
        return s.substring(start,start+maxLen);
    }
    //**********************************************************************************************************

    /*
        Idea :
            Expand from the center of every substring

            single char = palindrome (base case)
            next possible is 2 char, 3 char
                can be even or odd length substring. depending upon this place the left and right pointers.
     */
    /*
    https://leetcode.com/problems/longest-palindromic-substring/solutions/4212564/beats-96-49-5-different-approaches-brute-force-eac-dp-ma-recursion/
Algorithm :
1. At starting we have maz_str = s[0] and max_len = 1 as every single character is a palindrome.
2. Now, we will iterate over the string and for every character we will expand around its center.
3. For odd length palindrome, we will consider the current character as the center and expand around it.
4. For even length palindrome, we will consider the current character and the next character as the center and expand around it.
5. We will keep track of the maximum length and the maximum substring.
6. Print the maximum substring.

     */
    /*
    My submission : https://leetcode.com/problems/longest-palindromic-substring/submissions/1266287559/
    Tc : O(n^2). Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2).
    SC : O(1)
     */

    public String expandFromCenter(String s, int left, int right){
        int n = s.length();
        while(s.charAt(left) == s.charAt(right)){
            left--;
            right++;
            if(left < 0 || right >= n) break;
        }
        return s.substring(left+1,right); // abaa --> left = 2 , right = 3 , At this point left =1 and right = 4....so that substring will be "aa" (left+1 to right-1)
    }
    public String longestPalindrome(String s) {
        int n = s.length();

        if(n <= 1 ) return s;

        //At starting we have maxStr = s[0] and max_len = 1 as every single character is a palindrome.
        String maxStr = s.substring(0, 1);

        for(int i = 1 ; i < n; i++){ // 0 already covered in basecase
            // odd length  -- b
            String odd = expandFromCenter(s,i,i);
            String even = expandFromCenter(s,i-1,i);

            if(odd.length() > maxStr.length()){
                maxStr = odd;
            }
            if(even.length() > maxStr.length()) {
                maxStr = even;
            }

        }
        return maxStr;
    }



    public static void main(String[] args) {
        A7_LongestPalindromicSubstring ob = new A7_LongestPalindromicSubstring();
        //System.out.println(ob.isPali("madame"));
        System.out.println("*************** BruteForce ***************");
        System.out.println(ob.longestPalindrome_BruteForce("babad"));
        System.out.println(ob.longestPalindrome_BruteForce("cbbd"));
        System.out.println("*************** DP- Bottom up  ***************");
        System.out.println(ob.longestPalindrome_DP_BU("babad"));
        System.out.println("*************** 2pointer  ***************");
        System.out.println(ob.longestPalindrome("babad"));
       // System.out.println(ob.longestPalindrome_BruteForce("cbbd"));



    }
}
