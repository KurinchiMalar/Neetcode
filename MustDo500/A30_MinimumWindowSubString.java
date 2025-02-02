package MustDo500;
/*
https://leetcode.com/problems/minimum-window-substring/
Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.

 */

import java.util.HashMap;
import java.util.Map;

public class A30_MinimumWindowSubString {

    /*
    Tc : O(M ^ 3 + N) = O( M ^ 3)
    SC : O(n1)
     */
    public boolean containsAllChars(String substr,Map<Character,Integer> tMap){
        Map<Character,Integer> curMap = new HashMap<>(tMap);

        for(Character ch: substr.toCharArray()){  //--------------- O(m1) ...the entire source string can be a substring and thats the max worstcase length.
            if(curMap.containsKey(ch)){
                curMap.put(ch,curMap.getOrDefault(ch,0)-1);
                if(curMap.get(ch) == 0) curMap.remove(ch);
            }
        }
        return curMap.isEmpty();
    }
    public String minWindow_BruteForce(String s, String t) {
        if(s == null || s.isEmpty()) return "";
        if(t == null || t.isEmpty()) return "";
        int sLen = s.length();
        int tLen = t.length();
        // Map to hold all the required chars
        Map<Character,Integer> tMap = new HashMap<>();
        for(Character ch: t.toCharArray()){                    //--------------- O(n1)
            tMap.put(ch,tMap.getOrDefault(ch,0)+1);
        }

        int minLen = Integer.MAX_VALUE;
        String minWindowString = "";

        // for all substrings
        for(int i = 0 ; i < sLen; i++){        //--------------- O(m1 * m1)
            for(int j = i ; j < sLen; j++){
                String curSubStr = s.substring(i,j+1); // i to j substring
                int curSubLen = curSubStr.length();



                if(containsAllChars(curSubStr,tMap)){ //--------------- O(m1) (worst case)
                    if(curSubLen < minLen){
                        minLen = curSubLen;
                        minWindowString = curSubStr;
                    }
                }
            }
        }
        return minWindowString;
    }

    /*
    Idea :
        1) Keep all the expected chars and corresponding count in targetMap
        2) two pointers left and right (initially at 0 of source string)
             required = target.length

        3) Keep adding character to current window Map and move the right pointer.
            If we have formed the required length.A window with all characters of target from 0th position is obtained.
                    Let's try if there any more string that is shorter than current string.
                        Exclude the left most character of the current window and search for it in the rest.

     */
    /*
    https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/

    Readable code here
    https://chatgpt.com/c/3fb4cd4a-92c2-4ff7-8c00-2343936b2a50
     */
    /*
    my submission : https://leetcode.com/problems/minimum-window-substring/submissions/1289760529/
    TC : O(m + n)
        // O(n) for building tmap, O(m) for sliding window
    SC : O(k) //  where k is the number of unique characters in t
     */

    public String minWindow_SlidingWindow(String s, String t){
        if(s == null || s.isEmpty()) return "";
        if(t == null || t.isEmpty()) return "";

        int sLen = s.length();
        int tLen = t.length();
        if(sLen < tLen) return ""; // cannot form

        Map<Character,Integer> targetMap = new HashMap<>();
        // Required chars map
        for(Character ch: t.toCharArray()){ //--------------------------------------------------------- O(n)
            targetMap.put(ch,targetMap.getOrDefault(ch,0)+1);
        }

        int required = targetMap.size(); // unique chars
        int formed = 0; // formed so far
        int left = 0;
        int right = 0;
        Map<Character,Integer> curWindowMap = new HashMap<>();

        int[] result = new int[]{-1,0,0}; // minWindowLength, left,right
        while(right < sLen){ //---------------------------------------------------------  O(m)

            char ch = s.charAt(right);
            curWindowMap.put(ch,curWindowMap.getOrDefault(ch,0)+1);

            // ch is in targetMap and the freq matches in curWindow
            if(targetMap.containsKey(ch) && curWindowMap.get(ch).intValue() == targetMap.get(ch).intValue()){
                formed++;
            }

            // if we have formed a substring with all chars in target Already
            // try shrinking from left to get a better result
            while( left <= right && formed == required ){ //---------------------------------------------------------  O(m)
                // got a window so compare the length

                if(result[0] == -1 || (right-left+1) < result[0]){ // this is a better window
                    result[0] = right-left+1;
                    result[1] = left;
                    result[2] = right;
                }

                // check for better window excluding left
                char lch = s.charAt(left);
                // Adjust formed and curWindowMap accordingly
                curWindowMap.put(lch,curWindowMap.getOrDefault(lch,0)-1);
                if(targetMap.containsKey(lch) && curWindowMap.get(lch).intValue() < targetMap.get(lch).intValue()){
                    formed--;
                }
                // look for a better window
                left++;
            }
            // keep expandin right
            right++;
        }

        return result[0]==Integer.MAX_VALUE?"":s.substring(result[1],result[2]+1);

    }

    public static void main(String[] args) {
        A30_MinimumWindowSubString ob = new A30_MinimumWindowSubString();
        System.out.println("****************** BruteForce **********************");
        System.out.println(ob.minWindow_BruteForce("ADOBECODEBANC","ABC"));
        System.out.println(ob.minWindow_BruteForce("a","a"));
        System.out.println(ob.minWindow_BruteForce("a","aa"));
        System.out.println("****************** Optimal **********************");
        System.out.println(ob.minWindow_SlidingWindow("ADOBECODEBANC","ABC"));
        System.out.println(ob.minWindow_SlidingWindow("a","a"));
        System.out.println(ob.minWindow_SlidingWindow("a","aa"));
    }
}
