import java.util.*;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        // Creating vertices
        Vertex<String> almaty = new Vertex<>("Almaty");
        Vertex<String> astana = new Vertex<>("Astana");
        Vertex<String> shymkent = new Vertex<>("Shymkent");
        Vertex<String> atyrau = new Vertex<>("Atyrau");
        Vertex<String> kostanay = new Vertex<>("Kostanay");
        Vertex<String> kyzylorda = new Vertex<>("Kyzylorda");

        // Adding vertices to the graph
        graph.addVertex(almaty);
        graph.addVertex(astana);
        graph.addVertex(shymkent);
        graph.addVertex(atyrau);
        graph.addVertex(kostanay);
        graph.addVertex(kyzylorda);

        // Adding edges with weights using existing vertex references
        fillWithWeights(graph, almaty, astana, shymkent, atyrau, kostanay, kyzylorda);

        // Initialize search algorithms (e.g., Dijkstra's)
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.search(almaty);

        // Output the path from Almaty to Kyzylorda
        outputPath(dijkstra, kyzylorda);
    }

    public static void fillWithWeights(WeightedGraph<String> graph, Vertex<String> almaty, Vertex<String> astana,
                                       Vertex<String> shymkent, Vertex<String> atyrau, Vertex<String> kostanay,
                                       Vertex<String> kyzylorda) {
        graph.addEdge(almaty, astana, 2.1);
        graph.addEdge(shymkent, atyrau, 7.8);
        graph.addEdge(atyrau, astana, 7.1);
        graph.addEdge(almaty, shymkent, 7.2);
        graph.addEdge(shymkent, astana, 3.9);
        graph.addEdge(astana, kostanay, 3.5);
        graph.addEdge(shymkent, kyzylorda, 5.4);
    }

    public static void outputPath(Search<String> search, Vertex<String> end) {
        List<Vertex<String>> path = search.getPath(end);
        if (path != null) {
            for (Vertex<String> vertex : path) {
                System.out.print(vertex + " -> ");
            }
            System.out.println("End");
        } else {
            System.out.println("No path found.");
        }
    }
}
