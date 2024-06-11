

public class Main {

    private static void printDijkstraPaths(DijkstraSearch<String> search, String destination) {
        System.out.println("Dijkstra:");
        Iterable<String> path = search.pathTo(destination);
        if (path != null) {
            path.forEach(city -> System.out.print(city + " -> "));
            System.out.println("\b\b\b\b    "); // Remove the last arrow and add spaces
        } else {
            System.out.println("No path found. Checking connectivity and weights...");
            if (search.getMarkedVertices().contains(destination)) {
                System.out.println("Destination was reached during search, but no valid path constructed.");
            } else {
                System.out.println("Destination was not reached during search. Check edge weights and graph connectivity.");
            }
        }
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(true);
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
        DijkstraSearch<String> djk = new DijkstraSearch<>(graph, "Almaty");

        printDijkstraPaths(djk, "Astana");
    }

    private static void printPath(DijkstraSearch<String> search, String destination) {
        System.out.print("Path to " + destination + ": ");
        Iterable<String> path = search.pathTo(destination);
        if (path != null) {
            path.forEach(v -> System.out.print(v + " "));
            System.out.println();
        } else {
            System.out.println("No path found.");
        }
    }

//        System.out.println(graph.getAdjacencyList("Astana"));
//        System.out.println(graph.getAdjacencyList("Almaty"));
//        System.out.println(graph.getAdjacencyList("Kyzylorda"));

//        System.out.println("Hello world!");
    }
