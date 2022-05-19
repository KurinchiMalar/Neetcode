import java.util.*;
class GroupAnagrams {
    /*
    Time : O(m+n)
    Space : O(n)
     */
    public static boolean isValidAnagram(String s, String t){
        int sourceStrLen = s.length();
        if(s==null || t==null){
            return false;
        }
        if(sourceStrLen != t.length()){
            return false;
        }
        HashMap<Character,Integer> occurenceMap = new HashMap<>();
        for(int i=0; i < sourceStrLen ; i++){
            occurenceMap.put(s.charAt(i),occurenceMap.getOrDefault(s.charAt(i),0)+1);
            occurenceMap.put(t.charAt(i),occurenceMap.getOrDefault(t.charAt(i),0)-1);
        }
        for(Character ch:occurenceMap.keySet()){
            if(occurenceMap.get(ch) > 0){
                return false;
            }
        }
        return true;
    }
    /*
     wont work for the following two reasons
     1. ascii sum will be same for ad , bc
     2. duh , ill ascii sum same as string can have repeated characters it wont work.
     */
    public static boolean isValidAnagramAsciiSum(String s, String t){
        if(s==null || t==null){
            return false;
        }
        int sourceSum = 0;
        int targetSum = 0;
        for(Character ch:s.toCharArray()){
            sourceSum += ch;
        }
        for(Character ch:t.toCharArray()){
            targetSum += ch;
        }
        if(sourceSum == targetSum){
            return true;
        }
        return false;
    }
    /*
  Time : O(n) - Single parse of original array
  Space : O(n)
   */
    public static List<List<String>> groupAnagramsNaive(String[] strs) {
        ArrayList<List<String>> resultList = new ArrayList<List<String>>();

        if(strs == null){
            resultList.add(Arrays.asList(""));
            return resultList;
        }
        if(strs.length == 1){
            resultList.add(Arrays.asList(strs[0]));
            return resultList;
        }
        HashMap<String,List<String>> hmap = new HashMap<>();
        hmap.put(strs[0],new ArrayList<String>(Arrays.asList(strs[0])));
        boolean found = false;
        for(int i=1;i < strs.length; i++){
            for(String key:hmap.keySet()){
                if(isValidAnagram(key,strs[i])){
                    found = true;
                    hmap.get(key).add(strs[i]);
                    break;
                }
            }
            if(!found){
                ArrayList<String> anagramGroupList = new ArrayList<String>();
                anagramGroupList.add(strs[i]);
                hmap.put(strs[i],anagramGroupList);
            }
            found = false; // reset found
        }

        for(String key:hmap.keySet()){
            resultList.add(hmap.get(key));
        }
        return resultList;
    }
    /*
    TimeComplexity : O(m*n*26) = O(m*n)
         where m - # of total strings, n = average length of each string , 26 freqAr length.
    SpaceComplexity :O(n)
           worst case when there are no anagram pairs...
           all strings will have corresponding  keys in map.
     */
    public static List<List<String>> groupAnagramsEfficient(String[] strs) {
        if(strs==null || strs.length==0){
            return new ArrayList<>();
        }
        HashMap<String,ArrayList<String>> hmap = new HashMap<>();
        for(String s:strs){
            char[] freq = new char[26];
            for(Character ch:s.toCharArray()){
                freq[ch-'a']++;
            }
            String curStringCharacterFreqValue = String.valueOf(freq);
            if(!hmap.containsKey(curStringCharacterFreqValue)){
                hmap.put(curStringCharacterFreqValue,new ArrayList<>());
            }
            hmap.get(curStringCharacterFreqValue).add(s);
        }
        return new ArrayList<>(hmap.values());
    }

        public static void main(String[] args){
        String[] strs={"eat","tea","tan","ate","nat","bat"};
        //List<List<String>> resultList = groupAnagrams(strs);
            List<List<String>> resultList = groupAnagramsEfficient(strs);
        System.out.println(resultList);
    }
}