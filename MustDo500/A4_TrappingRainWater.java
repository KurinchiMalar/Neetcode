package MustDo500;

/*


https://leetcode.com/problems/trapping-rain-water/description/
 */
public class A4_TrappingRainWater {

    /*
    My submission: https://leetcode.com/problems/trapping-rain-water/submissions/1264884022/
    TC :O(3*N) as we are traversing through the array only once. And O(2*N) for computing leftMax and rightMax array.
    SC : O(n) + O(n) for leftMax and rightMax
     */
    public static int trap(int[] height) {

        // Construct leftMax and rightMax array
        // unit of water stored at any index = min(leftMax, rightMax) - curHeight
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = 0;
        for(int i = 1 ; i < n; i++){
            leftMax[i] = Math.max(leftMax[i-1],height[i-1]);
        }

        rightMax[n-1] = 0;
        for(int j = n-2; j >=0 ; j--){
            rightMax[j] = Math.max(rightMax[j+1],height[j+1]);
        }
        int trap = 0;
        for(int i = 0 ; i < n ; i++){
            int minHeightOnBothSides = Math.min(leftMax[i],rightMax[i]);
            int watercollectedAtCurIndex = minHeightOnBothSides - height[i];
            watercollectedAtCurIndex = watercollectedAtCurIndex < 0 ? 0 : watercollectedAtCurIndex;
            trap += watercollectedAtCurIndex;
        }
        return trap;
    }

    /*
    Intution : https://takeuforward.org/data-structure/trapping-rainwater/
            Instead of 2 arrays at each index decide the min portion is left or right , and then calculate the water collected by comparing with maxSoFar in that portion
     TC : O(n)
     SC : O(1)
     */
    public static int trapEfficient_Using2Pointers(int[] height) {

        int n = height.length;

        int left = 0;
        int right = n-1;

        int leftMax = 0;
        int rightMax = 0;

        int result = 0;

        while(left < right){

            if(height[left] <= height[right]){ // left is min so water will be stored in the left portion only
                if(height[left] > leftMax){ // cannot store water
                    leftMax = height[left];
                }else{
                    result += leftMax - height[left];
                }
                left++;
            }else{ // right is min , so water will be stored in the right portion only
                if(height[right] > rightMax){ // cannot store water
                    rightMax = height[right];
                }else{
                    result += rightMax - height[right];
                }
                right--;
            }
        }
        return result;
    }
        public static void main(String args[]) {
        int arr[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Trapped water : " + trap(arr));
        System.out.println("Trapped water 2Pointer approach: " + trapEfficient_Using2Pointers(arr));

        }
}
