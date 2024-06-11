import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private Map<T, T> edgeTo;
    private Map<T, Double> distTo;
    private PriorityQueue<Vertex<T>> pq;

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));

        for (T v : graph.vertices.keySet()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);
        Vertex<T> sourceVertex = graph.getVertex(source);
        if (sourceVertex != null) {
            pq.offer(sourceVertex);
        } else {
            System.err.println("Source vertex is null: " + source);
        }

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
        if (distTo.get(wData) == null) {
            System.err.println("distTo missing entry for: " + wData);
            return;
        }
        if (distTo.get(wData) > distTo.get(vData) + weight) {
            distTo.put(wData, distTo.get(vData) + weight);
            edgeTo.put(wData, vData);
            pq.remove(w); // Remove old instance of w with the higher distance
            pq.offer(w); // Add new instance of w with the updated distance
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
