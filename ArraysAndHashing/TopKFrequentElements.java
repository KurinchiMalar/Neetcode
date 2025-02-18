package ArraysAndHashing;/*
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
import java.util.function.Function;
import java.util.stream.Collectors;

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
        for(int num:nums){ //O(N)
            numsFrequencyMap.put(num,numsFrequencyMap.getOrDefault(num,0)+1);
        }
        /*for(Entry entry:numsFrequencyMap.entrySet()){
            System.out.println("key: "+entry.getKey()+" value: "+entry.getValue());
        }*/

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int freq:numsFrequencyMap.values()){
            maxHeap.add(freq);   // O(log N)
        }
        int[] resultAr = new int[k];

        for(int i=0;i < k;i++){  // O(k)
            int currentMax = maxHeap.poll(); // O(logn)

            for(Entry entry:numsFrequencyMap.entrySet()){
                if((int)entry.getValue()==currentMax ){
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

    //-------------------------------------------------------------------------------------------------------------------
    /*
     * Time Complexity: O(N + M log M)
     * - Counting occurrences: O(N)
     * - Sorting the map entries: O(M log M), where M is the number of unique elements
     * - Extracting top K elements: O(K)
     * - Overall: O(N log M)
     *
     * Space Complexity: O(M + K)
     * - Frequency map: O(M)
     * - Sorting overhead: O(M)
     * - Output list: O(K)
     */
    public static int[] topKFrequentHashMap(int[] nums, int k){
        if(nums==null || nums.length==0){
            return new int[0];
        }
        if(nums.length==1){
            return new int[]{nums[0]};
        }
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(toList());

        // Count the frequency of each element
        Map<Integer,Long> freqMap = numsList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        // sort based on the freq Count
        List<Integer> resultList = freqMap.entrySet().stream()
                .sorted((a,b) -> Long.compare(b.getValue(),a.getValue()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(toList());
        return resultList.stream().mapToInt(i->i).toArray();

    }
   /*
    * Time Complexity: O(N + M log K)
    * - Counting occurrences: O(N)
    * - Adding elements to heap: O(M log K)
    * - Extracting elements: O(K log K)
    * - Overall: O(N log K)
    *
    * Space Complexity: O(M + K)
    * - Frequency map: O(M)
    * - Min-heap storage: O(K)
    * - Output list: O(K)
    */
    public static int[] topKFrequentMinHeap(int[] nums, int k){
        if(nums==null || nums.length==0){
            return new int[0];
        }
        if(nums.length==1){
            return new int[]{nums[0]};
        }
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(toList());
        Map<Integer,Long> freqMap = numsList.stream().collect(Collectors.groupingBy(n->n, Collectors.counting()));

        // Step 2: Use a PriorityQueue (Min-Heap) to keep track of top k elements
        PriorityQueue<Map.Entry<Integer, Long>> minPQ = new PriorityQueue<>(
                Comparator.comparingLong(Map.Entry::getValue) // Sort by frequency (ascending) , PQ is sorted based on the map value
        );

        // Step 3: Add elements to minHeap, maintaining size <= k
        for(Entry<Integer,Long> entry : freqMap.entrySet()){
            minPQ.offer(entry);
            while(minPQ.size() > k){
                minPQ.poll(); // remove the least val so far
            }
        }

        // Step 4: Extract top K elements from minHeap
        List<Integer> resultList =  minPQ.stream()
                                        .map(Map.Entry::getKey)
                                        .sorted((a, b) -> Long.compare(freqMap.get(b), freqMap.get(a))) // Sort by frequency descending
                                        .collect(Collectors.toList());

        return resultList.stream().mapToInt(i->i).toArray();
    }
    /*
	Time Complexity :O(n)
	// Maintaining array of : count index {arraylist of keys}
	Space Complexity :O(n)
	https://leetcode.com/problems/top-k-frequent-elements/submissions/
	https://github.com/KurinchiMalar/Neetcode/blob/Arrays/TopKFrequentElements.java
 */
    public static int[] topKFrequentEfficient(int[] nums, int k) {
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
        int maxFreqPossible = nums.length+1;
        ArrayList<Integer>[] freqIndexedAr = new ArrayList[maxFreqPossible];
        // initialize empty arraylist for all the indexes.
        for(int i=0; i < maxFreqPossible; i++){
            freqIndexedAr[i] = new ArrayList<Integer>();
        }

        for(Entry entry:numsFrequencyMap.entrySet()){
            int currentFreq = (int)entry.getValue();
            freqIndexedAr[currentFreq].add((int)entry.getKey());
        }
        ArrayList<Integer> resultList = new ArrayList<>();
        for(int j=maxFreqPossible-1; j > 0; j--){
            ArrayList<Integer> listForCurrentFrequency = freqIndexedAr[j];
            if(!listForCurrentFrequency.isEmpty()){
                for(int i=0;i<listForCurrentFrequency.size();i++){
                    resultList.add(listForCurrentFrequency.get(i));
                    if(resultList.size() == k){
                        return resultList.stream().mapToInt(e -> e).toArray();
                    }
                }
            }
        }
        return resultList.stream().mapToInt(e -> e).toArray();
    }

    public static void main(String[] args){
        //System.out.println(""+Arrays.toString(topKFrequentNlognNaive(new int[]{1,1,1,2,2,3},2)));// Expected Output: [1,2]
        //System.out.println(""+Arrays.toString(topKFrequentNlognNaive(new int[]{1},1)));// Expected Output: [1]
        //System.out.println(""+Arrays.toString(topKFrequentUsingMaxHeap(new int[]{1,1,1,2,2,3},2)));// Expected Output: [1,2]
        //System.out.println(""+Arrays.toString(topKFrequentUsingMaxHeap(new int[]{1},1)));// Expected Output: [1]
        //System.out.println(""+Arrays.toString(topKFrequentNlognNaive(new int[]{1,2},2)));// Expected Output: [1,2]
        System.out.println(Arrays.toString(topKFrequentUsingMaxHeap(new int[]{1, 2}, 2)));// Expected Output: [1,2]
        //System.out.println(""+Arrays.toString(topKFrequentUsingMaxHeap(new int[]{4,1,-1,2,-1,2,3},2)));// Expected Output: [1,2]
        System.out.println(Arrays.toString(topKFrequentEfficient(new int[]{1, 1, 1, 2, 2, 3}, 2)));// Expected Output: [1,2]
        System.out.println(Arrays.toString(topKFrequentEfficient(new int[]{1}, 1)));// Expected Output: [1]
        System.out.println(Arrays.toString(topKFrequentEfficient(new int[]{1, 2}, 2)));// Expec

        System.out.println("***************************** After NatWest ***********************************************");
        System.out.println(Arrays.toString(topKFrequentHashMap(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4},2)));
        System.out.println(Arrays.toString(topKFrequentMinHeap(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4},2)));
    }
}