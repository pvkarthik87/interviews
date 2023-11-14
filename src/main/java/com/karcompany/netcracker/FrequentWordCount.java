package com.karcompany.netcracker;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FrequentWordCount {

    public static void main(String[] args) {
        String text = "Xyz is employee of ABC company, XYZ is from blore, XYZ! is good in java.";
        List<Word> result = findFrequentWords(text.toLowerCase().split("[,!. ]"));
        System.out.println(result);
    }

    static class Word {
        String text;
        Integer count;
        public Word(String word, Integer count) {
            this.text = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "[" + text + "," + count + "]";
        }
    }

    private static List<Word> findFrequentWords(String[] words) {
        Map<String, Integer> wordMap = new LinkedHashMap<>();
        for (String word : words) {
            wordMap.put(word, (wordMap.getOrDefault(word, 0)) + 1);
        }
        List<Word> result = wordMap.entrySet().stream().sorted(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() < o2.getValue())
                    return 1;
                return 0;
            }
        }).toList().stream().map((item) -> new Word(item.getKey(), item.getValue())).toList();
        int firstFrequency = result.stream().findFirst().get().count;

        return result.stream().filter(it -> it.count == firstFrequency).toList();

    }
}
