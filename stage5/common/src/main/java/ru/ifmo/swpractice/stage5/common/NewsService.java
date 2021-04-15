package ru.ifmo.swpractice.stage5.common;

import java.util.Map;

public interface NewsService {
    Map<String, Integer> getWordsRanking();

    String getId();
}
