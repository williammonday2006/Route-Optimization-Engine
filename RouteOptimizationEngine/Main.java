import java.util.ArrayList;
import java.util.List;

class WeightedGraph<T> {
    private List<T> vertices;
    private int[][] matrix;

    public WeightedGraph(int size) {
        vertices = new ArrayList<>();
        matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = -1;
            }
        }
    }

    public void addVertex(T vertex) {
        if (vertices.size() >= matrix.length) {
            throw new IllegalStateException("Graph is full.");
        }

        if (vertices.contains(vertex)) {
            throw new IllegalArgumentException("Vertex already exists.");
        }

        vertices.add(vertex);
    }

    public void addEdge(T source, T destination, int weight) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Vertex not found.");
        }

        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }

        matrix[sourceIndex][destinationIndex] = weight;
        matrix[destinationIndex][sourceIndex] = weight;
    }

    public void displayGraph() {
        System.out.println("Delivery Network:");

        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + ": ");

            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j] != -1) {
                    System.out.print(vertices.get(j) + " (" + matrix[i][j] + ") ");
                }
            }

            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(6);

        graph.addVertex("New York");
        graph.addVertex("Chicago");
        graph.addVertex("Denver");
        graph.addVertex("Los Angeles");
        graph.addVertex("Seattle");
        graph.addVertex("Miami");

        graph.addEdge("New York", "Chicago", 790);
        graph.addEdge("New York", "Miami", 1280);
        graph.addEdge("Chicago", "Denver", 1000);
        graph.addEdge("Denver", "Los Angeles", 830);
        graph.addEdge("Denver", "Seattle", 1300);
        graph.addEdge("Los Angeles", "Seattle", 1135);

        graph.displayGraph();
    }
}