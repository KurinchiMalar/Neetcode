package MustDo500;

/*
https://leetcode.com/problems/container-with-most-water/description/

 */
class A9_ContainerWithMostWater {

    // we can use two pointers and keep adjusting the size of the container.
    // Be greedy and expect for bigger heights to that you can maximize the quantity of water.
    // Area = base * height
    /*
    'TC : O(n)
     SC : O(1)
     */
    public int maxArea(int[] height) {

        int maxArea = Integer.MIN_VALUE;
        int n = height.length;
        int head = 0;
        int tail = n-1;

        while(head  < tail){

            int base = tail - head;
            int h = Math.min(height[head],height[tail]); // the min of the two determines the max height of the container

            int curArea = base * h;

            maxArea = (curArea > maxArea) ? curArea : maxArea;
            if(height[head] < height[tail]){
                // Be greedy
                head++;
            }else{
                tail--;
            }
        }
        return maxArea;

    }


    public static void main(String[] args) {
        A9_ContainerWithMostWater ob = new A9_ContainerWithMostWater();
        System.out.println(ob.maxArea(new int[]{1, 2, 1}));
        System.out.println(ob.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));

    }
}
