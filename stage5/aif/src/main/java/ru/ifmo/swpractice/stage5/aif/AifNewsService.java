package ru.ifmo.swpractice.stage5.aif;

import org.osgi.service.component.annotations.Component;
import ru.ifmo.swpractice.stage5.common.NewsService;
import ru.ifmo.swpractice.stage5.common.RSSParser;
import ru.ifmo.swpractice.stage5.common.TextUtilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AifNewsService implements NewsService {

    @Override
    public Map<String, Integer> getWordsRanking() {
        try {
            final List<String> titles = RSSParser.getTitles("https://aif.ru/rss/news.php");
            Map<String, Integer> result = new HashMap<>();
            for (String title : titles) {
                TextUtilities.tokenizeAndAddWords(title, result);
            }
            return result;
        } catch (IOException e) {
            System.err.println("Connection to AiF has failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getId() {
        return "aif";
    }

}
