
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dr. Lillis
 */
public class Graph_3_Tester {

    static ArrayList<TestResult> tests = new ArrayList<>();
    static String expected, result;
    static Graph g;

    public static void main(String[] args) {
        BFSOrderTests();
        DFSOrderTests();
        DFSTreeTests();
        BFSTreeTests();
        isConnectedTests();

        TestResult.reportTestResults(tests);
    }

    static void DFSTreeTests() {
        Graph g1, expectedG, resultG;
        // DFS Tree Test 1
        g1 = GraphUtils.BFS_DFS_Tree_Test1();
        
        expectedG = GraphUtils.DFS_Tree_Test1_SOL();
        resultG = g1.dfsTree("G");

        expected = "Graphs are equal";
        result = "Graphs are " + (GraphUtils.equals(expectedG, resultG) ? "" : "not ") + "equal";
        tests.add(new TestResult("DFS Tree Test 1", expected, result));
        
        // DFS Tree Test 2
        g1 = GraphUtils.BFS_DFS_Tree_Test2();
        
        expectedG = GraphUtils.DFS_Tree_Test2_SOL();
        resultG = g1.dfsTree("G");

        expected = "Graphs are equal";
        result = "Graphs are " + (GraphUtils.equals(expectedG, resultG) ? "" : "not ") + "equal";
        tests.add(new TestResult("DFS Tree Test 2", expected, result));


        // DFS Tree Test 3
        g1 = GraphUtils.BFS_DFS_Tree_Test3();
        
        expectedG = GraphUtils.DFS_Tree_Test3_SOL();
        resultG = g1.dfsTree("B");

        expected = "Graphs are equal";
        result = "Graphs are " + (GraphUtils.equals(expectedG, resultG) ? "" : "not ") + "equal";
        tests.add(new TestResult("DFS Tree Test 3", expected, result));


    }


    static void BFSTreeTests() {
        Graph g1, expectedG, resultG;
        // BFS Tree Test 1
        g1 = GraphUtils.BFS_DFS_Tree_Test1();
        
        expectedG = GraphUtils.BFS_Tree_Test1_SOL();
        resultG = g1.bfsTree("G");

        expected = "Graphs are equal";
        result = "Graphs are " + (GraphUtils.equals(expectedG, resultG) ? "" : "not ") + "equal";
        tests.add(new TestResult("BFS Tree Test 1", expected, result));
        
        // BFS Tree Test 2
        g1 = GraphUtils.BFS_DFS_Tree_Test2();
        
        expectedG = GraphUtils.BFS_Tree_Test2_SOL();
        resultG = g1.bfsTree("G");

        expected = "Graphs are equal";
        result = "Graphs are " + (GraphUtils.equals(expectedG, resultG) ? "" : "not ") + "equal";
        tests.add(new TestResult("BFS Tree Test 2", expected, result));


        // BFS Tree Test 3
        g1 = GraphUtils.BFS_DFS_Tree_Test3();
        
        expectedG = GraphUtils.BFS_Tree_Test3_SOL();
        resultG = g1.bfsTree("B");

        expected = "Graphs are equal";
        result = "Graphs are " + (GraphUtils.equals(expectedG, resultG) ? "" : "not ") + "equal";
        tests.add(new TestResult("BFS Tree Test 3", expected, result));


    }


    static void BFSOrderTests() {
        g = GraphUtils.star6();
        expected = "[A, B, C, D, E, F]";
        result = Arrays.toString(g.bfsOrder("A"));
        tests.add(new TestResult("Star - BFS Order from A", expected, result));

        expected = "[F, A, B, C, D, E]";
        result = Arrays.toString(g.bfsOrder("F"));
        tests.add(new TestResult("Star - BFS Order from F", expected, result));

        g = GraphUtils.wheel6();
        expected = "[A, B, C, D, E, F]";
        result = Arrays.toString(g.bfsOrder("A"));
        tests.add(new TestResult("Wheel - BFS Order from A", expected, result));

        expected = "[F, A, B, E, C, D]";
        result = Arrays.toString(g.bfsOrder("F"));
        tests.add(new TestResult("Wheel - BFS Order from F", expected, result));

        g = GraphUtils.connected10();
        expected = "[G, C, H, A, B, D, I, J, E, F]";
        result = Arrays.toString(g.bfsOrder("G"));
        tests.add(new TestResult("Connected - BFS Order from G", expected, result));

        g = GraphUtils.disconnected10();
        expected = "[A, B, C, D, E, F]";
        result = Arrays.toString(g.bfsOrder("A"));
        tests.add(new TestResult("2 Components - BFS Order from A", expected, result));

        expected = "[I, H, J, G]";
        result = Arrays.toString(g.bfsOrder("I"));
        tests.add(new TestResult("2 Components - BFS Order from I", expected, result));

        g = GraphUtils.isolated5();
        expected = "[C]";
        result = Arrays.toString(g.bfsOrder("C"));
        tests.add(new TestResult("5 Isolated - BFS Order from C", expected, result));

        g = GraphUtils.isolated1();
        expected = "[A]";
        result = Arrays.toString(g.bfsOrder("A"));
        tests.add(new TestResult("1 Isolated - BFS Order from A", expected, result));
    }

    static void DFSOrderTests() {
        g = GraphUtils.star6();
        expected = "[A, B, C, D, E, F]";
        result = Arrays.toString(g.dfsOrder("A"));
        tests.add(new TestResult("Star - DFS Order from A", expected, result));

        expected = "[F, A, B, C, D, E]";
        result = Arrays.toString(g.dfsOrder("F"));
        tests.add(new TestResult("Star - DFS Order from F", expected, result));

        g = GraphUtils.wheel6();
        expected = "[A, B, C, D, E, F]";
        result = Arrays.toString(g.dfsOrder("A"));
        tests.add(new TestResult("Wheel - DFS Order from A", expected, result));

        expected = "[F, A, B, C, D, E]";
        result = Arrays.toString(g.dfsOrder("F"));
        tests.add(new TestResult("Wheel - DFS Order from F", expected, result));

        g = GraphUtils.connected10();
        expected = "[G, C, A, B, F, E, D, H, I, J]";
        result = Arrays.toString(g.dfsOrder("G"));
        tests.add(new TestResult("Connected - DFS Order from G", expected, result));

        g = GraphUtils.disconnected10();
        expected = "[A, B, C, D, E, F]";
        result = Arrays.toString(g.dfsOrder("A"));
        tests.add(new TestResult("2 Components - DFS Order from A", expected, result));

        expected = "[I, H, G, J]";
        result = Arrays.toString(g.dfsOrder("I"));
        tests.add(new TestResult("2 Components - DFS Order from I", expected, result));

        g = GraphUtils.isolated5();
        expected = "[C]";
        result = Arrays.toString(g.dfsOrder("C"));
        tests.add(new TestResult("5 Isolated - DFS Order from C", expected, result));

        g = GraphUtils.isolated1();
        expected = "[A]";
        result = Arrays.toString(g.dfsOrder("A"));
        tests.add(new TestResult("1 Isolated - DFS Order from A", expected, result));
    }

    static void isConnectedTests() {
        g = GraphUtils.star6();
        expected = "" + true;
        result = "" + g.isConnected();
        tests.add(new TestResult("Star - Is Connected", expected, result));

        g = GraphUtils.wheel6();
        expected = "" + true;
        result = "" + g.isConnected();
        tests.add(new TestResult("Wheel - Is Connected", expected, result));

        g = GraphUtils.connected10();
        expected = "" + true;
        result = "" + g.isConnected();
        tests.add(new TestResult("Connected - Is Connected", expected, result));

        g = GraphUtils.disconnected10();
        expected = "" + false;
        result = "" + g.isConnected();
        tests.add(new TestResult("2 Components - Is Connected", expected, result));

        g = GraphUtils.isolated5();
        expected = "" + false;
        result = "" + g.isConnected();
        tests.add(new TestResult("5 Isolated - Is Connected", expected, result));

        g = GraphUtils.isolated1();
        expected = "" + true;
        result = "" + g.isConnected();
        tests.add(new TestResult("1 Isolated - Is Connected", expected, result));
    }
}
