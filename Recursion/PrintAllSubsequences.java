package Recursion;

import java.util.ArrayList;
import java.util.Arrays;

/*
https://www.youtube.com/watch?v=AxNNVECce8c&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=7

//Strategy : Each node has 2 choices ---> PICK / NOT PICK
 */

/*
TC : O( 2 pow n  * n)   ----> 2 choices (2 pow n) + O(n) to print the list at each i=n level .
SC : O(n)
 */
public class PrintAllSubsequences {

    public static void helper(int[] nums,int i,int n, ArrayList<Integer> list){

        if(i == n){
            System.out.println(list.toString());
            return;
        }

        //Pick
        list.add(nums[i]);
        helper(nums,i+1,n,list);

        //Not Pick
        list.remove(list.size()-1);
        helper(nums,i+1,n,list);
    }

    public static void printSubseq(int[] nums){
        helper(nums,0,nums.length,new ArrayList<Integer>());
    }

    public static void main(String[] args) {
        printSubseq(new int[]{3,1,2});

    }
}
