package MustDo500;
/*
https://leetcode.com/problems/merge-two-sorted-lists/description/
My submission : https://leetcode.com/problems/merge-two-sorted-lists/submissions/1276860399/
 */
public class A25_MergeTwoSortedLists {

    /*
    TC : O(n1 +n2)
    SC : O(1) ...n1+n2 for result
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode l3 = dummy;

        while(list1 != null && list2 != null){

            if(list1.val <= list2.val){
                l3.next = new ListNode(list1.val);
                l3 =l3.next;
                if(list1 != null) list1 = list1.next;
            }else{
                l3.next = new ListNode(list2.val);
                l3 =l3.next;
                if(list2 != null) list2 = list2.next;
            }
        }
        while(list1 != null){
            l3.next = new ListNode(list1.val);
            l3 =l3.next;
            list1 = list1.next;
        }
        while(list2 != null){
            l3.next = new ListNode(list2.val);
            l3 =l3.next;
            list2 = list2.next;
        }
        return dummy.next;
    }
}
