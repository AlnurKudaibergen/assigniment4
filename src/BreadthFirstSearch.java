import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;
    private Map<Vertex<V>, Vertex<V>> prev = new HashMap<>();

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    public void search(Vertex<V> start) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(start);
        prev.put(start, null);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Edge<Vertex<V>> edge : current.getEdges()) {
                if (!prev.containsKey(edge.getDest())) {
                    queue.add(edge.getDest());
                    prev.put(edge.getDest(), current);
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
