/*
Given a string s, partition s such that every
substring
 of the partition is a
palindrome
. Return all possible palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]


Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */
package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public boolean isPalindrome(String s, int low, int high){
        while(low < high){
            if(s.charAt(low) != s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
    public List<List<String>> getPalindromePartitions(String s){
        List<List<String>> list = new ArrayList<>();
        backtrack(list,new ArrayList<>(),s,0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if(start == s.length()){
            list.add(new ArrayList<>(tempList));
        }

        for(int i=start; i < s.length(); i++){
            if(isPalindrome(s,start,i)){
                tempList.add(s.substring(start,i+1)); //until i from start
                backtrack(list,tempList,s,i+1);
                tempList.remove(tempList.size()-1);
            }
        }
    }

    public static void main(String[] args){
        PalindromePartitioning ob = new PalindromePartitioning();
        System.out.println(ob.getPalindromePartitions("aab"));
        System.out.println(ob.getPalindromePartitions("a"));
        System.out.println(ob.getPalindromePartitions("aa"));
        System.out.println(ob.getPalindromePartitions("madam"));



    }
}
