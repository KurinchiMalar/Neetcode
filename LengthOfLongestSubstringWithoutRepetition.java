/*
Given a string s, find the length of the longest substring without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */
/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
Time Complexity : O(n+n) = O(n)
worst case : "bbbbbbbbb"
Best case when there is no repetition at all:  eg) "abcdef"
Space Complextiy : O(n)
 */
import java.util.*;
public class LengthOfLongestSubstringWithoutRepetition {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        if(s==null){
            return 0;
        }
        int sLen = s.length();
        if(sLen == 0){
            return 0;
        }
        if(sLen == 1){
            return 1;
        }
        //pre-initialize first char of substring
        //say string is bb , when i=0 b is the maxSubstring so far with maxSubLen=1
        map.put(s.charAt(0),1);
        int maxSubLen = 1;
        for(int i=0,j=i+1; i < sLen && j < sLen; ){
            if(map.containsKey(s.charAt(j))){
                map.clear();
                maxSubLen = (maxSubLen < (j-1-i)+1) ? (j-1-i)+1:maxSubLen;
                i++;
                j=i+1;
                // pre-initialize first char of new substring
                map.put(s.charAt(i),1);
            }else{
                map.put(s.charAt(j),1);
                j++; // let i remain.
            }
        }
        // Loop could end on case where j > n but still i < n. eg) "au"
        if(!map.isEmpty()){
            int mapSum = map.values().stream().mapToInt(d->d).sum();
            maxSubLen = (maxSubLen < mapSum) ? mapSum:maxSubLen;
        }
        return maxSubLen;
    }
    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));

    }
}
