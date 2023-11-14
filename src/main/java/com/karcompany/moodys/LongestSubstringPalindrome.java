package com.karcompany.moodys;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstringPalindrome {
    static String findLongestPalindrome(String src) {
        List<String> subStrings = new ArrayList<>();
        for (int i = 0; i < src.length(); i++) {
            subStrings.add(src.substring(i));
        }

        for (String candidate : subStrings) {
            if (isPalindrome(candidate))
                return candidate;
        }
        return null;
    }

    private static boolean isPalindrome(String candidate) {
        int i = 0, j = candidate.length() - 1;
        while(i<j) {
            if (candidate.charAt(i) == candidate.charAt(j)) {
                i ++;
                j--;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindrome("bananas"));
    }
}
