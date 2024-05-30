package MustDo500;



import java.util.HashMap;

/*
Idea :
    1) Actual cache maintained as l -> r (Doubly linked list) each node has {k,v}
    2) Maintain a hashmap<Integer,Dnode> for faster retrieval
 */
/*
My Submission: https://leetcode.com/problems/lru-cache/submissions/1271103284/

 */
class DNode{
    int key;
    int val;
    DNode prev;
    DNode next;
    DNode(int key, int val){
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }

}
public class A13_LRUCache {

    int capacity = 0;
    HashMap<Integer,DNode> hmap = null;
    DNode left = null;
    DNode right = null;

    A13_LRUCache(int capacity){
        this.capacity = capacity;
        this.hmap = new HashMap<Integer,DNode>();
        left = new DNode(0,0);
        right = new DNode(0,0);
        left.next = right;
        right.prev = left;
    }

    public void insert(DNode node){
        // Insert at the right
        DNode p = right.prev;
        p.next = node;
        node.prev = p;

        node.next = right;
        right.prev = node;
    }

    public void remove(DNode node){
        DNode p = node.prev;
        DNode n = node.next;

        p.next = n;
        n.prev = p;
    }

    public int get(int k){
        if(!hmap.containsKey(k)) return -1;
        // remove and insert at the left
        DNode cur = hmap.get(k);
        // Adjust the RU
        remove(cur);
        insert(cur);
        return cur.val;
    }

    public void put(int k,  int v){
        // put on the right

        // if the key already exists
        if (hmap.containsKey(k)) { // requires an update.
            remove(hmap.get(k));
            //hmap.remove(k);  --> you can directly update the value of the key.
        }
        DNode newNode = new DNode(k,v);
        hmap.put(k,newNode);
        insert(newNode);
        if(hmap.size() > capacity){ // need to evict the LRU, which is on the left
            DNode lru = left.next;
            hmap.remove(lru.key);
            remove(lru);
        }
    }

    public static void main(String[] args) {
        A13_LRUCache lRUCache = new A13_LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));
    }

}
