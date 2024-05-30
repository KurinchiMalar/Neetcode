package MustDo500;

/*

https://leetcode.com/problems/merge-intervals/description/

My submission : https://leetcode.com/problems/merge-intervals/submissions/1271163203/
 */
/*
IDEA:
    sort based on the start timings and check eligibility to merge based on end timings.
 */
/*
TC: O(nlogn)
SC : O(1)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class A14_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        int n = intervals.length;
        // sort based on start timings
        Arrays.sort(intervals, Comparator.comparing(ar -> ar[0]));

        int start_i = intervals[0][0];
        int end_i = intervals[0][1];

        for(int i = 1 ; i < n; i++){
            if(intervals[i][0] <= end_i){ //eligible to merge
                end_i = Math.max(end_i,intervals[i][1]);
                //start_i = Math.min(start_i,intervals[i][0]); (already sorted)
            }else{
                result.add(new int[]{start_i,end_i}); // finish off the window
                start_i = intervals[i][0];
                end_i = intervals[i][1];
            }
        }
        result.add(new int[]{start_i,end_i}); // last elem
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        A14_MergeIntervals ob = new A14_MergeIntervals();
        System.out.println(Arrays.deepToString(ob.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}})));
        System.out.println(Arrays.deepToString(ob.merge(new int[][]{{1,4},{0,4}})));
        System.out.println(Arrays.deepToString(ob.merge(new int[][]{{1,4},{0,0}})));

    }
}
