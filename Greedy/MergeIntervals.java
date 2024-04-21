package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/problems/merge-intervals/

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
        result.add(new int[]{start,end});
        return result.toArray(new int[result.size()][]);

    }


    public static void main(String[] args) {
        MergeIntervals ob = new MergeIntervals();
        int[][] intervals= {{1,4},{0,4}};
        System.out.println(Arrays.deepToString(ob.merge(intervals)));

    }
}
