package HackerRankPractice.Graphs;

import java.util.*;

/*
https://www.hackerrank.com/challenges/food-delivery-service/problem
Atul has started working as a food delivery executive for Swiggy and he needs to deliver food in a magical place called Swiggy-City where houses are numbered from 1  to N . Atul starts from house number 1. From any house number u  he can move to house number v  such that.
v = u+1 or v = u-1 , v = u*2 or v= u/2 .
The number v  can be achieved by sorting the digits of number u  in descending order.
Note that  v should be whole number and within boudaries 1 <=v<=N
Since Atul is stressed out and wants to deliver as many food packets as possible,
 please help him to get to the house number X from house number 1 using a minimum number of operations.
 */
public class FoodDeliveryService {

    public  int solve(int n, int x) {
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        // first house
        queue.offer(new int[]{1,0}); // houseNumber , steps
        visited.add(1);

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int u = current[0]; // houseNumber
            int steps = current[1];

            if(u == x){ // reached target House
                return steps;
            }

            // possible v's (or) next houses
            List<Integer> nextHouses = new ArrayList<>();
            nextHouses.add(u+1);
            nextHouses.add(u-1);
            nextHouses.add(u*2);
            if(u % 2 == 0) nextHouses.add(u/2);

            // v can also be rearranged from u
            String uStr = String.valueOf(u);
            char[] digits = uStr.toCharArray();
            Arrays.sort(digits);
            StringBuilder sb = new StringBuilder(new String(digits));
            sb.reverse();
            int v = Integer.parseInt(sb.toString());
            nextHouses.add(v);

            // Iterate through all nextHouses
            for(int nextHouse:nextHouses){
                if(nextHouse >= 1 && nextHouse <= n && !visited.contains(nextHouse)){
                    visited.add(nextHouse);
                    queue.offer(new int[]{nextHouse,steps+1});
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        FoodDeliveryService ob = new FoodDeliveryService();

        //Atul can reach house number 100 from house number 1  in the following order: .
        // 1->2->4->8->16->61 So the answer is 5.
        System.out.println(ob.solve(100,61));
    }  

}
