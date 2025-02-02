package HackerRankPractice.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given an array and you need to find number of tripets of indices (i,j,k) such that the elements
at those indices are in geometric progression for a given common ratio r  and  i < j < k

 arr= [1,4,16,64] r = 4

 2 triplets ==> 1,4,16
                4,16,64

  https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&isFullScreen=true&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
 */
public class CountTriplets {

     long countTriplets(List<Long> arr, long r) {
        // a/r , a, ar = GP we try to identify this pattern
        Map<Long,Long> leftMap = new HashMap<>();
        Map<Long,Long> rightMap = new HashMap<>();
        int n = arr.size();

        // populate rightMap first
        for(Long x : arr){
            rightMap.put(x,rightMap.getOrDefault(x, 0L)+1);
        }

        long count = 0;

        for(int i = 0 ; i < n; i++){

            long cur= arr.get(i); // middle elem of a/r , a, ar
            long c1 = 0; long c2 = 0;

            // this element's ocurrence to be removed from right map
            rightMap.put(cur, rightMap.get(cur)-1);

            // check how many a/r is available on left
            if( leftMap.containsKey(cur/r) && (cur % r == 0) ){
                c1 = leftMap.get(cur/r);
            }

            if(rightMap.containsKey(cur*r)){
                c2 = rightMap.get(cur*r);
            }
            count += (c1 * c2);
            // for next iteration as i will increment this element occurence has to be added to leftMap
            leftMap.put(cur, leftMap.getOrDefault(cur, 0L)+1);
        }
        return count;

    }

    public static void main(String[] args) {
        CountTriplets ob = new CountTriplets();
        System.out.println(ob.countTriplets(List.of(1L,4L,16L,64L),4));
    }


}
