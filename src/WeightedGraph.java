import java.util.*;

public class WeightedGraph<T> {
    public Map<T, Vertex<T>> vertices;
    private boolean isDirected;

    public WeightedGraph(boolean isDirected) {
        this.isDirected = isDirected;
        vertices = new HashMap<>();
    }

    public void addVertex(T data) {
        vertices.put(data, new Vertex<>(data));
    }

    public void addEdge(T source, T destination, double weight) {
        Vertex<T> s = vertices.get(source);
        Vertex<T> d = vertices.get(destination);
        if (s == null || d == null) {
            throw new IllegalArgumentException("Vertex not found!");
        }
        s.addAdjacentVertex(d, weight);
        if (!isDirected) {
            d.addAdjacentVertex(s, weight);
        }
    }

    public Vertex<T> getVertex(T data) {
        return vertices.get(data);
    }
}
