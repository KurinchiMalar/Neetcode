package slidingWindow;/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
 */
/*
https://leetcode.com/problems/longest-repeating-character-replacement/submissions/
Time Complexity : O(26*n) = O(n)
Space Complexity : O(26)
 */
import java.util.*;
public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {

        if(s==null){
            return 0;
        }
        int sLen = s.length();
        int maxResultLen = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int left=0,right=0; left < sLen && right < sLen;){
            // put current char in map and update frequency
            map.put(s.charAt(right),map.getOrDefault(s.charAt(right),0)+1);

            int lenOfCurWindow = (right-left)+1;
            int numReplacementsReqdInCurWindow = lenOfCurWindow - Collections.max(map.values());

            if(numReplacementsReqdInCurWindow <= k){
                // valid window
                maxResultLen = maxResultLen < lenOfCurWindow ?  lenOfCurWindow:maxResultLen;
                right++;
            }else{
                map.put(s.charAt(left),map.get(s.charAt(left))-1);
                left++;
                right++;
            }
        }
        return maxResultLen;
    }
    public static void main(String[] args){
        System.out.println(characterReplacement("AABABBA",1));
        System.out.println(characterReplacement("ABAB",2));
        System.out.println(characterReplacement("A",1));


    }
}
