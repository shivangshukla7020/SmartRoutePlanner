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
        System.out.println("Trie built.");

        // Step 3: Build sample graph with some nodes and weighted edges
        Graph graph = new Graph();
        graph.addEdge("a", "b", 5);
        graph.addEdge("a", "c", 10);
        graph.addEdge("b", "d", 3);
        graph.addEdge("c", "d", 1);
        graph.addEdge("d", "e", 2);

        System.out.println("Sample graph built with nodes: a, b, c, d, e.");

        // User interaction loop
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1: Autocomplete word prefix");
            System.out.println("2: Find shortest path between nodes");
            System.out.println("3: Exit");
            System.out.print("Enter choice (1/2/3): ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("3")) {
                break;
            }

            switch (choice) {
                case "1":
                    System.out.print("Enter prefix: ");
                    String prefix = scanner.nextLine().toLowerCase();
                    List<String> suggestions = trie.autocomplete(prefix);
                    if (suggestions.isEmpty()) {
                        System.out.println("No suggestions found.");
                    } else {
                        System.out.println("Suggestions: " + suggestions);
                    }
                    break;

                case "2":
                    System.out.print("Enter start node: ");
                    String start = scanner.nextLine().toLowerCase();
                    System.out.print("Enter end node: ");
                    String end = scanner.nextLine().toLowerCase();

                    List<String> path = graph.dijkstra(start, end);
                    if (path.isEmpty()) {
                        System.out.println("No path found between " + start + " and " + end + ".");
                    } else {
                        System.out.println("Shortest path: " + path);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
        System.out.println("Program exited.");
    }
}
