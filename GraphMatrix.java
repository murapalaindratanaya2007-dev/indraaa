import java.util.*;

// -------- GRAPH USING ADJACENCY MATRIX --------
class GraphMatrix {
    int vertices;
    int[][] adjMatrix;

    GraphMatrix(int v) {
        vertices = v;
        adjMatrix = new int[v][v];
    }

    void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;  // Undirected graph
    }

    // BFS using Adjacency Matrix
    void BFS(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS (Matrix): ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    // DFS using Adjacency Matrix
    void DFS(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS (Matrix): ");
        DFSUtil(start, visited);
        System.out.println();
    }

    void DFSUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[node][i] == 1 && !visited[i]) {
                DFSUtil(i, visited);
            }
        }
    }
}


// -------- GRAPH USING ADJACENCY LIST --------
class GraphList {
    int vertices;
    LinkedList<Integer>[] adjList;

    GraphList(int v) {
        vertices = v;
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++)
            adjList[i] = new LinkedList<>();
    }

    void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u);  // Undirected graph
    }

    // BFS using Adjacency List
    void BFS(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS (List): ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // DFS using Adjacency List
    void DFS(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS (List): ");
        DFSUtil(start, visited);
        System.out.println();
    }

    void DFSUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }
}


// -------- MAIN CLASS --------
public class GraphTraversal {
    public static void main(String[] args) {

        int v = 5;

        // -------- USING ADJACENCY MATRIX --------
        GraphMatrix gMatrix = new GraphMatrix(v);
        gMatrix.addEdge(0, 1);
        gMatrix.addEdge(0, 2);
        gMatrix.addEdge(1, 3);
        gMatrix.addEdge(2, 4);

        gMatrix.BFS(0);
        gMatrix.DFS(0);


        // -------- USING ADJACENCY LIST --------
        GraphList gList = new GraphList(v);
        gList.addEdge(0, 1);
        gList.addEdge(0, 2);
        gList.addEdge(1, 3);
        gList.addEdge(2, 4);

        gList.BFS(0);
        gList.DFS(0);
    }
}