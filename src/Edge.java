import java.util.Objects;

public class Edge<Vertex> {
    private Vertex source;
    private Vertex destination;
    private Double weight;

    // Constructor for weighted edge
    public Edge(Vertex source, Vertex destination, Double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Constructor for unweighted edge, initializes weight to null
    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
        this.weight = null;
    }

    // Setters and getters for source vertex
    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getSource() {
        return source;
    }

    // Setters and getters for destination vertex
    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public Vertex getDestination() {
        return destination;
    }

    // Setters and getters for weight of the edge
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true; // Fast comparison for reference equality
        if (other == null || getClass() != other.getClass()) return false; // Type check

        Edge<?> otherEdge = (Edge<?>) other; // Safe cast after type checking

        // Compare source and destination without considering weight
        return Objects.equals(source, otherEdge.source) && Objects.equals(destination, otherEdge.destination);
    }

    @Override
    public int hashCode() {
        // Include source and destination in hash calculation
        return Objects.hash(source, destination);
    }
}
