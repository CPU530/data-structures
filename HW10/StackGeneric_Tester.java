
import java.util.ArrayList;

public class StackGeneric_Tester {

    private static final ArrayList<TestResult> tests = new ArrayList<>();

    /*  This tester tests the methods in StackGeneric_Interface. It can be used
        for any implementation of that interface. To use it for other
        implementations, the name of the concrete implementing class needs to be
        changed in two places:
            1) In the makeNewStack() method (1 change required).
            2) In the runConstructorTests() method (2 changes required).
    */
    
    
    public static void main(String[] args) {
        String className = makeNewStack().getClass().getName();
        System.out.println("Testing " + className);

        // Test the constructors
        runConstructorTests();

        // Test methods required by ListOfString_Interface
        runPushPopTests(makeNewStack());
        runClearTests(makeNewStack());
        runExceptionTests(makeNewStack());

        // Display test reuslts
        TestResult.reportTestResults(tests);
    }

    public static StackGeneric_Interface<String> makeNewStack(){
        return new DLinked_StackGeneric();
    }
    
    
    public static void runConstructorTests() {
        DLinked_StackGeneric<String> stack = new DLinked_StackGeneric();

        tests.add(new TestResult("Constructor 1-1, toString()", "TOS []", stack.toString()));
        tests.add(new TestResult("Constructor 1-2, size()", 0, stack.size()));
        tests.add(new TestResult("Constructor 1-3, isEmpty()", true, stack.isEmpty()));
    }

    // =========================================================================
    // The concrete class is not mentioned in the rest of this tester
    public static void runPushPopTests(StackGeneric_Interface<String> stack) {
        // TOS []
        tests.add(new TestResult("Push 1-2, size()", 0, stack.size()));
        tests.add(new TestResult("Push 1-3, isEmpty()", true, stack.isEmpty()));
        tests.add(new TestResult("Push 1-4, toString()", "TOS []", stack.toString()));

        // TOS [A]
        stack.push("A");
        tests.add(new TestResult("Push 2-1, peek()", "A", stack.peek()));
        tests.add(new TestResult("Push 2-2, size()", 1, stack.size()));
        tests.add(new TestResult("Push 2-3, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Push 2-4, toString()", "TOS [A]", stack.toString()));
        
        // TOS [B, A]
        stack.push("B");
        tests.add(new TestResult("Push 3-1, peek()", "B", stack.peek()));
        tests.add(new TestResult("Push 3-2, size()", 2, stack.size()));
        tests.add(new TestResult("Push 3-3, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Push 3-4, toString()", "TOS [B, A]", stack.toString()));

        // TOS [C, B, A]
        stack.push("C");
        tests.add(new TestResult("Push 4-1, peek()", "C", stack.peek()));
        tests.add(new TestResult("Push 4-2, size()", 3, stack.size()));
        tests.add(new TestResult("Push 4-3, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Push 4-4, toString()", "TOS [C, B, A]", stack.toString()));

        // TOS [B, A]
        tests.add(new TestResult("Pop 1-1, pop()", "C", stack.pop()));
        tests.add(new TestResult("Pop 1-2, peek()", "B", stack.peek()));
        tests.add(new TestResult("Pop 1-3, size()", 2, stack.size()));
        tests.add(new TestResult("Pop 1-4, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Pop 1-5, toString()", "TOS [B, A]", stack.toString()));

        // TOS [A]
        tests.add(new TestResult("Pop 2-1, pop()", "B", stack.pop()));
        tests.add(new TestResult("Pop 2-2, peek()", "A", stack.peek()));
        tests.add(new TestResult("Pop 2-3, size()", 1, stack.size()));
        tests.add(new TestResult("Pop 2-4, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Pop 2-5, toString()", "TOS [A]", stack.toString()));

        // TOS []
        tests.add(new TestResult("Pop 3-1, pop()", "A", stack.pop()));
        tests.add(new TestResult("Pop 3-2, size()", 0, stack.size()));
        tests.add(new TestResult("Pop 3-3, isEmpty()", true, stack.isEmpty()));
        tests.add(new TestResult("Pop 3-4, toString()", "TOS []", stack.toString()));
    }

    public static void runClearTests(StackGeneric_Interface<String> stack) {
        // TOS []
        tests.add(new TestResult("Clear 1-2, size()", 0, stack.size()));
        tests.add(new TestResult("Clear 1-3, isEmpty()", true, stack.isEmpty()));
        tests.add(new TestResult("Clear 1-4, toString()", "TOS []", stack.toString()));

        // TOS [A]
        stack.push("A");
        tests.add(new TestResult("Clear 2-1, peek()", "A", stack.peek()));
        tests.add(new TestResult("Clear 2-2, size()", 1, stack.size()));
        tests.add(new TestResult("Clear 2-3, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Clear 2-4, toString()", "TOS [A]", stack.toString()));
        
        // TOS [B, A]
        stack.push("B");
        tests.add(new TestResult("Clear 3-1, peek()", "B", stack.peek()));
        tests.add(new TestResult("Clear 3-2, size()", 2, stack.size()));
        tests.add(new TestResult("Clear 3-3, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Clear 3-4, toString()", "TOS [B, A]", stack.toString()));

        // TOS [C, B, A]
        stack.push("C");
        tests.add(new TestResult("Clear 4-1, peek()", "C", stack.peek()));
        tests.add(new TestResult("Clear 4-2, size()", 3, stack.size()));
        tests.add(new TestResult("Clear 4-3, isEmpty()", false, stack.isEmpty()));
        tests.add(new TestResult("Clear 4-4, toString()", "TOS [C, B, A]", stack.toString()));
        
        stack.clear();
        tests.add(new TestResult("Clear 5-2, size()", 0, stack.size()));
        tests.add(new TestResult("Clear 5-3, isEmpty()", true, stack.isEmpty()));
        tests.add(new TestResult("Clear 5-4, toString()", "TOS []", stack.toString()));        

        // Run clear() on an empty stack
        stack.clear();
        tests.add(new TestResult("Clear 6-2, size()", 0, stack.size()));
        tests.add(new TestResult("Clear 6-3, isEmpty()", true, stack.isEmpty()));
        tests.add(new TestResult("Clear 6-4, toString()", "TOS []", stack.toString()));        
    }


    public static void runExceptionTests(StackGeneric_Interface<String> stack) {
        try{
            stack.peek();
            tests.add(new TestResult("Exceptions 1-1", "Exception expected", "Exception not thrown"));
        } catch (EmptyStackException e){
            tests.add(new TestResult("Exceptions 1-2", "Exception thrown", "Exception thrown"));            
        }

        try{
            stack.pop();
            tests.add(new TestResult("Exceptions 2-1", "Exception expected", "Exception not thrown"));
        } catch (EmptyStackException e){
            tests.add(new TestResult("Exceptions 2-2", "Exception thrown", "Exception thrown"));            
        }
        
        stack.push("A");
        try{
            stack.peek();
            tests.add(new TestResult("Exceptions 1-1", "No exception", "No exception"));
        } catch (EmptyStackException e){
            tests.add(new TestResult("Exceptions 1-2", "Exception not expected", "Exception thrown"));            
        }
        
        try{
            stack.pop();
            tests.add(new TestResult("Exceptions 1-1", "No exception", "No exception"));
        } catch (EmptyStackException e){
            tests.add(new TestResult("Exceptions 1-2", "Exception not expected", "Exception thrown"));            
        }

    }
}