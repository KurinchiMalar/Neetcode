package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/problems/merge-intervals/

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104


IDEA:

main idea here is to sort by first value,
      then iterate entire interval to check if two intervals are overlapping.
         If yes, then merge;
         else add to result
 */
/*
TC: O(nlogn) //sorting
SC : O(n) //result list
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        //sort based on start timings -------> IMPORTANT!!!!!!!!!   (To be able to merge , you need to sort based on start timings and merge the endTimings acccordingly)
        Arrays.sort(intervals,Comparator.comparing(o -> o[0]));
        ArrayList<int[]> result = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];

        for(int[] interval:intervals){

            if(interval[0] <= end){
                //eligible for merge, so update end if applicable
                end = Math.max(end,interval[1]);
            }else {
                result.add(new int[]{start,end});
                //reintialize window
                start = interval[0];
                end = interval[1];
            }
        }
        result.add(new int[]{start,end}); // adding the final merged window whatever is available.
        return result.toArray(new int[result.size()][]);

    }


    public static void main(String[] args) {
        MergeIntervals ob = new MergeIntervals();
        int[][] intervals= {{1,4},{0,4}};
        System.out.println(Arrays.deepToString(ob.merge(intervals)));

    }
}
