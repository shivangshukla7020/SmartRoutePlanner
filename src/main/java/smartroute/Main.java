package smartroute;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Fetch dictionary words from online source
        System.out.println("Fetching dictionary words...");
        List<String> words = DictionaryScraper.fetchWords();
        System.out.println("Fetched " + words.size() + " words.");

        // Step 2: Build Trie from words for autocomplete
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word.toLowerCase());
        }

        System.out.println("Trie built. Type a prefix to get autocomplete suggestions (type 'exit' to quit):");
        while (true) {
            System.out.print("Enter prefix: ");
            String prefix = scanner.nextLine().toLowerCase();
            if (prefix.equals("exit")) break;

            List<String> suggestions = trie.autocomplete(prefix);
            if (suggestions.isEmpty()) {
                System.out.println("No suggestions found.");
            } else {
                System.out.println("Suggestions: " + suggestions);
            }
        }

        scanner.close();
    }
}
