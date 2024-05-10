/**
 *
 * @author Robert McVey
 * this program creates and maintains a gerneric heap class  
 */


import java.util.Comparator;

public class Heap<E> implements HeapInterface<E> {
    private Comparator<E> comparator;
    private E[] array;
    private int size;
    private static final int INITIAL_SIZE = 10; // You can adjust this as needed

    public Heap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.array = (E[]) new Object[INITIAL_SIZE];
        this.size = 0;
    }

    // adds elements to the heap & resizes when needed & increments the size
    @Override
    public void add(E element) {
        if (size == array.length) {
            // Resizes the array if needed
            resizeArray();
        }
        array[size] = element;
        heapifyUp(size);
        size++;
    }


    // removes elements from heap and deincrements the size
    @Override
    public E remove() throws EmptyHeapException {
        if (isEmpty()) {
            throw new EmptyHeapException("Heap is empty");
        }
        E root = array[0];
        array[0] = array[size - 1];
        size--;
        heapifyDown(0);
        return root;
    }

    // returns the size of the heap
    @Override
    public int size() {
        return size;
    }

    // returns true if the size of the heap is 0
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // returns the comparator
    @Override
    public Comparator<E> comparator() {
        return comparator;
    }

    /// the below links were referenced for the creation of the methods below
    // Please note Nothing was directly coppied
    // I find that i heavily relied on this for my own understanding
    // please remove points accordingly if required
    // https://www.geeksforgeeks.org/difference-between-min-heap-and-max-heap/

    // https://www.geeksforgeeks.org/min-heap-in-java/
    private void heapifyUp(int index) {
        int parent_Index = (index - 1) / 2;
        while (index > 0 && comparator.compare(array[parent_Index], array[index]) > 0) {
            swap(parent_Index, index);
            index = parent_Index;
            parent_Index = (index - 1) / 2;
        }
    }

    // https://www.geeksforgeeks.org/max-heap-in-java/
    private void heapifyDown(int index) {
        int left_Child_Index = 2 * index + 1;
        int right_Child_Index = 2 * index + 2;
        int smallest_Index = index;

        if (left_Child_Index < size && comparator.compare(array[left_Child_Index], array[smallest_Index]) < 0) {
            smallest_Index = left_Child_Index;
        }
        if (right_Child_Index < size && comparator.compare(array[right_Child_Index], array[smallest_Index]) < 0) {
            smallest_Index = right_Child_Index;
        }

        if (smallest_Index != index) {
            swap(index, smallest_Index);
            heapifyDown(smallest_Index);
        }
    }

    // resizes the array based on needs
    private void resizeArray() {
        E[] newArray = (E[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    // swaps two elemetns of an array when needed
    private void swap(int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
