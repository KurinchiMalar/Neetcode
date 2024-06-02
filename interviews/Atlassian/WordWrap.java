package interviews.Atlassian;

import java.io.*;
import java.util.*;

public class WordWrap {

    public static List<String> wrapLines(String[] words,int threshold){

        List<String> result = new ArrayList<String>();
        List<String> curList = new ArrayList<String>();
        for(String word: words){
            int curLen = 0;

            // compute the length after adding the word will be
            if(!curList.isEmpty()){
                String exitingWithhyphens = String.join("-",curList);
                curLen = exitingWithhyphens.length() + 1 + word.length();
            }else{
                curLen = word.length();
            }

            if(curLen <= threshold){
                // you can add this word
                curList.add(word);
            }else{
                // If it doesn't fit, add the current line to result and start a new line
                result.add(String.join("-",curList));
                curList = new ArrayList<>();
                curList.add(word);
            }
        }

        // Add the last line to the result
        if(!curList.isEmpty()){
            result.add(String.join("-",curList));
        }

        return result;
    }
    public static void main(String[] argv) {
        String[] words1 = {"The","day","began","as","still","as","the","night","abruptly","lighted","with","brilliant","flame"};
        String[] words2 = {"Hello"};
        String[] words3 = {"Hello", "Hello"};
        String[] words4 = {"Well", "Hello", "world"};
        String[] words5 = {"Hello", "HelloWorld", "Hello", "Hello"};
        String[] words6 = {"a", "b", "c", "d"};

        System.out.println(wrapLines(words1,13));
        System.out.println(wrapLines(words1,12));
        System.out.println(wrapLines(words1,20));
        System.out.println(wrapLines(words2,5));
        System.out.println(wrapLines(words2,30));
        System.out.println(wrapLines(words3,5));
        System.out.println(wrapLines(words4,5));
        System.out.println(wrapLines(words5,20));
        System.out.println(wrapLines(words6,20));
        System.out.println(wrapLines(words6,4));
        System.out.println(wrapLines(words6,1));



    }
}
