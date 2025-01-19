package LinkedList;

public class RemoveLinkedListElements {

    /*
    TC : O(n)
    SC : O(n) for recursive stack
     */
    public static ListNode removeElementsRecursive(ListNode head,int val){
        if(head == null) return null;
        //go till the end
        // head.next will be assigned to head from prev call or head.next depending on whether it should be removed
        head.next = removeElementsRecursive(head.next,val);
        return head.val==val?head.next:head;
    }

    /*
    TC : O(n)
    SC : O(1) dummy node
     */

    public static ListNode removeElements(ListNode head, int val) {

        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // if head is to be removed, position dummy appropriately
        while(dummy.next != null && dummy.next.val == val){
            ListNode toRemoveNode = dummy.next;
            dummy.next = toRemoveNode.next;
            toRemoveNode.next = null;
            toRemoveNode = null;
        }

        if(dummy.next == null) return null; // full list is removed.

        // dummy.next here contains the proper head of the list to begin iteration.
        head = dummy.next;
        ListNode prev = head;
        ListNode cur = head.next;

        while(cur != null){
            if(cur.val == val){
                ListNode toRemoveNode = cur;
                prev.next = cur.next;
                cur = cur.next;

                toRemoveNode.next = null;
                toRemoveNode = null;

            }else{
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /*
    Tc : O(n)
    SC : O(1)
     */
    public ListNode removeElementsWithoutDummyNode(ListNode head, int val) {

        if(head == null) return null;

        // Position head
        while(head != null && head.val == val){
            head = head.next;
        }

        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;

            }else{
                cur = cur.next;
            }
        }

        return head;
    }



    public static void main(String[] args){
        ListNode head = ListNode.createNode(1);
        head.next = ListNode.createNode(2);
        head.next.next = ListNode.createNode(3);
        head.next.next.next = ListNode.createNode(4);
        head.next.next.next.next = ListNode.createNode(5);
        head.next.next.next.next.next = ListNode.createNode(6);
        head = removeElements(head,6);
        ListNode.printList(head);
        System.out.println("***********************************************");
        ListNode head2 = ListNode.createNode(7);
        head2.next = ListNode.createNode(7);
        head2.next.next = ListNode.createNode(7);
        head2.next.next.next = ListNode.createNode(7);
        head2.next.next.next.next = ListNode.createNode(7);
        head2.next.next.next.next.next = ListNode.createNode(5);
        head2 = removeElements(head2,7);
        ListNode.printList(head2);

    }
}
