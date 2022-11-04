/*
You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Input: head = [1,2,3,4]
Output: [1,4,2,3]

Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 */

/*
TC : O(n)
SC : O(1)
https://leetcode.com/problems/reorder-list/
 */
package LinkedList;


public class ReorderList {
    public static ListNode reverse(ListNode head){
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while(current != null){
            ListNode save = current.next;
            current.next = prev;
            prev = current;
            current = save;
        }
        return prev;
    }

    public static ListNode reorderList(ListNode head) {

        ListNode fast=head;
        ListNode slow= head;
        ListNode midPrev = null;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            midPrev = slow;
            slow = slow.next;
        }

        //slow points to mid node
        midPrev.next = reverse(slow);

        ListNode list2 = midPrev.next;
        midPrev.next = null;    // break the list


        // Merge orig and reversed list
        ListNode p = head;
        ListNode list1 = head.next;

        while(list1 != null && list2 != null){
            p.next = list2;
            list2 = list2.next;
            p = p.next;
            p.next = list1;
            p = list1;
            list1 = list1.next;
        }
        if(list1 != null){
            p.next = list1;
        }
        if(list2 != null){
            p.next = list2;
        }
        return head;
    }

    public static void main(String[] args){
        ListNode list1 = ListNode.createNode(1);
        list1.next = ListNode.createNode(2);
        list1.next.next = ListNode.createNode(3);
        //list1.next.next.next = ListNode.createNode(4);
        ListNode.printList(reorderList(list1));
    }
}
