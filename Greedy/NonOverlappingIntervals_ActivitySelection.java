package Greedy;
import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/problems/non-overlapping-intervals/
TC : O(nlogn)+O(n) = O(nlogn)
SC :O(1)
 */
public class NonOverlappingIntervals_ActivitySelection {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        // sort intervals based on end time
        Arrays.sort(intervals,Comparator.comparing(o -> o[1]));
        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int endTimeSoFar = intervals[0][1];
        int overLapCount = 0;
        for(int i = 1 ; i < n ; i++){
            if(intervals[i][0] < endTimeSoFar ){
                overLapCount++;
                continue; // skip this as it causes overlap
            }
            endTimeSoFar = intervals[i][1];
        }
        return overLapCount;

    }

    public int eraseOverlapIntervalsAnotherImpl(int[][] intervals) {
        int n = intervals.length;
        // sort intervals based on end time
        Arrays.sort(intervals,Comparator.comparing(o -> o[1]));
        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int prev = 0;

        int overLapCount = 0;
        for(int i = 1 ; i < n ; i++){
            if(intervals[i][0] < intervals[prev][1] ){
                overLapCount++;
                continue; // skip this as it causes overlap
            }
            //update the window
            prev = i;
        }
        return overLapCount;

    }
    public static void main(String[] args) {

        NonOverlappingIntervals_ActivitySelection ob = new NonOverlappingIntervals_ActivitySelection();
        int[][] intervals1 = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(ob.eraseOverlapIntervals(intervals1));
        System.out.println(ob.eraseOverlapIntervalsAnotherImpl(intervals1));


        int[][] intervals2 = {{1,2},{1,2},{1,2}};
        System.out.println(ob.eraseOverlapIntervals(intervals2));
        System.out.println(ob.eraseOverlapIntervalsAnotherImpl(intervals2));

        int[][] intervals3 = {{1,2},{2,3}};
        System.out.println(ob.eraseOverlapIntervals(intervals3));
        System.out.println(ob.eraseOverlapIntervalsAnotherImpl(intervals3));



    }
}
