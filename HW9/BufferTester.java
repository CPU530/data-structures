
import java.util.ArrayList;

/**
 *
 * @author Kevin Lillis
 */

public class BufferTester {
    private static final ArrayList<TestResult> tests = new ArrayList<>();

    public static void main(String[] args) {
        constructorTests();
        addTests();
        removeTests();
        exceptionTests();
        clear_toString_Tests();
        
        TestResult.reportTestResults(tests);
    }
    
    static void constructorTests(){
        tests.add(new TestResult("Constructor 1", 3, (new ExpandableArrayBuffer()).capacity()));
        tests.add(new TestResult("Constructor 2", 42, (new ExpandableArrayBuffer(42)).capacity()));
    }
    
    static void addTests(){
        ExpandableBuffer buffer = new ExpandableArrayBuffer(); // F[]R
        tests.add(new TestResult("Add 1-1, size()", 0, buffer.size()));
        tests.add(new TestResult("Add 1-2, free()", 3, buffer.free()));
        tests.add(new TestResult("Add 1-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Add 1-4, isEmpty()", true, buffer.isEmpty()));
        tests.add(new TestResult("Add 1-5, isFull()", false, buffer.isFull()));

        buffer.add("A"); // F[A]R
        tests.add(new TestResult("Add 2-1, size()", 1, buffer.size()));
        tests.add(new TestResult("Add 2-2, free()", 2, buffer.free()));
        tests.add(new TestResult("Add 2-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Add 2-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 2-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Add 2-6, front()", "A", buffer.front()));
        tests.add(new TestResult("Add 2-7, rear()", "A", buffer.rear()));

        buffer.addRear("B"); // F[A, B]R
        tests.add(new TestResult("Add 3-1, size()", 2, buffer.size()));
        tests.add(new TestResult("Add 3-2, free()", 1, buffer.free()));
        tests.add(new TestResult("Add 3-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Add 3-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 3-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Add 3-6, front()", "A", buffer.front()));
        tests.add(new TestResult("Add 3-7, rear()", "B", buffer.rear()));

        buffer.addFront("C"); // F[C, A, B]R
        tests.add(new TestResult("Add 4-1, size()", 3, buffer.size()));
        tests.add(new TestResult("Add 4-2, free()", 0, buffer.free()));
        tests.add(new TestResult("Add 4-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Add 4-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 4-5, isFull()", true, buffer.isFull()));
        tests.add(new TestResult("Add 4-6, front()", "C", buffer.front()));
        tests.add(new TestResult("Add 4-7, rear()", "B", buffer.rear()));

        buffer.addRear("D"); // F[C, A, B, D]R
        tests.add(new TestResult("Add 5-1, size()", 4, buffer.size()));
        tests.add(new TestResult("Add 5-2, free()", 2, buffer.free()));
        tests.add(new TestResult("Add 5-3, capacity()", 6, buffer.capacity()));
        tests.add(new TestResult("Add 5-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 5-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Add 5-6, front()", "C", buffer.front()));
        tests.add(new TestResult("Add 5-7, rear()", "D", buffer.rear()));
        
        buffer.addRear("E"); // F[C, A, B, D, E]R
        tests.add(new TestResult("Add 6-1, size()", 5, buffer.size()));
        tests.add(new TestResult("Add 6-2, free()", 1, buffer.free()));
        tests.add(new TestResult("Add 6-3, capacity()", 6, buffer.capacity()));
        tests.add(new TestResult("Add 6-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 6-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Add 6-6, front()", "C", buffer.front()));
        tests.add(new TestResult("Add 6-7, rear()", "E", buffer.rear()));

        buffer.addFront("F"); // F[F, C, A, B, D, E]R
        tests.add(new TestResult("Add 7-1, size()", 6, buffer.size()));
        tests.add(new TestResult("Add 7-2, free()", 0, buffer.free()));
        tests.add(new TestResult("Add 7-3, capacity()", 6, buffer.capacity()));
        tests.add(new TestResult("Add 7-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 7-5, isFull()", true, buffer.isFull()));
        tests.add(new TestResult("Add 7-6, front()", "F", buffer.front()));
        tests.add(new TestResult("Add 7-7, rear()", "E", buffer.rear()));

        buffer.addFront("G"); // F[G, F, C, A, B, D, E]R
        tests.add(new TestResult("Add 8-1, size()", 7, buffer.size()));
        tests.add(new TestResult("Add 8-2, free()", 5, buffer.free()));
        tests.add(new TestResult("Add 8-3, capacity()", 12, buffer.capacity()));
        tests.add(new TestResult("Add 8-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 8-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Add 8-6, front()", "G", buffer.front()));
        tests.add(new TestResult("Add 8-7, rear()", "E", buffer.rear()));
        
        // F[F, C, A, B, D, E]R
        tests.add(new TestResult("Add 9-1, removeFront()", "G", buffer.removeFront()));
        tests.add(new TestResult("Add 9-2, size()", 6, buffer.size()));
        tests.add(new TestResult("Add 9-3, free()", 6, buffer.free()));
        tests.add(new TestResult("Add 9-4, capacity()", 12, buffer.capacity()));
        tests.add(new TestResult("Add 9-5, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Add 9-6, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Add 9-7, front()", "F", buffer.front()));
        tests.add(new TestResult("Add 9-8, rear()", "E", buffer.rear()));
        
    }
    
    static void removeTests() throws EmptyBufferException{
        ExpandableBuffer buffer = new ExpandableArrayBuffer(); // F[]R
        tests.add(new TestResult("Remove 1-1, size()", 0, buffer.size()));
        tests.add(new TestResult("Remove 1-2, free()", 3, buffer.free()));
        tests.add(new TestResult("Remove 1-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 1-4, isEmpty()", true, buffer.isEmpty()));
        tests.add(new TestResult("Remove 1-5, isFull()", false, buffer.isFull()));
        
        for(char ch : "ABCDEF".toCharArray()){
            buffer.add("" + ch);
        } // F[A, B, C, D, E, F]R
        
        tests.add(new TestResult("Remove 2-1, front()", "A", buffer.front()));
        tests.add(new TestResult("Remove 2-2, front()", "A", buffer.front()));
        tests.add(new TestResult("Remove 2-3, rear()", "F", buffer.rear()));
        tests.add(new TestResult("Remove 2-4, rear()", "F", buffer.rear()));
        tests.add(new TestResult("Remove 2-5, size()", 6, buffer.size()));
        tests.add(new TestResult("Remove 2-6, free()", 0, buffer.free()));
        tests.add(new TestResult("Remove 2-7, capacity()", 6, buffer.capacity()));
        tests.add(new TestResult("Remove 2-8, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 2-9, isFull()", true, buffer.isFull()));
        
        tests.add(new TestResult("Remove 3-1, remove()", "A", buffer.remove()));
        tests.add(new TestResult("Remove 3-2, front()", "B", buffer.front()));
        tests.add(new TestResult("Remove 3-3, size()", 5, buffer.size()));
        tests.add(new TestResult("Remove 3-4, free()", 1, buffer.free()));
        tests.add(new TestResult("Remove 3-5, capacity()", 6, buffer.capacity()));
        tests.add(new TestResult("Remove 3-6, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 3-7, isFull()", false, buffer.isFull()));
        // F[B, C, D, E, F]R
        
        tests.add(new TestResult("Remove 4-1, removeFront()", "B", buffer.removeFront()));
        tests.add(new TestResult("Remove 4-2, front()", "C", buffer.front()));
        tests.add(new TestResult("Remove 4-3, size()", 4, buffer.size()));
        tests.add(new TestResult("Remove 4-4, free()", 2, buffer.free()));
        tests.add(new TestResult("Remove 4-5, capacity()", 6, buffer.capacity()));
        tests.add(new TestResult("Remove 4-6, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 4-7, isFull()", false, buffer.isFull()));
        // F[C, D, E, F]R

        tests.add(new TestResult("Remove 5-1, removeRear()", "F", buffer.removeRear()));
        tests.add(new TestResult("Remove 5-2, front()", "E", buffer.rear()));
        tests.add(new TestResult("Remove 5-3, size()", 3, buffer.size()));
        tests.add(new TestResult("Remove 5-4, free()", 3, buffer.free()));
        tests.add(new TestResult("Remove 5-5, capacity()", 6, buffer.capacity()));
        tests.add(new TestResult("Remove 5-6, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 5-7, isFull()", false, buffer.isFull()));
        // F[C, D, E]R

        buffer = new ExpandableArrayBuffer(); // F[]R
        tests.add(new TestResult("Remove 6-1, size()", 0, buffer.size()));
        tests.add(new TestResult("Remove 6-2, free()", 3, buffer.free()));
        tests.add(new TestResult("Remove 6-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 6-4, isEmpty()", true, buffer.isEmpty()));
        tests.add(new TestResult("Remove 6-5, isFull()", false, buffer.isFull()));

        buffer.add("A"); // F[A]R
        tests.add(new TestResult("Remove 7-1, size()", 1, buffer.size()));
        tests.add(new TestResult("Remove 7-2, free()", 2, buffer.free()));
        tests.add(new TestResult("Remove 7-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 7-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 7-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Remove 7-6, front()", "A", buffer.front()));
        tests.add(new TestResult("Remove 7-7, rear()", "A", buffer.rear()));

        buffer.add("B"); // F[A, B]R
        tests.add(new TestResult("Remove 8-1, size()", 2, buffer.size()));
        tests.add(new TestResult("Remove 8-2, free()", 1, buffer.free()));
        tests.add(new TestResult("Remove 8-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 8-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 8-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Remove 8-6, front()", "A", buffer.front()));
        tests.add(new TestResult("Remove 8-7, rear()", "B", buffer.rear()));

        // F[B]R
        tests.add(new TestResult("Remove 9-1, remove()", "A", buffer.remove()));
        tests.add(new TestResult("Remove 9-2, size()", 1, buffer.size()));
        tests.add(new TestResult("Remove 9-3, free()", 2, buffer.free()));
        tests.add(new TestResult("Remove 9-4, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 9-5, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 9-6, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Remove 9-7, front()", "B", buffer.front()));
        tests.add(new TestResult("Remove 9-8, rear()", "B", buffer.rear()));

        buffer.add("C"); // F[B, C]R
        tests.add(new TestResult("Remove 10-1, size()", 2, buffer.size()));
        tests.add(new TestResult("Remove 10-2, free()", 1, buffer.free()));
        tests.add(new TestResult("Remove 10-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 10-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 10-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Remove 10-6, front()", "B", buffer.front()));
        tests.add(new TestResult("Remove 10-7, rear()", "C", buffer.rear()));

        // F[C]R
        tests.add(new TestResult("Remove 11-1, remove()", "B", buffer.remove()));
        tests.add(new TestResult("Remove 11-2, size()", 1, buffer.size()));
        tests.add(new TestResult("Remove 11-3, free()", 2, buffer.free()));
        tests.add(new TestResult("Remove 11-4, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 11-5, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 11-6, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Remove 11-7, front()", "C", buffer.front()));
        tests.add(new TestResult("Remove 11-8, rear()", "C", buffer.rear()));

        buffer.add("D"); // F[C, D]R
        tests.add(new TestResult("Remove 12-1, size()", 2, buffer.size()));
        tests.add(new TestResult("Remove 12-2, free()", 1, buffer.free()));
        tests.add(new TestResult("Remove 12-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 12-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 12-5, isFull()", false, buffer.isFull()));
        tests.add(new TestResult("Remove 12-6, front()", "C", buffer.front()));
        tests.add(new TestResult("Remove 12-7, rear()", "D", buffer.rear()));
        
        buffer.add("E"); // F[C, D, E]R
        tests.add(new TestResult("Remove 13-1, size()", 3, buffer.size()));
        tests.add(new TestResult("Remove 13-2, free()", 0, buffer.free()));
        tests.add(new TestResult("Remove 13-3, capacity()", 3, buffer.capacity()));
        tests.add(new TestResult("Remove 13-4, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Remove 13-5, isFull()", true, buffer.isFull()));
        tests.add(new TestResult("Remove 13-6, front()", "C", buffer.front()));
        tests.add(new TestResult("Remove 13-7, rear()", "E", buffer.rear()));
    }
    
    static void exceptionTests(){
        ExpandableBuffer buffer = new ExpandableArrayBuffer();
        
        try{
            buffer.front();
            tests.add(new TestResult("Exceptions 1-1", "Exception expected", "Exception not thrown"));
        } catch (EmptyBufferException e){
            tests.add(new TestResult("Exceptions 1-2", "Exception thrown", "Exception thrown"));            
        }

        try{
            buffer.rear();
            tests.add(new TestResult("Exceptions 2-1", "Exception expected", "Exception not thrown"));
        } catch (EmptyBufferException e){
            tests.add(new TestResult("Exceptions 2-2", "Exception thrown", "Exception thrown"));            
        }

        try{
            buffer.remove();
            tests.add(new TestResult("Exceptions 3-1", "Exception expected", "Exception not thrown"));
        } catch (EmptyBufferException e){
            tests.add(new TestResult("Exceptions 3-2", "Exception thrown", "Exception thrown"));            
        }

        try{
            buffer.removeRear();
            tests.add(new TestResult("Exceptions 4-1", "Exception expected", "Exception not thrown"));
        } catch (EmptyBufferException e){
            tests.add(new TestResult("Exceptions 4-2", "Exception thrown", "Exception thrown"));            
        }

        try{
            buffer.removeFront();
            tests.add(new TestResult("Exceptions 5-1", "Exception expected", "Exception not thrown"));
        } catch (EmptyBufferException e){
            tests.add(new TestResult("Exceptions 5-2", "Exception thrown", "Exception thrown"));            
        }

    }
    
    static void clear_toString_Tests(){
        ExpandableBuffer buffer = new ExpandableArrayBuffer(); // F[]R
        tests.add(new TestResult("To String 1-1, toString()", "F[]R", buffer.toString()));
        
        // F[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]R
        for(char ch : "ABCDEFGHIJKLMNOP".toCharArray()){
            buffer.add("" + ch);
        }

        tests.add(new TestResult("To String 1-2, toString()", 
                "F[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]R", buffer.toString()));
        
        tests.add(new TestResult("Clear 1-1, front()", "A", buffer.front()));
        tests.add(new TestResult("Clear 1-2, rear()", "P", buffer.rear()));
        tests.add(new TestResult("Clear 1-3, size()", 16, buffer.size()));
        tests.add(new TestResult("Clear 1-4, free()", 8, buffer.free()));
        tests.add(new TestResult("Clear 1-5, capacity()", 24, buffer.capacity()));
        tests.add(new TestResult("Clear 1-6, isEmpty()", false, buffer.isEmpty()));
        tests.add(new TestResult("Clear 1-7, isFull()", false, buffer.isFull()));
        
        buffer.clear();  // F[]R
        tests.add(new TestResult("Clear 2-3, size()", 0, buffer.size()));
        tests.add(new TestResult("Clear 2-4, free()", 24, buffer.free()));
        tests.add(new TestResult("Clear 2-5, capacity()", 24, buffer.capacity()));
        tests.add(new TestResult("Clear 2-6, isEmpty()", true, buffer.isEmpty()));
        tests.add(new TestResult("Clear 2-7, isFull()", false, buffer.isFull()));

        buffer.clear();  // F[]R
        tests.add(new TestResult("Clear 3-3, size()", 0, buffer.size()));
        tests.add(new TestResult("Clear 3-4, free()", 24, buffer.free()));
        tests.add(new TestResult("Clear 3-5, capacity()", 24, buffer.capacity()));
        tests.add(new TestResult("Clear 3-6, isEmpty()", true, buffer.isEmpty()));
        tests.add(new TestResult("Clear 3-7, isFull()", false, buffer.isFull()));
    }
}