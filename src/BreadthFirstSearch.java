import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private Map<T, T> edgeTo;
    private Set<T> marked;

    public BreadthFirstSearch(WeightedGraph<T> graph, T source) {
        edgeTo = new HashMap<>();
        marked = new HashSet<>();
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, T source) {
        Queue<T> queue = new LinkedList<>();
        marked.add(source);
        queue.offer(source);

        while (!queue.isEmpty()) {
            T v = queue.poll();
            Vertex<T> current = graph.getVertex(v);
            for (Vertex<T> w : current.getAdjacentVertices().keySet()) {
                if (!marked.contains(w.getData())) {
                    edgeTo.put(w.getData(), v);
                    marked.add(w.getData());
                    queue.offer(w.getData());
                }
            }
        }
    }

    @Override
    public Iterable<T> pathTo(T destination) {
        if (!marked.contains(destination)) {
            return null;
        }

        LinkedList<T> path = new LinkedList<>();
        for (T x = destination; x != null; x = edgeTo.get(x)) {
            path.push(x);
        }
        return path;
    }
}
