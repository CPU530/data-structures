/**
 *
 * @author Robert McVey
 * this program creates a graph that creates and maintains an adjacency matrix
 */

import java.util.Arrays;

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
        int edgeIndex =  0; 
        for (int i =  0; i < numVertices; i++) {
            for (int j =  i; j < numVertices; j++) {
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
        if (edgeMatrix[index1][index2] == true ) {
            
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
}