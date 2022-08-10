/*
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.



Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false


Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
 */
/*
Time Complexity :O(n)
Space Complexity : O(m) where m is s1.length()
https://leetcode.com/problems/permutation-in-string/submissions/
 */
import java.util.*;
class PermutionInString {
    public static void populateMap(String s1,HashMap<Character,Integer> s1FreqMap){
        for(Character ch:s1.toCharArray()){
            s1FreqMap.put(ch,s1FreqMap.getOrDefault(ch,0)+1);
        }
    }
    public static boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        HashMap<Character,Integer> s1Map = new HashMap<>();
        populateMap(s1,s1Map);
        // take a shallow copy
        HashMap<Character,Integer> map = new HashMap<>();
        map.putAll(s1Map);
        for(int left=0,right=0; left < n && right < n; ){
            // left to right window size should always be equal to m.
            right=m+left-1;

            while(right >= left && right < n){
                if(map.containsKey(s2.charAt(right))){
                    // reduce the frequency in the copy map denoting that we have seen the char in current window.
                    map.put(s2.charAt(right), map.get(s2.charAt(right))-1);
                    if( map.get(s2.charAt(right)) == 0){
                        map.remove(s2.charAt(right));
                    }
                    right--;
                }
                else{
                    break;
                }
            }
            if(map.isEmpty()){
                return true;
            }
            // Adjust window and reinitalize map.
            left++;
            map.clear();
            map.putAll(s1Map);
        }
        return false;
    }
    public static void main(String[] args){
        System.out.println(""+checkInclusion("ab","eidbaooo"));
        System.out.println(""+checkInclusion("ab","eidboaoo"));

    }
}

