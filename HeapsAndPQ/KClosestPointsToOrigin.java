package HeapsAndPQ;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

Example 1:

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.


Constraints:

1 <= k <= points.length <= 104
-104 < xi, yi < 104
 */
/*
TC : O(nlogn)
SC : O(n) ...number of points
 */
public class KClosestPointsToOrigin {
    // min heap based on distance from origin
    static PriorityQueue<PointNode> pq = new PriorityQueue<>((a,b) -> a.dist - b.dist);
    public int[][] kClosest(int[][] points, int k) {
        //points = [[1,3],[-2,2]]
        for(int i = 0 ; i < points.length; i++){  // ntimes
            //a*a + b*b   no need sqrt and all.. if 8 < 10  , then sqrt(8) < sqrt(10)
            int distance = (points[i][0] * points[i][0]) + (points[i][1] * points[i][1]);
            pq.add(new PointNode(distance,points[i][0],points[i][1]));  // log n
        }

        int[][] result = new int[k][2];
        for(int i=0 ; i < k && pq.size() > 0; i++){
            PointNode pnode = pq.poll();
            result[i][0] = pnode.x;
            result[i][1] = pnode.y;

        }
        return result;

    }
    public class PointNode {
        int dist;
        int x;
        int y;
        PointNode(int dist,int x,int y){
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args){
        KClosestPointsToOrigin ob = new KClosestPointsToOrigin();
        System.out.println(Arrays.deepToString(ob.kClosest(new int[][]{{1,3},{-2,2}},1)));
        ob.pq.clear();
        System.out.println(Arrays.deepToString(ob.kClosest(new int[][]{{3,3},{5,-1},{-2,4}},2)));



    }
}


