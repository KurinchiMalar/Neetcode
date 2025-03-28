package HackerRankPractice.Sorting;

import java.util.List;

/*
https://www.hackerrank.com/challenges/ctci-bubble-sort/problem
 */
public class BubbleSort {

    public static void swap(int[] ar, int i, int j){
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }
    public static int bubbleSort(int[] ar,int n){
        int swapCount = 0;
        for(int i = 0 ; i < n ; i++ ){
            for(int j = 0; j < n-1 ; j++){
                if(ar[j] > ar[j+1]){
                    swap(ar,j , j+1);
                    swapCount++;
                }
            }
        }
        return swapCount;
    }
    public static void countSwaps(List<Integer> a) {
        int[] ar = a.stream().mapToInt(i->i).toArray();
        int n = ar.length;

        int swapCount = bubbleSort(ar,n);
        System.out.println("Array is sorted in "+swapCount+" swaps.");
        System.out.println("First Element: "+ar[0]);
        System.out.println("Last Element: "+ar[n-1]);

    }

    public static void main(String[] args) {
        BubbleSort.countSwaps(List.of(1, 2, 3));
        BubbleSort.countSwaps(List.of(3,2,1));
    }

}
