package MustDo500;
import java.util.*;
/*
https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class A24_MergeKSortedLists {

    /*
    Using mergeTwoLists - 2 pointer approach
    My submission : https://leetcode.com/problems/merge-k-sorted-lists/submissions/1276874006/
     */

    /*
    TC :  O(n1 +n2)
    SC : O(1)
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

    /*
    TC : O(N * k^2) ~= O(N ^ 3)
    SC : O(1)
     */
    public ListNode mergeKLists_TwoPointer(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        ListNode l0 = lists[0];
        for(int i = 1 ; i < lists.length; i++){
            l0 = mergeTwoLists(l0,lists[i]);
            /* TC : O(n1+n2) + O((n1+n2)+n3) + O((n1+n2+n3)+n4)......
                =  2N + 3N + 4N +....... +KN
                = N * (1 + 2 + 3+ .....k)
                = N * ( k*(k+1)/2)
            SC : O(1)*/
        }
        return l0;
    }
    /***********************************************************************************************/
    /*
    Using Priority Queue.
        Add the heads of given lists into PQ and build the result list by poping from PQ
     */
        /*
        My submission : https://leetcode.com/problems/merge-k-sorted-lists/submissions/1276901070/
        TC:
         */
    class NodeComparator implements Comparator<ListNode>{
        public int compare(ListNode n1, ListNode n2){
            if(n1.val > n2.val){
                return 1; // requires swap/sort
            }else if(n1.val < n2.val){
                return -1;
            }
            return 0;
        }

    }
    /*
    TC : O(K log K) + O(N * Klogk)
    SC: O(K) ... PW contains at max K elements at every ieration.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode l3 = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new NodeComparator());

        for(int i = 0 ; i < lists.length; i++){  // O(K)
            if(lists[i] != null){
                pq.add(lists[i]); // add the head of all the lists  // O(log K)
            }
        }

        while(!pq.isEmpty()){ // O( K *N)
            ListNode curr = pq.poll(); // O(log k)
            l3.next = curr;
            l3 = l3.next;
            if(curr.next != null )pq.add(curr.next); // O(log k)

        }
        return dummy.next;
    }

}
