
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Kevin Lillis
 */
public class Graph_2_Tester {

    private static final ArrayList<TestResult> tests = new ArrayList<>();
    // public static Object expected, received;

    public static void main(String[] args) {
        constructorTests();
        deleteEdgeTest();
        deleteVertexTest();
        degreeTest();


        TestResult.reportTestResults(tests);

    }

    public static void constructorTests() {
        String[] vertices = {"A", "B", "C", "D"};
        Graph g = new StringGraph(vertices);

        tests.add(new TestResult("Constructor 1", 4, g.numberOfVertices()));
        tests.add(new TestResult("Constructor 2", 0, g.numberOfEdges()));
        tests.add(new TestResult("Constructor 3", 4, g.capacity()));

        tests.add(new TestResult("Constructor 4", true, g.vertexExists("A")));
        tests.add(new TestResult("Constructor 5", true, g.vertexExists("B")));
        tests.add(new TestResult("Constructor 6", true, g.vertexExists("C")));
        tests.add(new TestResult("Constructor 7", true, g.vertexExists("D")));

        vertices = new String[]{"A", "B", "C", "D"};
        String[][] edges = {{"A", "C"}, {"C", "D"}, {"D", "A"}};
        g = new StringGraph(vertices, edges);

        tests.add(new TestResult("Constructor 8", 4, g.numberOfVertices()));
        tests.add(new TestResult("Constructor 9", 3, g.numberOfEdges()));
        tests.add(new TestResult("Constructor 10", 4, g.capacity()));

        tests.add(new TestResult("Constructor 11", true, g.vertexExists("A")));
        tests.add(new TestResult("Constructor 12", true, g.vertexExists("B")));
        tests.add(new TestResult("Constructor 13", true, g.vertexExists("C")));
        tests.add(new TestResult("Constructor 14", true, g.vertexExists("D")));

        tests.add(new TestResult("Constructor 15", true, g.edgeExists("A", "C")));
        tests.add(new TestResult("Constructor 16", true, g.edgeExists("C", "A")));
        tests.add(new TestResult("Constructor 17", true, g.edgeExists("C", "D")));
        tests.add(new TestResult("Constructor 18", true, g.edgeExists("D", "C")));
        tests.add(new TestResult("Constructor 19", true, g.edgeExists("D", "A")));
        tests.add(new TestResult("Constructor 20", true, g.edgeExists("A", "D")));
    }

    public static void deleteEdgeTest() {
        String[] vertices = new String[]{"A", "B", "C", "D"};
        String[][] edges = {{"A", "C"}, {"C", "D"}, {"D", "A"}};
        Graph g = new StringGraph(vertices, edges);

        g.deleteEdge("A", "C");
        tests.add(new TestResult("Delete Edge 1", false, g.edgeExists("A", "C")));
        tests.add(new TestResult("Delete Edge 2", 4, g.numberOfVertices()));
        tests.add(new TestResult("Delete Edge 3", 2, g.numberOfEdges()));
        tests.add(new TestResult("Delete Edge 4", true, g.vertexExists("A")));
        tests.add(new TestResult("Delete Edge 5", true, g.vertexExists("C")));
    }

    public static void deleteVertexTest() {
        String[] vertices = new String[]{"A", "B", "C", "D"};
        String[][] edges = {{"A", "C"}, {"C", "D"}, {"D", "A"}};
        Graph g = new StringGraph(vertices, edges);

        g.deleteVertex("A");
        tests.add(new TestResult("Delete Vertex 1", false, g.vertexExists("A")));
        tests.add(new TestResult("Delete Vertex 2", 3, g.numberOfVertices()));
        tests.add(new TestResult("Delete Vertex 3", 1, g.numberOfEdges()));
        tests.add(new TestResult("Delete Vertex 4", true, g.edgeExists("D", "C")));
    }
    
    public static void degreeTest(){
        /*
              B\       /E
              | \     / |
              |  A---D  |  G
              | /     \ |
              C/       \F
        */
        Graph g = new StringGraph(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        g.addEdges(new String[][]{{"A", "B"}, {"B", "C"}, {"C", "A"}});
        g.addEdges(new String[][]{{"D", "E"}, {"E", "F"}, {"F", "D"}});
        g.addEdge("A", "D");

        tests.add(new TestResult("Degree 1", 3, g.degree("A")));
        tests.add(new TestResult("Degree 2", 2, g.degree("B")));
        tests.add(new TestResult("Degree 3", 2, g.degree("C")));
        tests.add(new TestResult("Degree 4", 3, g.degree("D")));
        tests.add(new TestResult("Degree 5", 2, g.degree("E")));
        tests.add(new TestResult("Degree 6", 2, g.degree("F")));
        tests.add(new TestResult("Degree 7", 0, g.degree("G")));
        
        tests.add(new TestResult("Max Degree 1", 3, g.maxDegree()));
        tests.add(new TestResult("Min Degree 1", 0, g.minDegree()));
        tests.add(new TestResult("Average Degree 1", 2.0, g.averageDegree()));
        
        tests.add(new TestResult("Degree sequence 1", 
                new int[]{3,3,2,2,2,2,0}, g.degreeSequence()));
    }

}