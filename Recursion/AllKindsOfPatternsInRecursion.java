package Recursion;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
House robber : https://www.youtube.com/watch?v=3WaxQMELSkw

waiting on

(Maxmimum sum of non adj elements) (Pick/Notpick)
https://www.youtube.com/watch?v=GrMBfJNk_NY


L6 over
L7 in progress
https://www.youtube.com/watch?v=eQCS_v3bw0Q&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=7


 */
public class AllKindsOfPatternsInRecursion {
    // Print subsequences whose sum is k
    /*
    PICK AND NOT PICK STRATEGY
    TC : O( 2 pow n  * n)   ----> 2 choices (2 pow n) + O(n) to print the list at each i=n level .
    SC : O(n)

    */
    public void helper(int[] nums , int i, int n , int sum , int k, ArrayList<Integer> temp){
        if(i == n){
            if(sum == k){
                System.out.println(temp.toString());
            }
            return;
        }
        //Pick
        temp.add(nums[i]);
        sum += nums[i];
        helper(nums,i+1,n,sum,k,temp);

        //Not pick
        temp.remove(temp.size()-1);
        sum -= nums[i];
        helper(nums,i+1,n,sum,k,temp);

    }

    public void printSubSequenceSumK(int[] nums,int k){
        helper(nums,0,nums.length,0,k,new ArrayList<Integer>());
    }

/********************************************************************************************************************************/
    public boolean helperBool(int[] nums, int i, int n , int sum , int k,ArrayList<Integer> temp ){
        if(i == n){

            //Condition satisfied
            if(sum == k){
                System.out.println(temp.toString());
                return true;
            }

            //Condition Not satisfied
            return false;
        }
        //Pick
        temp.add(nums[i]);
        sum += nums[i];
        if(helperBool(nums, i + 1, n, sum, k, temp)){ // avoiding other recursion calls if the solution is found
            return true;
        }
        //Not Pick
        temp.remove(temp.size()-1);
        sum-=nums[i];
        if(helperBool(nums, i + 1, n, sum, k, temp)){ // avoiding other recursion calls if the solution is found
            return true;
        }

        return false;
    }

    public void printOnlyOneSubSequenceSumK(int[] nums,int k){
        helperBool(nums,0,nums.length,0,k,new ArrayList<Integer>());
    }
 /******************************************************************************************************************************/
 public int helperCount(int[] nums , int i, int n , int sum , int k){
     if(i == n){
         if(sum == k){
             return 1;
         }
         return 0;
     }
     //Pick
     sum += nums[i];
     int left = helperCount(nums,i+1,n,sum,k);

     //Not pick
     sum -= nums[i];
     int right = helperCount(nums,i+1,n,sum,k);

     return left+right;
 }
    public int countSequenceSumK(int[] nums,int k){
        return helperCount(nums,0,nums.length,0,k);
    }
    public static void main(String[] args) {
        AllKindsOfPatternsInRecursion ob = new AllKindsOfPatternsInRecursion();

        //PICK AND NOT PICK STRATEGY EXPLAINED

        ob.printSubSequenceSumK(new int[]{1,2,1},2);

        System.out.println("******************************************************");
        // Print only one subsequence. Recursion implementation technique --> Functional Methods

        ob.printOnlyOneSubSequenceSumK(new int[]{1,2,1},2);

        System.out.println("******************************************************");
        //Count # of subsequences with sum K.
        System.out.println(ob.countSequenceSumK(new int[]{1,2,1},2));

    }
}
