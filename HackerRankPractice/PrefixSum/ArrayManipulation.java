package HackerRankPractice.PrefixSum;
/*
https://www.hackerrank.com/challenges/crush/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
https://medium.com/@mlgerardvla/hackerrank-array-manipulation-beat-the-clock-using-prefix-sum-92471060035e
https://github.com/Java-aid/Hackerrank-Solutions/blob/master/HackerRankDashboard/CoreCS/DataStructures/src/main/java/com/javaaid/hackerrank/solutions/datastructures/arrays/ArrayManipulation.java

“Starting with a 1-indexed array of zeros and a list of operations,
for each operation add a value to each the array element between two given indices, inclusive.
Once all operations have been performed, return the maximum value in the array.”

 n = 10
queries = [[1,5,3],[4,8,7],[6,9,1]]  // 1 indexed assume
Queries are interpreted as follows :
a  b  k
1  5  3
4  8  7
6  9  1

first line --> index 1 to index 5 (add 3 to every element )
second line --> index 4 to index 8 (add 7 to every element )
third line --> index 6 to index 9 ( add 1 to every element)

Initial Array : [0,0,0, 0, 0,0,0,0,0, 0]
	[3,3,3, 3, 3,0,0,0,0, 0]
	[3,3,3,10,10,7,7,7,0, 0]
	[3,3,3,10,10,8,8,8,1, 0]

Opt : 10 [The largest value is 10 after all operations are performed.]

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Brute Force :
    1) Iterate through every line of query , Update the values in array from index a to index b with k
    2) compare current max of a to b with the max so far.

    Worst Case : TC : O(n*n), If the queries are too nested with in the same window

Efficient :
A prefix sum array is created as you traverse the original array,
and update the current element by adding it with the previous element.

    Sum of a subset of array = prefixSum[b]- prefixSum[a — 1], where b > a,
    In our Array Manipulation problem, we can leverage the power of using a prefix sum array
        — but in reverse!
    What is this other array arr? All we have to do is reverse the process.
    Let’s go right to left and subtract the arr element as we go.

    arr =       [3, 0 , 0, 0, 0, -3, 0, 0,  0, 0, 0 ,0] // calculate prefix sum
    prefixSum = [3, 3,  3, 3, 3, 0,  0 ,0 , 0, 0, 0 , 0  ] // resulting array

    Consider prefixSum[5], the last 3. prefixSum[6] is 0.
    What would arr[6] have to be to make prefixSum[5] a 3 after subtraction?arr[6] has to be -3, since 0-(-3) = 3


    arr =       [3, 0 , 0, 0, 0, -3, 0, 0,  0, 0, 0 ,0]  // this is what we intend to do at every query
    arr[a] += k
    arr[b+1] = arr[b+1] - k; // ie) prefixSum[b] + arr[b+1] = 0 :)

*/
public class ArrayManipulation {
    /*
    TC : O(m + n) //m queries, n ====> one pass of array
    SC : O(n) prefix sum array
     */
    public  long arrayManipulation(int n, List<List<Integer>> queries) {
        // update the boundaries from queries
        long[] outputArr = new long[n+2];// 1 indexed , store b+1 when b is last index

        for(int i = 0 ; i < queries.size(); i++){

            int a = queries.get(i).get(0);
            int b = queries.get(i).get(1);
            int k = queries.get(i).get(2);

            //Populate the prefix array
            // a to b should be summed with k
            outputArr[a] = outputArr[a] + k;
            outputArr[b+1] = outputArr[b+1]-k; // orig array[b+1] will be 0 therefore
            System.out.println(Arrays.toString(outputArr));

        }
        System.out.println("Final Output array");
        System.out.println(Arrays.toString(outputArr));
        long max = Long.MIN_VALUE;
        long prefixSum = 0;
        for (int i = 0; i < outputArr.length; i++) {
            prefixSum += outputArr[i];
            System.out.println("index : "+i+" Sum : "+ prefixSum);
            max = Math.max(max, prefixSum);
        }
        return max;
    }

    public static void main(String[] args) {
        ArrayManipulation ob = new ArrayManipulation();
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(List.of(1,2,100));
        queries.add(List.of(2,5,100));
        queries.add(List.of(3,4,100));

        System.out.println(ob.arrayManipulation(5,queries));
    }


}
