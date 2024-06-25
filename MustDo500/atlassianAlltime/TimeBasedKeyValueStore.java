package MustDo500.atlassianAlltime;

/*
Design a time-based key-value data structure that can store
multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 */
 /*
 IDEA:
    single key can have multiple (values , ts)
  */

import BinarySearch.TimeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Data{
    String value;
    int timeStamp;
    Data(String value,int timeStamp){
        this.value = value;
        this.timeStamp = timeStamp;
    }
}
/*
TC : set O(1) , get O(log n)
SC : k unique key...each key can have n Data entries ==> O( k * n)
 */
public class TimeBasedKeyValueStore {
    HashMap<String, List<Data>> tMap;

    TimeBasedKeyValueStore(){
        tMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!tMap.containsKey(key)){
            tMap.put(key,new ArrayList<Data>());
        }
        tMap.get(key).add(new Data(value,timestamp));
    }

    public String get(String key, int timestamp){
        if(!tMap.containsKey(key)){
            return "";
        }
        return binarySearch(tMap.get(key),timestamp);
    }

    public String binarySearch(List<Data> dataList,int timestamp){

        int low = 0;
        int high = dataList.size()-1;

        while(low < high){

            int mid = low - ((high-low) >> 1);

            String midValue = dataList.get(mid).value;
            int midTimeStamp = dataList.get(mid).timeStamp;

            if(timestamp == midTimeStamp){
                return midValue;
            }
            if(timestamp > midTimeStamp){ // go to right half
                // check if we are at boundary
                // mid = 4 , mid+1 = 6 ...given timestamp 5 ...return mid
                if(mid +1 <= high && dataList.get(mid+1).timeStamp > timestamp){
                    return midValue;
                }
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return (timestamp < dataList.get(low).timeStamp ? "":dataList.get(low).value);
    }
    public static void main(String[] args){
        TimeBasedKeyValueStore tob = new TimeBasedKeyValueStore();
        tob.set("love","high",10);
        tob.set("love","low",20);
        System.out.println(tob.get("love",5));
        System.out.println(tob.get("love",10));
        System.out.println(tob.get("love",15));
        System.out.println(tob.get("love",20));
        System.out.println(tob.get("love",25));
    }
}
