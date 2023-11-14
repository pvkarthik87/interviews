package com.karcompany.guidewire;


/*
Find country with highest investment cost
when project codes and investments are given
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


class Solution {
    public String solution(String[] A, int[] B) {
        // Implement your solution here
        if ((A == null || A.length == 0) || (B == null || B.length == 0) || (A.length != B.length))
            return "";

        Map<String, Integer> countryMap = new HashMap<>();
        int numProjects = A.length;

        for (int i = 0; i < numProjects; i++) {
            String country = A[i].substring(0, 2);
            int projectInvestment = B[i];

            if (countryMap.containsKey(country)) {
                countryMap.put(country, (countryMap.get(country) + projectInvestment));
            } else {
                countryMap.put(country, projectInvestment);
            }
        }

        Optional<Map.Entry<String, Integer>> result = countryMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue));
        if (result.isPresent()) {
            return result.get().getKey();
        }

        return "";
    }
}
