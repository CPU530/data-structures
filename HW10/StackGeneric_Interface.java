
/**
 * A generic stack.
 *
 * @param <E> Type of elements stored in the stack.
 * @author Kevin Lillis
 */
public interface StackGeneric_Interface<E> {

    /**
     * Adds the given element to the top of the stack.
     *
     * @param element element to be added to the top of this stack
     */
    void push(E element);

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return the element at the top of this stack
     * @throws EmptyStackException if this stack is empty.
     */
    E pop() throws EmptyStackException;

    /**
     * Returns but does not remove the element at the top of this stack.
     *
     * @return the element at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    E peek() throws EmptyStackException;

    /**
     * Returns the number of elements in this stack.
     *
     * @return number of elements in this stack
     */
    int size();

    /**
     * Returns true if this stack is empty, returns false otherwise.
     *
     * @return true if this stack is empty, returns false otherwise
     */
    boolean isEmpty();

    /**
     * Removes all elements from this stack. If the stack is empty, nothing is
     * done.
     */
    void clear();

    /**
     * Returns a string containing each element in this list, separated by
     * commas, enclosed in square brackets. The top of the stack is indicated to
     * the left. For example:
     *
     * TOS [e1, e1, e3]
     *
     * @return a string representation of this list.
     */
    @Override
    String toString();
}
