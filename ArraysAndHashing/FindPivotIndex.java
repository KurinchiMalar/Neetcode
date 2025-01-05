package ArraysAndHashing;
/*
https://leetcode.com/problems/find-pivot-index/description/
 */
public class FindPivotIndex {
    /*
    TC : O(n) + O(n) + O(n) ==> O(n)
    SC : O(n) + O(n)
     */
    public int pivotIndex(int[] nums) {

        if(nums == null || nums.length==1) return 0;
        int n = nums.length;

        int[] leftPrefix = new int[n];
        int[] rightPrefix = new int[n];
        leftPrefix[0] = 0;
        rightPrefix[n-1] = 0;
        for(int i = 1; i < n; i++){
            leftPrefix[i] = leftPrefix[i-1] + nums[i-1];
        }
        for(int j = n-2; j >= 0; j--){
            rightPrefix[j] = rightPrefix[j+1] + nums[j+1];
        }

        for(int k = 0 ; k < n; k++){
            if(leftPrefix[k] == rightPrefix[k]){
                return k;
            }
        }
        return -1;
    }

    /*
    TC : O(n)
    SC : O(n)
     */
    public int pivotIndexEfficient(int[] nums) {

        /*
        Let's try to calculate both left and right sum with one array
        */
        if(nums == null || nums.length == 1) return 0;
        // Construct prefixSumArr for entire array.
        int n = nums.length;
        int[] prefixSumArr = new int[n];

        int prevSum = 0;
        for(int i = 0 ; i < n; i++){
            prefixSumArr[i] = prevSum + nums[i];
            prevSum = prefixSumArr[i];
        }

        for(int i = 0 ; i < n; i++){
            int leftSum = (i > 0) ? prefixSumArr[i-1]:0; // for 0th position there is nothing left
            // last index has total sum.
            // totalsum - (curElemSum Including the leftsum) = rightSum
            int rightSum = prefixSumArr[n-1] - prefixSumArr[i];

            if(leftSum == rightSum){
                return i;
            }

        }
        return -1;

    }


    public static void main(String[] args) {
        FindPivotIndex ob = new FindPivotIndex();
        System.out.println(ob.pivotIndex(new int[]{1,7,3,6,5,6}));
        System.out.println(ob.pivotIndex(new int[]{1,2,3}));
        System.out.println(ob.pivotIndex(new int[]{2,1,-1}));
        System.out.println("******************* Efficient ******************");
        System.out.println(ob.pivotIndexEfficient(new int[]{1,7,3,6,5,6}));
        System.out.println(ob.pivotIndexEfficient(new int[]{1,2,3}));
        System.out.println(ob.pivotIndexEfficient(new int[]{2,1,-1}));
    }
}
