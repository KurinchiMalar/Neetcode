package DynamicProgramming;

import java.util.HashMap;
import java.util.stream.IntStream;

/*
top down memoization
TC : O(n)
SC : O(n)

 */
public class FibonacciDP {


    /*
    top down memoization
    TC : O(n)
    SC : O(n) (Additional memo  + recursion stack)
    */
    public static int fibodpTD(HashMap<Integer,Integer> memo , int n){
        if(n == 0) return 0;
        if(n == 1) return 1;

        if(!memo.containsKey(n)){  // recursion happens only for those which are not already calculated :)
            memo.put(n,fibodpTD(memo,n-1) + fibodpTD(memo,n-2));
        }
        return memo.get(n);

    }

    /*
    Bottom up with tabulation
    TC : O(n)
    SC : O(n) // No recursion , only memo ----> Most efficient.
     */
    public static int fibodpBU(HashMap<Integer,Integer> memo , int n){
        if(n == 0) memo.put(n,0);
        if(n == 1) memo.put(n,1);
        // start from 0 and iterate till n
        for(int i = 2 ; i <=n ; i++){
            if(!memo.containsKey(n)){
                memo.put(n , memo.get(n-1) + memo.get(n-2));
            }
        }
        return memo.get(n);
    }

    /*
   Bottom up with tabulation
   TC : O(n)
   SC : O(1)
    */
    public static int fibodpBU_SpaceOptimised(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;

        int prev1 = 0;
        int prev2 = 1;
        int result = 0;
        // start from 0 and iterate till n
        for(int i = 2 ; i <=n ; i++){
            result = prev1 + prev2;
            prev1 = prev2;
            prev2 = result;
        }
        return prev2;
    }

    public static void main(String[] args) {
        HashMap<Integer,Integer> memoTD = new HashMap<Integer,Integer>();
        IntStream.rangeClosed(0,10).forEach(i-> System.out.println("f( "+i+" )"+" = "+fibodpTD(memoTD,i)));
        System.out.println("*******************************************************************************");
        HashMap<Integer,Integer> memoBU = new HashMap<Integer,Integer>();
        IntStream.rangeClosed(0,10).forEach(i-> System.out.println("f( "+i+" )"+" = "+fibodpBU(memoBU,i)));
        System.out.println("*******************************************************************************");
        IntStream.rangeClosed(0,10).forEach(i-> System.out.println("f( "+i+" )"+" = "+fibodpBU_SpaceOptimised(i)));

    }
}
