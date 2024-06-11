import java.util.ArrayList;
import java.util.*;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<Vertex<V>>>> graph = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        graph.putIfAbsent(vertex, new ArrayList<>());  // Ensure each vertex has an empty list
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        if (!graph.containsKey(source) || !graph.containsKey(dest)) {
            throw new IllegalArgumentException("Both vertices must be added before adding an edge");
        }
        graph.get(source).add(new Edge<>(source, dest, weight));  // Now safely adding edges
    }

    public Map<Vertex<V>, List<Edge<Vertex<V>>>> getGraph() {
        return graph;
    }
}
