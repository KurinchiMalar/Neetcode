/*
https://leetcode.com/problems/roman-to-integer/description/
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.



Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
package ArraysAndHashing.BeginnerProblems;

import java.util.HashMap;

public class RomanToInt {

    HashMap<Character,Integer> romanMap = new HashMap<>();

    public RomanToInt(){
        romanMap.put('I',1);
        romanMap.put('V',5);
        romanMap.put('X',10);
        romanMap.put('L',50);
        romanMap.put('C',100);
        romanMap.put('D',500);
        romanMap.put('M',1000);
    }

    public int romanToIntNaive(String s){
        int result = 0;
        if(s==null || s.isEmpty()){
            return -1;
        }

        char[] strArr = s.toCharArray();
        int m = strArr.length;
        for(int i = 0 ; i < m; i++){
            char curCh = strArr[i];
            if(!romanMap.containsKey(curCh)){ //
                return -1;
            }

            if(curCh == 'I'){

                if(i+1 < m && strArr[i+1]=='V' ) {
                    result += 4;
                    i++;
                }
                else if(i+1 < m && strArr[i+1]=='X' ) {
                    result += 9;
                    i++;
                }
                else result += romanMap.get(curCh);

            }else if(curCh == 'X'){

                if(i+1 < m && strArr[i+1]=='L' ) {
                    result += 40;
                    i++;
                }
                else if(i+1 < m && strArr[i+1]=='C' ) {
                    result += 90;
                    i++;
                }
                else result += romanMap.get(curCh);

            }else if(curCh == 'C'){

                if(i+1 < m && strArr[i+1]=='D' ) {
                    result += 400;
                    i++;
                }
                else if(i+1 < m && strArr[i+1]=='M' ) {
                    result += 900;
                    i++;
                }
                else result += romanMap.get(curCh);

            }else{
                result += romanMap.get(curCh);
            }
        }
        return result;
    }

    /*
    Intuition:
    The key intuition lies in the fact that in Roman numerals,
            when a smaller value appears before a larger value, it represents subtraction, (IX) = 9
            while when a smaller value appears after or equal to a larger value, it represents addition. (XI) = 11
     */
    /*
        TC: O(n)
        SC: O(1)
     */
    public int romanToIntOptimal(String s){

        int ans = 0;

        int m = s.length();

        for(int i = 0 ; i < m ; i++) {

            char ch = s.charAt(i);

            if (i < m - 1 && romanMap.get(ch) < romanMap.get(s.charAt(i + 1))) { // need to subtract
                ans -= romanMap.get(ch);
            } else {
                ans += romanMap.get(ch);
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        RomanToInt ob = new RomanToInt();
        System.out.println("MCMXCIV : "+ob.romanToIntNaive("MCMXCIV"));
        System.out.println("MCMXCIV : "+ob.romanToIntOptimal("MCMXCIV"));

        System.out.println("XCIX : "+ob.romanToIntNaive("XCIX"));
        System.out.println("XCIX : "+ob.romanToIntOptimal("XCIX"));

        System.out.println("LVIII : "+ob.romanToIntNaive("LVIII"));
        System.out.println("LVIII : "+ob.romanToIntOptimal("LVIII"));


        System.out.println("III : "+ob.romanToIntNaive("III"));
        System.out.println("III : "+ob.romanToIntOptimal("III"));
    }
}
