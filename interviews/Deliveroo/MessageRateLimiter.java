package interviews.Deliveroo;
/*

https://chatgpt.com/c/679fe6df-3fc4-8005-8375-14b763f4cdc1


Coding problem. A basic message delivery service that has a rate limiting algorithm that drops any message that has balready been dlevered in the last k seconds.
Given the integer k, a list of messages as an array of n strings, messages and a sorted intger array timestamps representing the time at which the message arrived, for each message report the sting "true" if the message is delivered adn "false" otherwise.

Example:

Suppose n = 6, timestamps =[1,4,510,11,14], messages=["hello", "bye","bye","hello","bye","hello"] and k=5
Answer is ["true","true","false","true","ture","false"]


public static List<String> getMessageStatus(List<Integer> timestamps, List<String> messages, int k ){}

please help with code
 */
import java.util.*;

import java.util.*;

public class MessageRateLimiter {
    public static List<String> getMessageStatus(List<Integer> timestamps, List<String> messages, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> lastDelivered = new HashMap<>();

        for (int i = 0; i < timestamps.size(); i++) {
            int time = timestamps.get(i);
            String message = messages.get(i);

            // If message was delivered before or not delivered within the time window, drop it
            if (!lastDelivered.containsKey(message) || time - lastDelivered.get(message) >= k) {
                result.add("true"); // Message is delivered
                lastDelivered.put(message, time); // Update last delivered timestamp
            } else {
                result.add("false"); // Message is dropped
            }
        }

        return result;
    }
    /*
    Another optimized solution by sorted timestamps :

    https://chatgpt.com/c/679fe6df-3fc4-8005-8375-14b763f4cdc1
     */

    public static void main(String[] args) {
        List<Integer> timestamps = Arrays.asList(4, 1, 1, 1, 11, 4);
        List<String> messages = Arrays.asList("message-2", "message-2", "message-3", "message-2", "message-5");
        int k = 5;

        System.out.println(getMessageStatus(timestamps, messages, k)); // [true, false, true, true]
    }
}

