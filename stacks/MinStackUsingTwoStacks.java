package stacks;
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
/*
Time Complexity : O(1)  for push , pop , top , getMin
Space Complexity : O(n) // for aux stack.
 */
import java.util.Stack;

public class MinStackUsingTwoStacks {

    Stack<Integer> mainStack;
    Stack<Integer> auxStack;
    public MinStackUsingTwoStacks() {
        mainStack = new Stack<Integer>();
        auxStack = new Stack<Integer>();
    }

    public void push(int val) {
        if(mainStack.isEmpty()){
            mainStack.push(val);
            auxStack.push(val);
        }else{
            mainStack.push(val);
            int minSoFar = auxStack.peek();
            if(val >= minSoFar){
                auxStack.push(minSoFar);
            }else{
                auxStack.push(val);
            }
        }
    }

    public void pop() {
        if(!mainStack.isEmpty()){
            mainStack.pop();
            auxStack.pop();
        }
    }

    public int top() {
        if(!mainStack.isEmpty()){
            return mainStack.peek();
        }else{
            return 0;
        }
    }

    public int getMin() {
        if(!mainStack.isEmpty()){
            return auxStack.peek();
        }else{
            return 0;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
