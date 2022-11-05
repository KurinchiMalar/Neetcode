/*
Return true if there is a cycle in the linked list. Otherwise, return false.
 */
/*
TC : O(n)
SC : O(1)
 */
package LinkedList;

public class CycleInLIst {

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode tort = head;
        ListNode hare = head;
        while(hare != null && hare.next != null){
            hare = hare.next.next;
            tort = tort.next;
            if(hare == tort){
                return true;
            }
        }
        return false;
    }
}
