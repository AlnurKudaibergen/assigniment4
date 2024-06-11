import java.util.ArrayList;
import java.util.*;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<Vertex<V>>>> adjList = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> src, Vertex<V> dest, double weight) {
        adjList.get(src).add(new Edge<>(src, dest, weight));
    }
}
