package ArraysAndHashing;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.



Example 1:

Input: nums = [3,2,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2.
The third distinct maximum is 1.
Example 2:

Input: nums = [1,2]
Output: 2
Explanation:
The first distinct maximum is 2.
The second distinct maximum is 1.
The third distinct maximum does not exist, so the maximum (2) is returned instead.
Example 3:

Input: nums = [2,2,3,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2 (both 2's are counted together since they have the same value).
The third distinct maximum is 1.


Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


Follow up: Can you find an O(n) solution?
 */
public class ThirdMaximumNumber {

    /*
    Contains check --> O(n)
    insertion to pq if no duplicates ---> O(log n)
    For 1 element = O(n + log n)
    For n elements = O( N * (n + log n))


    TC : O(n * (n + logn)) + O(log n ) ===> O(n * n)
    SC : O(n)
    */
    public int thirdMax(int[] nums) {

        if(nums == null) return 0;
        if(nums.length == 1) return nums[0];

        PriorityQueue<Integer> pq = new PriorityQueue(Comparator.reverseOrder());
        for(int num: nums){  // O( N * (n + log n))
            if(!pq.contains(num)){
                pq.add(num);
            }
        }
        int k = 0;
        int max = 0;
        int mostMax = pq.peek();
        while(!pq.isEmpty() && k < 3){  // O( 3 * log n) = O(log n)
            max = pq.poll();
            k++;
        }

        return (k==3)?max:mostMax;
    }

    /*
    TC : O(n)
    SC : O(1)
     */
    public int thirdMaxEfficient(int[] nums) {

        if(nums == null) return 0;
        if(nums.length == 1) return nums[0];
        // We will not use Integer.MIN here we will compare between the 3 elements itself
        Integer max1 = null; // 1st max
        Integer max2 = null; // 2nd max
        Integer max3 = null; // 3rd max

        for(Integer n : nums){
            if(n.equals(max1) || n.equals(max2) || n.equals(max3)) continue; // skipping duplicates
            if(max1 == null || n > max1){
                max3 = max2;
                max2 = max1;
                max1 = n;
            }else if (max2 == null || n > max2){
                max3 = max2;
                max2 = n;
            }else if (max3 ==  null || n > max3){
                max3 = n;
            }
        }

        return (max3 == null)?max1:max3;

    }

    public static void main(String[] args) {
        ThirdMaximumNumber ob = new ThirdMaximumNumber();
        System.out.println(ob.thirdMax(new int[]{3,2,2,1}));
        System.out.println(ob.thirdMax(new int[]{1,6,2,2,2,2,2,2,2,2,1}));
        System.out.println(ob.thirdMax(new int[]{3,2,1}));
        System.out.println(ob.thirdMax(new int[]{1,2}));
        System.out.println(ob.thirdMax(new int[]{2,2,3,1}));
        System.out.println("***************** Efficient *****************");
        System.out.println(ob.thirdMaxEfficient(new int[]{3,2,2,1}));
        System.out.println(ob.thirdMaxEfficient(new int[]{1,6,2,2,2,2,2,2,2,2,1}));
        System.out.println(ob.thirdMaxEfficient(new int[]{3,2,1}));
        System.out.println(ob.thirdMaxEfficient(new int[]{1,2}));
        System.out.println(ob.thirdMaxEfficient(new int[]{2,2,3,1}));
    }
}
