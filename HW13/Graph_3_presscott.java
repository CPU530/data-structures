
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Robert McVey
 * so this is my tester that im using to return what the objects to see if they are returning correctly 
 */
public class Graph_3_presscott {

    public static void main(String[] args) {
    StringGraph g = new StringGraph();
    String[] vertices = new String[]{"A", "B", "C", "D", "P", "V"};
    String[][] edges = {{"A", "C"}, {"C", "D"}, {"C", "B"}, {"D", "A"}, {"D", "P"}, {"P", "V"}};
    g.addVertices(vertices);
    g.addEdges(edges);
    System.out.println(Arrays.deepToString(g.getNeighbors("A")));
    System.out.println(Arrays.deepToString(g.getNeighbors("P")));
    System.out.println(Arrays.deepToString(g.getNeighbors("C")));
    System.out.println(Arrays.deepToString(g.getNeighbors("V")));
    
    
    System.out.println(Arrays.deepToString(g.bfsOrder("A")));
    System.out.println((g.bfsTree("A")));
    System.out.println(Arrays.deepToString(g.dfsOrder("A")));
    System.out.println((g.dfsTree("A")));
    

    }
}