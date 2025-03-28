package HackerRankPractice.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyQueries {

     List<Integer> freqQuery(List<List<Integer>> queries) {

        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> hmap = new HashMap<>();
        for(List<Integer> query : queries){
            int q = query.get(0);
            int val = query.get(1);

            switch(q){
                case 1:
                    hmap.put(val,hmap.getOrDefault(val,0)+1);
                    break;
                case 2:
                    if(hmap.containsKey(val)){
                        hmap.put(val, hmap.get(val)-1);
                        if(hmap.get(val) == 0) hmap.remove(val);
                    }

                    break;
                case 3:
                    boolean found = false;
                    for(int x : hmap.values()){
                        if(x == val){
                            result.add(1);
                            found = true;
                            break;
                        }
                    }
                    if(!found)result.add(0);
                    break;
            }
        }
        return result;
    }

    /*
    One map to maintain the actual counts of elements  ---> countMap
    Another map to maintain how many elements for a particular frequency --> freqMap
     */

    static List<Integer> freqQueryEfficient(List<List<Integer>> queries) {

        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> countMap = new HashMap<>();
        Map<Integer,Integer> freqMap = new HashMap<>();

        for(List<Integer> query: queries){
            int q = query.get(0);
            int val = query.get(1);

            switch(q){
                case 1: // insert
                    int countMapVal = countMap.getOrDefault(val, 0);
                    // old freq countMap.get(val) should be decreased. x occured 2 times
                    if(countMapVal > 0){  // to avoid decrements going negative
                        freqMap.put(countMapVal,freqMap.getOrDefault(countMapVal,0)-1);
                    }

                    countMap.put(val, countMap.getOrDefault(val, 0)+1);
                    countMapVal = countMap.get(val);
                    // new freq countMap.get(val) should be increased. x occurs now 3 times

                    freqMap.put(countMapVal,freqMap.getOrDefault(countMapVal,0)+1);
                    break;
                case 2:
                    countMapVal = countMap.getOrDefault(val, 0);
                    if(countMapVal > 0){ // to avoid decrements going negative
                        freqMap.put(countMapVal,freqMap.getOrDefault(countMapVal,0)-1);

                        countMap.put(val, countMap.getOrDefault(val, 0)-1);
                        countMapVal = countMap.get(val);

                        freqMap.put(countMapVal,freqMap.getOrDefault(countMapVal,0)+1);
                    }
                    break;
                case 3 :
                    int res = (freqMap.containsKey(val) && freqMap.get(val)>0) ? 1 : 0;
                    result.add(res);
                    break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FrequencyQueries ob = new FrequencyQueries();
        System.out.println(ob.freqQuery(List.of(List.of(3,4),List.of(2,1003),List.of(1,16),List.of(3,1))));
    }
}
