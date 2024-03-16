package ArraysAndHashing;


import java.util.Map;
import java.util.HashMap;
public class Permutation {
    static Map<Integer,Integer> freqmap = new HashMap<>();
    public static boolean permutation(int[] array1, int[] array2){
        int m = array1.length;
        int n = array2.length;
        if( m != n || m < n || m > n){
            return false;
        }else{
            for(int i=0 ; i < array1.length; i++){
                freqmap.put(array1[i],freqmap.getOrDefault(array1[i],0)+1);
                freqmap.put(array2[i],freqmap.getOrDefault(array2[i],0)-1);
               if(freqmap.get(array1[i]) == 0){
                   freqmap.remove(array1[i]);
               }
               if(freqmap.get(array2[i]) == 0){
                   freqmap.remove(array2[i]);
               }

            }
            if(freqmap.isEmpty()){
                return true;
            }
            return false;
        }


    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {5,1,2,3,4};
        System.out.println(permutation(arr1,arr2));
    }

}

