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
        int s1Len = s1.length();
        int s2Len = s2.length();
        HashMap<Character,Integer> s1Map = new HashMap<>();
        populateMap(s1,s1Map);
        // take a copy
        HashMap<Character,Integer> map = new HashMap<>();
        map.putAll(s1Map);
        for(int l=0,r=0; l < s2Len && r < s2Len; ){
            r=s1Len+l-1; //
            while(r >= l && r < s2Len){
                if(map.containsKey(s2.charAt(r))){
                    int curFreq = map.get(s2.charAt(r));
                    map.put(s2.charAt(r),curFreq-1);
                    if(curFreq-1 == 0){
                        map.remove(s2.charAt(r));
                    }
                    r--;
                }
                else{
                    break;
                }
            }
            if(map.isEmpty()){
                return true;
            }
            l++;
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

