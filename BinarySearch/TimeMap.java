package BinarySearch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;


class TimeMapDS {
    HashMap<String, LinkedHashMap<Integer,String>> dmap;

    public TimeMapDS() {
        dmap = new HashMap<String,LinkedHashMap<Integer,String>>();
    }

    public void set(String key, String value, int timestamp) {

        if(dmap.containsKey(key)){
            dmap.get(key).put(timestamp,value);

        }else{
            LinkedHashMap<Integer,String> tsmap = new LinkedHashMap<Integer,String>();
            tsmap.put(timestamp,value);
            dmap.put(key,tsmap);
        }

    }

    public String get(String key, int timestamp) {

        if(!dmap.containsKey(key)){
            return "";
        }else{
            LinkedHashMap<Integer,String> tsmap = dmap.get(key);
            if(tsmap.containsKey(timestamp)){
                return tsmap.get(timestamp);
            }else{
                // we need to find the nearest maximum timestamp available in tsmap to given timestamp
                Integer[] nums = tsmap.keySet().toArray(new Integer[tsmap.size()]);

                int left = 0;
                int right = tsmap.size()-1;
                while(left <= right){
                    int mid = left + ((right-left)>>1);
                    if(timestamp < nums[mid]){
                        right = mid - 1;
                    }else{
                        left = mid + 1;
                    }
                }
                if(right < 0) {
                    return (timestamp < nums[left])?"":tsmap.get(nums[left]);    // if requested value is less than the most minimum in array return ""
                }else if(left > tsmap.size()-1){
                    return (timestamp < nums[right])?"":tsmap.get(nums[right]);
                }
                // considering (right, left) adjacent elements .. one with mimimum difference with timestamp is the nearest max.
                return (nums[left] - timestamp) < (timestamp - nums[right]) ? tsmap.get(nums[left]) : tsmap.get(nums[right]);
            }
        }

    }
}

public class TimeMap{

    public static void main(String[] args){
        TimeMapDS tob = new TimeMapDS();
        tob.set("love","high",10);
        tob.set("love","low",20);
        System.out.println(tob.get("love",5));
        System.out.println(tob.get("love",10));
        System.out.println(tob.get("love",15));
        System.out.println(tob.get("love",20));
        System.out.println(tob.get("love",25));
    }
}
