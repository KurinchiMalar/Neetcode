package MustDo500;

import java.util.Arrays;

public class A17_ProductExceptSelf {

    /*
    My submission : https://leetcode.com/problems/product-of-array-except-self/
    TC: O(n)
    SC: O(n)
     */
    public int[] productExceptSelf_ExtraSpace(int[] nums) {
        int n = nums.length;
        int[] leftProduct = new int[n];
        int[] rightProduct = new int[n];
        int[] answer = new int[n];
        leftProduct[0] = 1;
        rightProduct[n-1] = 1;
        for(int i=1; i < n; i++){
            leftProduct[i] = nums[i-1]*leftProduct[i-1];
        }
        for(int j=n-2; j >= 0; j--){
            rightProduct[j] = nums[j+1]*rightProduct[j+1];
        }
        for(int i = 0; i < n; i++){
            answer[i] = leftProduct[i] * rightProduct[i];
        }
        return answer;

    }

    /*
    TC : O(n)
    SC : O(1)
    My submission : https://leetcode.com/problems/product-of-array-except-self/submissions/1271344650/
     */
    public int[] productExceptSelf_WithoutExtraSpace(int[] nums) {
        int n = nums.length;
        //int[] leftProduct = new int[n];
        //int[] rightProduct = new int[n];
        int[] answer = new int[n];
        Arrays.fill(answer,1);
        int cur = 1;
        for(int i=0; i < n; i++){
            answer[i] *= cur;
            cur *= nums[i]; // current i will be used for next iteration ...( product except self ---> thats why)
        }
        cur = 1;
        for(int j=n-1; j >= 0; j--){
            answer[j] *= cur;
            cur *= nums[j];
        }
        return answer;
    }

    public static void main(String[] args) {
        A17_ProductExceptSelf ob = new A17_ProductExceptSelf();
        System.out.println("******************* Using Extra Space ******************");
        System.out.println(Arrays.toString(ob.productExceptSelf_ExtraSpace(new int[] {1,2,3,4})));
        System.out.println("******************* Without Extra Space ******************");
        System.out.println(Arrays.toString(ob.productExceptSelf_WithoutExtraSpace(new int[] {1,2,3,4})));


    }
}
