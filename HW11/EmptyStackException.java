/**
 *
 * @author Robert McVey
 * this file establishes a Empty Stack Exception to be used in the DLinked_StackGeneric class
 */

public class EmptyStackException extends RuntimeException {
    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String message) {
        super(message);
    }
}
