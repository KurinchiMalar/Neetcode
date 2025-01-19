package LinkedList;
/*
https://leetcode.com/problems/palindrome-linked-list/description/
Given the head of a singly linked list, return true if it is a
palindrome
 or false otherwise.

 Input: head = [1,2,2,1]
Output: true
Input: head = [1,2]
Output: false

Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    /*
    TC: O(n/2) // reversing half the list
    SC : O(1)
     */
    public static ListNode reverse(ListNode head){
        if(head == null) return null;
        ListNode p = null;
        ListNode cur = head;
        while(cur != null){
            ListNode save = cur.next;
            cur.next = p;
            p = cur;
            cur = save;
        }
        return p;
    }
    /*
    TC : O(n)
    SC : O(1)
     */
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;

        // find middle using floyd's

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = slow;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        // slow points at middle now , cut the links.
        // cut the link now
        prev.next = null;
        // reverse the backhalf (ie) slow to end of list
        slow = reverse(slow);

        fast = head;
        // Fast will move one one step for comparision anymore
        while(fast != null && slow != null){
            if(fast.val != slow.val) return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createNode(1);
        head.next = ListNode.createNode(2);
        head.next.next = ListNode.createNode(2);
        head.next.next.next = ListNode.createNode(1);
        ListNode.printList(head);
        System.out.println(isPalindrome(head));

    }
}
