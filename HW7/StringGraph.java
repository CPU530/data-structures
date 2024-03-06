
/**
 *
 * @author Robert McVey
 * this program creates a graph that creates and maintains an adjacency matrix
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        System.arraycopy(edgeMatrix, 0, newEdges, 0, numEdges);
        capacity = newCapacity;
        labels = newLabels;

        edgeMatrix = newEdges;
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

            if (edgeExists(Label[0], Label[1])) {
                throw new GraphException("Edge already exists: {" + Label[0] + ", " + Label[1] + "}");
            }
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
        /*
         * need all edges and vertexs then parse
         * exsisting edges for pair that includes vertex
         * if a pair is found then find the other vertex
         * in the edge pair and add it to a list of neighbors
         */
        String[][] edge_list = this.getEdges();
        String[] neighbors = new String[edge_list.length];

        int index = 0;
        for (int i = 0; i < edge_list.length - 1; i++) {

            if (edge_list[i][1] == vertex) {
                neighbors[index] = edge_list[i][0];
                index++;

            }
            if (edge_list[i][0] == vertex) {
                neighbors[index] = edge_list[i][1];
                index++;

            }

        }

        String[] new_neighbors = removeNullsFromStringArray(neighbors);
        return new_neighbors;
    }

    @Override
    public String[] bfsOrder(String vertex) throws GraphException {
        String[] queue = this.getNeighbors(vertex);
        Arrays.sort(queue);
        String[] visited = new String[numVertices];
        visited[0] = vertex;
        int i = 0;

        while (queue.length > 0) {

            //add visted point to visited list
            visited[i + 1] = queue[0];
            
            //generate list of neighbors to be added to the back 
            String[] add_to_back = getNeighbors(queue[0]);
            
            String[] dupecheck = visited;

            List<String> dupecheck_List = Arrays.asList(dupecheck);


            queue[0] = null;
            queue = removeNullsFromStringArray(queue);
            String[] new_que = new String[(queue.length) + (add_to_back.length)];
            int j = 0;


            // add all of que to new que
            for (; j < queue.length; j++) {
                new_que[j] = queue[j];
            }
            
            // add all of add to back to new que
            for (int X = 0; j < new_que.length; j++) {
                if (!dupecheck_List.contains(add_to_back[X]) ) {

                    new_que[j] = add_to_back[X];

                }
                X++;
            }
            queue = new_que;
            queue = removeNullsFromStringArray(queue);
            i++;
            
        }
        return visited;

    }

    @Override
    public Graph bfsTree(String vertex) throws GraphException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bfsTree'");
    }

    @Override
    public String[] dfsOrder(String vertex) throws GraphException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dfsOrder'");
    }

    @Override
    public Graph dfsTree(String vertex) throws GraphException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dfsTree'");
    }

    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isConnected'");
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

    public String[] visitStrings(String[] queue) {
        String[] visited = new String[numVertices];
        for (int i = 0; i < queue.length; i++) {
            visited[i + 1] = queue[i];
        }

        return visited;
    }
}