package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
https://leetcode.com/problems/maximum-units-on-a-truck/
You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.



Example 1:

Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
Example 2:

Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
Output: 91


Constraints:

1 <= boxTypes.length <= 1000
1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
1 <= truckSize <= 106
 */

/*
Variation of Fractional Knapsack

TC : O(nlogn) // sorting
SC : O(1)  // sorting based on units given
 */
public class MaximumUnitsTruck {

    class BoxCategory{
        int count;
        int numUnits;
        BoxCategory(int count, int numUnits){
            this.count = count;
            this.numUnits = numUnits;
        }
    }
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int maxUnits = 0;
        ArrayList<BoxCategory> boxCatList = new ArrayList<BoxCategory>();
        for(int[] boxType : boxTypes){
            boxCatList.addAll(Arrays.asList(new BoxCategory(boxType[0],boxType[1])));
        }

        Collections.sort(boxCatList,new Comparator<BoxCategory>(){
            @Override
            public int compare(BoxCategory o1, BoxCategory o2){
                if(o1.numUnits < o2.numUnits){
                    return 1;
                }else if(o1.numUnits > o2.numUnits){
                    return -1;
                }else{
                    return 0;
                }
            }
        });

        for(BoxCategory boxCategory : boxCatList){

            int curCount = boxCategory.count;
            int curNumUnits = boxCategory.numUnits;

            if(curCount <= truckSize){
                // can be taken as whole
                truckSize = truckSize - curCount;
                maxUnits += (curNumUnits * curCount);
            }else{
                //fraction to be taken
                double fraction =  (double)truckSize / (double)curCount;
                //truckSize = truckSize - (double)(fraction * curNumUnits);
                double temp = fraction * (double) (curNumUnits * curCount);
                maxUnits += (int)Math.round(temp);
                break;
            }

        }
        return maxUnits;
    }

    public static void main(String[] args) {
        MaximumUnitsTruck ob = new MaximumUnitsTruck();
        int[][] boxTypes = {{5,10},{2,5},{4,7},{3,9}};
        //System.out.println(ob.maximumUnits(boxTypes,10));
        int[][] boxTypes1 = {{35,14},{57,99},{70,48},{50,70},{59,24},{48,72},{27,48},{50,89},{91,9},{87,66},{74,58},{52,29},{10,19},{11,87},{56,71},{83,67},{73,31},{41,58},{26,39},{100,99},{96,51},{33,34},{43,23},{22,41},{89,28},{43,19},{87,56},{30,95},{54,93},{81,98},{84,26},{51,52},{21,16}};
        System.out.println(ob.maximumUnits(boxTypes1,270));

    }
}
