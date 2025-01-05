package ArraysAndHashing;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/maximum-population-year/description/
You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year.
 The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1].
 Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.



Example 1:

Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
Example 2:

Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation:
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.


Constraints:

1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050
 */
/*
TC : N * 2 matrix --> O(N) + O(N * N) ==> O(N*N)
SC : O(N) --> for the years list
 */
public class MaximumPopulationYear {

    public int maximumPopulation(int[][] logs) {
        if(logs == null) return 0;
        if(logs.length == 1) return logs[0][0];

        List<Integer> years = new ArrayList<>();

        for(int[] log: logs){  // take out all the birth years , which becomes our source list of years
            years.add(log[0]);
        }
        int maxPoplnSoFar = 0;
        int resultYear = Integer.MAX_VALUE;
        for(int year: years){ // For each of the year...iterate through the entire logs and compute the population count.
            int curYearPopln = 0;

            for(int[] log : logs){
                int curB = log[0];
                int curD = log[1];

                if(curB <= year && year < curD){
                    curYearPopln++;
                }
            } // end of this total population of this year is determined

            if(curYearPopln > maxPoplnSoFar){
                maxPoplnSoFar = curYearPopln;
                resultYear = year;
            }else if(curYearPopln == maxPoplnSoFar){
                resultYear = Math.min(resultYear,year); // earliest year is desired
            }
        }
        return resultYear;
    }

    /*
    https://leetcode.com/problems/maximum-population-year/solutions/1198978/java-on-solution-with-explanation-range-4e3ei/
    O(n) solution using prefix sum

    // Given 1 <= logs.length <= 100

    TC : O( n + 100) ==> O(n) processing the logs , second log of prefix sum though 2051 is size of arr, we process for 100 logs/years  O(100)
        Generalizing O(n + No. of Years to process)
    SC : O(2051) ==> O(1) , can be simplified further
     by taking array of size 101 and not of 2051 because we only consider years from 1950 to 2050.
     */

    public int maximumPopulationEfficient(int[][] logs) {
        if(logs == null) return 0;
        if(logs.length == 1) return logs[0][0];

        // Given constraint says 1950 <= birthi < death <= 2050
        // Let's initialize an array of size 2051

        int[] arr = new int[2051]; // this will be filled with 0's by default

        // Every birth increments the population by 1
        // Every death decrements the population by 1
        for(int[] log: logs){
            arr[log[0]]++;
            arr[log[1]]--;
        }

        // compute the prefix sum and while doing that keep track of the maxPopulation so far, and the associated year.
        int maxPopSoFar = 0;
        int maxPopYear = Integer.MAX_VALUE;
        for(int i = 1 ; i < arr.length; i++){

            arr[i] = arr[i-1]+arr[i]; // this is current population for ith year
            if(arr[i] > maxPopSoFar){
                maxPopSoFar = arr[i];
                maxPopYear = i;
            }else if(arr[i] == maxPopSoFar){
                maxPopYear = Math.min(maxPopYear,i); // earliest year is desired.
            }
        }
        return maxPopYear;
    }

    public static void main(String[] args) {
        MaximumPopulationYear ob = new MaximumPopulationYear();
        System.out.println("************* Naive ***************");
        System.out.println(ob.maximumPopulation(new int[][]{{1993,1999},{2000,2010}}));
        System.out.println(ob.maximumPopulation(new int[][]{{1950,1961},{1960,1971},{1970,1981}}));
        System.out.println("****************** Efficient **************************");
        System.out.println(ob.maximumPopulationEfficient(new int[][]{{1993,1999},{2000,2010}}));
        System.out.println(ob.maximumPopulationEfficient(new int[][]{{1950,1961},{1960,1971},{1970,1981}}));
    }
}
