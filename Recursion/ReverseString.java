package Recursion;

/*
Reverse a string
TC : (n)
SC : O(n)
 */
public class ReverseString {

    static String result = "";
    public static String reverse(String str)

    {
        if(str == null)return str;
        return revRecursive(str,str.length()-1);
    }

    public static String revRecursive(String str, int n){

        if(n < 0)return str;

        if(n == 0)return String.valueOf(str.charAt(n));

        result = str.charAt(n) + revRecursive(str,--n);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverse("java"));
        System.out.println(reverse("apple"));

    }
}
