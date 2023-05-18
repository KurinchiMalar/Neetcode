package ArraysAndHashing;

import java.util.HashMap;
import java.util.Map;

public class FindUniqueAmongDuplicates {

    static HashMap<Integer, Integer> hmap = new HashMap<>();

    public static int getUnique(int[] ar) {
        for (int num : ar) {
            hmap.put(num, hmap.getOrDefault(num, 0)+1);
        }
        for (Map.Entry entry : hmap.entrySet()) {
            if ((Integer) entry.getValue() == 1) {
                return (Integer) entry.getKey();
            }
        }
        return -1;
    }
    public static void main(String[] args){
        System.out.println(getUnique(new int[]{1,3,2,1,2}));
    }
}
