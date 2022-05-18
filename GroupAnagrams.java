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
  Time : O(n) - Single parse of original array
  Space : O(n)
   */
    public static List<List<String>> groupAnagrams(String[] strs) {
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
    public static void main(String[] args){
        String[] strs={"eat","tea","tan","ate","nat","bat"};
        List<List<String>> resultList = groupAnagrams(strs);

        System.out.println(resultList);
    }
}