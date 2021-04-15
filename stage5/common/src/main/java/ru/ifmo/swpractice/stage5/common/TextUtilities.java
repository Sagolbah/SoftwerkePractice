package ru.ifmo.swpractice.stage5.common;

import java.util.Map;

public final class TextUtilities {
    public static void tokenizeAndAddWords(String s, Map<String, Integer> collector){
        String[] words = s.replaceAll("[^\\p{L}]", " ").toLowerCase().split("\\s+");
        for (String word : words) {
            if (word.equals("")) continue;
            collector.put(word, collector.getOrDefault(word, 0) + 1);
        }
    }
}
