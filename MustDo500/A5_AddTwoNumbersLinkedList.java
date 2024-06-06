package MustDo500;


  /*
  TC : O(n)
  Sc : O(n) // for result
   */
public class A5_AddTwoNumbersLinkedList {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode l3 = dummy;
        int carry = 0;
        while(l1 != null || l2 != null || carry > 0 ){
            int v1 = (l1==null) ? 0 : l1.val;
            int v2 = (l2== null) ? 0: l2.val;
            int sum = v1 + v2 + carry;
            l3.next = new ListNode(sum % 10);
            l3 = l3.next;
            carry = sum / 10;
            if(l1 != null)l1 = l1.next;
            if(l2 != null)l2 = l2.next;

        }

        return dummy.next;
    }
}
