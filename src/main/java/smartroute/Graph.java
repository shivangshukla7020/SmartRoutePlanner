package smartroute;

import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String dest, int weight) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(dest, new ArrayList<>());
        adjacencyList.get(source).add(new Edge(dest, weight));
        // For undirected graph, also add reverse edge:
        adjacencyList.get(dest).add(new Edge(source, weight));
    }

    public Map<String, Integer> dijkstra(String start) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            String u = current.destination;

            for (Edge neighbor : adjacencyList.getOrDefault(u, Collections.emptyList())) {
                int newDist = distances.get(u) + neighbor.weight;
                if (newDist < distances.get(neighbor.destination)) {
                    distances.put(neighbor.destination, newDist);
                    pq.offer(new Edge(neighbor.destination, newDist));
                }
            }
        }

        return distances;
    }
}
