/**
 *
 * @author Robert McVey
 * this file establishes a class to be used in the DLinked_StackGeneric class
 */
public class DNode_Generic<E> {
    protected E element; // Data element stored in this node
    protected DNode_Generic<E> next; // Reference to the next node
    protected DNode_Generic<E> prev; // Reference to the previous node

    protected DNode_Generic(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }
}
