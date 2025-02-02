package HackerRankPractice.Arrays;

import java.util.List;

/*
https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem
 The player can jump on any cumulus cloud having a number that is equal to
    the number of the current cloud plus 1 or 2.
 The player must avoid the thunderheads.
 Determine the minimum number of jumps it will take to jump from the starting postion to the last cloud.
  It is always possible to win the game.

  0 --> safe
  1 --> to be avoided

  eg) c = [0,1,0,0,0,1,0]
  Player must avoid clouds at indices 1 and 5
  paths : 0->2->4->6 (2 steps)  -- > 3 jumps
          0->2->3->4->6 (1 step) --> 4 jumps

          min = 3 jumps
 */
public class JumpingOnClouds {
    /*
    We need min jumps, therefore try to do the double jump to finish fast.
    If double jump not possibel do a single
     */
    public  int jumpingOnClouds(List<Integer> c) {

        int minJumpCount = 0 ;

        int[] input = c.stream().mapToInt(i->i).toArray();
        int n = input.length;
        for(int i = 0 ; i < n-1; ){ // single jump

            // check if double jump possible
            if(i + 2 < n && input[i+2] == 0) i = i + 2;
            else i = i + 1;
            minJumpCount++;

        }
        return minJumpCount;
    }

    public static void main(String[] args) {
        JumpingOnClouds ob = new JumpingOnClouds();
        System.out.println(ob.jumpingOnClouds(List.of(0,1,0,0,0,1,0)));
        System.out.println(ob.jumpingOnClouds(List.of(0,0,0,0,1,0)));
        System.out.println(ob.jumpingOnClouds(List.of(0,0,1,0,0,1,0)));
    }
}
