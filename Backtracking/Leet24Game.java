package Backtracking;
/*
https://leetcode.com/problems/24-game/description/


https://github.com/happygirlzt/algorithm-illustrations/blob/master/24%20Game.png
https://www.youtube.com/watch?v=ET_HIyJTl1E
https://happygirlzt.com/code/679.html
 */
public class Leet24Game {

    /*
    TC : 4 numbers picked in pairs, each pair tried 6 combinations in compute ==>
    Work Done per Recursive call : For each pair ==> 6 operations and constructs new array in O(n) time.
                            ==> O(6 pow (n-1) * n! * n)

     SC : Recursion depth n, at each step new array of size n-1 ==> O(n * n)
     */

    public boolean judgePoint24(int[] cards) {

        // Since arithmetic is involved, change everything to double for exact value.
        double[] cardsD = new double[]{cards[0],cards[1],cards[2],cards[3]};

        return helper(cardsD);
    }

    private boolean helper(double[] arr){

        int n = arr.length;

        if(n == 1){ // check if this is closer to 24 (double that's why)
            return Math.abs(arr[0]-24) < 0.0001;
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = i+1 ; j < n ; j++){ // pick 2 elements at a time

                // after picking i and j elem , compute(i,j) reduces this to a single elem
                // therefore an initial arr of size 4 now becomes  of size 3, create a separate array d
                double[] d = new double[n-1];
                int index = 0;
                // Add all the rest of the elems other than elems at i and j from orig array to d
                for(int k = 0 ; k < n; k++){
                    if(k != i && k != j){
                        d[index] = arr[k];
                        index++;
                    }
                }
                // the computed result of elems at i and j should go to the last position of d--> d[d.length-1]

                for(double num : compute(arr[i],arr[j])){
                    // put the computed result to the last position
                    d[d.length-1] = num;
                    // d is now constructed and ready for validation
                    if(helper(d))return true;
                }

            }
        }
        return false;
    }

    private double[] compute(double x, double y){
        return new double[] {x+y, x-y, y-x, x*y, x/y, y/x};
    }

    public static void main(String[] args) {
        Leet24Game ob = new Leet24Game();
        System.out.println(ob.judgePoint24(new int[]{4,1,8,7}));
        System.out.println(ob.judgePoint24(new int[]{1,2,1,2}));
    }
}
