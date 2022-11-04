/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.



Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22


Constraints:

1 <= tokens.length <= 104
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200]
 */
package stacks;
import java.util.*;
import java.util.regex.Pattern;

        /*
                Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?"); // https://www.baeldung.com/java-check-string-number
        Now let's use regex -?\d+(\.\d+)? to match numeric Strings consisting of the positive or negative integer and floats.
Let’s break down this regex and see how it works:

-? – this part identifies if the given number is negative, the dash “–” searches for dash literally and the question mark “?” marks its presence as an optional one
\d+ – this searches for one or more digits
(\.\d+)? – this part of regex is to identify float numbers. Here we're searching for one or more digits followed by a period. The question mark, in the end, signifies that this complete group is optional.
          */
/*
TC : O(n)
SC : O(n)
https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
class ReversePolishNotation {
    static Stack<String> stack = new Stack<>();
    static Set<String> opSet = new HashSet<>(Arrays.asList("+","-","*","/"));
    public static int evalRPN(String[] tokens) {
        int result = 0;
        if(tokens.length == 1){
            result = Integer.valueOf(tokens[0]);
            return result;
        }
        Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        for(String token:tokens){
            if(numPattern.matcher(token).matches()){ // is number (normal , -ve, float)
                stack.push(token);
            }else if(opSet.contains(token)){
                String num1 = stack.pop();
                String num2 = stack.pop();
                int n1 = Integer.valueOf(num1);
                int n2 = Integer.valueOf(num2);
                switch(token){
                    case "+":
                        result = n1+n2;
                        break;
                    case "-":
                        result = n2-n1;
                        break;
                    case "*":
                        result = n1*n2;
                        break;
                    case "/":
                        result = n2/n1;
                        break;
                    default:
                        break;
                }
                stack.push(Integer.toString(result));
            }else{
                return -1;
            }
        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(evalRPN(new String[]{"18"}));
        System.out.println(evalRPN(new String[]{"4","3","/"}));
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
        System.out.println(evalRPN(new String[]{"4","13","5","/","+"}));
        System.out.println(evalRPN(new String[]{"4","13","5","/","+"}));
        System.out.println(evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
        System.out.println(evalRPN(new String[]{"4","13","5","/","+"}));

    }
}