package smartroute;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryScraper {
    // Example: Scrapes words from an online simple dictionary page
    // You can replace the URL with any online dictionary page with a list of words.
    private static final String DICTIONARY_URL = "https://www.mit.edu/~ecprice/wordlist.10000";

    public static List<String> fetchWords() throws IOException {
        List<String> words = new ArrayList<>();

        Document doc = Jsoup.connect(DICTIONARY_URL).get();
        // This URL is a plain text list of words, so use body text split by newline
        String body = doc.body().text();
        for (String word : body.split(" ")) {
            if (!word.trim().isEmpty()) {
                words.add(word.trim());
            }
        }

        return words;
    }
}
