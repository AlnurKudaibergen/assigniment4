import java.util.*;

public class WeightedGraph<Vertex> {
    private final boolean undirected;
    private final Map<Vertex, List<Edge<Vertex>>> adjacencyMap = new HashMap<>();

    public WeightedGraph() {
        this(true); // Default to undirected graph
    }

    public WeightedGraph(boolean isUndirected) {
        this.undirected = isUndirected;
    }

    public void addVertex(Vertex vertex) {
        if (!hasVertex(vertex)) {
            LinkedList local = new LinkedList<>();
            adjacencyMap.put(vertex, local);
        }
    }

    public void addEdge(Vertex source, Vertex destination, double weight) {
        if (!hasVertex(source)) {
            addVertex(source);
        }
        if (!hasVertex(destination)) {
            addVertex(destination);
        }

        // Avoid adding parallel edges and self-loops
        if (hasEdge(source, destination) || source.equals(destination)) {
            return;
        }

        adjacencyMap.get(source).add(new Edge<>(source, destination, weight));
        if (undirected) {
            adjacencyMap.get(destination).add(new Edge<>(destination, source, weight));
        }
    }

    public int getVerticesCount() {
        return adjacencyMap.size();
    }

    public int getEdgesCount() {
        int count = adjacencyMap.values().stream()
                .mapToInt(List::size)
                .sum();
        return undirected ? count / 2 : count;
    }

    public boolean hasVertex(Vertex vertex) {
        return adjacencyMap.containsKey(vertex);
    }

    public boolean hasEdge(Vertex source, Vertex destination) {
        if (!hasVertex(source)) return false;
        return adjacencyMap.get(source).contains(new Edge<>(source, destination));
    }

    public List<Vertex> getAdjacencyList(Vertex vertex) {
        if (!hasVertex(vertex)) return null;

        List<Vertex> adjacentVertices = new LinkedList<>();
        for (Edge<Vertex> edge : adjacencyMap.get(vertex)) {
            adjacentVertices.add(edge.getDestination());
        }
        return adjacentVertices;
    }

    public Iterable<Edge<Vertex>> getEdges(Vertex vertex) {
        if (!hasVertex(vertex)) return null;
        return adjacencyMap.get(vertex);
    }
}

