
/**
 *
 * @author Robert McVey
 * this program creates a graph that creates and maintains an adjacency matrix
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class StringGraph implements Graph {
    // Number of vertices that can be stored in this graph before the size of the
    // labels and adjacency arrays needs to be increased.

    protected int capacity;

    // 2D array that stores adjacencies between pairs of vertices.
    protected boolean[][] edgeMatrix;

    // 1D array that stores the vertex labels.
    protected String[] labels;

    // Number of edges in this graph (the "size" of this graph).
    protected int numEdges;

    // Number of vertices in this graph (the "order" of this graph).
    protected int numVertices;

    // Parameterized constructor sets initial capacity to given capacity
    public StringGraph(int capacity) {
        if (capacity < 1) {
            throw new GraphException("The initial capacity must be greater than or equal to one");
        }
        this.capacity = capacity;
        this.numVertices = 0;
        this.numEdges = 0;
        this.labels = new String[capacity];
        this.edgeMatrix = new boolean[capacity][capacity];

    }

    // Default constructor sets initial capacity to the value indicated by the
    // constant value Graph.Default_Capacity. Note: This constructor calls the
    // parameterized constructor that takes an initial capacity.
    public StringGraph() {
        this(DEFAULT_CAPACITY);
        this.numVertices = 0;
        this.numEdges = 0;
        this.labels = new String[DEFAULT_CAPACITY];
        this.edgeMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    }

    public StringGraph(String[] initVertices) {
        this(DEFAULT_CAPACITY);
        this.numVertices = 0;
        this.numEdges = 0;
        addVertices(initVertices);
        this.edgeMatrix = new boolean[numVertices][numVertices];
    };

    public StringGraph(String[] initVertices, String[][] initEdges) {
        this(DEFAULT_CAPACITY);
        this.numVertices = 0;
        this.numEdges = 0;
        addVertices(initVertices);
        addEdges(initEdges);
    };

    @Override
    public int numberOfVertices() {
        return numVertices;
    }

    @Override
    public int numberOfEdges() {
        return numEdges;
    }

    @Override
    public String[] getVertices() {
        String[] nonNullVertices = new String[numVertices];
        int index = 0;

        for (String vertex : labels) {
            if (vertex != null) {
                nonNullVertices[index] = vertex;
                index++;
            }
        }

        Arrays.sort(nonNullVertices);

        return nonNullVertices;
    }

    @Override
    public String[][] getEdges() {
        String[][] edges = new String[numEdges][2];
        int edgeIndex = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i; j < numVertices; j++) {
                if (edgeMatrix[i][j]) {

                    edges[edgeIndex][0] = labels[i];
                    edges[edgeIndex][1] = labels[j];
                    edgeIndex++;
                }
            }
        }
        return edges;
    }

    @Override
    public int capacity() {
        return (capacity);
    }

    @Override
    public void resize(int newCapacity) {
        if (newCapacity <= capacity) {
            throw new IllegalArgumentException("New capacity must be greater than the current capacity.");
        }

        String[] newLabels = new String[newCapacity];
        System.arraycopy(labels, 0, newLabels, 0, numVertices);

        boolean[][] newEdges = new boolean[newCapacity][newCapacity];
        // System.arraycopy(edgeMatrix, 0, newEdges, 0, numEdges);
        capacity = newCapacity;
        labels = newLabels;

        // edgeMatrix = newEdges;
        edgeMatrix = create_new_graph(newEdges, getEdges());
    }

    @Override
    public boolean vertexExists(String vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (labels[i].equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addVertex(String vertex) throws GraphException {
        if (vertexExists(vertex)) {
            throw new GraphException("Vertex already exists: " + vertex);
        }
        if (numVertices >= capacity) {
            resize(capacity * 2);
        }
        labels[numVertices] = vertex;
        numVertices++;
    }

    @Override
    public void addVertices(String[] vertices) {
        for (String vertex : vertices) {
            try {
                addVertex(vertex);
            } catch (GraphException e) {

            }
        }
    }

    @Override
    public boolean edgeExists(String vertex1, String vertex2) throws GraphException {
        if (!vertexExists(vertex1) || !vertexExists(vertex2)) {
            throw new GraphException("One or both vertices do not exist.");
        }
        // this is incorrect as there are fixed numbers in the fields
        // find the indicies of the verteex 1 and vertex 2 so that the "X,Y" in the
        // edgeMatrix can be checked specifically

        int index1 = findIndex(vertex1);
        int index2 = findIndex(vertex2);

        if (edgeMatrix[index1][index2] == true) {

            return true;
        }
        return false;
    }

    @Override
    public void addEdge(String vertex1, String vertex2) throws GraphException {
        if (edgeExists(vertex1, vertex2)) {
            throw new GraphException("Edge already exists: {" + vertex1 + ", " + vertex2 + "}");
        }
        if (!vertexExists(vertex1) || !vertexExists(vertex2)) {
            throw new GraphException("One or both vertices do not exist.");
        }
        // need to make this actually affect the right point in the edge matric which
        // means it should be soemthing like edgeMatrix[vertex1][vertex2]

        int index1 = findIndex(vertex1);
        int index2 = findIndex(vertex2);

        // Add the edge to the edgeMatrix
        edgeMatrix[index1][index2] = true;
        edgeMatrix[index2][index1] = true; // For undirected graph
        numEdges++;
    }

    @Override
    public void addEdges(String[][] edges) {
        for (String[] Label : edges) {
            if (Label.length != 2) {
                throw new GraphException("Invalid edge format: " + Arrays.toString(Label));
            }
            addEdge(Label[0], Label[1]);
        }

    }

    private int findIndex(String label) {
        for (int i = 0; i < numVertices; i++) {
            if (labels[i].equals(label)) {
                return i;
            }
        }
        return -1; // Not found
    }

    @Override
    public void deleteVertex(String vertex) throws GraphException {
        // must called delete edge
        if (!vertexExists(vertex)) {
            throw new GraphException("The vertex do not exist.");
        }
        for (int i = 0; i < numVertices; i++) {
            // remove edges from related vertex
            if (labels[i] != vertex) {
                if (edgeExists(vertex, labels[i])) {
                    deleteEdge(vertex, labels[i]);
                }

            }
        }
        // store edges so that they can be appended later
        String[][] edge_recreation = getEdges();

        // overwrite label value with last values of label
        int Del_Vertex_index = findIndex(vertex);

        // find the last label/index of last label in the list
        int last_vertex_index = findIndex(labels[(numVertices - 1)]);

        // reduce the count of vertices
        numVertices--;

        // assign last label to the specified inxdex to be deleted
        labels[Del_Vertex_index] = labels[last_vertex_index];

        // this removes the last item in the array effectively removing the duplicate
        String[] newLabels = new String[numVertices];
        System.arraycopy(labels, 0, newLabels, 0, numVertices);
        labels = newLabels;

        // make instance of class with new matrix with correct values and then swap the
        // adjacency matrix's of the two

        // StringGraph new_matrix = new StringGraph(newLabels, edge_recreation);
        // this.edgeMatrix = new_matrix.edgeMatrix;

        // it has come to my attention that calling a class withing a class to create a
        // new matrix which was simple to do was ultimately dum so now we have
        // "crete_new_graph()" ./sigh ./shrug

        boolean[][] test = new boolean[numVertices][numVertices];
        this.edgeMatrix = create_new_graph(test, edge_recreation);

    }

    @Override
    public void deleteEdge(String vertex1, String vertex2) throws GraphException {
        if (!edgeExists(vertex1, vertex2)) {
            throw new GraphException("Edge does not exist: {" + vertex1 + ", " + vertex2 + "}");
        }
        if (!vertexExists(vertex1) || !vertexExists(vertex2)) {
            throw new GraphException("One or both vertices do not exist.");
        }
        int index1 = findIndex(vertex1);
        int index2 = findIndex(vertex2);

        // Add the edge to the edgeMatrix
        edgeMatrix[index1][index2] = false;
        edgeMatrix[index2][index1] = false; // For undirected graph
        numEdges--;

    }

    @Override
    public int degree(String vertex) throws GraphException {
        int num_degree = findDegree(vertex);
        return num_degree;
    }

    @Override
    public int maxDegree() {
        int largestDegree = 0;
        int currentDegree = 0;
        for (int i = 0; i < numVertices; i++) {
            currentDegree = findDegree(labels[i]);
            if (currentDegree > largestDegree) {
                largestDegree = currentDegree;
            }

        }
        return largestDegree;
    }

    @Override
    public int minDegree() {
        int smallestDegree = 0;
        int currentDegree = 0;
        for (int i = 0; i < numVertices; i++) {
            currentDegree = findDegree(labels[i]);
            if (currentDegree < smallestDegree) {
                smallestDegree = currentDegree;
            }

        }
        return smallestDegree;
    }

    @Override
    public double averageDegree() {
        ArrayList<Integer> all_degrees = new ArrayList<Integer>();
        int currentDegree = 0;
        double average;
        int sum = 0;
        for (int i = 0; i < numVertices; i++) {
            currentDegree = findDegree(labels[i]);
            all_degrees.add(currentDegree);
        }

        for (int i = 0; i < all_degrees.size(); i++) {

            sum += all_degrees.get(i);
        }
        average = sum / all_degrees.size();
        return average;
    }

    @Override
    public int[] degreeSequence() {
        ArrayList<Integer> all_degrees = new ArrayList<Integer>();
        int currentDegree = 0;
        int[] ordered_sequence = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            currentDegree = findDegree(labels[i]);
            all_degrees.add(currentDegree);
        }

        Collections.sort(all_degrees);
        Collections.reverse(all_degrees);

        for (int i = 0; i < all_degrees.size(); i++) {
            ordered_sequence[i] = all_degrees.get(i);
        }

        return ordered_sequence;
    }

    public int findDegree(String vertex) {
        int Degree_Vertex = 0;
        int index1 = findIndex(vertex);
        for (int i = 0; i < numVertices; i++) {
            if (edgeMatrix[index1][i]) {
                Degree_Vertex++;

            }

        }

        return Degree_Vertex;
    };

    public boolean[][] create_new_graph(boolean[][] empty_graph, String[][] Edges_add) {

        for (String[] Label : Edges_add) {
            if (Label.length != 2) {
                throw new GraphException("Invalid edge format: " + Arrays.toString(Label));
            }
            /*
             * if (edgeExists(Label[0], Label[1])) {
             * throw new GraphException("Edge already exists: {" + Label[0] + ", " +
             * Label[1] + "}");
             * }
             */
            if (!vertexExists(Label[0]) || !vertexExists(Label[1])) {
                throw new GraphException("One or both vertices do not exist.");
            }
            // need to make this actually affect the right point in the edge matric which
            // means it should be soemthing like edgeMatrix[vertex1][vertex2]

            int index1 = findIndex(Label[0]);
            int index2 = findIndex(Label[1]);

            // Add the edge to the edgeMatrix
            empty_graph[index1][index2] = true;
            empty_graph[index2][index1] = true; // For undirected graph
        }

        boolean[][] new_graph = empty_graph;
        return new_graph;
    }

    @Override
    public String[] getNeighbors(String vertex) throws GraphException {
        String[] neighbors = new String[numVertices];
        int V_POS = findIndex(vertex);
        if (V_POS == -1) {
            throw new GraphException("This vertex does not exist");
            
        }
        for (int i = 0; i < numVertices; i++) {
        
            if (this.edgeMatrix[V_POS][i] == true) {
                neighbors[i] = labels[i];
            }

        }
        neighbors = removeNullsFromStringArray(neighbors);
        //System.out.println(Arrays.deepToString(neighbors));
        return neighbors;
    }

    @Override
    public String[] bfsOrder(String vertex) throws GraphException {
        ArrayList<String> que = new ArrayList<String>();
        que.add(vertex);
        ArrayList<String> visit_order = new ArrayList<String>();

        while (que.isEmpty() != true) {
            String current_node = que.remove(0);
            visit_order.add(current_node);
            String[] values_to_que = getNeighbors(current_node);
            Arrays.sort(values_to_que);
            for (int i = 0; i < values_to_que.length; i++) {
                if (!que.contains(values_to_que[i]) && !visit_order.contains(values_to_que[i])) {
                    que.add(values_to_que[i]);

                }
            }

        }
        String[] visted_final = visit_order.toArray(new String[0]);
        return visted_final;
    }

    @Override
    public Graph bfsTree(String vertex) throws GraphException {
        ArrayList<String> que = new ArrayList<String>();
        que.add(vertex);
        ArrayList<String> visit_order = new ArrayList<String>();
        int counter = 0;
        ArrayList<String[]> edge_holder = new ArrayList<String[]>();
        String previous_node = vertex;
        while (que.isEmpty() != true) {
            String current_node = que.remove(0);
            visit_order.add(current_node);
            String[] values_to_que = getNeighbors(current_node);
            Arrays.sort(values_to_que);
            for (int i = 0; i < values_to_que.length; i++) {
                if (!que.contains(values_to_que[i]) && !visit_order.contains(values_to_que[i])) {
                    que.add(values_to_que[i]);

                }

                if (counter != 0) {
                    String[] traversed_edge = new String[] { current_node, previous_node };
                    boolean alreadyExists = false;

                    // Check if an equivalent array already exists in the list
                    for (String[] existingEdge : edge_holder) {
                        if (existingEdge[0].equals(traversed_edge[0]) && existingEdge[1].equals(traversed_edge[1])) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    if (!alreadyExists) {
                        edge_holder.add(traversed_edge);
                    }
                }

            }
            previous_node = current_node;
            counter++;
        }
        /*
         * returns a graph class therefore must be
         * return and instance of the graph class
         * we want to return a adjaceny matrix of the traversal
         * 
         * What do we need
         * the edges taversed
         * and the labels
         * 
         * we have labels labels needs not be changed persay
         * labels can be the "visited order because that will contain on viable vertexs"
         */
        String[] visted_vertexs = visit_order.toArray(new String[0]);
        String[][] traversal_edges = edge_holder.toArray(new String[0][1]);
        Graph BFS_graph = new StringGraph(visted_vertexs, traversal_edges);

        return BFS_graph;
    }

    @Override
    public String[] dfsOrder(String vertex) throws GraphException {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        List<String> visitOrder = new ArrayList<>();

        stack.push(vertex);

        while (!stack.isEmpty()) {
            String current_node = stack.pop();

            if (!visited.contains(current_node)) {
                visited.add(current_node);
                visitOrder.add(current_node);

                String[] neighbors = getNeighbors(current_node);
                Arrays.sort(neighbors, Collections.reverseOrder()); // Sort neighbors in reverse order for DFS

                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return visitOrder.toArray(new String[0]);
    }

    @Override
    public Graph dfsTree(String vertex) throws GraphException {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        List<String> visitOrder = new ArrayList<>();
        ArrayList<String[]> edge_holder = new ArrayList<>();
        String previous_node = vertex;
        int counter = 0;

        stack.push(vertex);
        while (!stack.isEmpty()) {
            String current_node = stack.pop();

            if (!visited.contains(current_node)) {
                visited.add(current_node);
                visitOrder.add(current_node);

                String[] neighbors = getNeighbors(current_node);
                Arrays.sort(neighbors, Collections.reverseOrder()); // Sort neighbors in reverse order for DFS

                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }

                if (counter != 0) {
                    String[] traversed_edge = new String[] { current_node, previous_node };
                    boolean alreadyExists = false;

                    // Check if an equivalent array already exists in the list
                    for (String[] existingEdge : edge_holder) {
                        if (existingEdge[0].equals(traversed_edge[0]) && existingEdge[1].equals(traversed_edge[1])) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    if (!alreadyExists) {
                        edge_holder.add(traversed_edge);
                    }
                }
                previous_node = current_node;
                counter++;
            }
        }

        String[] visted_vertexs = visitOrder.toArray(new String[0]);
        String[][] traversal_edges = edge_holder.toArray(new String[0][1]);
        Graph DFS_graph = new StringGraph(visted_vertexs, traversal_edges);

        return DFS_graph;
    }

    // unsure
    /*
     * assume the following
     * a connected graph means that all elements are have at least one edge
     * or that all elements that have and edge are connected to all edges through
     * some for
     * 
     * the main pivot here is; does a floating vertex with no edge fail the
     * connected condition
     */

    /*
     * GOAL: if we pick a starting point all vertexs should be visited if not then
     * they are not connneceted
     * 
     * however better idea compare lengths since the DFS/BFS are based on existing
     * list of labels if all labels are visited by search then they should be the
     * same length
     */
    @Override
    public boolean isConnected() {

        Graph Search_graph = this.bfsTree(labels[0]);
        String[] visited_verticies = Search_graph.getVertices();
        String[] exsiting_vertices = this.getVertices();

        if (visited_verticies.length != exsiting_vertices.length) {
            return false;
        }
        return true;
    }

    public static String[] removeNullsFromStringArray(String[] inputArray) {
        List<String> listWithoutNulls = new ArrayList<>();
        for (String element : inputArray) {
            if (element != null) {
                listWithoutNulls.add(element);
            }
        }
        return listWithoutNulls.toArray(new String[0]);
    }
    /*
     * 
     * 
     * ignore this i was attempting to do a I for x in array through a function but
     * i had issuse adding to the que
     * 
     * public String[] visitStrings(String[] queue) {
     * String[] visited = new String[numVertices];
     * for (int i = 0; i < queue.length; i++) {
     * visited[i + 1] = queue[i];
     * }
     * 
     * return visited;
     * }
     */

    @Override
public String[] bfsSSSP(String vertex) throws GraphException {
    // Initialize data structures
    Map<String, String> previousVertices = new HashMap<>();
    Map<String, Integer> distances = new HashMap<>();
    Queue<String> queue = new LinkedList<>();

    // Initialize distances (source vertex has distance 0)
    distances.put(vertex, 0);
    previousVertices.put(vertex, null);
    queue.add(vertex);

    while (!queue.isEmpty()) {
        String currentVertex = queue.poll();
        String[] neighbors = getNeighbors(currentVertex);
        Arrays.sort(neighbors);

        for (String neighbor : neighbors) {
            if (!distances.containsKey(neighbor)) {
                // Neighbor not visited yet
                distances.put(neighbor, distances.get(currentVertex) + 1);
                previousVertices.put(neighbor, currentVertex);
                queue.add(neighbor);
            }
        }
    }

    // Convert previousVertices map to an array
    String[] result = new String[previousVertices.size()];
    int index = 0;
    for (Map.Entry<String, String> entry : previousVertices.entrySet()) {
        result[index++] = entry.getValue();
    }

    return result;
}

    @Override
    public String[] shortestPath(String s, String t) throws GraphException {
        // Perform BFS starting from vertex s
        if (findIndex(t) == -1|| findIndex(s) == -1) {
            throw new GraphException("One or vertices does not exist");
            
        }
        if (isConnected() == false) {
            String[] disconnnected_vertex = new String[1];
            disconnnected_vertex[0] = "";
            return disconnnected_vertex;   
           }

        ArrayList<String> queue = new ArrayList<>();
        ArrayList<String> visited = new ArrayList<>();
        HashMap<String, String> previousMap = new HashMap<>(); // Stores previous vertex for each visited vertex
    
        queue.add(s);
        visited.add(s);
        previousMap.put(s, null);
    
        while (!queue.isEmpty()) {
            String currentVertex = queue.remove(0);
    
            // Process neighbors
            String[] neighbors = getNeighbors(currentVertex);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previousMap.put(neighbor, currentVertex);
                }
            }
        }
    
        // Construct the path from t to s
        ArrayList<String> path = new ArrayList<>();
        String current = t;
        while (current != null) {
            path.add(current);
            current = previousMap.get(current);
        }
    
        // Convert path to String array (reverse the order)
        String[] result = new String[path.size()];
        for (int i = 0; i < path.size(); i++) {
            result[i] = path.get(path.size() - 1 - i);
        }
    
        return result;
    }
}