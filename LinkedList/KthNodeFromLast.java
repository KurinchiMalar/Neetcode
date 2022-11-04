/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

 */
/*
TC : O(n)
SC : O(1)
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
package LinkedList;

public class KthNodeFromLast {

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if(head.next == null){
            if(n==1){
                head = null;
            }
            return null;
        }

        ListNode diff = head;
        ListNode kthnode = head;
        ListNode prev = null;

        // move diff n times from beginnning.
        for(int i=0; i < n; i++){
            diff = diff.next;
        }

        // diff to reach end ->  has to move remaining length-n nodes
        // kthnode can move same length-n from beginning to point at nth          //node from last.
        while(diff != null){
            diff = diff.next;
            prev = kthnode;
            kthnode = kthnode.next;
        }
        //remove kthnode;
        if(prev == null){ // head is the kthnode.
            prev = head;
            head = head.next;
            prev = null;
        }else{
            prev.next = kthnode.next;
            kthnode = null;
        }

        return head;
    }

    public static void main(String[] args){
        ListNode list1 = ListNode.createNode(1);
        list1.next = ListNode.createNode(2);
        list1.next.next = ListNode.createNode(3);
        list1.next.next.next = ListNode.createNode(4);
        list1.next.next.next.next = ListNode.createNode(5);

        ListNode list2 = ListNode.createNode(1);
        list2.next = ListNode.createNode(2);

        ListNode.printList(removeNthFromEnd(list1,2));
        ListNode.printList(removeNthFromEnd(list2,2));
    }
}
