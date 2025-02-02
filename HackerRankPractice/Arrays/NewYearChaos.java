package HackerRankPractice.Arrays;

import java.util.List;

/*
https://www.hackerrank.com/challenges/new-year-chaos/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

Each person initial position 1 to n
Any person can bribe the person directly in front of them to swap positions, but they still wear their original sticker.

One person can bribe at most two others.

Determine the minimum number of bribes that took place to get to a given queue order.
Print the number of bribes, or, if anyone has bribed more than two people, print Too chaotic.
 */
public class NewYearChaos {

    /*
    TC : O(n) .... outerloop O(n) , inner loop atmost 2 elements for each elem
    SC : O(1)
     */

    public  int minimumBribes(List<Integer> q) {
        int bribes = 0;

        // start from last person
        int n = q.size();
        for(int i = n-1; i >= 0 ; i--){

            /* check chaotic condition,
                current pos and initial pos should not be greater than 2.
            i+1 to cater to initial position 1 to N (array index starts from 0)*/
            if(q.get(i)-(i+1) > 2){
                System.out.println("Too chaotic");
                return -1;
            }
            /* Let's count the bribes... the position would have moved forward max 2 places*/
            // i- current position,
            // j - possible positions could have been before bribe
            // q.get(i)-2 could fall beyond 0, the max is to check that boundary.
            for(int j = Math.max(0,q.get(i)-2); j < i ; j++){
                // only greater numbers would have bribed and pushed this guy back
                if(q.get(j) > q.get(i)){
                    bribes++;
                }
            }
        }
        return bribes;
    }

    public static void main(String[] args) {
        NewYearChaos ob = new NewYearChaos();
        System.out.println(ob.minimumBribes(List.of(2,1,5,3,4)));
        System.out.println(ob.minimumBribes(List.of(2,5,1,3,4)));
        System.out.println(ob.minimumBribes(List.of(1,2,5,3,4,7,8,6)));
        System.out.println(ob.minimumBribes(List.of(5,1,2,3,7,8,6,4)));
        System.out.println("********************************");
        System.out.println(ob.minimumBribes(List.of( 1,2,5,3,7,8,6,4)));


    }
}
