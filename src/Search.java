import java.util.*;

public interface Search<T> {
    Iterable<T> pathTo(T destination);
}
