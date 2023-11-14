package com.karcompany.guidewire;

public class FindSecondHighestNumberIndex {

    public static int f(int[] A) {
        if (A == null || A.length < 2)
            return -1;

        int maxElemIndex;
        int preMaxElemIndex;

        if (A[0] < A[1]) {
            maxElemIndex = 1;
            preMaxElemIndex = 0;
        } else {
            maxElemIndex = 0;
            preMaxElemIndex = 1;
        }

        int startIndex = 2;
        if (A[maxElemIndex] == A[preMaxElemIndex]) {
            for (int i = 2; i < A.length; i++) {
                if (A[i] != A[maxElemIndex]) {
                    if (A[i] > A[maxElemIndex]) {
                        preMaxElemIndex = maxElemIndex;
                        maxElemIndex = i;
                    } else {
                        preMaxElemIndex = i;
                    }
                    startIndex = i;
                    break;
                }
            }
            startIndex++;
        }

        if (A[maxElemIndex] == A[preMaxElemIndex]) {
            preMaxElemIndex = -1;
        }

        for (int i = startIndex; i < A.length; i++) {
            if (A[i] > A[maxElemIndex]) {
                preMaxElemIndex = maxElemIndex;
                maxElemIndex = i;
            } else {
                if (A[i] > A[preMaxElemIndex] && A[i] < A[maxElemIndex]) {
                    preMaxElemIndex = i;
                }
            }
        }
        return preMaxElemIndex;
    }

    public static void main(String[] args) {
        System.out.println(f(null) == -1);
        System.out.println(f(new int[]{}) == -1);
        System.out.println(f(new int[]{0}) == -1);
        System.out.println(f(new int[]{1, 2}) == 0);
        System.out.println(f(new int[]{2, 1}) == 1);
        System.out.println(f(new int[]{3, 2}) == 1);
        System.out.println(f(new int[]{1, 2, 3}) == 1);
        System.out.println(f(new int[]{3, 2, 1}) == 1);
        System.out.println(f(new int[]{2, 1, 3}) == 0);
        System.out.println(f(new int[]{2, 3, 1, 4}) == 1);
        System.out.println(f(new int[]{2, 2, 1}) == 2);
        System.out.println(f(new int[]{3, 2, 2}) == 1);
        System.out.println(f(new int[]{2, 2, 1, 3}) == 0);
        System.out.println(f(new int[]{3, 2, 2, 3}) == 1);
        System.out.println(f(new int[]{2, 2, 2}) == -1);
    }
}
