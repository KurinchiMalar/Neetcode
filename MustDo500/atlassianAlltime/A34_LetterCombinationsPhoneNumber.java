package MustDo500.atlassianAlltime;

import java.util.*;

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

Given a string containing digits from 2-9 inclusive,
return all possible letter combinations that the number could represent.
 Return the answer in any order.


Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 */
/*
Idea :
    Recursion/backtracking
    digits = 23
        2 ---> abc
        3 ---> def

        Soln
            a --> (d , e , f) = ad, ae, af
            b --> (d , e, f) = bd, be , bf
            c ---> (d, e, f) = cd, ce, cf

 */
public class A34_LetterCombinationsPhoneNumber {
    HashMap<Integer,String> phoneMap = new HashMap<>();
    public void buildPhoneMap(){
        phoneMap.put(0,"");
        phoneMap.put(1,"");
        phoneMap.put(2,"abc");
        phoneMap.put(3,"def");
        phoneMap.put(4,"ghi");
        phoneMap.put(5,"jkl");
        phoneMap.put(6,"mno");
        phoneMap.put(7,"pqrs");
        phoneMap.put(8,"tuv");
        phoneMap.put(9,"wxyz");
    }
    public void backTrack(String digits, int ind, StringBuilder sb, List<String> result){
        if(ind == digits.length()){
            if(sb.length() != 0){
                result.add(sb.toString());
                return;
            }
        }
        String letters = phoneMap.get(digits.charAt(ind) -'0'); // - '0' to get the number
        for(char letter:letters.toCharArray()){
            sb.append(letter);
            //move forward
            backTrack(digits,ind+1,sb,result);
            //unwind
            sb.deleteCharAt(sb.length()-1);
        }
    }

    /*
    Tc : O( 4 ^ n) worst case since some digits have 4 chars mapping // 4 choices each
    SC : O( 4 ^ n) stack= O(n),  the overall space complexity is dominated by the space required to store the results

    https://leetcode.com/problems/letter-combinations-of-a-phone-number/solutions/2021106/4-approaches-bf-4-loops-backtracking-bfs-queue-with-image-explanation/
     */

    public List<String> letterCombinations_DFSBackTrack(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.isEmpty()) return result;

        buildPhoneMap();
        StringBuilder sb = new StringBuilder();
        sb.append("");// currentString in recursion

        backTrack(digits, 0, sb, result);
        return result;
    }
    /*
    //1.  "" + {a, b, c}
    //2. a -> {d,e,f}
         b -> {d,e,f}
         c -> {d,e,f}

     */
     /*
     TC : O( 4 ^ n)
     SC : O( 4 ^ n)
      */

    public List<String> letterCombinations_BFS(String digits) {

        List<String> result = new ArrayList<>();
        if(digits == null || digits.isEmpty()) return result;

        buildPhoneMap();

        result.add("");

        // For each digit retrieve the letters
        // To every existing char in result already, append each char of letters.
        for(char digit: digits.toCharArray()){

            // retrieve the letters for this digit
            String letters = phoneMap.get(digit-'0');
            List<String> newRes = new ArrayList<>();

            for(String item: result){
                for(char letter: letters.toCharArray()){
                    newRes.add(item+letter);
                }
            }
            result = newRes;
        }

        return result;
    }
    /*
    https://leetcode.com/problems/letter-combinations-of-a-phone-number/solutions/2021106/4-approaches-bf-4-loops-backtracking-bfs-queue-with-image-explanation/
    TC : O(4 ^ n)
    SC : O( 4 ^ n)
     */

    public List<String> letterCombinations_UsingQueue(String digits) {

        if(digits == null || digits.isEmpty()) return  new ArrayList<>();;

        buildPhoneMap();

        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        for(char digit: digits.toCharArray()){

            // retrieve the letters for this digit
            String letters = phoneMap.get(digit-'0');
            int size = queue.size();  // each level
            for(int j = 0 ; j < size; j++){
                String temp = queue.poll();
                for(char letter : letters.toCharArray()){
                    queue.offer(temp+letter);
                }
            }
        }
        return new ArrayList<>(queue);
    }


        public static void main(String[] args) {
        A34_LetterCombinationsPhoneNumber ob = new A34_LetterCombinationsPhoneNumber();
        System.out.println("********** DFS BackTracking **********************");
        System.out.println(ob.letterCombinations_DFSBackTrack("23"));
        System.out.println(ob.letterCombinations_DFSBackTrack(""));
        System.out.println(ob.letterCombinations_DFSBackTrack("2"));
        System.out.println("********** BFS **********************");
        System.out.println(ob.letterCombinations_BFS("23"));
        System.out.println(ob.letterCombinations_BFS(""));
        System.out.println(ob.letterCombinations_BFS("2"));
        System.out.println("********** Using Queue **********************");
        System.out.println(ob.letterCombinations_UsingQueue("23"));
        System.out.println(ob.letterCombinations_UsingQueue(""));
        System.out.println(ob.letterCombinations_UsingQueue("2"));

        }

}