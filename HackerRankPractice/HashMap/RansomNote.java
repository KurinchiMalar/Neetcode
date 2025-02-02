package HackerRankPractice.HashMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&isFullScreen=false&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
Given the words in the magazine and the words in the ransom note,
 print Yes if he can replicate his ransom note exactly using whole words from the magazine; otherwise, print No.

Example
 magazine= "attack at dawn"  note= "Attack at dawn"

The magazine has all the right words, but there is a case mismatch. The answer is No .
 */
/*
Tc : O(m + n)
SC : O(m) // magazine dominates the space
 */
public class RansomNote {

    public  boolean checkMagazine(List<String> magazine, List<String> note) {

        // populate words from magazine
        Map<String, Integer> magMap = new HashMap<>();
        for(String word: magazine){
            magMap.put(word,magMap.getOrDefault(word, 0)+1);
        }
        System.out.println(magMap);
        for(String word: note){
            if(!magMap.containsKey(word)){
                System.out.println("No");
                return false;
            }
            magMap.put(word,magMap.get(word)-1);
            if(magMap.get(word) == 0) magMap.remove(word);
        }
        System.out.println("Yes");
        return true;
    }

    public static void main(String[] args) {
        RansomNote ob = new RansomNote();
        System.out.println(ob.checkMagazine(List.of("give", "me" ,"one", "grand", "today", "night"),List.of("give", "me" ,"one", "grand", "today")));
    }
}
