/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9

Example 2:
Consider arr[] = {3, 0, 2, 0, 4}

Therefore, left[] = {3, 3, 3, 3, 4} and right[] = {4, 4, 4, 4, 4}
Now consider iterating using i from 0 to end

For i = 0:
        => left[0] = 3, right[0] = 4 and arr[0] = 3
        => Water stored = min(left[0], right[0]) – arr[0] = min(3, 4) – 3 = 3 – 3 = 0
        => Total = 0 + 0 = 0

For i = 1:
        => left[1] = 3, right[1] = 4 and arr[1] = 0
        => Water stored = min(left[1], right[1]) – arr[1] = min(3, 4) – 0 = 3 – 0 = 3
        => Total = 0 + 3 = 3

For i = 2:
        => left[2] = 3, right[2] = 4 and arr[2] = 2
        => Water stored = min(left[2], right[2]) – arr[2] = min(3, 4) – 2 = 3 – 2 = 1
        => Total = 3 + 1 = 4

For i = 3:
        => left[3] = 3, right[3] = 4 and arr[3] = 0
        => Water stored = min(left[3], right[3]) – arr[3] = min(3, 4) – 0 = 3 – 0 = 3
        => Total = 4 + 3 = 7

For i = 4:
        => left[4] = 4, right[4] = 4 and arr[4] = 4
        => Water stored = min(left[4], right[4]) – arr[4] = min(4, 4) – 4 = 4 – 4 = 0
        => Total = 7 + 0 = 7

So total rain water trapped = 7

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
/*
https://leetcode.com/problems/trapping-rain-water/submissions/
https://www.geeksforgeeks.org/trapping-rain-water/
Time Complexity: O(N). Only one traversal of the array is needed, So time Complexity is O(N).
Space Complexity: O(N). Two extra arrays are needed, each of size N.
 */
import java.util.Arrays;

class TrappingRainWater {
    /*
    waterTrappedAti = min(leftMax,rightMax)-height[i]
    */
    public static int trap(int[] height) {
        int trap = 0;
        int heightLen = height.length;
        int j = heightLen-1;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int[] left = new int[heightLen];
        int[] right = new int[heightLen];
        for(int i = 0; i < heightLen && j >= 0; i++,j--){

            if(leftMax < height[i]){
                leftMax = height[i];
            }
            if(rightMax < height[j]){
                rightMax = height[j];
            }
            left[i]=leftMax;
            right[i] = rightMax;
        }
        j = heightLen-1;
        for(int i = 0; i < heightLen && j >= 0; i++,j--){
            trap += Math.min(left[i],right[j])-height[i];
        }
        return trap;
    }
    public static void main(String[] args){
        //System.out.println(trap(new int[]{3, 0, 2, 0, 4}));
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

    }
}