package ArraysAndHashing;

import java.util.*;

/*
Given input array, remove the duplicates
 */
public class RemoveDuplicate {

    //UnSorted Array , using extra space
    public static int[] removeDuplicatesUnSortedExtraSpace(int[] arr) {
        Set<Integer> hset = new HashSet<>();
        //int[] result = new int[arr.length];
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < arr.length; i++){
            if(!hset.contains(arr[i])){
                result.add(arr[i]);
                hset.add(arr[i]);
            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }

    /*UnSorted Array, without extraspace
    //https://stackoverflow.com/questions/5414854/remove-duplicates-from-array-in-linear-time-and-without-extra-arrays
    public static int[] removeDuplicatesUnSortedWithoutExrraSpace(int[] arr) {
            //TODO

    }*/
    // Remove Duplicates in sorted Array inplace and return the size now
    public static int removeDuplicatesSortedInplace(int[] nums) {
        //Just maintaining another updated index i.e. j
        int j = 0;
        for(int i = 0 ; i < nums.length-1; i++){
            if(nums[i] != nums[i+1]){
                nums[j] = nums[i];
                j++;
            }
        }
        nums[j] = nums[nums.length-1];
        j++;
        return j;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeDuplicatesUnSortedExtraSpace(new int[]{1, 1, 2, 2, 3, 4, 5})));
        System.out.println((removeDuplicatesSortedInplace(new int[]{1, 1, 2, 2, 3, 4, 5})));
    }
}
