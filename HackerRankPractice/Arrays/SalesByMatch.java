package HackerRankPractice.Arrays;

import java.util.List;

/*
// https://www.hackerrank.com/challenges/sock-merchant/problem
There is a large pile of socks that must be paired by color. Given an array of integers representing the color of each sock,
 determine how many pairs of socks with matching colors there are.

 Constraints : 1 <= n <= 100
               1 <= ar[i] <= 100 where 0 <= i < n
 */
public class SalesByMatch {

    /*
    TC : O(n)
    SC : O(n) // freqAr
     */
    public  int sockMerchant(int n, List<Integer> ar) {

        int[] freqAr = new int[101];
        //O(n)
        for(int num : ar){
            freqAr[num]++;
        }

        int pairs = 0;
        for(int i = 0 ; i < freqAr.length; i++){
            if(freqAr[i] != 0 ){
                pairs += freqAr[i]/2;
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        SalesByMatch ob = new SalesByMatch();
        System.out.println(ob.sockMerchant(7,List.of(1,2,1,2,1,3,2)));
        System.out.println(ob.sockMerchant(9,List.of(10,20,20,10,10,30,50,10,20)));
    }
}
