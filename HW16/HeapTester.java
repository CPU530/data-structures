
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class HeapTester {
    private static int failCount = 0;
    private static int testCount = 0;
    private static ArrayList<Integer> result;
    private static HeapInterface<Integer> heap;
    private static Random generator = new Random();
    
    public static void main(String[] args) {
        runPermutationsUpTo4();
        runRandomInputs(30, 5, 15);
        runTest(new ArrayList<>(Arrays.asList(4, 3, 2, 0, 4, 2)));
        
        System.out.println(testCount + " tests were executed");
        if(failCount == 0){
            System.out.println("All Passed");
        } else {
            System.out.println(failCount + " Failed");
        }
    }
    
    private static void runTest(ArrayList<Integer> inputs){
        testCount++;
        heap = new Heap<>(new IntegerComparator());
        result = new ArrayList<>();
        for(Integer i : inputs){
            heap.add(i);
        }
        
        while(!heap.isEmpty()){
            result.add(heap.remove());
        }
        
        ArrayList<Integer> solution = (ArrayList<Integer>) inputs.clone();
        solution.sort(new IntegerComparator());
        
        if(!result.equals(solution)){
            System.out.println("Failed on input " + inputs + ".  Output was " + result);
            failCount++;
        }
    }
    
    private static void runPermutationsUpTo4(){
        // Nothing added to heap
        runTest(new ArrayList<>(Arrays.asList()));

        // One number added to heap
        runTest(new ArrayList<>(Arrays.asList(1)));
        
        // Two numbers added to heap
        runTest(new ArrayList<>(Arrays.asList(1,2)));
        runTest(new ArrayList<>(Arrays.asList(2,1)));
        
        // Three numbers added to heap
        runTest(new ArrayList<>(Arrays.asList(1,2,3)));
        runTest(new ArrayList<>(Arrays.asList(1,3,2)));
        runTest(new ArrayList<>(Arrays.asList(2,1,3)));
        runTest(new ArrayList<>(Arrays.asList(2,3,1)));
        runTest(new ArrayList<>(Arrays.asList(3,1,2)));
        runTest(new ArrayList<>(Arrays.asList(3,2,1)));
        
        // Four numbers added to heap
        runTest(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        runTest(new ArrayList<>(Arrays.asList(1, 2, 4, 3)));
        runTest(new ArrayList<>(Arrays.asList(1, 3, 2, 4)));
        runTest(new ArrayList<>(Arrays.asList(1, 3, 4, 2)));
        runTest(new ArrayList<>(Arrays.asList(1, 4, 2, 3)));
        runTest(new ArrayList<>(Arrays.asList(1, 4, 3, 2)));
        runTest(new ArrayList<>(Arrays.asList(2, 1, 3, 4)));
        runTest(new ArrayList<>(Arrays.asList(2, 1, 4, 3)));
        runTest(new ArrayList<>(Arrays.asList(2, 3, 1, 4)));
        runTest(new ArrayList<>(Arrays.asList(2, 3, 4, 1)));
        runTest(new ArrayList<>(Arrays.asList(2, 4, 1, 3)));
        runTest(new ArrayList<>(Arrays.asList(2, 4, 3, 1)));
        runTest(new ArrayList<>(Arrays.asList(3, 1, 2, 4)));
        runTest(new ArrayList<>(Arrays.asList(3, 1, 4, 2)));
        runTest(new ArrayList<>(Arrays.asList(3, 2, 1, 4)));
        runTest(new ArrayList<>(Arrays.asList(3, 2, 4, 1)));
        runTest(new ArrayList<>(Arrays.asList(3, 4, 1, 2)));
        runTest(new ArrayList<>(Arrays.asList(3, 4, 2, 1)));
        runTest(new ArrayList<>(Arrays.asList(4, 1, 2, 3)));
        runTest(new ArrayList<>(Arrays.asList(4, 1, 3, 2)));
        runTest(new ArrayList<>(Arrays.asList(4, 2, 1, 3)));
        runTest(new ArrayList<>(Arrays.asList(4, 2, 3, 1)));
        runTest(new ArrayList<>(Arrays.asList(4, 3, 1, 2)));
        runTest(new ArrayList<>(Arrays.asList(4, 3, 2, 1)));   
    }
    
    /**
     * For each size, minSize <= size <= maxSize, run numRuns tests on random 
     * lists of that size. The list is filled with numbers in the range 
     * from 0 to 2/3 * size.
     */
    private static void runRandomInputs(int numRuns, int minSize, int maxSize){
        for(int size = minSize; size <= maxSize; size++){
            int maxValue = (int)(2.0*size/3.0);
            for(int run = 0; run < numRuns; run++){
                ArrayList<Integer> numbers = new ArrayList<>(size);
                
                // fill the array list
                for(int i = 0; i < size; i++){
                    numbers.add(generator.nextInt(maxValue));
                }
                runTest(numbers);
            }
         }
    }

}

class IntegerComparator implements Comparator<Integer>{

    @Override
    public int compare(Integer x, Integer y) {
        return x - y;
    }
}