import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public abstract class Search<V> {
    protected V source;
    protected Set<V> marked;  // To keep track of visited nodes
    protected Map<V, V> edgeTo;  // To store the path tree

    public Search(V source) {
        this.source = source;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    // Method to determine if a vertex has been visited
    public boolean hasPathTo(V vertex) {
        return marked.contains(vertex);
    }

    // Method to return the path from the source to a given vertex
    public Iterable<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;

        Stack<V> path = new Stack<>();
        for (V x = vertex; x != source; x = edgeTo.get(x)) {
            path.push(x);
        }
        path.push(source);
        return path;
    }

    // Abstract method to start the search process
    protected abstract void executeSearch();

    // Getters for properties
    public V getSource() {
        return source;
    }

    public Set<V> getMarkedVertices() {
        return marked;
    }

    public Map<V, V> getEdgeToMap() {
        return edgeTo;
    }
}
