package smartroute;

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add an edge (undirected graph)
    public void addEdge(String src, String dest, int weight) {
        adjacencyList.putIfAbsent(src, new ArrayList<>());
        adjacencyList.get(src).add(new Edge(src, dest, weight));

        adjacencyList.putIfAbsent(dest, new ArrayList<>());
        adjacencyList.get(dest).add(new Edge(dest, src, weight));
    }

    // Dijkstra's algorithm to find shortest path from start to end
    public List<String> dijkstra(String start, String end) {
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(nd -> nd.distance));
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        // Initialize distances
        for (String node : adjacencyList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.offer(new NodeDistance(start, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            String currentNode = current.node;

            if (currentNode.equals(end)) break;

            if (current.distance > distances.get(currentNode)) continue;

            for (Edge edge : adjacencyList.getOrDefault(currentNode, Collections.emptyList())) {
                String neighbor = edge.getDestination();
                int newDist = distances.get(currentNode) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, currentNode);
                    pq.offer(new NodeDistance(neighbor, newDist));
                }
            }
        }

        // Reconstruct the path
        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);

        // If no path found, path will only contain 'end' without start, handle this case
        if (path.size() == 1 && !path.get(0).equals(start)) {
            return Collections.emptyList();
        }

        return path;
    }

    // Helper class for priority queue in Dijkstra
    private static class NodeDistance {
        String node;
        int distance;

        NodeDistance(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
