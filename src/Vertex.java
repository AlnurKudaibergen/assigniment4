import java.util.*;

public class Vertex<V> {
    private V data;
    private List<Edge<Vertex<V>>> edges;

    public Vertex(V data) {
        this.data = data;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Vertex<V> destination, double weight) {
        Edge<Vertex<V>> newEdge = new Edge<>(this, destination, weight);
        edges.add(newEdge);
    }

    // Getters
    public V getData() {
        return data;
    }

    public List<Edge<Vertex<V>>> getEdges() {
        return edges;
    }

    // Overriding toString to return Vertex data directly
    @Override
    public String toString() {
        return data.toString();
    }
}
