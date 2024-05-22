package MustDo500;
/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

 */

import java.util.HashMap;
import java.util.HashSet;

public class A2_LongestSubstringWithoutRepeatingCharacters {
    /*
    My submission : https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1264439761/
    TC : O(n * n)
    SC : O(n)
     */
    public int lengthOfLongestSubstring_BruteForce(String s) {
        HashSet<Character> hset = new HashSet<>();
        StringBuilder curSubString = new StringBuilder();
        int maxSubLen = Integer.MIN_VALUE;
        if(s == null || s.isEmpty())return 0;
        int n = s.length();
        if( n == 1) return 1;

        for(int i = 0 ; i < n; i++){
            curSubString.delete(0,curSubString.length());
            hset.clear();
            for(int j = i; j < n; j++){
                if(hset.contains(s.charAt(j))){
                    break;
                }
                curSubString.append(s.charAt(j));
                //System.out.println(curSubString.toString()+ " i: "+i+ " j: "+j);
                maxSubLen = Math.max(maxSubLen,j-i+1);
                hset.add(s.charAt(j));
            }
        }
        return maxSubLen;


    }

    /*
    Sliding window approach
    My submission : https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1264448308/
    TC : O(n * n) // because left, right = left +1 on every duplicate identified.
    SC : O(n) for hset
     */
    public int lengthOfLongestSubstring_SWindow_Naive1(String s) {
        if(s == null || s.isEmpty()) return 0;
        int n = s.length();
        if(n == 1) return 1;

        int maxSubLen = Integer.MIN_VALUE;
        HashSet<Character> hset = new HashSet<>();
        for(int left = 0, right = left; left < n && right < n;){
            if(hset.contains(s.charAt(right))){
                left++; // ignoring current window
                right = left;
                hset.clear();
                continue;
            }else{
                maxSubLen = Math.max(maxSubLen,right-left+1);
                hset.add(s.charAt(right));
                right++; // expand current window
            }
        }

        return maxSubLen;
    }
    /*
    My submission : https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1264457844/
    TC : O(2n) // each character visited twice in worstcase , once by left and once by right. (on duplicate identification)
    SC : O(n)
     */
    public int lengthOfLongestSubstring_SWindow_Naive2(String s) {
        if(s == null || s.isEmpty()) return 0;
        int n = s.length();
        if(n == 1) return 1;

        int maxSubLen = Integer.MIN_VALUE;
        HashSet<Character> hset = new HashSet<>();
        for(int left = 0, right = left; left < n && right < n;){
            if(hset.contains(s.charAt(right))){
                // remove all occurences of left that matches
                while(left < right && hset.contains(s.charAt(right))){
                    hset.remove(s.charAt(left));
                    left++; // ignoring current window
                }

            }else{
                maxSubLen = Math.max(maxSubLen,right-left+1);
                hset.add(s.charAt(right));
                right++; // expand current window
            }
        }

        return maxSubLen;
    }
    /*
    My submission : https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1264473459/
    TC : O(n)
    Sc : O(n)
     */
    public int lengthOfLongestSubstring_SWindow_Effcient(String s) {

        if(s == null || s.isEmpty()) return 0;
        int n = s.length();
        if(n == 1) return 1;

        int maxSubLen = Integer.MIN_VALUE;
        HashMap<Character,Integer> hmap = new HashMap<>(); // Character and it's index
        for(int left =0,right =0; left < n && right < n;){
            if(hmap.containsKey(s.charAt(right))){
                // abca  --> map (a-> 0 ,b -> 1 , c-> 2, right is at a (index = 3)) ,
                // we will adjust left to ignore the previous occurence,
                // this step is done instead of left++ (saves time)
                left = Math.max(hmap.get(s.charAt(right))+1,left);
            }
            maxSubLen = Math.max(maxSubLen,right-left+1);
            hmap.put(s.charAt(right),right);
            right++; // Expand current window

        }
        return maxSubLen;
    }

        public static void main(String[] args) {
        A2_LongestSubstringWithoutRepeatingCharacters ob = new A2_LongestSubstringWithoutRepeatingCharacters();
        System.out.println("************ Naive - BruteForce *************************");
        System.out.println(ob.lengthOfLongestSubstring_BruteForce("pwwkew"));
        System.out.println(ob.lengthOfLongestSubstring_BruteForce("abcabcbb"));
        System.out.println(ob.lengthOfLongestSubstring_BruteForce("bbbbb"));
        System.out.println("************ Naive - Sliding window - Naive O(N*N) still... *************************");
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Naive1("pwwkew"));
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Naive1("abcabcbb"));
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Naive1("bbbbb"));
        System.out.println("************ Better - Sliding window Using Hashset-- O( 2 * n) . *************************");
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Naive2("pwwkew"));
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Naive2("abcabcbb"));
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Naive2("bbbbb"));
        System.out.println("************ Efficient - Sliding window Using index map-- O( n) . *************************");
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Effcient("pwwkew"));
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Effcient("abcabcbb"));
        System.out.println(ob.lengthOfLongestSubstring_SWindow_Effcient("bbbbb"));
    }
}
