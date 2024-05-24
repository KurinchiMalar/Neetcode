package MustDo500;


import java.util.*;

/*
https://leetcode.com/problems/3sum/

 */
public class A6_3Sum {

    /*
    TC : O(n*n*n)
    Sc : O(1)
    Notice that the solution set must not contain duplicate triplets.

    But this brute force solution does contain duplicates.

    I/P {-1,0,1,2,-1,-4}  --> Op [[-1, 0, 1], [-1, 2, -1], [0, 1, -1]]
                               Expected op = [[-1,-1,2],[-1,0,1]]
     */
    public List<List<Integer>> threeSum_BruteForce(int[] nums) {

        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> curResultSet = new HashSet<>();

        for(int i = 0 ; i < n; i++){
            for(int j = i+1; j < n; j++){
                for(int k = j+1; k < n; k++){
                    if(nums[i]+nums[j]+nums[k] == 0){

                        curResultSet.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    }
                }
            }
        }
        result.addAll(curResultSet);
        return result;
    }
    /*
    TC: O(nlogn) + O(n*n) = O(n*n)
    Sc : O(1) ....O(n) for result
     */
    public List<List<Integer>> threeSum_Sorting(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> curResultSet = new HashSet<>();

        int n = nums.length;
        Arrays.sort(nums);
        for(int i = 0 ; i < n; i++){
            int left = i+1;
            int right = n-1;
            while(left < right){
                int sum = nums[i] + nums[left]+nums[right];
                if(sum == 0){
                    curResultSet.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                }else if(sum > 0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        result.addAll(curResultSet);
        return result;
    }


        public static void main(String[] args) {
        A6_3Sum ob = new A6_3Sum();
        System.out.println(ob.threeSum_BruteForce(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(ob.threeSum_Sorting(new int[]{-1,0,1,2,-1,-4}));

        }
}
