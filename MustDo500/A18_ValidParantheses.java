package MustDo500;
import java.util.Stack;
/*
https://leetcode.com/problems/valid-parentheses/

 */
public class A18_ValidParantheses {

    /*
    My submission: https://leetcode.com/problems/valid-parentheses/submissions/1271362380/
    TC: O(n)
    SC : O(n)
     */
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        if(s == null || s.length() <= 1) return false;

        for(Character ch: s.toCharArray()){
            switch(ch){
                case '(':
                case '{':
                case '[':
                    stk.push(ch);
                    break;
                case ')':
                    if(stk.isEmpty() || stk.pop() != '(')return false;
                    break;
                case '}':
                    if(stk.isEmpty() || stk.pop() != '{')return false;
                    break;
                case ']':
                    if(stk.isEmpty() || stk.pop() != '[')return false;
                    break;
            }
        }
        if(!stk.isEmpty())return false;
        return true;
    }

    public static void main(String[] args) {
        A18_ValidParantheses ob = new A18_ValidParantheses();
        System.out.println(ob.isValid("()[]{}"));
        System.out.println(ob.isValid("}()"));
    }

}
