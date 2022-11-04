package stacks;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */
/*
TC: O(2 pow n) ... 2 branches one for open and one for close. height of tree n ...hence total tc 2 pow n
SC : O(n)
https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParanthesis {
    public static void generateParanthesis(int n){
        List<String> list = new ArrayList<String>();
        backTrack(list,"",0,0,n);
        list.forEach(System.out::println); // print all elements in the result list.
    }

    /*
    The idea here is to only add '(' and ')' that we know will guarantee us a solution (instead of adding 1 too many close).
    Once we add a '(' we will then discard it and try a ')' which can only close a valid '('. Each of these steps are recursively called.
     */
    public static void backTrack(List<String> list, String s, int open, int close, int n){
        if(s.length() == n*2){
            list.add(s);
            return;
        }
        if(open < n ){
            backTrack(list,s+"(",open+1,close, n);
        }
        if (close < open) {
            backTrack(list,s+")",open, close+1,n);
        }
    }
    public static void main(String[] args){
        generateParanthesis(3);
        System.out.println("-------------");
        generateParanthesis(1);
        System.out.println("-------------");
        generateParanthesis(2);
    }
}
