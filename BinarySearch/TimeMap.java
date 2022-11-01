
/*
Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".


Example 1:

Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"


Constraints:

1 <= key.length, value.length <= 100
key and value consist of lowercase English letters and digits.
1 <= timestamp <= 107
All the timestamps timestamp of set are strictly increasing.
At most 2 * 105 calls will be made to set and get.
 */

/*
All the timestamps timestamp of set are strictly increasing.
 */
package BinarySearch;

import java.sql.Time;
import java.util.*;


class Data{
    int timeStamp;
    String value;
    public Data(int timeStamp,String value){
        this.timeStamp = timeStamp;
        this.value = value;
    }
}

public class TimeMap{

    HashMap<String, List<Data>> map;
    public TimeMap(){
        map = new HashMap<String, List<Data>>();
    }
    public void set(String key, String value, int timestamp){
        if(!map.containsKey(key)){
            map.put(key,new ArrayList<Data>());
        }
        map.get(key).add(new Data(timestamp,value));
    }

    public String get(String key,int timestamp){
        if(!map.containsKey(key)){
            return "";
        }
        return binarySearch(map.get(key),timestamp);
    }

    public String binarySearch(List<Data> dataList,int timeStamp){

        int low = 0;
        int high = dataList.size()-1;
        while(low < high){
            int mid = low + ((high - low)>>1);

            if(dataList.get(mid).timeStamp==timeStamp){
                return dataList.get(mid).value;
            }

            if(dataList.get(mid).timeStamp < timeStamp){ // go to right half

                if(dataList.get(mid+1).timeStamp > timeStamp){
                    return dataList.get(mid).value;
                }
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ((timeStamp > dataList.get(low).timeStamp) ? dataList.get(low).value:"");  // low = high = 10 , timestamp = 5
    }



    public static void main(String[] args){
        TimeMap tob = new TimeMap();
        tob.set("love","high",10);
        tob.set("love","low",20);
        System.out.println(tob.get("love",5));
        System.out.println(tob.get("love",10));
        System.out.println(tob.get("love",15));
        System.out.println(tob.get("love",20));
        System.out.println(tob.get("love",25));
    }
}
