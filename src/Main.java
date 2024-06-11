import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Weighted graph for Dijkstra's Algorithm
        WeightedGraph<String> weightedGraph = new WeightedGraph<>(false);
        fillWithWeights(weightedGraph);

        System.out.println("Dijkstra:");
        Search<String> dijkstra = new DijkstraSearch<>(weightedGraph, "Almaty");
        outputPath(dijkstra, "Kyzylorda");

        System.out.println("--------------------------------");

        //  graph for BFS and DFS
        WeightedGraph<String> graph = new WeightedGraph<>(false);
        fillWithWeights(graph);

        System.out.println("DFS:");
        Comparator<Vertex<String>> vertexComparator = Comparator.comparing(Vertex::getData);
        Search<String> dfs = new DepthFirstSearch<>(weightedGraph, "Almaty", vertexComparator);
        outputPath(dfs, "Kyzylorda");

        System.out.println("--------------------------------");

        System.out.println("BFS:");
        Search<String> bfs = new BreadthFirstSearch<>(graph, "Almaty");
        outputPath(bfs, "Kyzylorda");
    }

    public static void fillWithoutWeights(WeightedGraph<String> graph) {
        graph.addVertex("Almaty");
        graph.addVertex("Astana");
        graph.addVertex("Shymkent");
        graph.addVertex("Atyrau");
        graph.addVertex("Kostanay");
        graph.addVertex("Kyzylorda");

        graph.addEdge("Almaty", "Astana", 0); // 16 - 19
        graph.addEdge("Shymkent", "Atyrau", 0);
        graph.addEdge("Atyrau", "Astana", 0);
        graph.addEdge("Almaty", "Shymkent", 0);
        graph.addEdge("Shymkent", "Astana", 0);
        graph.addEdge("Astana", "Kostanay", 0);
        graph.addEdge("Shymkent", "Kyzylorda", 0);
    }

    public static void fillWithWeights(WeightedGraph<String> graph) {
        graph.addVertex("Almaty");
        graph.addVertex("Astana");
        graph.addVertex("Shymkent");
        graph.addVertex("Atyrau");
        graph.addVertex("Kostanay");
        graph.addVertex("Kyzylorda");

        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    public static void outputPath(Search<String> search, String destination) {
        System.out.print("Path to " + destination + ": ");
        Iterable<String> path = search.pathTo(destination);
        if (path == null) {
            System.out.println("No path available.");
        } else {
            for (String v : path) {
                System.out.print(v + " -> ");
            }
            System.out.println("END");
        }
    }
}
