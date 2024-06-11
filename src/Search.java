import java.util.*;

public interface Search<V> {
    void search(Vertex<V> start);
    List<Vertex<V>> getPath(Vertex<V> end);
}
