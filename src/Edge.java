public class Edge<Vertex> {
    private Vertex source;
    private Vertex dest;
    private double weight;

    public Edge(Vertex source, Vertex dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    // Getters and setters
    public Vertex getSource() {
        return source;
    }

    public Vertex getDest() {
        return dest;
    }

    public double getWeight() {
        return weight;
    }
}
