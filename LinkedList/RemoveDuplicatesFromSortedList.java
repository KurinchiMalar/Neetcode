package LinkedList;
/*
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
eg 1) Input: head = [1,1,2]
Output: [1,2]

eg 2) Input: head = [1,1,2,3,3]
Output: [1,2,3]
 */
public class RemoveDuplicatesFromSortedList {
    /*
    TC : O(n)
    SC : O(n) // recursive stack
     */
    public static ListNode deleteDuplicatesRecursive(ListNode head){
        if(head == null || head.next == null) return head;
        head.next = deleteDuplicatesRecursive(head.next);
        return (head.val == head.next.val) ? head.next:head; // 1-> 1-> 1-> , recursion goes till end. when equal link curHead.next = prevhead.next. (to remove elem in between)
    }
    /*
    TC : O(n)
    SC : O(1)
     */
    public static ListNode deleteDuplicates(ListNode head) {

        if(head == null ||head.next == null) return head;

        ListNode cur = head;
        ListNode p = head;
        while(cur != null){
            while(cur != null && cur.val == p.val){
                cur = cur.next;
            }
            // till end same elem , so correct linkage and return head.
            if(cur == null){
                p.next = cur;
                return head;
            }
            // found  a new elem to make linkage
            p.next = cur;
            p = cur;
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createNode(1);
        head.next = ListNode.createNode(2);
        head.next.next = ListNode.createNode(3);
        head.next.next.next = ListNode.createNode(4);
        head.next.next.next.next = ListNode.createNode(5);
        head.next.next.next.next.next = ListNode.createNode(6);
        head = deleteDuplicates(head);
        ListNode.printList(head);

        ListNode head1 = ListNode.createNode(1);
        head1.next = ListNode.createNode(1);
        head1.next.next = ListNode.createNode(1);
        head1.next.next.next = ListNode.createNode(1);
        head1.next.next.next.next = ListNode.createNode(1);
        head1.next.next.next.next.next = ListNode.createNode(1);
        head1 = deleteDuplicates(head1);
        ListNode.printList(head1);

        ListNode head2 = ListNode.createNode(1);
        head2.next = ListNode.createNode(1);
        head2.next.next = ListNode.createNode(1);
        head2.next.next.next = ListNode.createNode(1);
        head2.next.next.next.next = ListNode.createNode(1);
        head2.next.next.next.next.next = ListNode.createNode(1);
        head2 = deleteDuplicatesRecursive(head2);
        ListNode.printList(head2);
    }
}
