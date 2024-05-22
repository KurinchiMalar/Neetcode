package MustDo500;

/*
https://leetcode.com/problems/two-sum/
My submission : https://leetcode.com/problems/two-sum/submissions/1263957586/
 */

import java.util.HashMap;

public class A1_TwoSum {

    HashMap<Integer,Integer> hmap = new HashMap<>();

    //Time :O(n)
    //Space : O(n)
    public int[] twoSum(int[] nums, int target) {
        for(int i=0; i < nums.length; i++){
            int diff = target-nums[i];
            if(hmap.containsKey(diff)){
                return new int[]{i,hmap.get(diff)};
            }
            hmap.put(nums[i],i);
        }
        return new int[]{};
    }
}
