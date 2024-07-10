package MustDo500.atlassianAlltime;

import java.util.Arrays;

/*
https://leetcode.com/problems/shortest-distance-to-a-character/

 */
/*
Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the distance from index i to the closest occurrence of character c in s.

The distance between two indices i and j is abs(i - j), where abs is the absolute value function.


Input: s = "loveleetcode", c = "e"
Output: [3,2,1,0,1,0,0,1,2,2,1,0]


Input: s = "aaab", c = "b"
Output: [3,2,1,0]

Input: s = "aaba", c = "b"
Output: [2,1,0,1 ]
 */
/*
TC : O(n)
SC : O(n) // for ans array
 */
public class ShortestDistanceToChar {

    public int[] shortestToChar(String s, char c) {
        int MAX = Integer.MAX_VALUE;
        int prev = MAX;
        int n = s.length();
        int[] ans = new int[n];
        // first pass
        for(int i = 0 ; i < n; i++){
            if(s.charAt(i) == c){
                prev = i;
                ans[i] = 0;
            }else{
                ans[i] = (prev == MAX)? MAX : i- prev;
            }
        }

        System.out.println("After first pass: "+ Arrays.toString(ans));
        prev = Integer.MAX_VALUE; // reinitialize prev
        //second pass
        for(int j = n-1; j >= 0; j--){
            if(s.charAt(j) == c){
                prev = j;
                ans[j] = 0;
            }else{
                ans[j] = Math.min(ans[j], prev-j);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ShortestDistanceToChar ob = new ShortestDistanceToChar();
        System.out.println(Arrays.toString(ob.shortestToChar("loveleetcode",'e')));
        System.out.println(Arrays.toString(ob.shortestToChar("aaab",'b')));
        System.out.println(Arrays.toString(ob.shortestToChar("aaba",'b')));

    }
}
