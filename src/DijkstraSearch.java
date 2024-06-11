import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private final Map<T, T> edgeTo;
    private final Map<T, Double> distTo;
    private final PriorityQueue<Vertex<T>> pq;

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.getOrDefault(v.getData(), Double.POSITIVE_INFINITY)));

        // Initialize distances to infinity
        for (T v : graph.vertices.keySet()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        // Set distance to source to 0
        distTo.put(source, 0.0);

        // Add the source vertex to the priority queue
        Vertex<T> sourceVertex = graph.getVertex(source);
        if (sourceVertex != null) {
            pq.offer(sourceVertex);
        } else {
            System.err.println("Source vertex is null: " + source);
        }

        // Process the priority queue
        while (!pq.isEmpty()) {
            Vertex<T> v = pq.poll();
            if (v == null) continue; // Safeguard against null vertices

            for (Map.Entry<Vertex<T>, Double> entry : v.getAdjacentVertices().entrySet()) {
                relax(v, entry.getKey(), entry.getValue());
            }
        }
    }

    private void relax(Vertex<T> v, Vertex<T> w, double weight) {
        T vData = v.getData();
        T wData = w.getData();
        double newDist = distTo.get(vData) + weight;

        if (distTo.get(wData) > newDist) {
            distTo.put(wData, newDist);
            edgeTo.put(wData, vData);

            // Update priority queue: remove and re-add the vertex to update its position
            pq.remove(w);
            pq.offer(w);

            System.out.println("Relaxing edge from " + vData + " to " + wData + " with weight " + weight);
            System.out.println("Updated distance to " + wData + ": " + newDist);
        }
    }

    @Override
    public Iterable<T> pathTo(T destination) {
        if (!distTo.containsKey(destination) || distTo.get(destination) == Double.POSITIVE_INFINITY) {
            return null; // No path to destination
        }

        LinkedList<T> path = new LinkedList<>();
        for (T x = destination; x != null; x = edgeTo.get(x)) {
            path.push(x);
        }
        return path;
    }
}
