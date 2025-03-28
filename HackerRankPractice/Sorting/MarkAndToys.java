package HackerRankPractice.Sorting;

import java.util.List;
import java.util.stream.Collectors;

/*
https://www.hackerrank.com/challenges/mark-and-toys
Given a list of toy prices and an amount to spend, determine the maximum number of gifts he can buy.

Note Each toy can be purchased only once.

prices = [1,2,3,4]
k=7

Budget is 7 , he can either buy [1,2,3] or [3,4] for 7 units

max is 3 {1,2,3}
 */
public class MarkAndToys {

    public static int maximumToys(List<Integer> prices, int k) {

        List<Integer> pricesSorted = prices.stream().sorted().collect(Collectors.toList());

        int boughtSoFar = 0;
        int countOfToys = 0;

        for(int price : pricesSorted){
            if(boughtSoFar + price > k){
                break;
            }
            boughtSoFar += price;
            countOfToys++;
        }
        return countOfToys;
    }

    public static void main(String[] args) {
        System.out.println(MarkAndToys.maximumToys(List.of(1,2,3,4),7));
        System.out.println(MarkAndToys.maximumToys(List.of(1,12,5,111,200,1000,10),50));

    }
}
