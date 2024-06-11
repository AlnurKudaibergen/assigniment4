import java.util.*;

public class DepthFirstSearch<T> implements Search<T> {
    private final Map<T, T> edgeTo;
    private final Set<T> marked;
    private final T source;
    private final Comparator<Vertex<T>> comparator;

    public DepthFirstSearch(WeightedGraph<T> graph, T source, Comparator<Vertex<T>> comparator) {
        this.edgeTo = new HashMap<>();
        this.marked = new HashSet<>();
        this.source = source;
        this.comparator = comparator;

        // Start DFS from the source
        dfs(graph, source);
    }

    private void dfs(WeightedGraph<T> graph, T v) {
        Stack<T> stack = new Stack<>();
        stack.push(v);
        marked.add(v);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            Vertex<T> vertex = graph.getVertex(current);

            if (vertex != null) {
                // Get neighbors in a deterministic order
                List<Vertex<T>> neighbors = new ArrayList<>(vertex.getAdjacentVertices().keySet());
                neighbors.sort(comparator);

                for (Vertex<T> neighbor : neighbors) {
                    T neighborData = neighbor.getData();
                    if (!marked.contains(neighborData)) {
                        marked.add(neighborData);
                        edgeTo.put(neighborData, current);
                        stack.push(neighborData);
                    }
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
