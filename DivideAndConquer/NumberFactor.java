package DivideAndConquer;

import java.util.stream.IntStream;

/*
Given N , find number of ways to express N as sum of 1, 3 and 4

Idea : N's combination will be adding 1 to the result so far previously.


ie)
f(0) n--> combinations = 1
{}

f(1)  n--> combinations = 1
{1}

f(2) n--> combinations = 1
{1 1 }

******************************************************************

f(3)n--> combinations = 2

{1 1 1} , {3}

******************************************************************
f(4) --> combinations  = 4

{1 1 1 1} ,{ 3, 1} , {1, 3},  {4}

f(n) = f(n-1) + f(n-3) + f(n-4)
     = f(3) + f(1) + f(0)
     = 2 + 1 + 1
     = 4
******************************************************************

f(5) --> combinations will be as follows = 6

{1 1 1 1 }

{ 3, 1, 1} {1 , 3, 1} {1, 1, 3}

{4 1} {1 4}

f(n) = f(n-1) + f(n-3) + f(n-4)
     = f(4) + f(2) + f(1)
     = 4 + 1 + 1
     = 6
******************************************************************
f(6) --> combinations will be as follows = 9

{1 1 1 1 1}

{ 3, 1, 1 ,1} {1 , 3, 1, 1} {1, 1, 3, 1} {1,1,1,3}

{4, 1, 1} {1, 4, 1} {1 , 1, 4}

{3 , 3}

f(n) = f(n-1) + f(n-3) + f(n-4)
    = f(5) + f(3) + f(2)
    = 6 + 2 + 1
    = 9
******************************************************************

Derivation now

f(4) = 1 + f(3) + f(1) + f(0)
f(5) = 1 + f(4) + f(2) + f(1)

Optimal Substructure = f(n) = 1 + f(n-1) + f(n-3) + f(n-4)


 */

/*
TC : O( 3 ^ n) // 3 recursive calls
SC : O(n)
 */
public class NumberFactor {

    public static int waysUsing134(int n){
        if(n <=2) return 1; // {}
        if(n == 3) return 2;
        return waysUsing134(n-1) + waysUsing134(n - 3)+ waysUsing134(n - 4);
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(0,10).forEach(i-> System.out.println("f( "+i+" )"+" = "+waysUsing134(i)));
    }

}
