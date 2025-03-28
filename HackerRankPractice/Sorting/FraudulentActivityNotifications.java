package HackerRankPractice.Sorting;

import java.util.Arrays;
import java.util.List;

/*

https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&isFullScreen=false&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting

Given the number of trailing days d and a client's total daily expenditures for a period of  days,
 determine the number of times the client will receive a notification over all  days.
ar = [10,20,30,40]
 [10,20,30] ---> median = 20 , days exp 40 ==> notification sent ( 40 >= 20)
 */
public class FraudulentActivityNotifications {

    public static int activityNotifications(List<Integer> expenditure, int d) {

        int[] expenditureAr = expenditure.stream().mapToInt(i->i).toArray();
        int n = expenditureAr.length;
        int notifCount = 0;
        if(n <= d){
            //only data is collected no notifications sent
            return 0;
        }
        for(int i = d; i < n;i++){

            double median = findMedianNaive(expenditureAr,i-d,i-1);
            if(expenditureAr[i] >= 2*median){
                notifCount++;
            }
        }
        return notifCount;
    }

    public static double findMedianNaive(int[] arr, int start, int end){
        int[] temp = Arrays.copyOfRange(arr, start, end + 1);
        Arrays.sort(temp);
        int n = temp.length;
        int mid = n/2;
        if(n % 2 != 0){ // odd
            return temp[mid];
        }
        // even
        return (temp[mid-1]+temp[mid])/2.0; // to avoid rounding off (Eg: 2.5 rounded of to 2)
    }

    public static void main(String[] args) {
        System.out.println(FraudulentActivityNotifications.activityNotifications(List.of(10,20,30,40,50),3));
    }
}
