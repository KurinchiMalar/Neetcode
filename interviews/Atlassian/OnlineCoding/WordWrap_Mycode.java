package interviews.Atlassian.OnlineCoding;

import java.util.ArrayList;
import java.util.List;

/*
String builder was a wrong choice here

Should have use String.join( , )
 */
public class WordWrap_Mycode {

    public static List<String> wrapLines(String[] words, int threshold){

        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        int curThres = threshold;
        int n = words.length;

        for(int i = 0 ; i < n; i++){
            String word = words[i];
            if(word.length() > curThres){
                result.add(sb.toString());
                sb.setLength(0);
                curThres = threshold;
            }else{
                sb.append(word);
                sb.append("-");
                System.out.println(sb);
                curThres = curThres - word.length();
                System.out.println("Word: "+word +" curThres: "+curThres);
                System.out.println("--------------");
            }
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

    }
}