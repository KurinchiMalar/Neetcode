package MustDo500;


class ListNode {
    int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
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
public class A22_ReverseLinkedList {

    /*
    My submission : https://leetcode.com/problems/reverse-linked-list/submissions/1275189741/
    TC: O(n)
    SC: O(n)

     */
    public static ListNode reverseListRecursion(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }
        ListNode newNode = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;

    }

    /*
    My submission : https://leetcode.com/problems/reverse-linked-list/submissions/1275184427/
    TC: O(n)
    SC: O(1)
     */
    public ListNode reverseList(ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode q= head;
        ListNode p = null;
        while(q != null){
            ListNode save = q.next;
            q.next = p;
            p = q;
            q = save;
        }
        return p;

    }

    public static void main(String[] args){
        ListNode head = ListNode.createNode(1);
        head.next = ListNode.createNode(2);
        head.next.next = ListNode.createNode(3);
        head.next.next.next = ListNode.createNode(4);
        head.next.next.next.next = ListNode.createNode(5);
        ListNode.printList(head);
        //head = reverseListIterative(head);
        head = reverseListRecursion(head);
        ListNode.printList(head);

    }

}
