package DynamicProgramming;

import java.util.Arrays;

/*
https://www.youtube.com/watch?v=fJaKO8FbDdo&t=128s
https://leetcode.com/problems/edit-distance/description/
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {

    /*
    TC : O(3 pow n)  // 3 recursive calls
    SC : O(m + n)
     */
    public int recFunction(int i, int j,String word1,String word2){

        // base cases
        if(i < 0) return j+1;
        if(j < 0) return i+1;
        if(word1.charAt(i) == word2.charAt(j)){
            return recFunction(i - 1, j - 1, word1, word2); // do nothing just move past . No operation required so no 1+
        }
        int replace = recFunction(i-1,j-1,word1,word2);  // move both
        int delete = recFunction(i-1,j,word1,word2);  // no change to word2 so j stays as such
        int insert = recFunction(i,j-1,word1,word2); // no change to current char of word1 so i stays as such, j's char is processed so moved. (We hypothetically inserted in word1 and matched j's current position with that position of i)
        return 1 + Math.min(replace,Math.min(delete,insert));
    }


    public int minDistanceUsingRecursion(String word1, String word2) {

        return recFunction(word1.length()-1,word2.length()-1,word1,word2);
    }
    /***********************************************************************************************************************/

     /*
    TC : O(m * n)
    SC : O(m * n)  dp array + O(m + n) recursive stack space
     */
    public int tdMemoization(int i, int j,String word1,String word2,int[][] dp){

        // base cases
        if(i < 0) return j+1;
        if(j < 0) return i+1;
        if(dp[i][j] != -1) return dp[i][j]; // Avoids unnnecessary recursion
        if(word1.charAt(i) == word2.charAt(j)){
            dp[i][j] = tdMemoization(i - 1, j - 1, word1, word2,dp); // do nothing just move past . No operation required so no 1+
            return dp[i][j];
        }
        int replace = tdMemoization(i-1,j-1,word1,word2,dp);   // move both
        int delete = tdMemoization(i-1,j,word1,word2,dp);  // no change to word2 so j stays as such
        int insert = tdMemoization(i,j-1,word1,word2,dp);  // no change to current char of word1 so i stays as such, j's char is processed so moved. (We hypothetically inserted in word1 and matched j's current position with that position of i)

        dp[i][j] = 1 +  Math.min(replace,Math.min(delete,insert));
        return dp[i][j];
    }


    public int minDistanceUsingTopDownMemoization(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach( row -> Arrays.fill(row,-1));
        return tdMemoization(m-1,n-1,word1,word2,dp);
    }

    /*************************************************************************************/
    /*
    TC : O(m * n)
    SC : O(m * n) dp array
     */
    public int minDistanceUsingBottomUpTabulation(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1]; // 0 j , i 0  prefilled....actuals start from 1 index until m and n

        // to avoid negative indexing, we treat actual values as 1 based index. We will precalculate the base cases for i and j 0
        for(int i=0 ; i < m ; i++){
            dp[i][0] = i;
        }
        for(int j=0 ; j < n ; j++){
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // If the characters match, no edit is needed, so take the value from the diagonal.
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // If the characters don't match, take the minimum of three possibilities:
                    // 1. Replace the character in S1 with the character in S2 (diagonal).
                    // 2. Delete the character in S1 (left).
                    // 3. Insert the character from S2 into S1 (up).
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    /*
    Algorithm / Intuition
    https://takeuforward.org/data-structure/edit-distance-dp-33/
If we closely look the relation,

dp[i][j] =  min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])

We see that to calculate a value of a cell of the dp array, we need only the previous row values (say prev). So, we don’t need to store an entire array. Hence we can space optimize it.

Approach:
We will space optimize in the following way:

We take two rows ‘prev’ and ‘cur’.
We initialize it to the base condition.
        1. Now, at starting the prev row needs to be initialized with its column value. Corresponding to the below for loop
            for(int j=0 ; j < n ; j++){
                dp[0][j] = j;   // 0th row should have the column values
            }
        2. Moreover, the cur variable whenever declared should have its first cell as a row value. The below for loop initialization happens during the iteration itself.

            for(int i=0 ; i < m ; i++){
                dp[i][0] = i;  // for all rows 0th column is being initialized.
            }

Next, we implement the memoization logic. We replace dp[i-1] with prev and dp[i] by cur.
After every inner loop execution, we set prev=cur, for the next iteration.
At last, we return prev[m] as our answer.
     */
    /*
    Time Complexity: O(M*N)
    Auxiliary Space: O(N)
     */
    public int minDistanceUsingBottomUpTabulation_SpaceOtpmized(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        // Create two arrays to store the previous and current rows of minimum edit distances
        int[] prev = new int[n+1];  // for row.
        int[] cur = new int[n+1];  // for column

        //Initialize the first row with their respective indices
        for(int j= 0 ; j <= n; j++){
            prev[j] = j;
        }
        //Replace dp[i-1] to prev
        //Replace dp[i] to cur
        for (int i = 1; i <= m; i++) {
            cur[0] = i; // initializing the 0th column
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // If the characters match, no edit is needed, so take the value from the diagonal.
                    cur[j] = prev[j - 1];
                } else {
                    // If the characters don't match, take the minimum of three possibilities:
                    // 1. Replace the character in S1 with the character in S2 (diagonal).
                    // 2. Delete the character in S1 (left).
                    // 3. Insert the character from S2 into S1 (up).
                    int mn = Math.min(1 + prev[j],
                            1 + cur[j - 1]);
                    cur[j] = Math.min(mn, 1 + prev[j - 1]);
                }
            }
            prev = cur.clone();
        }
        return prev[n];
    }


    public static void main(String[] args) {
        EditDistance ob = new EditDistance();
        System.out.println("************ Recursion **********************");

        System.out.println(ob.minDistanceUsingRecursion("horse","ros"));
        System.out.println(ob.minDistanceUsingRecursion("intention","execution"));

        System.out.println("************ Top Down Memoization **********************");

        System.out.println(ob.minDistanceUsingTopDownMemoization("horse","ros"));
        System.out.println(ob.minDistanceUsingTopDownMemoization("intention","execution"));

        System.out.println("************ Bottom Up Tabulation **********************");
        System.out.println(ob.minDistanceUsingBottomUpTabulation("horse","ros"));
        System.out.println(ob.minDistanceUsingBottomUpTabulation("intention","execution"));

        System.out.println("************ Bottom Up Tabulation Space Optimized mine **********************");
        System.out.println(ob.minDistanceUsingBottomUpTabulation_SpaceOtpmized("horse","ros"));
        System.out.println(ob.minDistanceUsingBottomUpTabulation_SpaceOtpmized("intention","execution"));

    }

}
