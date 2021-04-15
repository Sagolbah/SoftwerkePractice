package ru.ifmo.swpractice.stage5.rbc;

import org.osgi.service.component.annotations.Component;
import ru.ifmo.swpractice.stage5.common.NewsService;
import ru.ifmo.swpractice.stage5.common.RSSParser;
import ru.ifmo.swpractice.stage5.common.TextUtilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RBCNewsService implements NewsService {

    @Override
    public Map<String, Integer> getWordsRanking() {
        try {
            final List<String> titles = RSSParser.getTitles("http://static.feed.rbc.ru/rbc/logical/footer/news.rss");
            Map<String, Integer> result = new HashMap<>();
            for (String title : titles) {
                TextUtilities.tokenizeAndAddWords(title, result);
            }
            return result;
        } catch (IOException e) {
            System.err.println("Connection to RBC has failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getId() {
        return "rbc";
    }

}
