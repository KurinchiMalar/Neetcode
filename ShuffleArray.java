import java.lang.reflect.Array;
import java.util.Arrays;

/*
https://leetcode.com/problems/shuffle-the-array/
Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].

Return the array in the form [x1,y1,x2,y2,...,xn,yn].



Example 1:

Input: nums = [2,5,1,3,4,7], n = 3
Output: [2,3,5,4,1,7]
Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
Example 2:

Input: nums = [1,2,3,4,4,3,2,1], n = 4
Output: [1,4,2,3,3,2,4,1]
Example 3:

Input: nums = [1,1,2,2], n = 2
Output: [1,2,1,2]


Constraints:

1 <= n <= 500
nums.length == 2n
1 <= nums[i] <= 10^3
 */
public class ShuffleArray {

    /*
    TC : O(n) [while loop O(n) ==> i (0 to n-1) , j (n to 2n-1)]
    SC : O(m)
     */
    public int[] shuffle(int[] nums, int n) {
        if(nums == null) return new int[]{};
        int m = nums.length;
        int[] result = new int[m];
        if(m > 2*n) return result;

        int i = 0 ;
        int j = 0;

        // Move j and place it until n.
        j = j+n;

        // Now start shuffling
        int k = 0;
        while(i < j && j < m){   // -----------------------------------------> O(n) ==> i (0 to n-1) , j (n to 2n-1)
            result[k] = nums[i];
            result[k+1] = nums[j];
            k+=2;
            i++; j++;
        }
        return result;
    }

    /* Encode and Decode logic to do everything inplace
      Take a big number not in array and multiply to encode, to decode divide.
      bignumber = 1024 Gn: 1 <= nums[i] <= 10^3. 1000 is the max possible in array so lets take 1024.
   */
    /*
    TC : O(n) ...given number n , which is half of the length of the array
    SC : O(1)
     */
    public int[] shuffleInplace(int[] nums, int n) {
        if(nums == null) return new int[]{};

        int m = nums.length;
        int CONST = 1024;

        // I will multiply by CONST and add the number that will be shuffled with current elem,
        // so that we can retrieve by / and % the respective numbers during decoding

        // on right hand side we encode such that from each element both numbers (shuffle pair) can be retrieved.
        // Lets encode num

        // Start from nth position and the encoded values are put in the right part of the array
        for(int i = n ; i < m ; i++){
            // nums[i-n] is the element at the beginning to be shuffled with ,
            // we will retrieve both nums[i] and nums[i-n] from this equation
            nums[i] = (nums[i] * CONST) + nums[i-n];
        }

        // Lets decode and populate.
        int k = 0;
        for(int i = n ; i < m ; i++){
            nums[k] = nums[i] % CONST;
            nums[k+1] = nums[i] / CONST;
            k+=2;
        }
        return nums;

    }

    public static void main(String[] args) {
        ShuffleArray ob = new ShuffleArray();
        System.out.println(Arrays.toString(ob.shuffle(new int[]{2,5,1,3,4,7},3)));
        System.out.println(Arrays.toString(ob.shuffle(new int[]{1,2,3,4,4,3,2,1},4)));
        System.out.println(Arrays.toString(ob.shuffle(new int[]{1,1,2,2},2)));
        System.out.println("************** Efficient In place Shuffle *****************");
    }
}
