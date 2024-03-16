package Recursion;

public class DecimalToBinary {

    static int res = 0;

    // TC : O(log  n base2)
    //SC : O(log n base2)
    public static int comvertToBinary(int x){

        if(x == 0){
            return 0;
        }

        return x % 2 + 10 * comvertToBinary(x / 2)  ;
    }

    public static void main(String[] args) {
        System.out.println(comvertToBinary(24));
        System.out.println(comvertToBinary(10));
    }
}
