import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private PriorityQueue<V> priorityQueue;
    private Map<V, Double> shortestDistances;
    private WeightedGraph<V> graph;
    private Comparator<V> vertexDistanceComparator;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        this.graph = graph;
        this.shortestDistances = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>(Comparator.comparing(this::getShortestDistance));
        executeSearch();
    }

    @Override
    protected void executeSearch() {
        shortestDistances.put(source, 0.0);
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            V current = priorityQueue.poll();
            for (V neighbor : graph.getAdjacencyList(current)) {
                double edgeWeight = getEdgeWeight(current, neighbor);
                double newDistance = getShortestDistance(current) + edgeWeight;
                if (newDistance < getShortestDistance(neighbor)) {
                    shortestDistances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, current);
                    if (!priorityQueue.contains(neighbor)) {
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }
    }

    private double getEdgeWeight(V from, V to) {
        Iterable<Edge<V>> edges = graph.getEdges(from);
        for (Edge<V> edge : edges) {
            if (edge.getDest().equals(to)) {
                return edge.getWeight();
            }
        }
        throw new NoSuchElementException("Edge not found between " + from + " and " + to);
    }

    public double getShortestDistance(V vertex) {
        return shortestDistances.getOrDefault(vertex, Double.MAX_VALUE);
    }
}
