/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.

 */
/*
TC: O(n)
SC : O(n)
https://leetcode.com/problems/add-two-numbers/description/
 */
package LinkedList;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // this is a dummy node. dummy.next will be head of the result linked list.
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        /* 1.  either l1 or l2 should have digit ; both cannot be null
           2.  consider l1 = 7 ; l2 = 8
                  sum = 7+8+0 = 15 , new Node(5) , carry = 1
                  l1 = null, l2 = null, carry = 1.
                  sum = 0+0+1 = 1.  new Node(1)
              RESULT = 5 -> 1.    */
        while(l1 != null || l2 != null || carry == 1){
            int v1 = (l1 != null)?l1.val:0;
            int v2 = (l2 != null)?l2.val:0;

            int sum = v1 + v2 + carry;
            //compute new carry
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if(l1 != null)l1 = l1.next;
            if(l2 != null)l2 = l2.next;
        }
        return dummy.next;
    }

    public static void main(String[] args){
        ListNode list1 = ListNode.createNode(2);
        list1.next = ListNode.createNode(4);
        list1.next.next = ListNode.createNode(3);

        ListNode list2 = ListNode.createNode(5);
        list2.next = ListNode.createNode(6);
        list2.next.next = ListNode.createNode(4);
        ListNode.printList(addTwoNumbers(list1,list2));

        ListNode list3 = ListNode.createNode(7);
        ListNode list4 = ListNode.createNode(8);
        ListNode.printList(addTwoNumbers(list3,list4));

        ListNode list5 = ListNode.createNode(1);
        list5.next = ListNode.createNode(2);
        list5.next.next = ListNode.createNode(9);

        ListNode list6 = ListNode.createNode(0);
        list6.next = ListNode.createNode(1);
        list6.next.next = ListNode.createNode(9);
        ListNode.printList(addTwoNumbers(list5,list6));
    }
}
