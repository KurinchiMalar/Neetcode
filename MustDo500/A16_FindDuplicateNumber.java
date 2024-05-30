package MustDo500;

import java.util.Arrays;
import java.util.HashMap;

/*
https://leetcode.com/problems/find-the-duplicate-number/
 */
public class A16_FindDuplicateNumber {

    /*
    TC : O(n)
    SC : O(n) // worstcase
     */
    public int findDuplicate_BruteExtraSpace(int[] nums) {

        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int num:nums){
            if(hmap.containsKey(num)){
                return num;
            }
            hmap.put(num,1);
        }
        return -1;
    }

    /*
    TC : O(n log n)
    SC : O(1)
     */
    public int findDuplicate_Sorting(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1 ; i < nums.length; i++){
            if(nums[i] == nums[i-1]) return nums[i];
        }
        return -1;
    }
    /*

     */

    /*
    TC: O(n)
    SC: O(1)
    My submission : https://leetcode.com/problems/find-the-duplicate-number/submissions/1271320959/
     */
    public int findDuplicate_ListCycleMethod(int[] nums) {
        int n = nums.length;
        int fast = 0;
        int slow = 0;

        //fast moves twice, slow moves once. Stop and re-initialize fast to 0
        while(true){
            if(fast >= n || slow >= n ) return -1;
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast == slow){
                fast = 0; // bring back fast to front
                break;
            }
        }
        // fast and slow move one step
        while(true){
            if(fast >= n || slow >= n ) return -1;
            fast = nums[fast];
            slow = nums[slow];
            if(fast == slow){
                return slow;
            }
        }
    }

    public static void main(String[] args) {
        A16_FindDuplicateNumber ob = new A16_FindDuplicateNumber();
        System.out.println("******************* Hashing ********************");
        System.out.println(ob.findDuplicate_BruteExtraSpace(new int[]{1,3,4,2,2}));
        System.out.println(ob.findDuplicate_BruteExtraSpace(new int[]{2,5,9,6,9,3,8,9,7,1}));
        System.out.println("******************* Sorting ********************");
        System.out.println(ob.findDuplicate_Sorting(new int[]{1,3,4,2,2}));
        System.out.println(ob.findDuplicate_Sorting(new int[]{2,5,9,6,9,3,8,9,7,1}));
        System.out.println("******************* Constant space - List cycle method ********************");

        System.out.println(ob.findDuplicate_ListCycleMethod(new int[]{1,3,4,2,2}));
        System.out.println(ob.findDuplicate_ListCycleMethod(new int[]{2,5,9,6,9,3,8,9,7,1}));



    }
}
