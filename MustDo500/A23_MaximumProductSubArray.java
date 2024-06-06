package MustDo500;

public class A23_MaximumProductSubArray {

    /*
    TC: O(n * n)
    SC: O(1)
     */
    public int maxProductBruteForce(int[] nums) {

        int n = nums.length;

        int maxProduct = Integer.MIN_VALUE;
        int curProduct = 1;
        // iterate through all subarrays and compare product
        for(int i = 0 ; i < n; i++){
            curProduct = 1;
            for(int j = i; j < n; j++){
                curProduct *= nums[j];
                maxProduct = Math.max(maxProduct,curProduct);
            }
        }
        return maxProduct;
    }
    /*
    Cases :
    1. All positives ---> product of all elems of array
    2. even negatives ---> product of all elems of array
    3. odd negatives ---> one negative has to be removed to get the max product ,

            Left Max product Of Whole array(PrefixMax)
            Right Max product Of Whole array(SuffixMax)
             Max of these two will be the result.
     4. has zeroes inbetween --> chunk those portions and compare the product of the chunks.
     */
    /*
    Case1 :- All the elements are positive : Then your answer will be product of all the elements in the array.
Case2 :- Array have positive and negative elements both :
If the number of negative elements is even then again your answer will be complete array because on multiplying all the negative numbers it will become positive.
If the number of negative elements is odd then you have to remove just one negative element and for that u need to check your subarrays to get the max product.
Case3 :- Array also contains 0 : Then there will be not much difference...its just that your array will be divided into subarray around that 0. What u have to so is just as soon as your product becomes 0 make it 1 for the next iteration, now u will be searching new subarray and previous max will already be updated.
     */
    /*
    TC: O(n)
    SC: O(1)
     */
    public int maxProductOptimal(int[] nums) {

        int n = nums.length;

        int maxProduct = Integer.MIN_VALUE;
        int prefixProduct = 1;
        int suffixProduct = 1;
        for(int i = 0 ; i < n ; i++){
            if (prefixProduct == 0) prefixProduct = 1;
            if (suffixProduct == 0) suffixProduct = 1;
            prefixProduct =  prefixProduct * nums[i];
            suffixProduct =  suffixProduct * nums[n-i-1];

            maxProduct = Math.max(maxProduct,Math.max(prefixProduct , suffixProduct));
        }
        return maxProduct==Integer.MIN_VALUE ? 0:maxProduct;

    }

    /*
    The following approach is motivated by Kandane’s algorithm. To know Kadane’s Algorithm follow Kadane's Algorithm

The pick point for this problem is that we can get the maximum product from the product of two negative numbers too.

Following are the steps for the approach:

Initially store 0th index value in prod1, prod2 and result.
Traverse the array from 1st index.
For each element, update prod1 and prod2.
Prod1 is maximum of current element, product of current element and prod1, product of current element and prod2
Prod2 is minimum of current element, product of current element and prod1, product of current element and prod2
Return maximum of result and prod1
     */

    public int maxProductKadaneAlgo(int[] nums){

        int n = nums.length;

        int result = nums[0];
        int prod1 = nums[0]; // max
        int prod2 = nums[0]; // min

        for(int i = 1; i < n; i++){
            int x = prod1 * nums[i];
            int y = prod2 * nums[i];
            int temp = Math.max(nums[i],Math.max(x,y));
            prod2 = Math.min(nums[i],Math.min(x,y));
            prod1 = temp;
            result = Math.max(result,prod1);
        }
        return result;
    }

    public static void main(String[] args) {
        A23_MaximumProductSubArray ob = new A23_MaximumProductSubArray();
        System.out.println("****************** BruteForce *******************************");
        System.out.println(ob.maxProductBruteForce(new int[]{2,3,-2,4}));
        System.out.println(ob.maxProductBruteForce(new int[]{-2,-1}));
        System.out.println(ob.maxProductBruteForce(new int[]{-1,1}));
        System.out.println(ob.maxProductBruteForce(new int[]{-2,1}));
        System.out.println(ob.maxProductBruteForce(new int[]{-2}));
        System.out.println(ob.maxProductBruteForce(new int[]{-2,0,-1}));
        //System.out.println(ob.maxProductBruteForce(new int[]{0,10,10,10,10,10,10,10,10,10,-10,10,10,10,10,10,10,10,10,10,0}));


        System.out.println("****************** Using Prefix and suffix product *******************************");
        System.out.println(ob.maxProductOptimal(new int[]{2,3,-2,4}));
        System.out.println(ob.maxProductOptimal(new int[]{-2,-1}));
        System.out.println(ob.maxProductOptimal(new int[]{-1,1}));
        System.out.println(ob.maxProductOptimal(new int[]{-2,1}));
        System.out.println(ob.maxProductOptimal(new int[]{-2}));
        System.out.println(ob.maxProductOptimal(new int[]{-2,0,-1}));
        //System.out.println(ob.maxProductOptimal(new int[]{0,10,10,10,10,10,10,10,10,10,-10,10,10,10,10,10,10,10,10,10,0}));
        System.out.println("****************** Using Kedane's Algo *******************************");
        System.out.println(ob.maxProductKadaneAlgo(new int[]{2,3,-2,4}));
        System.out.println(ob.maxProductKadaneAlgo(new int[]{-2,-1}));
        System.out.println(ob.maxProductKadaneAlgo(new int[]{-1,1}));
        System.out.println(ob.maxProductKadaneAlgo(new int[]{-2,1}));
        System.out.println(ob.maxProductKadaneAlgo(new int[]{-2}));
        System.out.println(ob.maxProductKadaneAlgo(new int[]{-2,0,-1}));



    }
}
