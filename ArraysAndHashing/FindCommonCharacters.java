package ArraysAndHashing;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/find-common-characters/description/
Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.



Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
 */
public class FindCommonCharacters {

    /*
    TC : O(n * (m*26)) + O(26)  = O( m * n)
    SC : O(26) freqAr + O(m) (result list common chars --> worst case m considering the entire first string is available in  all other strings)
    */
    public static List<String> commonChars(String[] words) {

        List<String> result = new ArrayList<>();

        // construct the base freq Arr with first word
        int[] last = constructFreqArr(words[0]);

        for(int i = 1; i < words.length; i++){   //------------------------------------------------------------------ O(n)

            //update last with the intersection achieved...this will be the source comparision for the next word
            last = intersection(last, constructFreqArr(words[i])); //---------------------------------------------------O(26 * m)

        }

        for(int i = 0 ; i < 26; i++){     // ------------------------------------------------O(26)

            if(last[i] != 0){ // there is a common freq across words that is captured

                /*
                From char to get index ==> i = ch - 'a'
                Now from index to get char ==> ch = i + 'a';
                */
                char ch = 'a';
                ch += i;
                while(last[i] != 0){ // the same char may occur more than once in common
                    result.add(""+ch);
                    last[i]--;
                }

            }
        }
        return result;
    }

    public static int[] intersection(int[] last, int[] cur){  //---------------------------------------------------- O(26)
        int[] intersectionArr = new int[26];

        // you take the min, so that you capture just the most common part.
        for(int i = 0 ; i < 26; i++){
            intersectionArr[i] = Math.min(last[i],cur[i]);
        }
        return intersectionArr;
    }

    public static int[] constructFreqArr(String word){   //-------------------------------------------------- O(m)
        int[] freqAr = new int[26]; // we have 26 characters;
        for(Character ch: word.toCharArray()){

            // from char to get index
            int index = ch - 'a';
            freqAr[index]++;
        }
        return freqAr;
    }

    public static void main(String[] args) {
        FindCommonCharacters ob = new FindCommonCharacters();
        System.out.println(ob.commonChars(new String[]{"bella","label","roller"}));
        System.out.println(ob.commonChars(new String[]{"cool","lock","cook"}));

    }
}
