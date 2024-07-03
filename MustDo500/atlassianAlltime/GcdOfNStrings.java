package MustDo500.atlassianAlltime;

/*
https://leetcode.com/problems/greatest-common-divisor-of-strings/description/
https://www.geeksforgeeks.org/program-to-find-greatest-common-divisor-gcd-of-n-strings/
 */
/*
For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).

Given two strings str1 and str2,
return the largest string x such that x divides both str1 and str2.



Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""

Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of English uppercase letters.
 */
/*
My submission: https://leetcode.com/problems/greatest-common-divisor-of-strings/submissions/1307646419/

The recursion depth is determined by
how many times the substring operation is called until str2 is empty
or the strings are no longer prefixes of each other.

In the worst case, the recursion could be called n times, where n is the length of the shorter string (or the length of the GCD of the strings).

So, for each recursive call, the startsWith and substring operations dominate the time complexity, leading to a time complexity of O(n * m), where:
        n is the number of recursive calls.
        m is the length of the substring operation in each call.
In the worst case, if the strings are highly repetitive and the GCD is the entire string, the recursion might run min(len(str1), len(str2)) times.

TC :  O(n ^ 2) where n is the length of the smaller string
SC : O(n) --> where n is the length of the smaller string
        Recursive Call Stack: The depth of the recursion can go up to the length of the smaller string, which is O(n).
 */
/*
Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"

gcd(ABABAB,ABAB)
    gcd(AB , ABAB)
       gcd(ABAB , AB) //swapped
            gcd(AB, AB)
                gcd("" , AB)
                    gcd( AB , "") // swapped
 */
public class GcdOfNStrings {

    public String helper(String str1,String str2){
        if(str1.length() < str2.length()){   //--------------------------------------- O(1)
            return helper(str2,str1);
        }
        // nothing to divide
        if(str2.isEmpty()) return str1; //--------------------------------------- O(1)

        //invalid case
        if(!str1.startsWith(str2)) return ""; //--------------------------------------- O(n)

        // chop of str2 from str1 on valid scenerio
        return helper(str1.substring(str2.length()),str2);//--------------------------------------- O(m)


    }
    public String gcdOfStringsRecursion(String str1, String str2) {

        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return "";
        return helper(str1,str2);

    }

    //***********************************************************************************
    /*
    https://leetcode.com/problems/greatest-common-divisor-of-strings/solutions/3125858/the-idea-of-using-string-equality-and-gcd-time-space-complexity-elaboration/
IDEA:

   Length of the result = gcd( length(str1), length(str2) )

Time complexity: O(N)
GCD calculation takes O(logN) time.
But the string iteration and equals checking takes O(N+M) time
where, N and M are the length of str1 and str2.

Space complexity: O(N)
We are concatenating str1+str2 this is kept in the memory.
In the code, we haven't declared extra variable,
but this is still stored in the run time memory stack.
This will require, O(N+M) memory where, N and M are the length of str1 and str2. Thus, we can write O(N).
     */
    public int getGcd(int a ,  int b){
        if(b == 0) return a;

        return getGcd(b, a % b);
    }

    public String gcdOfStringsEuclidAlgo(String str1, String str2) {

        if(!(str1+str2).equals(str2+str1)) return "";
        int gcd = getGcd(str1.length(),str2.length()); // O(log n)
        return str1.substring(0,gcd);

    }

    public static void main(String[] args) {
        GcdOfNStrings ob = new GcdOfNStrings();
        System.out.println("********************** Recursion ****************************");
        System.out.println(ob.gcdOfStringsRecursion("ABCABC","ABC"));
        System.out.println(ob.gcdOfStringsRecursion("ABABAB","ABAB"));
        System.out.println(ob.gcdOfStringsRecursion("LEET","CODE"));
        System.out.println("********************** Euclidean Algo ****************************");
        System.out.println(ob.gcdOfStringsEuclidAlgo("ABCABC","ABC"));
        System.out.println(ob.gcdOfStringsEuclidAlgo("ABABAB","ABAB"));
        System.out.println(ob.gcdOfStringsEuclidAlgo("LEET","CODE"));
    }
}
