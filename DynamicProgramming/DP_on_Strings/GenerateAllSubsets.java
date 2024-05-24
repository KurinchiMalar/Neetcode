package DynamicProgramming.DP_on_Strings;

import java.util.ArrayList;
import java.util.List;

/*
Given a string generate all subsets /subsequences.


i/ p : {a,b,c}

o/p : [abc, ab, ac, a, bc, b, c]

Use PowerSet
 */
public class GenerateAllSubsets {

    /*
    TC : O(2 pow n)
    SC : O(n)
     */
    public void helperRec(int i, int n,String curr, String s,List<String> result){

        if(i == n){
            if(!curr.isEmpty()){
                result.add(curr);
            }
            return;
        }

        helperRec(i+1,n,curr+s.charAt(i),s,result);   //pick
        helperRec(i+1,n,curr,s,result); // not pick
    }
    public List<String> generateAllSubSets(String s){
        List<String> result = new ArrayList<>();
        int n = s.length();
        helperRec(0, n,"",s,result);
        return result;
    }

    /*
    TC : O( 2 pow n   * n) // O(2^n) for the outer for loop and O(n) for the inner for loop.
    SC : O(1) .... for result list = O( 2 pow n)
     */
    public List<String> generateAllSubSets_Bits(String s){
        int n = s.length();
        List<String> result = new ArrayList<>();

        for(int num = 0 ; num < (1 << n); num++){
            // 0 to (2 pow n) -1
            StringBuilder temp = new StringBuilder();
            for(int i = 0; i < n; i++){
                if((num & (1 << i)) != 0){
                    temp.append(s.charAt(i));
                }
            }
            if(temp.length() > 0){
                result.add(temp.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GenerateAllSubsets ob = new GenerateAllSubsets();
        System.out.println(ob.generateAllSubSets("abc"));
        System.out.println(ob.generateAllSubSets("abcd"));
        System.out.println("***********************************");

        System.out.println(ob.generateAllSubSets_Bits("abc"));
        System.out.println(ob.generateAllSubSets_Bits("abcd"));


    }
}
