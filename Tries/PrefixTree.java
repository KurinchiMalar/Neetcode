package Tries;

import java.util.HashMap;

class TrieNode{
    HashMap<Character,TrieNode> children;
    boolean isEndOfWord;
    public TrieNode(){
        this.children = new HashMap<Character,TrieNode>();
        this.isEndOfWord = false;
    }
}
class PrefixTree {
    TrieNode root;
    public PrefixTree() {
        this.root = new TrieNode();
    }

    /*
    Time complexity : O(m), where m is the key length.
In each iteration of the algorithm, we either examine or create a node in the trie till we reach the end of the key. This takes only mmm operations.

    Space complexity : O(m).
In the worst case newly inserted key doesn't share a prefix with the the keys already inserted in the trie. We have to add mmm new nodes, which takes us O(m)O(m)O(m) space.
     */
    public void insert(String word) {
        TrieNode cur = root;
        for(Character ch:word.toCharArray()){
            if(!cur.children.containsKey(ch)){
                cur.children.put(ch,new TrieNode());
            }
            cur = cur.children.get(ch);
        }
        cur.isEndOfWord = true;
    }
    /*
    Time complexity : O(m)
    In each step of the algorithm we search for the next key character. In the worst case the algorithm performs mmm operations.

    Space complexity : O(1)
     */
    public boolean search(String word) {
        TrieNode cur = root;
        for(Character ch:word.toCharArray()){
            if(!cur.children.containsKey(ch)){
                return false;
            }
            cur = cur.children.get(ch);
        }
        return cur.isEndOfWord;
    }
    /*
     Time complexity : O(m)
     Space complexity : O(1)
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(Character ch:prefix.toCharArray()){
            if(!cur.children.containsKey(ch)){
                return false;
            }
            cur = cur.children.get(ch);
        }
        return true;
    }
    public static void main(String[] args){
        PrefixTree trie = new PrefixTree();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
    }
}

