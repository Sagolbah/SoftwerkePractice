package ru.ifmo.swpractice.stage5.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public final class RSSParser {
    public static List<String> getTitles(final String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Chrome").get();
        final Elements titles = doc.select("item > title");
        return titles.stream().map(Element::text).collect(Collectors.toList());
    }
}
