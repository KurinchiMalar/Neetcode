package stacks;
import java.util.*;
/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false


Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 */
/*
Time Complexity : O(n)
Space Complexity : O(n)
https://leetcode.com/problems/valid-parentheses/submissions/
 */
public class ValidParanthesis {
    static Stack<Character> stack = new Stack<>();
    public static boolean isValid(String s) {
        if(s.length() == 1){
            return false;
        }
        for(Character ch:s.toCharArray()){
            switch(ch){
                case '{':
                case '[':
                case '(':
                    stack.push(ch);
                    break;
                case '}':
                    if(stack.isEmpty()){
                        return false;
                    }
                    if(stack.pop() != '{'){
                        return false;
                    }
                    break;
                case ']':
                    if(stack.isEmpty()){
                        return false;
                    }
                    if(stack.pop() != '['){
                        return false;
                    }
                    break;
                case ')':
                    if(stack.isEmpty()){
                        return false;
                    }
                    if(stack.pop() != '('){
                        return false;
                    }

                    break;
                default:
                    return false;
            }

        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(isValid("()"));
        System.out.println(isValid("((((((("));
        System.out.println(isValid("("));
        System.out.println(isValid("){}"));
        System.out.println(isValid("]()"));

    }
}
