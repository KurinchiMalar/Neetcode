package Recursion;

public class PalindromeCheck {

    static boolean isPali(String str){

        int n = str.length();
        if(str == null || n == 0){
            return true;
        }

        return isPaliRec(str,0,n-1);
    }

    static boolean isPaliRec(String str, int start, int end){

        if(start == end ){
            return true;
        }
        if(!(start <= end)){
            return false;
        }
        if(str.charAt(start)!=(str.charAt(end))){
            return false;
        }
        isPaliRec(str,++start,--end);

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPali("madam"));
        System.out.println(isPali("madama"));

    }
}
