package com.karcompany.microsoft;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringManipulation {

    public String solution(String S, String T) {
        int srcLength = S.length();
        int targetLength = T.length();
        char[] srcArray = S.toCharArray();
        char[] targetArray = T.toCharArray();

        if (srcLength == targetLength) {
            if (S.equals(T))
                return "NOTHING";
        }
        Map<Character, Integer> charMap = new HashMap<>();
        Map<Character, Set<Integer>> srcCharIndexMap = new HashMap<>();
        for (int i=0; i<srcArray.length; i++) {
            char c = srcArray[i];
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
            Set<Integer> indexSet = srcCharIndexMap.getOrDefault(c, new HashSet<>());
            indexSet.add(i);
            srcCharIndexMap.put(c, indexSet);
        }

        Map<Character, Set<Integer>> targetCharIndexMap = new HashMap<>();
        for (int i=0; i<targetArray.length; i++) {
            char c = targetArray[i];
            int currentCount = charMap.getOrDefault(c, 0);
            if (currentCount == 1) {
                charMap.remove(c);
            } else {
                charMap.put(c, currentCount - 1);
            }
            Set<Integer> indexSet = targetCharIndexMap.getOrDefault(c, new HashSet<>());
            indexSet.add(i);
            targetCharIndexMap.put(c, indexSet);
        }

        if (srcLength + 1 == targetLength) {
            if (charMap.size() == 1) {
                return "INSERT " + charMap.entrySet().stream().findFirst().get().getKey();
            }
        }

        if (srcLength == targetLength) {
            if (charMap.size() == 2) {
                Set<Map.Entry<Character, Integer>> entries = charMap.entrySet();
                return "CHANGE " +
                        entries.stream().filter(entry -> entry.getValue() == 1).findFirst().get().getKey() +
                        " " +
                        entries.stream().filter(entry -> entry.getValue() == -1).findFirst().get().getKey();
            }

            if (charMap.size() == 0) {
                int mismatchCount = 0;
                for (int i=0; i<srcArray.length; i++) {
                    if (srcArray[i] == targetArray[i]) {
                        srcArray[i] = '?';
                        targetArray[i] = '?';
                    } else {
                        mismatchCount++;
                    }
                }

                if (mismatchCount == 2) {
                    char srcChar = '?';
                    for (char value : srcArray) {
                        if (value != '?') {
                            srcChar = value;
                            break;
                        }
                    }
                    char targetChar = '?';
                    for (char c : targetArray) {
                        if (c != '?') {
                            targetChar = c;
                            break;
                        }
                    }

                    return "SWAP "+ srcChar + " " + targetChar;
                }
            }
        }

        return "IMPOSSIBLE";
    }


    public static void main(String[] args) {
        StringManipulation s = new StringManipulation();

        System.out.println(s.solution("", ""));
        System.out.println(s.solution("again", "again"));

        System.out.println(s.solution("a", ""));
        System.out.println(s.solution("o", "odd"));

        System.out.println(s.solution("", "a"));
        System.out.println(s.solution("gain", "again"));
        System.out.println(s.solution("gain", "gainc"));
        System.out.println(s.solution("aaa", "aaaa"));

        System.out.println(s.solution("test", "tent"));
        System.out.println(s.solution("test", "tesk"));

        System.out.println(s.solution("late", "tela"));
        System.out.println(s.solution("late", "tale"));
        System.out.println(s.solution("late", "alte"));
    }
}
