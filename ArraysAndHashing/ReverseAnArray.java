package ArraysAndHashing;

import java.util.Arrays;

// TC : O(n) , SC : O(1)
public class ReverseAnArray {

    static int[] reverse(int[] arr){

        if(arr == null || arr.length == 1 ){
            return arr;
        }

        int n = arr.length;
        int first = 0;
        int last = n-1;

        while(first < last){
            int temp = arr[first];
            arr[first] = arr[last];
            arr[last] = temp;
            first++;
            last--;
        }
        return arr;

    }
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        System.out.println(Arrays.toString(reverse(arr1)));
        int[] arr2 = {1,2,4,5};
        System.out.println(Arrays.toString(reverse(arr2)));
    }
}
