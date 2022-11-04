/*
TC : O(n)
SC : O(1)
https://leetcode.com/problems/merge-two-sorted-lists/
 */
package LinkedList;

public class MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null && list2 != null) {
            return list2;
        }
        if (list1 != null && list2 == null) {
            return list1;
        }

        // position the pointers
        ListNode mainHead = null;
        ListNode p = null;

        if (list1.val <= list2.val) {
            mainHead = list1;
            p = list1;
            list1 = list1.next;
        } else {
            mainHead = list2;
            p = list2;
            list2 = list2.next;
        }

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }

        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return mainHead;
    }
    public static void main(String[] args){
        ListNode list1 = ListNode.createNode(1);
        list1.next = ListNode.createNode(2);
        list1.next.next = ListNode.createNode(4);

        ListNode list2 = ListNode.createNode(1);
        list2.next = ListNode.createNode(3);
        list2.next.next = ListNode.createNode(4);
        ListNode.printList(mergeTwoLists(list1,list2));
    }
}
