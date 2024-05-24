package DynamicProgramming.DP_on_Strings;

import Graphs.AdjList.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllSubstrings {

    /*
    TC : O(n * n * n)
    SC : O(1)
     */

    public List<String> generateSubstrings_BruteForce(String s){
        int n = s.length();
        List<String> result = new ArrayList<>();
        for(int i = 0 ; i < n; i++){
            for( int j = i ; j < n; j++){
                StringBuilder temp = new StringBuilder();
                for(int k = i; k <= j; k++){
                    temp.append(s.charAt(k));
                }
                if(temp.length() > 0){
                    result.add(temp.toString());
                }
            }
        }
        return result;
    }
    /*
    TC : O(n^3)
    This is because there are O(n^2) substrings generated and, on average, each substring operation takes O(n) time due to the length of the substrings.
    SC; O(1)
     */
    public List<String> generateSubstrings_BruteForce_UsingSubString(String s){
        int n = s.length();
        List<String> result = new ArrayList<>();

        for(int i = 0 ; i < n; i++){
            for( int j = i ; j < n; j++) {
                result.add(s.substring(i, j+1));
            }

        }
        return result;
    }

    public static void main(String[] args) {
        GenerateAllSubstrings ob = new GenerateAllSubstrings();
        System.out.println(ob.generateSubstrings_BruteForce("abc"));
        System.out.println(ob.generateSubstrings_BruteForce_UsingSubString("abc"));

    }
}
