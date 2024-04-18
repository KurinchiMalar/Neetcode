package ArraysAndHashing.BeginnerProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]


Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.

 */
class GroupAnagrams {

    /*
    TimeComplexity : O(m*n*26) = O(m*n)
         where m - # of total strings, n = average length of each string , 26 freqAr length.
    SpaceComplexity :O(m)
           worst case when there are no anagram pairs...
           all strings will have corresponding  keys in map.

      https://leetcode.com/problems/group-anagrams/submissions/
      https://github.com/KurinchiMalar/Neetcode/blob/Arrays/GroupAnagrams.java
     */
    public static List<List<String>> groupAnagramsEfficient(String[] strs) {
        if(strs==null || strs.length==0){
            return new ArrayList<>();
        }
        HashMap<String,ArrayList<String>> hmap = new HashMap<>();
        for(String s:strs){
            char[] freq = new char[26];
            for(Character ch:s.toCharArray()){
                freq[ch-'a']++;
            }
            String curStringCharacterFreqValue = String.valueOf(freq);
            if(!hmap.containsKey(curStringCharacterFreqValue)){
                hmap.put(curStringCharacterFreqValue,new ArrayList<>());
            }
            hmap.get(curStringCharacterFreqValue).add(s);
        }
        return new ArrayList<>(hmap.values());
    }

        public static void main(String[] args){
        //String[] strs={"eat","tea","tan","ate","nat","bat"};
            String[] strs={"eat","tea"};
        //List<List<String>> resultList = groupAnagrams(strs);
            List<List<String>> resultList = groupAnagramsEfficient(strs);
        System.out.println(resultList);
    }
}