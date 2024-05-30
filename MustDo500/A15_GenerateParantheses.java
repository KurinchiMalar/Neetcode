package MustDo500;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/generate-parentheses/description/
 */

/*
IDEA:

    #open , #close cannot exceed n
    Eligibility to add a close = (#close < #open)
    Eligibility to add open = (#open < n)
 */

public class A15_GenerateParantheses {

    /*
    TC : O( 2 ^ n)
    SC : O(n)
    My submission : https://leetcode.com/problems/generate-parentheses/submissions/1271273339/
     */
    public void backTrack(int open,int close, StringBuilder cur, List<String> result,int n){
        if(open ==n && close == n ){
            result.add(cur.toString());
            return;
        }
        if(open < n){ // can add open
            backTrack(open+1,close,cur.append("("),result,n);
            cur.setLength(cur.length()-1); // backtrack
        }
        if(close < open){
            backTrack(open,close+1,cur.append(")"),result,n);
            cur.setLength(cur.length()-1); // backtrack
        }
    }

    public List<String> generateParenthesis_BackTracking(int n) {

        List<String> result = new ArrayList<>();
        backTrack(0,0,new StringBuilder(),result,n);
        return result;

    }

    public static void main(String[] args) {
        A15_GenerateParantheses ob = new A15_GenerateParantheses();
        System.out.println("************ BackTracking **********************");
        System.out.println(ob.generateParenthesis_BackTracking(2));
    }
}

