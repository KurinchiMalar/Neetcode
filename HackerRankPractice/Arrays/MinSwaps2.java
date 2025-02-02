package HackerRankPractice.Arrays;
/*
https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&isFullScreen=false&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
ou are given an unordered array consisting of consecutive integers  [1, 2, 3, ..., n] without any duplicates.
You are allowed to swap any two elements.
Find the minimum number of swaps required to sort the array in ascending order.

Example
arr = [7,1,3,2,4,5,6]

i   arr                         swap (indices)
0   [7, 1, 3, 2, 4, 5, 6]   swap (0,3)
1   [2, 1, 3, 7, 4, 5, 6]   swap (0,1)
2   [1, 2, 3, 7, 4, 5, 6]   swap (3,4)
3   [1, 2, 3, 4, 7, 5, 6]   swap (4,5)
4   [1, 2, 3, 4, 5, 7, 6]   swap (5,6)
5   [1, 2, 3, 4, 5, 6, 7]
It took  5 swaps to sort the array.
 */
public class MinSwaps2 {

    /*
     TC : O(n)
     SC : O(1)
     */
     int minimumSwaps(int[] arr) {

        int n = arr.length;
        int swapCount = 0;
        for(int i = 0 ; i < n; i++){
            while(arr[i] != i+1){
                int temp = arr[i];
                // place temp in correct position, which is arr[temp-1](preserve the element at that place to arr[i])
                arr[i] = arr[temp-1];
                arr[temp-1] = temp;
                swapCount++;
            }
        }

        return swapCount;


    }

    public static void main(String[] args) {
        MinSwaps2 ob = new MinSwaps2();
        System.out.println(ob.minimumSwaps(new int[]{7,1,3,2,4,5,6}));
        System.out.println(ob.minimumSwaps(new int[]{4,3,1,2}));

    }
}
