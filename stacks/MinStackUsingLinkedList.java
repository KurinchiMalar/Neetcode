package stacks;
/*
TC : O(1)
SC : O(n) // n nodes in list created.
https://leetcode.com/problems/min-stack/submissions/
 */
class Node{
    int val;
    int min;
    Node next;

    Node(int val,int min,Node next){
        this.val =val;
        this.min = min;
        this.next = next;
    }
}
public class MinStackUsingLinkedList {
    Node head;
    public void push(int val) {
        if(head == null){
            head = new Node(val,val,null);
        }else{
            Node n = new Node(val,Integer.min(val,head.min),head);
            head = n;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
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