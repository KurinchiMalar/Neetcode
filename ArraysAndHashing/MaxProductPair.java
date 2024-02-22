package ArraysAndHashing;

import java.util.Arrays;

/*
Given an array with both +ive and -ive integers, return a pair with the highest product.

Examples :

Input: arr[] = {1, 4, 3, 6, 7, 0}
Output: {6,7}

Input: arr[] = {-1, -3, -4, 2, 0, -5}
Output: {-4,-5}

https://www.geeksforgeeks.org/return-a-pair-with-maximum-product-in-array-of-integers/

Naive :

    nested loop check every pair with maxproduct

Sort:
    Sort entire array
    all elem positive maxProduct = last 2 pairs
    all elem negative maxProduct = first 2 pairs

 Linear :
  1) max positive val
  2) second max positive val
  3) max negative val
  4) second max negative val
  Compare the products.
 */
 class MaxProductPair {
    // Function to find maximum product pair
    // in arr[0..n-1]
    static int[] maxProduct(int arr[], int n)
    {
        int[] result = new int[2];
        if (n < 2)
        {
            System.out.println("No pairs exists");
            return null;
        }

        if (n == 2)
        {
            System.out.println(arr[0] + " " + arr[1]);
            result[0] = arr[0];
            result[1] = arr[1];
            return result;
        }

        // Initialize maximum and second maximum
        int posa = Integer.MIN_VALUE,
                posb = Integer.MIN_VALUE;

        // Initialize minimum and second minimum
        int nega = Integer.MIN_VALUE,
                negb = Integer.MIN_VALUE;

        // Traverse given array
        for (int i = 0; i < n; i++)
        {
            // Update maximum and second maximum
            // if needed
            if(arr[i] > 0){
                if (arr[i] > posa)
                {
                    posb = posa;
                    posa = arr[i];
                }
                else if (arr[i] > posb)
                    posb = arr[i];
            }

            if(arr[i] < 0){
                // Update minimum and second minimum
                // if needed
                if (Math.abs(arr[i]) >
                        Math.abs(nega))
                {
                    negb = nega;
                    nega = arr[i];
                }
                else if(Math.abs(arr[i])
                        > Math.abs(negb))
                    negb = arr[i];

            }

        }

        if (nega * negb > posa * posb){
            result[0] = nega;
            result[1] =negb;
            System.out.println("Max product pair is {" + nega + ", " + negb + "}");
        }

        else{
            result[0] = posa;
            result[1] =posb;
            System.out.println("Max product pair is {" + posa + ", " + posb + "}");
        }
    return result;
    }

    /* Driver program to test above function */
    public static void main(String[] args)
    {
        int arr[] =  {-1, -3, -4, 2, 0, -5} ;
        int n = arr.length;
        System.out.println(Arrays.toString(maxProduct(arr, n)));

        System.out.println(Arrays.toString(maxProduct(new int[]{1, 4, 3, 6, 7, 0}, 6)));


    }
}
