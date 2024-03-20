/**
 * A circular buffer of Strings implemented with an array. When an element is
 * added to a full buffer, the array is increased so that the capacity of the
 * buffer is doubled.
 * 
 * @author Kevin Lillis
 */
public interface ExpandableBuffer {
    
    /**
     * The initial capacity of a buffer created with the default constructor.
     */
    static final int DEFAULT_CAPACITY = 3;

    /**
     * Adds the specified string to the rear of this buffer.
     * @param s string to be added to this buffer
     */
    void add(String s);
    
    /**
     * Adds the specified string to the rear of this buffer.
     * @param s string to be added to this buffer
     */
    void addRear(String s);
    
    /**
     * Adds the specified string to the front of this buffer.
     * @param s string to be added to this buffer
     */
    void addFront(String s);

    /**
     * Removes and returns the string at the front of this buffer.
     * @return the string at the front of this buffer
     * @throws EmptyBufferException if the buffer is empty
     */
    String remove() throws EmptyBufferException;

    /**
     * Removes and returns the string at the rear of this buffer.
     * @return the string at the rear of this buffer
     * @throws EmptyBufferException if the buffer is empty
     */
    String removeRear() throws EmptyBufferException;
    
    /**
     * Removes and returns the string at the front of this buffer.
     * @return the string at the front of this buffer
     * @throws EmptyBufferException if the buffer is empty
     */
    String removeFront() throws EmptyBufferException;
    
    /**
     * Returns but does not remove the string at the front of this buffer.
     * @return the string at the front of this buffer.
     * @throws EmptyBufferException if the buffer is empty
     */
    String front() throws EmptyBufferException;
    
    /**
     * Returns but does not remove the string at the rear of this buffer.
     * @return the string at the rear of this buffer.
     * @throws EmptyBufferException if the buffer is empty
     */
    String rear() throws EmptyBufferException;
    
    /**
     * The total number of elements that this buffer can hold without needing to
     * be resized.
     * @return the number of elements that this buffer can hold without needing 
     * to be resized
     */
    int capacity();
    
    /**
     * Returns the number of elements in this buffer.
     * @return the number of elements in this buffer
     */
    int size();
    
    /**
     * Returns the number of elements that can be added to this buffer before
     * before it becomes full.
     * @return the number of elements that can be added to this buffer before it
     * becomes full
     */
    int free();
    
    /**
     * Returns true if this buffer contains no elements, returns false otherwise.
     * @return true if this buffer contains no elements, returns false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns true if this buffer is full, returns false otherwise.
     * @return true if this buffer is full, returns false otherwise.
     */
    boolean isFull();
    
    /**
     * Removes all elements from this buffer. If the buffer is empty, nothing
     * is done.
     */
    void clear();
    
    /**
     * Doubles the capacity of this buffer.
     */
    void doubleCapacity();
    
    /**
     * Returns a string representation of this buffer. The string representation
     * consists of a list of this buffer's Strings in order from front to rear,
     * enclosed in square brackets ("[]"). Adjacent Strings are
     * separated by the characters ", " (comma and space). The letter
     * "F" should appear to the left to indicate the front of the buffer and the
     * letter "R" should appear to the right to indicate the rear of the
     * buffer. Fore example, a buffer containing "A", "B", and "C" would be
     * represented as "F[A, B, C]R".
     *
     * @return a string representation of this buffer
     */    
    @Override
    String toString();
}
