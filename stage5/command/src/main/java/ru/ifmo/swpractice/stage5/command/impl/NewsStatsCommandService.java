package ru.ifmo.swpractice.stage5.command.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import ru.ifmo.swpractice.stage5.command.NewsStatsCommand;
import ru.ifmo.swpractice.stage5.common.NewsService;

import java.util.*;
import java.util.stream.Collectors;

@Component(property = {
        "osgi.command.scope=news",
        "osgi.command.function=stats"
})
public class NewsStatsCommandService implements NewsStatsCommand {

    private static final Map<String, NewsService> services = new HashMap<>();

    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE,
            service = NewsService.class,
            bind = "bindNewsService",
            unbind = "unbindNewsService"
    )
    private void bindNewsService(NewsService service) {
        services.put(service.getId(), service);
    }

    private void unbindNewsService(NewsService service) {
        services.remove(service.getId());
    }


    private static final Comparator<WordCountPair> FREQUENCY_COMPARATOR = Comparator
            .comparingInt(WordCountPair::getCount)
            .thenComparing(WordCountPair::getWord)
            .reversed();
    private static final Set<String> IGNORED_WORDS = Set.of("в", "на", "и", "о", "с", "из",
            "от", "за", "по", "при", "к", "не", "об", "у", "до", "для");
    private static final int RESULT_ENTRIES = 10;

    @Override
    public void stats() {
        System.out.println("Using all news services by default");
        countStats(services.values());
    }

    @Override
    public void stats(String[] args) {
        if (args == null) {
            System.err.println("Args must not be null");
            return;
        }
        List<NewsService> sources = new ArrayList<>();
        for (String arg : args) {
            if (arg == null) {
                System.err.println("All arguments must not be null");
                return;
            }
            NewsService service = services.get(arg);
            if (service == null) {
                System.err.println("Service " + arg + " not found, skipping");
                continue;
            }
            sources.add(service);
        }
        countStats(sources);
    }

    private void countStats(Collection<NewsService> sources) {
        if (sources.isEmpty()) {
            System.out.println("No services found. Aborting.");
        }
        Map<String, Integer> lines = new HashMap<>();
        for (NewsService service : sources) {
            System.out.println("Loading " + service.getId());
            Map<String, Integer> rank = service.getWordsRanking();
            if (rank == null) {
                System.out.println("Connection to " + service.getId() + " failed");
                continue;
            }
            for (Map.Entry<String, Integer> entry : rank.entrySet()) {
                lines.put(entry.getKey(), lines.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }
        List<WordCountPair> stats = lines.entrySet().stream().map(WordCountPair::new).collect(Collectors.toList());
        stats = stats.stream().sorted(FREQUENCY_COMPARATOR)
                .filter(x -> !IGNORED_WORDS.contains(x.word)).limit(RESULT_ENTRIES).collect(Collectors.toList());
        for (WordCountPair pair : stats) {
            System.out.println(pair.getWord() + ": " + pair.getCount());
        }
    }

    private static class WordCountPair {
        private final String word;
        private final int count;

        public WordCountPair(Map.Entry<String, Integer> entry) {
            this.word = entry.getKey();
            this.count = entry.getValue();
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }
    }


}
