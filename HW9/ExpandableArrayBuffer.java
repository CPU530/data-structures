//import java.util.ArrayList;
//import java.util.List;
/**
 *
 * @author Robert McVey
 * this program creates and maintains a class that is supposed to function in a manner similar to a circular buffer
 */

public class ExpandableArrayBuffer implements ExpandableBuffer {
    private String[] buffer;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ExpandableArrayBuffer() {
        // Constructs a buffer with the default capacity.
        this.buffer = new String[ExpandableBuffer.DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = 0;
        this.capacity = ExpandableBuffer.DEFAULT_CAPACITY;
    }

    public ExpandableArrayBuffer(int initialCapacity) {
        // Constructs a buffer with the given capacity.
        this.buffer = new String[initialCapacity];
        this.front = 0;
        this.rear = 0;
        this.capacity = initialCapacity;
    }

    // Add an element to the buffer
    public void add(String s) {
        if (isFull()) {
            doubleCapacity();
        }
        buffer[rear] = s;
        rear = (rear + 1) % capacity;
        size++;
    }

    // Add an element to the rear of the buffer
    public void addRear(String s) {
        add(s);
    }

    // Add an element to the front of the buffer
    public void addFront(String s) {
        if (isFull()) {
            doubleCapacity();
        }
        front = (front - 1 + capacity) % capacity;
        buffer[front] = s;
        size++;
    }

    // Remove and return the front element from the buffer
    public String remove() throws EmptyBufferException {
        if (isEmpty()) {
            throw new EmptyBufferException("Buffer is empty");
        }
        String removed = buffer[front];
        front = (front + 1) % capacity;
        size--;
        return removed;
    }

    // Remove and return the rear element from the buffer
    public String removeRear() throws EmptyBufferException {
        if (isEmpty()) {
            throw new EmptyBufferException("Buffer is empty");
        }
        rear = (rear - 1 + capacity) % capacity;
        String removed = buffer[rear];
        size--;
        return removed;
    }

    // Remove and return the front element from the buffer
    public String removeFront() throws EmptyBufferException {
        return remove();
    }

    // Return the front element without removing it
    public String front() throws EmptyBufferException {
        if (isEmpty()) {
            throw new EmptyBufferException("Buffer is empty");
        }
        return buffer[front];
    }

    // Return the rear element without removing it
    public String rear() throws EmptyBufferException {
        if (isEmpty()) {
            throw new EmptyBufferException("Buffer is empty");
        }
        return buffer[(rear - 1 + capacity) % capacity];
    }

    // Return the total capacity of the buffer
    public int capacity() {
        return capacity;
    }

    // Return the current size of the buffer
    public int size() {
        return size;
    }

    // Return the number of free slots in the buffer
    public int free() {
        return capacity - size;
    }

    // Check if the buffer is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the buffer is full
    public boolean isFull() {
        return size == capacity;
    }

    // Clear the buffer
    public void clear() {
        front = 0;
        rear = 0;
        size = 0;
    }

    // Double the capacity of the buffer
    public void doubleCapacity() {
        String[] newBuffer = new String[capacity * 2];
        for (int i = 0; i < size; i++) {
            newBuffer[i] = buffer[(front + i) % capacity];
        }
        buffer = newBuffer;
        front = 0;
        rear = size;
        capacity *= 2;
    }

    // Return a string representation of the buffer
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(buffer[(front + i) % capacity]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}



