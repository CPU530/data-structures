
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Kevin Lillis
 */

public class Graph_1_Tester {
    private static final ArrayList<TestResult> tests = new ArrayList<>();
    //public static Object expected, received;
    

    public static void main(String[] args) {
        constructorTests();
        addVertexTest();
        addEdgeTest();
        addMultiplesTest();
        getVerticesTest();
        getEdgesTest();
        exceptionsTest();
        
        TestResult.reportTestResults(tests);
                
    }
    
    public static void constructorTests(){
        int expected, received;
        Graph g = new StringGraph();
        
        expected = 0;
        received = g.numberOfVertices();
        tests.add(new TestResult("Constructor 1",  expected, received));
        
        expected = 0;
        received = g.numberOfEdges();
        tests.add(new TestResult("Constructor 2",  expected, received));

        expected = 1;
        received = g.capacity();
        tests.add(new TestResult("Constructor 3",  expected, received));
        
        g = new StringGraph(10);
        
        expected = 0;
        received = g.numberOfVertices();
        tests.add(new TestResult("Constructor 4",  expected, received));
        
        expected = 0;
        received = g.numberOfEdges();
        tests.add(new TestResult("Constructor 5",  expected, received));

        expected = 10;
        received = g.capacity();
        tests.add(new TestResult("Constructor 6",  expected, received));
    }
    
    public static void addVertexTest(){
        int expected, received;
        Graph g = new StringGraph();
        //-------------------------------- Add Vertex
        g.addVertex("A");
        expected = 1;
        received = g.numberOfVertices();
        tests.add(new TestResult("Add Vertex 1",  expected, received));
        
        expected = 0;
        received = g.numberOfEdges();
        tests.add(new TestResult("Add Vertex 2",  expected, received));

        expected = 1;
        received = g.capacity();
        tests.add(new TestResult("Add Vertex 3",  expected, received));


        g.addVertex("B");
        expected = 2;
        received = g.numberOfVertices();
        tests.add(new TestResult("Add Vertex 4",  expected, received));
        
        expected = 0;
        received = g.numberOfEdges();
        tests.add(new TestResult("Add Vertex 5",  expected, received));

        expected = 2;
        received = g.capacity();
        tests.add(new TestResult("Add Vertex 6",  expected, received));

        
        g.addVertex("C");
        expected = 3;
        received = g.numberOfVertices();
        tests.add(new TestResult("Add Vertex 7",  expected, received));
        
        expected = 0;
        received = g.numberOfEdges();
        tests.add(new TestResult("Add Vertex 8",  expected, received));

        expected = 4;
        received = g.capacity();
        tests.add(new TestResult("Add Vertex 9",  expected, received));
        
        g.addVertex("D");
        expected = 4;
        received = g.numberOfVertices();
        tests.add(new TestResult("Add Vertex 10",  expected, received));
        
        expected = 0;
        received = g.numberOfEdges();
        tests.add(new TestResult("Add Vertex 11",  expected, received));

        expected = 4;
        received = g.capacity();
        tests.add(new TestResult("Add Vertex 12",  expected, received));


        g.addVertex("E");
        expected = 5;
        received = g.numberOfVertices();
        tests.add(new TestResult("Add Vertex 13",  expected, received));
        
        expected = 0;
        received = g.numberOfEdges();
        tests.add(new TestResult("Add Vertex 14",  expected, received));

        expected = 8;
        received = g.capacity();
        tests.add(new TestResult("Add Vertex 15",  expected, received));
    }
    
    public static void addEdgeTest(){
        boolean expected, received;
        Graph g = new StringGraph();
        g.addVertices(new String[]{"A", "B", "C", "D", "E"});
        // ------------------------------ Add Edge
        g.addEdge("A", "B");
        tests.add(new TestResult("Add Edge 1",  1, g.numberOfEdges()));

        g.addEdge("B", "C");
        tests.add(new TestResult("Add Edge 2",  2, g.numberOfEdges()));

        
        // ------------------------ Vertex Exists
        expected = true;
        received = g.vertexExists("A");
        tests.add(new TestResult("Vertex Exists 1",  expected, received));

        expected = true;
        received = g.vertexExists("E");
        tests.add(new TestResult("Vertex Exists 2",  expected, received));

        expected = false;
        received = g.vertexExists("F");
        tests.add(new TestResult("Vertex Exists 3",  expected, received));

        // ------------------------ Edge Exists
        expected = true;
        received = g.edgeExists("A", "B");
        tests.add(new TestResult("Edge Exists 1",  expected, received));

        expected = true;
        received = g.edgeExists("B", "A");
        tests.add(new TestResult("Edge Exists 2",  expected, received));

        expected = false;
        received = g.edgeExists("A", "A");
        tests.add(new TestResult("Edge Exists 3",  expected, received));

        expected = false;
        received = g.edgeExists("C", "E");
        tests.add(new TestResult("Edge Exists 4",  expected, received));
        
    }
    
    public static void addMultiplesTest(){
        boolean expected, received;
        // Make a new graph to add edges & vertices with different methods
        Graph g = new StringGraph();
        String[] vertices = {"V", "W", "X", "Y", "Z"};
        String[][] edges = {{"V", "W"}, {"V", "X"}, {"X", "Y"}, {"X", "Z"}};
        
        g.addVertices(vertices);
        g.addEdges(edges);
        
        //------------------------------ Add Vertices
        tests.add(new TestResult("Add Vertices 1",  5, g.numberOfVertices()));
        
        expected = true;
        received = g.vertexExists("V");
        tests.add(new TestResult("Add Vertices 2",  expected, received));
        
        expected = false;
        received = g.vertexExists("A");
        tests.add(new TestResult("Add Vertices 3",  expected, received));

        //------------------------------ Add Edges
        tests.add(new TestResult("Add Edges 1",  5, g.numberOfVertices()));
        
        expected = true;
        received = g.edgeExists("V", "W");
        tests.add(new TestResult("Add Edges 2",  expected, received));

        expected = true;
        received = g.edgeExists("X", "V");
        tests.add(new TestResult("Add Edges 3",  expected, received));

        expected = false;
        received = g.edgeExists("V", "Y");
        tests.add(new TestResult("Add Edges 4",  expected, received));
    }
    
    public static void getVerticesTest(){
        String[] expected, received;
        
        Graph g = new StringGraph();
        g.addVertices(new String[]{"C", "A", "E", "B", "D"});
        
        
        expected = new String[]{"A", "B", "C", "D", "E"};
        
        received = g.getVertices();
        Arrays.sort(received);
        
        tests.add(new TestResult("Get Vertices Test 1", expected, received));
    }

    public static void getEdgesTest(){
        String[][] expected, received;
        
        Graph g = new StringGraph();
        g.addVertices(new String[]{"A", "C", "D", "B"});
        g.addEdges(new String[][]{{"C", "B"}, {"A", "C"}, {"D", "B"}});
        
        String[][] expectedEdges = new String[][]{{"A", "C"}, {"B", "C"}, {"B", "D"}};
        String[][] receivedEdges = g.getEdges();
        
        for(String[] edge : receivedEdges){
            Arrays.sort(edge);
        }
        
        // Make a comparator using a lambda expression
        java.util.Comparator<String[]> comp = (String[] a, String[] b) -> {
            if (!a[0].equals(b[0])) {
                return a[0].compareTo(b[0]);
            } else {
                return a[1].compareTo(b[1]);
            }
        };
        Arrays.sort(receivedEdges, comp);

        expected = expectedEdges;
        received = receivedEdges;
        tests.add(new TestResult("Get Edges Test 1", expected, received));

    }
    
    public static void exceptionsTest(){
        Graph g = new StringGraph();

        //----------------- addVertex
        try{
            g.addVertex("A");
            tests.add(new TestResult("Exceptions 1", "no exception thrown", "no exception thrown"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 2", "exception not expected", "exception thrown"));            
        }
        
        try{
            g.addVertex("A");
            tests.add(new TestResult("Exceptions 3", "exception expected", "exception not thrown"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 4", "exception thrown", "exception thrown"));            
        }
        
        // ----------------- edgeExists
        try{
            g.edgeExists("A", "B");
            tests.add(new TestResult("Exceptions 5", "exception expected (no vertex B)", "exception not thrown"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 6", "exception thrown", "exception thrown"));            
        }
        
        try{
            g.edgeExists("B", "A");
            tests.add(new TestResult("Exceptions 7", "exception expected (no vertex B)", "exception not thrown"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 8", "exception thrown", "exception thrown"));            
        }

        // ------------------ addEdge
        try{
            g.addEdge("A", "B");
            tests.add(new TestResult("Exceptions 9", "exception expected (no vertex B)", "exception not thrown"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 10", "exception thrown", "exception thrown"));            
        }
        
        try{
            g.addEdge("B", "A");
            tests.add(new TestResult("Exceptions 11", "exception expected (no vertex B)", "exception not thrown"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 12", "exception thrown", "exception thrown"));            
        }
        
        g.addVertex("B");
        try{
            g.addEdge("A", "B");
            tests.add(new TestResult("Exceptions 13", "no exception thrown", "no exception thrown"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 14", "exception not expected", "exception thrown"));            
        }

    }
}