package Recursion;

/*
Implement a function that capitalizes each word in String.

Example

input: i love java

 */
public class CapitalizeWord {
    public static String capitalizeWord(String str){
        StringBuilder result = new StringBuilder(str);
        return helper(str,0,str.length(), result).toString();
    }

    static StringBuilder helper(String str,int start, int n,StringBuilder result){

        if(start >= n || start < 0){
            return result;
        }

        if(start == 0){
            result.setCharAt(start,Character.toUpperCase(str.charAt(start)));
            helper(str,++start,n,result);
            return result;
        }
        char prevChar = str.charAt(start-1);
        if(prevChar == ' '){
            result.setCharAt(start,Character.toUpperCase(str.charAt(start)));
        }
        helper(str,++start,n,result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(capitalizeWord("how are you"));
    }
}
