package ArraysAndHashing;
/*
Given an array of integers arr, return true if and only if it is a valid mountain array.

Recall that arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]



Example 1:

Input: arr = [2,1]
Output: false
Example 2:

Input: arr = [3,5,5]
Output: false
Example 3:

Input: arr = [0,3,2,1]
Output: true


Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 104
 */
public class ValidMountainArray {

    /*
     TC : O(n)
     SC : O(1)
     */

    public boolean validMountainArray(int[] arr) {

        if( arr == null )return false;
        int n = arr.length;
        if(n == 0 || n == 1 || n == 2) return false; // you need min 3 elements to make a peak

        int peakIndex = -1;
        // reach until peak (rule : should be strictly increasing)
        for(int i = 1 ; i < n-1 ; i++){
            if(arr[i-1] > arr[i]) return false;
            if(arr[i-1] < arr[i] && arr[i] > arr[i+1]){
                peakIndex = i;
                break;
            }else if (arr[i-1] == arr[i] || arr[i] == arr[i+1]){
                return false;
            }
        }
        if(peakIndex == -1) return false;
        for(int i = peakIndex+1; i < n-1 ; i++){
            if(arr[i] <= arr[i+1]){
                return false;
            }
        }
        return true;
    }

    /*
    TC : O(n)
    SC : O(1)
     */
    public boolean validMountainArrayEfficient(int[] arr) {

        if( arr == null )return false;
        int n = arr.length;
        if(n < 3) return false; // you need min 3 elements to make a peak

        // logic : have two pointers left and right... keep moving according to condition, if they meet it is a mountain :)

        int left = 0 ;
        int right = n-1;

        /*while(left+1 < n && arr[left] < arr[left+1] )left++;
        while(right-1 >= 0 && arr[right] < arr[right-1])right--;*/

        while(left+1 < n-1 && arr[left] < arr[left+1] )left++;
        while(right-1 > 0 && arr[right] < arr[right-1])right--;
        // if met at edges it is not mountain they are just strictly increasing/decreasing

        // you can avoid additional condition by handling this in the while condition itself.
        //if(left == right && (left != 0) &&  (left != n-1)) return true;
        if(left == right) return true;
        return false;
    }

    public static void main(String[] args) {
        ValidMountainArray ob = new ValidMountainArray();
        System.out.println(ob.validMountainArray(new int[]{2,1}));
        System.out.println(ob.validMountainArray(new int[]{3,5,5}));
        System.out.println(ob.validMountainArray(new int[]{0,3,2,1}));
        System.out.println(ob.validMountainArray(new int[]{2,1,2,0}));
        System.out.println("********** Efficient Using two pointers *********************");
        System.out.println(ob.validMountainArrayEfficient(new int[]{2,1}));
        System.out.println(ob.validMountainArrayEfficient(new int[]{3,5,5}));
        System.out.println(ob.validMountainArrayEfficient(new int[]{0,3,2,1}));
        System.out.println(ob.validMountainArrayEfficient(new int[]{2,1,2,0}));
    }
}
