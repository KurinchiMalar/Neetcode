package twoPointers;

/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.



Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
 */
/*
TimeComplexity : O(n)
SpaceComplexity : O(1)
https://leetcode.com/problems/valid-palindrome/submissions/
 */
class ValidPalindrome {

    public static boolean isPalindrome(String s) {

        if(s==null){
            return true;
        }
        int sLen = s.length();
        if(sLen == 1){
            return true;
        }

        int head = 0;
        int tail = sLen-1;
        while(head < tail){
            char headChar = Character.toLowerCase(s.charAt(head));
            char tailChar = Character.toLowerCase(s.charAt(tail));
            if(!Character.isLetterOrDigit(headChar)){
                head++;
                continue;
            }
            if(!Character.isLetterOrDigit(tailChar)){
                tail--;
                continue;
            }
            if(headChar != tailChar ){
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(""+isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(""+isPalindrome("madam"));
        System.out.println(""+isPalindrome("ma,dam"));
        System.out.println(""+isPalindrome("ma22am"));
        System.out.println(""+isPalindrome("m adam"));
        System.out.println(""+isPalindrome("race a car"));
        System.out.println(""+isPalindrome("m a&&&&@@@@@@@@dam"));

    }
}