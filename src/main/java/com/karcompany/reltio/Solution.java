package com.karcompany.reltio;

/*
input = 8
output =
1 1 1 1 1 1 1 2
3 2 2 2 2 2 2 2
3 3 3 3 3 3 3 4
5 4 4 4 4 4 4 4
5 5 5 5 5 5 5 6
7 6 6 6 6 6 6 6
7 7 7 7 7 7 7 8


input = 3
output =
1 1 2
3 2 2
 */

public class Solution {

    public static void printMatrix(int n) {
        int k = 1;
        boolean isRight = true;

        for (int i = 1; i <n; i++) {
            if (isRight) {
                for (int j = 0; j <n-1; j++) {
                    System.out.print(k);
                    System.out.print(' ');
                }
                System.out.print((k+1));
                System.out.println();
            } else {
                System.out.print((k+1));
                for (int j = 0; j <n-1; j++) {
                    System.out.print(' ');
                    System.out.print(k);
                }
                System.out.println();
            }
            k++;
            isRight = !isRight;
        }
    }

    public static void main(String[] args) {
        printMatrix(3);
    }
}
