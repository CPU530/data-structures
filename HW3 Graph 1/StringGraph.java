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
        this.edgeMatrix = new boolean[capacity][2];

    }

    // Default constructor sets initial capacity to the value indicated by the
    // constant value Graph.Default_Capacity. Note: This constructor calls the
    // parameterized constructor that takes an initial capacity.
    public StringGraph() {
        this(DEFAULT_CAPACITY);
        this.numVertices = 0;
        this.numEdges = 0;
        this.labels = new String[DEFAULT_CAPACITY];
        this.edgeMatrix = new boolean[DEFAULT_CAPACITY][2];
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

        // Sort the non-null vertices alphabetically
        Arrays.sort(nonNullVertices);

        return nonNullVertices;
    }

    public String[][] getEdges() {
        String[][] edgesWithLabels = new String[numEdges][2];
        // Each row represents an edge (pair of vertices)

        for (int j = 0; j < edgeMatrix.length; j++) {
            for (int i = 0; i < edgeMatrix.length; i++) {
                if (edgeMatrix[j][i]) {
                    // If there's an edge between vertex j and i, store their labels in the result
                    // array.
                    edgesWithLabels[numEdges][0] = labels[j]; // Label for vertex j
                    edgesWithLabels[numEdges][1] = labels[i]; // Label for vertex i

                }
            }
        }

        return edgesWithLabels;
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
        // labels resize
        String[] newLabels = new String[newCapacity];
        System.arraycopy(labels, 0, newLabels, 0, numVertices);

        // edge matrix resize
        String[][] newEdges = new String[newCapacity][2];
        System.arraycopy(edgeMatrix, 0, newEdges, 0, numEdges);
        capacity = newCapacity;
        labels = newLabels;
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
        for (int i = 0; i < numEdges; i++) {
            if ((edgeMatrix[i][0] && edgeMatrix[i][1]) ||
                    (edgeMatrix[i][1] && edgeMatrix[i][0])) {

                System.out.println((edgeMatrix[i][0]));
                System.out.println((edgeMatrix[i][1]));
                return true;
            }
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
        if (numEdges >= capacity - 1) {
            resize(capacity * 2);
        }
        if (edgeExists(vertex1, vertex2)) {
            edgeMatrix[numEdges][0] = true;
            edgeMatrix[numEdges][1] = true;
            numEdges++;
        }
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
}