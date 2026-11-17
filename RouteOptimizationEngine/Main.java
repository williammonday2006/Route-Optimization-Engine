import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

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

    public boolean bfs(T startVertex, T endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        boolean[] visited = new boolean[vertices.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[startIndex] = true;
        queue.add(startIndex);

        while (!queue.isEmpty()) {
            int current = queue.remove();

            if (current == endIndex) {
                return true;
            }

            for (int i = 0; i < vertices.size(); i++) {
                if (matrix[current][i] != -1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        return false;
    }

    public boolean dfs(T startVertex, T endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        boolean[] visited = new boolean[vertices.size()];
        Stack<Integer> stack = new Stack<>();

        stack.push(startIndex);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (current == endIndex) {
                return true;
            }

            if (!visited[current]) {
                visited[current] = true;

                for (int i = 0; i < vertices.size(); i++) {
                    if (matrix[current][i] != -1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }

        return false;
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

        System.out.println("\nBFS Connectivity Tests:");
        System.out.println("New York to Los Angeles: " +
                graph.bfs("New York", "Los Angeles"));
        System.out.println("Miami to Seattle: " +
                graph.bfs("Miami", "Seattle"));
        System.out.println("Chicago to Miami: " +
                graph.bfs("Chicago", "Miami"));

        System.out.println("\nDFS Connectivity Tests:");
        System.out.println("New York to Los Angeles: " +
                graph.dfs("New York", "Los Angeles"));
        System.out.println("Miami to Seattle: " +
                graph.dfs("Miami", "Seattle"));
        System.out.println("Chicago to Miami: " +
                graph.dfs("Chicago", "Miami"));

        System.out.println("\nUnreachable City Test:");

        WeightedGraph<String> disconnected = new WeightedGraph<>(4);

        disconnected.addVertex("Boston");
        disconnected.addVertex("Philadelphia");
        disconnected.addVertex("Dallas");
        disconnected.addVertex("Phoenix");

        disconnected.addEdge("Boston", "Philadelphia", 300);

        System.out.println("Boston to Philadelphia (BFS): " +
                disconnected.bfs("Boston", "Philadelphia"));
        System.out.println("Boston to Dallas (BFS): " +
                disconnected.bfs("Boston", "Dallas"));
        System.out.println("Boston to Dallas (DFS): " +
                disconnected.dfs("Boston", "Dallas"));

        System.out.println("\nTwo City Test:");

        WeightedGraph<String> smallGraph = new WeightedGraph<>(2);

        smallGraph.addVertex("Austin");
        smallGraph.addVertex("Houston");

        smallGraph.addEdge("Austin", "Houston", 165);

        System.out.println("Austin to Houston (BFS): " +
                smallGraph.bfs("Austin", "Houston"));
        System.out.println("Austin to Houston (DFS): " +
                smallGraph.dfs("Austin", "Houston"));

        System.out.println("\nSame Starting and Ending City:");
        System.out.println("Boston to Boston (BFS): " +
                disconnected.bfs("Boston", "Boston"));
        System.out.println("Boston to Boston (DFS): " +
                disconnected.dfs("Boston", "Boston"));
    }
}
