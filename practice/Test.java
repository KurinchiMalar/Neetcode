package practice;
import java.util.*;
import java.util.stream.Collectors.*;
import java.util.stream.*;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;

public class Test {
    public static HashMap<Integer,Integer> hmap = new HashMap<>();
    public static List<Integer> resultList = new ArrayList<>();
    public static List<Integer> getTopK(int[] nums,int k){

        if(nums == null || nums.length == 0 ){
            return resultList;
        }

        if(nums.length == 1 && k==1){
            resultList.add(nums[0]);
            return resultList;
        }

        for(int num : nums){
            hmap.put(num,hmap.getOrDefault(num,0)+1);
        }

        HashMap<Integer,Integer> lmap = hmap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e2,LinkedHashMap::new)
                );

        int count = 0;
        for(int key: lmap.keySet()){
            resultList.add(key);
            count++;
            if(count == k){
                break;
            }
        }
        return resultList;
    }

    public static void main(String[] args){
        //System.out.println("1,2,1 :"+doesContainDuplicates(new int[]{1,2,1}));
        //System.out.println(""+getTopK(new int[]{1,1,1,2,2,3},2));// Expected Output: [1,2]
        System.out.println(""+getTopK(new int[]{4,1,-1,2,-1,2,3},2));// Expected Output: [1,2]

    }
}
