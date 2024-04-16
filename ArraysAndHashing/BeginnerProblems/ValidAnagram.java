package ArraysAndHashing.BeginnerProblems;/*
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

import java.util.HashMap;
class ValidAnagram {

    /*
    TC : O(m) --> m is the length of s (s and t length must be equal for valid anagram)
    SC : O(m)
    */
    public static boolean isAnagram(String s, String t) {
        int sourceLen = s.length();
        if (s == null || t == null) {
            return false;
        }
        if (sourceLen != t.length()) {
            return false;
        }

        HashMap<Character, Integer> hmap = new HashMap<>();
        // for all strings with s, t of same length

        for (int i = 0; i < sourceLen; i++) {
            hmap.put(s.charAt(i), hmap.getOrDefault(s.charAt(i), 0) + 1);
            hmap.put(t.charAt(i), hmap.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (Character ch : hmap.keySet()) {
            if (hmap.get(ch) != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("cat,rat :" + isAnagram("cat", "rat"));
        System.out.println("aa,bb :" + isAnagram("aa", "bb"));
        System.out.println("aba,baa :" + isAnagram("aba", "baa"));
        System.out.println("anagram,gramana :" + isAnagram("anagram", "gramana"));
        /*System.out.println("aa,aaaaa :" + isAnagram("aa", "aaaaa"));
        System.out.println("duh,ill :" + isAnagram("duh", "ill"));
        System.out.println("duh,ill :" + isValidAnagramAsciiSum("duh", "ill"));*/
    }
}
