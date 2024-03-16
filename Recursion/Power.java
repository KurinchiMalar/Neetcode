package Recursion;

public class Power {
    /*
    TC: O(n)
    SC : O(n)

    https://www.youtube.com/watch?v=wAyrtLAeWvI&list=PL2_aWCzGMAwLz3g66WrxFGSXvSsvyfzCO&index=7
    Comp analysis:
    https://www.youtube.com/watch?v=VHcZtdp5054
     */
    static int powerOn(int x, int n){
        if(n < 0)return -1;
        if(n == 0)return 1;
        return x * powerOn(x,n-1);
    }

    // TC : O(log N)
    // SC : O(log N)
    static int powerlogN(int x, int n){
        if(n < 0) return -1;
        if(n == 0) return 1;
        if(n % 2 == 0){
            int temp = powerlogN( x, n/2);
            return temp*temp;
        }else{
            return x * powerlogN( x, n-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(powerOn(2,2));
        System.out.println(powerOn(2,-1));
        System.out.println(powerOn(4,1));

        System.out.println("--------------------");
        System.out.println(powerlogN(2,2));
        System.out.println(powerlogN(2,-1));
        System.out.println(powerlogN(4,1));


    }
}
