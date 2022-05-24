/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

import java.util.*;
import java.util.Map.*;
import static java.util.stream.Collectors.*;

class TopKFrequentElements {
    /*
	Time Complexity :O(n)+O(nlogn) (for sorting) + O(k)  = O(nlogn)
	// input parse + sort +  k times parse on freq hash map
	Space Complexity :O(n) // worst case no repeated elements
	https://leetcode.com/problems/top-k-frequent-elements/submissions/
	https://github.com/KurinchiMalar/Neetcode/blob/Arrays/TopKFrequentElements.java
 */
    public static int[] topKFrequentNlognNaive(int[] nums, int k) {
        if(nums==null || nums.length==0){
            return new int[0];
        }
        if(nums.length==1){
            return new int[]{nums[0]};
        }
        HashMap<Integer,Integer> numsFrequencyMap = new HashMap<>();
        for(int num:nums){
            numsFrequencyMap.put(num,numsFrequencyMap.getOrDefault(num,0)+1);
        }
        HashMap<Integer,Integer> numsFrequencySortedDescMap = numsFrequencyMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2) -> e2,
                                LinkedHashMap::new));

        //int[] valuesArray = (int[])numsFrequencySortedDescMap.values().toArray();
        ArrayList<Integer> keysList = new ArrayList<>(numsFrequencySortedDescMap.keySet());
        int[] resultAr = new int[k];
        for(int i=0;i < k;i++){
            resultAr[i] = keysList.get(i);
        }
        return resultAr;
    }
    /*
	Time Complexity :O(klogn)
	// k times poll from maxheap .
	Space Complexity :O(n) 
	https://leetcode.com/problems/top-k-frequent-elements/submissions/
	https://github.com/KurinchiMalar/Neetcode/blob/Arrays/TopKFrequentElements.java
 */
    public static int[] topKFrequentUsingMaxHeap(int[] nums, int k) {
        if(nums==null || nums.length==0){
            return new int[0];
        }
        if(nums.length==1){
            return new int[]{nums[0]};
        }
        HashMap<Integer,Integer> numsFrequencyMap = new HashMap<>();
        for(int num:nums){
            numsFrequencyMap.put(num,numsFrequencyMap.getOrDefault(num,0)+1);
        }
        /*for(Entry entry:numsFrequencyMap.entrySet()){
            System.out.println("key: "+entry.getKey()+" value: "+entry.getValue());
        }*/

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int freq:numsFrequencyMap.values()){
            maxHeap.add(freq);
        }
        int[] resultAr = new int[k];

        for(int i=0;i < k;i++){  // O(k)
            int currentMax = maxHeap.poll(); // O(logn)
            //System.out.println(currentMax);
            for(Entry entry:numsFrequencyMap.entrySet()){
                if((int)entry.getValue()==currentMax && i < k){
                    //resultList.add((int)entry.getKey());
                    resultAr[i] = (int)entry.getKey();
                    numsFrequencyMap.remove(entry.getKey());
                    break;
                }
            }
        }
        //resultAr = resultList.stream().mapToInt(i->i).toArray();
        //resultAr = (int[])ArrayList.toArray(resultList);
        return resultAr;
    }
    public static void main(String[] args){
        //System.out.println(""+Arrays.toString(topKFrequentNlognNaive(new int[]{1,1,1,2,2,3},2)));// Expected Output: [1,2]
        //System.out.println(""+Arrays.toString(topKFrequentNlognNaive(new int[]{1},1)));// Expected Output: [1]
        System.out.println(""+Arrays.toString(topKFrequentUsingMaxHeap(new int[]{1,1,1,2,2,3},2)));// Expected Output: [1,2]
        //System.out.println(""+Arrays.toString(topKFrequentUsingMaxHeap(new int[]{1},1)));// Expected Output: [1]
        //System.out.println(""+Arrays.toString(topKFrequentNlognNaive(new int[]{1,2},2)));// Expected Output: [1,2]
        System.out.println(""+Arrays.toString(topKFrequentUsingMaxHeap(new int[]{1,2},2)));// Expected Output: [1,2]
        //System.out.println(""+Arrays.toString(topKFrequentUsingMaxHeap(new int[]{4,1,-1,2,-1,2,3},2)));// Expected Output: [1,2]

    }
}