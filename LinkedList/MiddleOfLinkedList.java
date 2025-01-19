package LinkedList;
/*
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 */
public class MiddleOfLinkedList {

    public static ListNode middleNode(ListNode head) {
        if(head == null || head.next ==  null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createNode(1);
        head.next = ListNode.createNode(2);
        head.next.next = ListNode.createNode(3);
        head.next.next.next = ListNode.createNode(4);
        head.next.next.next.next = ListNode.createNode(5);
        head.next.next.next.next.next = ListNode.createNode(6);
        ListNode middleNode = middleNode(head);
        ListNode.printList(head);
        System.out.println("MiddleNode : "+middleNode.val);
    }
}
