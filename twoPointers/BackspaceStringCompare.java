package twoPointers;
/*
https://leetcode.com/problems/backspace-string-compare/description/

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.



Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".


Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.


Follow up: Can you solve it in O(n) time and O(1) space?
 */
public class BackspaceStringCompare {

    /*
    TC : O(m + n)
    SC : O( max(m,n))
     */
    public String getBackSpacedString(String str, StringBuilder sb){
        for(Character ch : str.toCharArray()){
            if(sb.isEmpty() && ch == '#')continue; // do nothing
            if(ch == '#')  sb.setLength(sb.length()-1) ;
            else sb.append(ch);

        }
        return sb.toString();
    }
    public boolean backspaceCompare(String s, String t) {
        StringBuilder sb = new StringBuilder();

        String s1 = getBackSpacedString(s,sb);
        sb.setLength(0);
        String s2 = getBackSpacedString(t,sb);

        return s1.equals(s2);

    }

    //--------------------------------------- Follow up O(1) space
    // lets keep two pointers at the end and go from back

    /*
    TC : O(m + n) ...each char processed only once. Processing done for every char in s and every char in t
    SC : O(1) ...no string builder , stack etc
     */
    public boolean backspaceCompareEfficient(String s, String t) {

        int sp = s.length()-1;
        int tp = t.length()-1;

        while(sp >= 0 || tp >= 0){ // making this || because boundary is checked internally

            sp = findNextValidCharIndex(s,sp);
            tp = findNextValidCharIndex(t,tp);

            if(sp < 0 && tp < 0) return true;
            if(sp < 0 || tp < 0) return false;
            if(s.charAt(sp) != t.charAt(tp)) return false;

            sp--;
            tp--;
        }
        return true;
    }

    int findNextValidCharIndex(String str, int end){
        int backSpaceCount = 0;
        // to keep moving until you find a char other than #
        while(end >= 0){
            if(str.charAt(end) == '#') backSpaceCount++;
                //found a char but check if this is eligible for deletion as there could be more # encountered previously.
            else if (backSpaceCount > 0) backSpaceCount--; // skipping this char and marking it as though it is deleted.
            else if(str.charAt(end) != '#') break;
            end--;
        }
        return end;
    }

    public static void main(String[] args) {
        BackspaceStringCompare ob = new BackspaceStringCompare();
        System.out.println(ob.backspaceCompare("ab#c","ad#c"));
        System.out.println(ob.backspaceCompare("ab##","c#d#"));
        System.out.println(ob.backspaceCompare("a#c","b"));
        System.out.println(ob.backspaceCompare("xywrrmp","xywrrmu#p"));
        System.out.println(ob.backspaceCompare("a","aaaa#a"));


        System.out.println("************************* Efficient******************");
        System.out.println(ob.backspaceCompareEfficient("ab#c","ad#c"));
        System.out.println(ob.backspaceCompareEfficient("ab##","c#d#"));
        System.out.println(ob.backspaceCompareEfficient("a#c","b"));
        System.out.println(ob.backspaceCompareEfficient("xywrrmp","xywrrmu#p"));
        System.out.println(ob.backspaceCompareEfficient("a","aaaa#a"));

    }
}
