import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;
    private Map<Vertex<V>, Vertex<V>> prev = new HashMap<>();
    private Map<Vertex<V>, Double> dist = new HashMap<>();

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    public void search(Vertex<V> start) {
        dist.put(start, 0.0);
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparing(dist::get));
        pq.add(start);
        prev.put(start, null);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            for (Edge<Vertex<V>> edge : current.getEdges()) {
                Vertex<V> neighbor = edge.getDest();
                double newDist = dist.get(current) + edge.getWeight();
                if (newDist < dist.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    }

    public List<Vertex<V>> getPath(Vertex<V> end) {
        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
