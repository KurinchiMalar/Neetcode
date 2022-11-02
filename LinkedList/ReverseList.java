package LinkedList;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public  static ListNode createNode(int data){
        return new ListNode(data,null);
    }

    public static void printList(ListNode head){
        ListNode temp = head;
        while (temp != null){
            System.out.print(temp.val+"->");
            temp = temp.next;
        }
        System.out.println();
    }
 }
public class ReverseList {
    /*
    TC : O(n)
    SC : O(n) recursive stack
     */
    public static ListNode reverseListRecursive(ListNode head) {

        if(head == null){
            return null;
        }
        ListNode newHead = head;
        if(head.next != null){ // has a valid subproblem
            newHead = reverseListRecursive(head.next);
            head.next.next = head;
        }
        head.next = null;
        return newHead;
    }
    /*
     TC : O(n)
     SC : O(1)
      */
    public static ListNode reverseListIterative(ListNode head){
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        ListNode cur = head;
        ListNode prev = null;
        while(cur != null){
            ListNode save = cur.next;
            cur.next = prev;
            prev = cur;
            cur = save;
        }
        return prev;
    }

    public static void main(String[] args){
        ListNode head = ListNode.createNode(1);
        head.next = ListNode.createNode(2);
        head.next.next = ListNode.createNode(3);
        head.next.next.next = ListNode.createNode(4);
        head.next.next.next.next = ListNode.createNode(5);
        ListNode.printList(head);
        //head = reverseListIterative(head);
        head = reverseListRecursive(head);
        ListNode.printList(head);

    }

}
