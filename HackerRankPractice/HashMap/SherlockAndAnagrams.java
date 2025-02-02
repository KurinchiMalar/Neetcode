package HackerRankPractice.HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&isFullScreen=true&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps


Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
mom = [m,m],[mo,om],[mom]  Opt = 3
abba = [a,a],[ab,ba],[b,b],[abb,bba]

 */
public class SherlockAndAnagrams {
    /*
    TC : O(n * n) * O(n log n ) = approx O( n * n)
    SC : O(n * n)  // The HashMap stores O(n²) entries in the worst case.
     */
    public  int sherlockAndAnagrams(String s) {
        int count = 0;
        // for all substrings
        int n = s.length();
        Map<String,Integer> hmap = new HashMap<>();
        for(int i = 0 ; i < n; i++ ){                          //-----------------------------------------------> O(n *n)
            for(int j = i ; j < n; j++){
                char[] subArr = s.substring(i, j+1).toCharArray();
                Arrays.sort(subArr);                              // -----------------------------------------> O(n log n)
                System.out.println(subArr);
                String sortedSubStr = new String(subArr);//.toString();
                hmap.put(sortedSubStr, hmap.getOrDefault(sortedSubStr, 0)+1);
            }
        }
        // Compute anagrammatic pairs using combinations formula f * (f - 1) / 2
        for (int frequency : hmap.values()) {
            count += (frequency * (frequency - 1)) / 2;
        }
        return count;

    }

    public static void main(String[] args) {
        SherlockAndAnagrams ob = new SherlockAndAnagrams();
        System.out.println(ob.sherlockAndAnagrams("mom"));
        System.out.println(ob.sherlockAndAnagrams("abba"));

    }
}
