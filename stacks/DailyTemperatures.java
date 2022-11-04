package stacks;

import java.util.Stack;
import java.util.*;

/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */
/*
TC : O(n)
SC : O(n)
https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> indicesStack = new Stack<Integer>();
        int n = temperatures.length;
        int[] resultAr = new int[n];
        for(int i=0; i < n; i++){
            while(!indicesStack.isEmpty() && temperatures[i] > temperatures[indicesStack.peek()]){
                int idx = indicesStack.pop();
                resultAr[idx] = i-idx;
            }
            indicesStack.push(i);
        }
        return resultAr;
    }

    public static int[] dailyTemperaturesWithoutStack(int[] temperatures) {
        int n = temperatures.length;
        int[] indicesAr = new int[n];
        int top = -1;
        int[] resultAr = new int[n];
        for(int i=0; i < n; i++){
            while(top > -1  && temperatures[i] > temperatures[indicesAr[top]]){
                int idx = indicesAr[top];
                top--;
                resultAr[idx] = i-idx;
            }
            indicesAr[++top] = i;
        }
        return resultAr;
    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30,40,50,60})));
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30,60,90})));
        System.out.println("-------------------------------");
        System.out.println(Arrays.toString(dailyTemperaturesWithoutStack(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println(Arrays.toString(dailyTemperaturesWithoutStack(new int[]{30,40,50,60})));
        System.out.println(Arrays.toString(dailyTemperaturesWithoutStack(new int[]{30,60,90})));
    }
}
