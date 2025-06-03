package smartroute;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert word into trie
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    // Return autocomplete suggestions for prefix
    public List<String> autocomplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode current = root;

        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return results; // empty list, prefix not found
            }
            current = current.children.get(ch);
        }

        // DFS from current node to find all words
        dfs(current, new StringBuilder(prefix), results);
        return results;
    }

    private void dfs(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix.toString());
        }
        for (char ch : node.children.keySet()) {
            prefix.append(ch);
            dfs(node.children.get(ch), prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
