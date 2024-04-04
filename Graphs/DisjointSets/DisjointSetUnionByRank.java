package Graphs.DisjointSets;

import java.util.ArrayList;
import java.util.List;


/*
Tutorial : https://www.youtube.com/watch?v=aBxjDBC4M1U
https://ide.geeksforgeeks.org/fde794b7-8b6f-41e8-8efc-0826b3d318e6
TC : O(4 Alpha) --> Alpha is almost a constant / slow growing function.

 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.


 */
public class DisjointSetUnionByRank {

    List<Integer> parentList = new ArrayList<>();
    List<Integer> rankList = new ArrayList<>();
    List<Integer> sizeList = new ArrayList<>();

    public DisjointSetUnionByRank(int n){
        for(int i = 0 ; i <= n; i++){ // 1 indexed , hence we need inclusive of n.
            rankList.add(0); // initially all ranks = 0
            parentList.add(i); // initially every node is it's parent
            sizeList.add(1); // initially each node is of size 1
        }
    }

    public  int findUltimateParent(int x){

        if(parentList.get(x) == x){  // base condition, if self parent reached that is the ultimate parent.
            return x;
        }
        int newUlp = findUltimateParent(parentList.get(x));
        parentList.set(x,newUlp); // Path Compression  .... without this will be logN complexity
        return newUlp;
    }



    public  void unionByRank(int u, int v){

        int ulpU = findUltimateParent(u);
        int ulpV = findUltimateParent(v);

        if(ulpU == ulpV ){ // same component , nothing to union
            return;
        }else if(rankList.get(ulpU) < rankList.get(ulpV)){
            //smaller rank becomes child
            parentList.set(ulpU,ulpV);
        } else if (rankList.get(ulpV) < rankList.get(ulpU)) {
            parentList.set(ulpV,ulpU);
        }else{  // both ranks equal , parent to be updated and rank to be updated.
            parentList.set(ulpU,ulpV);
            rankList.set(ulpV,rankList.get(ulpV)+1);
        }
    }

    public void unionBySize(int u, int v){
        int ulpU = findUltimateParent(u);
        int ulpV = findUltimateParent(v);

        if(ulpU == ulpV ) { // same component , nothing to union
            return;
        }
        if(sizeList.get(ulpU) < sizeList.get(ulpV)){
            parentList.set(ulpU,ulpV);
            sizeList.set(ulpV,sizeList.get(ulpV)+sizeList.get(ulpU));
        }else{
            parentList.set(ulpV,ulpU);
            sizeList.set(ulpU,sizeList.get(ulpV)+sizeList.get(ulpU));
        }
    }
    public boolean isSameComponent(DisjointSetUnionByRank d,int u, int v){
        if(d.findUltimateParent(u) == d.findUltimateParent(v)){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println("Union by rank..........................");
        DisjointSetUnionByRank ds = new DisjointSetUnionByRank(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);
        // if 3 and 7 same or not
        System.out.println(ds.isSameComponent(ds,3,7)?"Same":"Not Same");

        ds.unionByRank(3, 7);
        System.out.println(ds.isSameComponent(ds,3,7)?"Same":"Not Same");

        System.out.println("Union by size..........................");
        DisjointSetUnionByRank ds1 = new DisjointSetUnionByRank(7);
        ds1.unionBySize(1, 2);
        ds1.unionBySize(2, 3);
        ds1.unionBySize(4, 5);
        ds1.unionBySize(6, 7);
        ds1.unionBySize(5, 6);
        // if 3 and 7 same or not
        System.out.println(ds1.isSameComponent(ds1,3,7)?"Same":"Not Same");

        ds1.unionBySize(3, 7);
        System.out.println(ds1.isSameComponent(ds1,3,7)?"Same":"Not Same");

    }
}
