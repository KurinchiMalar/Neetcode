/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 */
/*
https://leetcode.com/problems/lru-cache/description/
get(), put() -> O(1)
 */
package LinkedList;

import java.util.HashMap;

class Dnode{
    int key;
    int val;
    Dnode prev;
    Dnode next;
    Dnode(int key, int val){
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

public class LRUCache {

    int capacity = 0;
    HashMap<Integer,Dnode> hmap = null;

    Dnode left = null;
    Dnode right = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hmap = new HashMap<Integer,Dnode>();
        // dummy left and right notes to point to LRU and MRU respectively.
        this.left = new Dnode(0,0);
        this.right = new Dnode(0,0);
        left.next = right;
        right.prev = left;
    }

    public void remove(Dnode node){
        Dnode p = node.prev;
        Dnode n = node.next;
        p.next = n;
        n.prev = p;
    }

    public void insert(Dnode node){
        // insert at right
        Dnode p = right.prev;
        p.next = node;
        right.prev = node;
        node.next = right;
        node.prev = p;
    }
    public int get(int key) {
        if(hmap.containsKey(key)){
            // remove and insert at right
            remove(hmap.get(key));
            insert(hmap.get(key));
            return hmap.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(hmap.containsKey(key)){
            // again remove and insert; we are touching it hence becomes recently used
            remove(hmap.get(key));
        }
        hmap.put(key,new Dnode(key,value));
        insert(hmap.get(key));

        if(hmap.size() > this.capacity){
            // remove the LRU and remove the corresponding key from hmap as well
            Dnode lru = left.next;
            hmap.remove(lru.key);
            remove(lru);
        }
    }

    public static void main(String[] args){
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4

    }
}

