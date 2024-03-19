
import java.util.Arrays;

/**
 *
 * @author Dr. Lillis
 */
public class GraphUtils {

    //=====================================================================
    // Example Graphs
    //=====================================================================
    public static Graph star6() {
        /*             B
                       |
                       |
                F------A------C
                      / \
                     /   \
                    E     D
         */

        Graph star = new StringGraph();

        String[] V = {"A", "B", "C", "D", "E", "F"};
        for (String v : V) {
            star.addVertex(v);
        }

        star.addEdge("A", "B");
        star.addEdge("A", "C");
        star.addEdge("A", "D");
        star.addEdge("A", "E");
        star.addEdge("A", "F");

        return star;
    }

    static Graph wheel6() {
        /*              B
                      / | \
                    /   |   \
                   F----A-----C
                    \  / \  /
                     \/   \/
                     E-----D
         */

        Graph wheel = GraphUtils.star6();

        wheel.addEdge("B", "C");
        wheel.addEdge("C", "D");
        wheel.addEdge("D", "E");
        wheel.addEdge("E", "F");
        wheel.addEdge("F", "B");

        return wheel;
    }

    static Graph connected10() {
        /*              B                      J
                      / | \                  / |
                    /   |   \              /   |
                   F----A-----C----G-----H     |
                    \  / \  /              \   | 
                     \/   \/                 \ |
                     E-----D                   I
         */

        Graph connected = wheel6();

        String[] V = {"G", "H", "I", "J"};
        for (String v : V) {
            connected.addVertex(v);
        }

        connected.addEdge("G", "H");
        connected.addEdge("H", "J");
        connected.addEdge("J", "I");
        connected.addEdge("I", "H");

        connected.addEdge("C", "G");

        return connected;
    }

    static Graph disconnected10() {
        /*              B                      J
                      / | \                  / |
                    /   |   \              /   |
                   F----A-----C    G-----H     |
                    \  / \  /              \   | 
                     \/   \/                 \ |
                     E-----D                   I
         */

        Graph twoComponents = connected10();

        twoComponents.deleteEdge("C", "G");

        return twoComponents;
    }

    static Graph isolated5() {
        /*             B
                       
                       
                F             C
                      
                     
                    E     D
         */
        Graph fiveIsolated = star6();
        fiveIsolated.deleteVertex("A");

        return fiveIsolated;
    }

    static Graph isolated1() {
        Graph isolated = star6();
        isolated.deleteVertex("B");
        isolated.deleteVertex("C");
        isolated.deleteVertex("D");
        isolated.deleteVertex("E");
        isolated.deleteVertex("F");

        return isolated;

    }

    //=====================================================================
    // Graphs for testing. Each test has three graphs:
    //   1) A starting graph
    //   2) A solution for running BFS on the starting graph.
    //   3) A solution for running DFS on the starting graph.
    //=====================================================================
    /**
     * The graph used for DFS Tree Test 1.
     */
    static Graph BFS_DFS_Tree_Test1() {
        return connected10();
    }

    /**
     * Solution to DFS Tree Test 1. This is the tree returned by DFS tree when
     * given the "BFS_DFS_Tree_Test1" and starting at vertex "G"
     */
    static Graph DFS_Tree_Test1_SOL() {
        /*              B                      J
                      / |                      |
                    /   |                      |
                   F    A-----C----G-----H     |
                    \                      \   | 
                     \                       \ |
                     E-----D                   I
         */

        Graph g = connected10();
        g.deleteEdge("C", "B");
        g.deleteEdge("A", "F");
        g.deleteEdge("A", "E");
        g.deleteEdge("A", "D");
        g.deleteEdge("C", "D");
        g.deleteEdge("H", "J");
        return g;
    }

    /**
     * Solution to DFS Tree Test 1. This is the tree returned by DFS tree when
     * given the "BFS_DFS_Tree_Test1" and starting at vertex "G"
     */
    static Graph BFS_Tree_Test1_SOL() {
        /*              B                      J
                          \                  / 
                            \              /   
                   F----A-----C----G-----H     
                       /    /              \    
                      /    /                 \ 
                     E     D                   I
         */

        Graph g = connected10();
        g.deleteEdge("B", "F");
        g.deleteEdge("F", "E");
        g.deleteEdge("E", "D");
        g.deleteEdge("A", "B");
        g.deleteEdge("A", "D");
        g.deleteEdge("I", "J");
        return g;
    }

    /**
     * The graph used for DFS Tree Test 2.
     */
    static Graph BFS_DFS_Tree_Test2() {
        return disconnected10();
    }

    /**
     * Solution to DFS Tree Test 2. This is the tree returned by DFS tree when
     * given the "BFS_DFS_Tree_Test2" and starting at vertex "G"
     */
    static Graph DFS_Tree_Test2_SOL() {
        /*             J
                       |
                       |
           G-----H     |
                   \   | 
                     \ |
                       I
         */

        Graph g = disconnected10();
        g.deleteVertex("A");
        g.deleteVertex("B");
        g.deleteVertex("C");
        g.deleteVertex("D");
        g.deleteVertex("E");
        g.deleteVertex("F");
        g.deleteEdge("H", "J");
        return g;
    }

    /**
     * Solution to BFS Tree Test 2. This is the tree returned by BFS tree when
     * given the "BFS_DFS_Tree_Test2" and starting at vertex "G"
     */
    static Graph BFS_Tree_Test2_SOL() {
        /*             J
                     / 
                   /   
           G-----H     
                   \    
                     \ 
                       I
         */

        Graph g = disconnected10();
        g.deleteVertex("A");
        g.deleteVertex("B");
        g.deleteVertex("C");
        g.deleteVertex("D");
        g.deleteVertex("E");
        g.deleteVertex("F");
        g.deleteEdge("I", "J");
        return g;
    }

    /**
     * The graph used for DFS Tree Test 3.
     */
    static Graph BFS_DFS_Tree_Test3() {
        return isolated5();
    }

    /**
     * Solution to DFS Tree Test 3. This is the tree returned by DFS tree when
     * given the "BFS_DFS_Tree_Test3" and starting at vertex "B". The result is
     * a graph with just one vertex, "B".
     */
    static Graph DFS_Tree_Test3_SOL() {
        /*             B
         */

        Graph g = isolated5();
        g.deleteVertex("C");
        g.deleteVertex("D");
        g.deleteVertex("E");
        g.deleteVertex("F");
        return g;
    }

    /**
     * Solution to BFS Tree Test 3. This is the tree returned by BFS tree when
     * given the "BFS_DFS_Tree_Test3" and starting at vertex "B". The result is
     * a graph with just one vertex, "B".
     */
    static Graph BFS_Tree_Test3_SOL() {
        return DFS_Tree_Test3_SOL();
    }

    //=====================================================================
    // Usful functions for testing graphs
    //=====================================================================
    public static boolean equals(Graph g1, Graph g2) {
        if (g1 == g2) {
            // Both references point to the same graph
            return true;
        }

        if (g1 == null || g2 == null) {
            // One is null and the other is not
            return false;
        }

        // Now we know both g1 and g2 point to a Graph objects, but not
        // the same Graph object.
        // Number of edges
        if (g1.numberOfEdges() != g2.numberOfEdges()) {
            return false;
        }

        // Number of vertices
        if (g1.numberOfVertices() != g2.numberOfVertices()) {
            return false;
        }

        String[] vertices1 = new String[g1.numberOfVertices()];
        System.arraycopy(g1.getVertices(), 0, vertices1, 0, vertices1.length);
        Arrays.sort(vertices1);

        String[] vertices2 = new String[g2.numberOfVertices()];
        System.arraycopy(g2.getVertices(), 0, vertices2, 0, vertices2.length);
        Arrays.sort(vertices2);

        if (!Arrays.deepEquals(vertices1, vertices2)) {
            return false;
        }

        // At this point we know the two graphs have the same exact vertices.
        // Now we must check the edges. We'll do that by using the 
        // getNeighbors method.
        String[] neighbors1, neighbors2;
        for (String v : vertices1) {
            neighbors1 = g1.getNeighbors(v);
            neighbors2 = g2.getNeighbors(v);
            Arrays.sort(neighbors1);
            Arrays.sort(neighbors2);
            if (!Arrays.deepEquals(neighbors1, neighbors2)) {
                return false;
            }
        }

        // All of our tests have passed.
        return true;
    }

}
