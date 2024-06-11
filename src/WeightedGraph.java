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

    // This method ensures vertices are added automatically if they don't exist
    private void ensureVertex(Vertex vertex) {
        adjacencyMap.putIfAbsent(vertex, new LinkedList<>());
    }

    public void addEdge(Vertex source, Vertex destination, double weight) {
        ensureVertex(source);
        ensureVertex(destination);

        // Avoid adding parallel edges and self-loops
        if (hasEdge(source, destination) || source.equals(destination)) {
            return;
        }

        adjacencyMap.get(source).add(new Edge<>(source, destination, weight));
        if (undirected && !source.equals(destination)) {
            adjacencyMap.get(destination).add(new Edge<>(destination, source, weight));
        }
    }

    public boolean hasVertex(Vertex vertex) {
        return adjacencyMap.containsKey(vertex);
    }

    public boolean hasEdge(Vertex source, Vertex destination) {
        if (!hasVertex(source)) return false;
        List<Edge<Vertex>> edges = adjacencyMap.get(source);
        return edges.stream().anyMatch(edge -> edge.getDest().equals(destination));
    }

    public List<Vertex> getAdjacencyList(Vertex vertex) {
        if (!hasVertex(vertex)) return null;
        List<Vertex> adjacentVertices = new LinkedList<>();
        for (Edge<Vertex> edge : adjacencyMap.get(vertex)) {
            adjacentVertices.add(edge.getDest());
        }
        return adjacentVertices;
    }

    public Iterable<Edge<Vertex>> getEdges(Vertex vertex) {
        if (!hasVertex(vertex)) return null;
        return new ArrayList<>(adjacencyMap.get(vertex));
    }

    public Set<Vertex> getVertices() {
        return adjacencyMap.keySet();
    }
}
