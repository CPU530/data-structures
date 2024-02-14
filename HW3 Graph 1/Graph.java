/**
 * A graph whose vertices are strings.
 *
 * @author Dr. Lillis
 */
public interface Graph {

    /**
     * The default capacity used when the no-argument constructor is called.
     */
    static final int DEFAULT_CAPACITY = 1;
    
    /**
     * Returns the number of vertices in this graph (the "order" of this graph).
     *
     * @return the number of vertices in this graph
     */
    int numberOfVertices();

    /**
     * Returns the number of edges in this graph (the "size" of this graph).
     *
     * @return the number of edges in this graph
     */
    int numberOfEdges();

    /**
     * Creates and returns a new array of Strings containing the vertices of
     * this graph. The length of the returned array equals the number of
     * vertices. The order of the vertices is unspecified.
     *
     * @return a String array containing the vertex labels
     */
    String[] getVertices();

    /**
     * Creates and returns a new 2D array of strings containing the edges of
     * this graph. The size of the returned array is m x 2. In other words,
     * there is one row for each edge. The order of the edges is unspecified 
     * and the order of the vertices in each edge is also unspecified.
     * For example, a graph with the three edges
     * {"A", "B"}, {"C", "A"}, and {"F", "D"} may return:
     *
     * {
     * {"A", "B"}, {"C", "A"}, {"F", "D"} }
     *
     * @return an m x 2 array in which each row represents and edge in this
     * graph.
     */
    String[][] getEdges();

    /**
     * Returns the number of vertices that can be added to this graph before
     * the array of vertices needs to be increased.
     * 
     * @return the number of vertices that can be added to this graph before the
     * array of vertices needs to be increased.
     */
    int capacity();

    /**
     * Resizes the array of labels and the adjacency matrix to a new capacity.
     * If the new capacity is larger than the current capacity, the capacity is
     * increased. Note: In future assignment the capacity may also be made
     * smaller.
     * newCapacity.
     *
     * @param newCapacity The new capacity
     */
    void resize(int newCapacity);

    /**
     * Determines if a vertex with the given label exists.
     *
     * @param vertex the label of a vertex.
     * @return true of the vertex exist, false if it does not exist.
     */
    boolean vertexExists(String vertex);

    /**
     * Adds a new vertex to this graph.
     *
     * @param vertex the new vertex to be added
     * @throws GraphException if the specified vertex already exists.
     */
    void addVertex(String vertex) throws GraphException;

    /**
     * Adds one or more vertices to this graph.
     *
     * @param vertices the vertices to be added
     */
    void addVertices(String[] vertices);

    /**
     * Determines if an edge with the given end vertices exists.
     *
     * @param vertex1 the label of one end of the edge
     * @param vertex2 the label of the other end of the edge
     * @return true edge {vertex1, vertex2} is in this graph, returns false if
     * the edge is not in this graph
     * @throws GraphException if either vertex1 or vertex2 is not in this graph.
     */
    boolean edgeExists(String vertex1, String vertex2) throws GraphException;

    /**
     * Adds a new edge.
     *
     * @param vertex1 the label of one end of the new edge
     * @param vertex2 the label of the other end of the new edge
     * @throws GraphException if the edge already exists or if either end vertex 
     * does not exist.
     */
    void addEdge(String vertex1, String vertex2) throws GraphException;

    /**
     * Adds multiple edges. Each row i in the edges parameter represents the
     * pair of vertices edges[i][0] and edges[i][1] for a new edge.
     *
     * @param edges a 2 x w array of vertices, w &ge; 0
     */
    void addEdges(String[][] edges);

    /**
     * Returns a string representation of this Graph.
     *
     * @return a string representation of this Graph.
     */
    @Override
    String toString();

}
