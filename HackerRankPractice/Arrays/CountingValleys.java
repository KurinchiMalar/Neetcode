package HackerRankPractice.Arrays;
/*
https://www.hackerrank.com/challenges/counting-valleys/problem
U- uphill
D- downhill
mountain = above sealevel
valley = below sealevel
Given the sequence of up and down steps during a hike,find and print the number of valleys walked through.

 I/P = [DDUUUUDD]  DDUU came to sealevel , UUDD mountain ....Therefore 1 valley
 O/p = 1

 I/P = [UDDDUDUU]   UD sealevel, DD UD UU  (1 valley)
 O/P = 1
 */

public class CountingValleys {

    /*
    U --> climb++
        Climbing up and , climb becomes zero ==> we are actually climbing a valley :)
    D --> climb--
     */
    /*
    TC : O(n)
    SC : O(1)
     */
    public  int countingValleys(int steps, String path) {

        int level = 0;
        int valleys = 0;
        for(Character c : path.toCharArray()){
            if(c.equals('U')){
                level++;
                if(level == 0) valleys++; // coming up from a valley to sealevel
            }else if (c.equals('D')){
                level--;
            }
        }
        return valleys;
    }

    public static void main(String[] args) {
        CountingValleys ob = new CountingValleys();
        System.out.println(ob.countingValleys(8,"DDUUUUDD"));
    }
}
