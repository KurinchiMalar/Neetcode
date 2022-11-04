/*
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

https://leetcode.com/problems/copy-list-with-random-pointer/description/

 */
package LinkedList;

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointer {

    /*
    TC : O(n)
    SC : O(1)
     */
    public Node copyRandomListWithoutExtraSpace(Node head) {

        if(head == null){
            return null;
        }
        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        Node current = head;
        while(current != null){
            Node temp = current.next;
            current.next = new Node(current.val);
            current.next.next = temp;
            current = temp;
        }
        // Second round: assign random pointers for the copy nodes.
        current = head;
        while(current != null){
            if(current.random == null){
                current.next.random = null;
            }else{
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
        // Third round: restore the original list, and extract the copy list.
        current = head;
        Node copyHead = head.next;
        Node copycurrent = copyHead;

        while(copycurrent.next != null){
            current.next = current.next.next;
            current = current.next;

            copycurrent.next = copycurrent.next.next;
            copycurrent = copycurrent.next;
        }
        //current and copycurrent at respective last nodes
        // last node in orig list next pointer should be updated
        current = current.next.next;
        return copyHead;
    }
    /*
    TC : O(n)
    SC : O(n)
     */
    public Node copyRandomListWithExtraSpace(Node head) {

        if(head == null){
            return null;
        }
        Node current = head;
        HashMap<Node,Node> oldToCopyMap = new HashMap<>();
        oldToCopyMap.put(null,null); // when random points in orig points to null node.
        // create oldToCopyMap with oldnode to newnode mapping - pass 1
        while(current != null){
            oldToCopyMap.put(current,new Node(current.val));
            current = current.next;
        }

        current = head;
        // assign next and random pointers for the copy nodes.
        while(current != null){
            Node copy = oldToCopyMap.get(current);
            copy.next = oldToCopyMap.get(current.next);
            copy.random = oldToCopyMap.get(current.random);
            current = current.next;
        }
        return oldToCopyMap.get(head);
    }

}
