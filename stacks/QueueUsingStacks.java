package stacks;
/*
https://leetcode.com/problems/implement-queue-using-stacks/description/
 */

/*
The expensive operation of moving elements from s1 to s2 happens only when s2 is empty.
Once moved, multiple pop() or peek() calls can be made in O(1)

O(1) time until s2 is exhausted.
Thus, the overall cost is spread across multiple operations, making the amortized time complexity O(1)


TC : O(1)
SC : O(n)
 */
import java.util.Stack;

class MyQueue {
    Stack s1;
    Stack s2;

    public MyQueue() {
        s1 = new Stack();
        s2 = new Stack();
    }

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return (int)s2.pop();
    }

    public int peek() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return (int)s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();

    }
}

public class QueueUsingStacks {
    public static void main(String[] args) {

        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false

    }


}
