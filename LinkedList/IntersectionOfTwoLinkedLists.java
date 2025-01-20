package LinkedList;
/*
https://leetcode.com/problems/intersection-of-two-linked-lists/
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
If the two linked lists have no intersection at all, return null.
 */
/*
Idea:
Start from the beginning and keep moving until one pointer reaches end (mostly the smaller list reaches end, if equal both will reach end)

Now skip from beginning the difference and then fixate l1 and l2 at equal lengths

Now do comparision step by step,
 */
/*
TC : 2 *  min(m,n) + |m-n| ==> O(m + n)
SC : O(1)

 */
public class IntersectionOfTwoLinkedLists {

        /*
        TC : O(n)
        Sc : O(1)
         */

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null && headB == null) return null;
        if(headA == null || headB == null) return null;

        ListNode l1 = headA;
        ListNode l2 = headB;
        ListNode p = null;

        // Keep moving until one of them reaches end. (Basically the smaller list exhausted)
        while(l1 != null && l2 != null){  // -------------------------------------------------> O(min (m,n))
            l1 = l1.next;
            l2 = l2.next;
        }

        // Now adjust the longer list for the diff and reposition the pointer
        // Adjusting longer list ----------------------------------------------------------------> O( |m-n| )
        if(l1 != null){ // l1 is longer
            p = l1;
            l1 = headA;
            l2 = headB;
            // Remove off the diff and position l1 appropriately
            while(p != null){
                l1 = l1.next;
                p = p.next;
            }
        }else if (l2 != null){ // l2 is longer
            p = l2;
            l2 = headB;
            l1 = headA;
            // Remove off the diff and position l2 appropriately
            while(p != null){
                l2 = l2.next;
                p = p.next;
            }
        }else{ // both are of equal length
            l1 = headA;
            l2 = headB;
        }
        //Both lists are of equal length from l1, l2 respectively
        while(l1 != null && l2 != null){ //-----------------------------------------------------------> O(min(m,n))
            if(l1 == l2){
                return l1;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return null; //no intersection
    }

    public static ListNode getIntersectionNodeConcise(ListNode headA, ListNode headB){
        if(headA == null || headB == null ) return null;
        ListNode a = headA;
        ListNode b = headB;
        while(a != b){
            a = a==null?headB:a.next;
            b = b==null?headA:b.next;
        }
        return a; // a (or) b
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createNode(4);
        head.next = ListNode.createNode(1);
        ListNode eight = ListNode.createNode(8);
        head.next.next = eight;
        eight.next = ListNode.createNode(4);
        eight.next.next = ListNode.createNode(5);
        ListNode.printList(head);

        ListNode head2 = ListNode.createNode(5);
        head2.next = ListNode.createNode(6);
        head2.next.next= ListNode.createNode(1);
        head2.next.next.next = eight;

        ListNode.printList(head2);
        System.out.println(getIntersectionNode(head,head2).val);
        System.out.println(getIntersectionNodeConcise(head,head2).val);


    }
}
