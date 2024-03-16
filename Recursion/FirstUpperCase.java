package Recursion;

public class FirstUpperCase {

    /*
Given a string find its first uppercase letter

Example

Input : appmillerS
Output : S
*/
    static boolean foundFirst = false;
    static char firstUpperCase(String str) {
        return helper(str,0,str.length(),foundFirst);
    }

    static char helper(String s,int start,int n, boolean found){

        if(start >= n){
            return '0';
        }
        if(!found && (int)s.charAt(start) < 97){
            found = true;
            return s.charAt(start);
        }

        return helper(s,++start,n,found);

    }

    public static void main(String[] args) {
        System.out.println(firstUpperCase("appMiller"));

    }
}
