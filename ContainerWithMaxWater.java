/*
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1


Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

Input: array = [1, 5, 4, 3]
Output: 6
Explanation :
5 and 3 are distance 2 apart.
So the size of the base = 2.
Height of container = min(5, 3) = 3.
So total area = 3 * 2 = 6

Input: array = [3, 1, 2, 4, 5]
Output: 12
Explanation :
5 and 3 are distance 4 apart.
So the size of the base = 4.
Height of container = min(5, 3) = 3.
So total area = 4 * 3 = 12
 */
/*
https://leetcode.com/problems/container-with-most-water/submissions/
Time Complexity : O(n)
Space Complexity : O(1)
 */
class ContainerWithMaxWater {
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int head = 0;
        int tail = height.length-1;
        while(head < tail){
            int curArea = (tail-head)*Math.min(height[head],height[tail]);
            if(height[head] < height[tail]){
                head++;
            }else{
                tail--;
            }
            maxArea = curArea > maxArea ? curArea:maxArea;
        }
        return maxArea;
    }
    public static void main(String[] args){
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(maxArea(new int[]{1,1}));
        System.out.println(maxArea(new int[]{1, 5, 4, 3}));
        System.out.println(maxArea(new int[]{3, 1, 2, 4, 5}));
    }
}
