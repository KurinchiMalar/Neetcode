package HackerRankPractice.Arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
Given an array  of  integers and a number, , perform  left rotations on the array.
Return the updated array to be printed as a single line of space-separated integers.

I/p : 1 2 3 4 5
d = 4

Output : 5 1 2 3 4

 */
public class ArraysLeftRotation {
    /*
    TC : O(n)
    SC : O(n) // result array
     */
    public  List<Integer> rotLeft(List<Integer> a, int d) {

        int[] ar = a.stream().mapToInt(i->i).toArray();
        int n = ar.length;
        int[] result = new int[n];

        int rotTimes = d;
        if(n == 1) return Arrays.stream(ar).boxed().collect(Collectors.toList());

        int start = d; // this index elem will be the start elem in result

        result[0] = ar[start];
        int end = start + 1;
        int k = 1;
        while(end != start){
            if(end == n) end = 0;
            result[k] = ar[end];
            end++;
            k++;
        }
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    /*
    TC : O(n)
    SC : O(n)
     */
    public  List<Integer> rotLeftUsingMod(List<Integer> a, int d) {

        int[] ar = a.stream().mapToInt(i->i).toArray();

        int n = ar.length;
        int[] result = new int[n];

        int rotTimes = d;
        if(n == 1) return Arrays.stream(ar).boxed().collect(Collectors.toList());
        int k = 0;
        for(int i = 0 ; i < n ; i++){
            k = (i+rotTimes)%n;
            result[i] = ar[k];
        }
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        ArraysLeftRotation ob = new ArraysLeftRotation();

        System.out.println(ob.rotLeft(List.of(1,2,3,4,5),4));
        System.out.println(ob.rotLeft(List.of(1,2,3,4,5),2));
        System.out.println("********************************************");
        System.out.println(ob.rotLeftUsingMod(List.of(1,2,3,4,5),4));
        System.out.println(ob.rotLeftUsingMod(List.of(1,2,3,4,5),2));
    }

}
