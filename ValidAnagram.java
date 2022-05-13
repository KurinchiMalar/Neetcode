/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false


Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

 */
//My solution
/*Time Complexity : O(m+n)
        m - length of string s ;  n - length of string t
        Space Complexity : O(n)
https://leetcode.com/problems/valid-anagram/
 */
import java.util.*;
class ValidAnagram {
    public boolean isAnagram(String s, String t) {

        if(s == null || t == null){
            return false;
        }
        HashMap<Character,Integer> hmap = new HashMap<>();
        for(char ch:s.toCharArray()){
            if(hmap.containsKey(ch)){
                hmap.put(ch,hmap.get(ch)+1);
            }else{
                hmap.put(ch,1);
            }

        }

        for(char ch:t.toCharArray()){
            if(hmap.containsKey(ch)){
                int curCount = hmap.get(ch);
                if(curCount > 1){
                    hmap.put(ch,curCount-1);
                }else{
                    hmap.remove(ch);
                }
            }else{
                return false;
            }
        }
        if(hmap.isEmpty()){
            return true;
        }
        return false;
    }
}
}