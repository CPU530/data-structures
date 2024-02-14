/**
 *
 * @author Robert McVey
 * this program fail to create a single linked list of snodes that contain strings as data
 */


public class SLinked_ListOfString implements ListOfString_Interface {

    private String[] elements;
    private int size;
    private String tail;
    private String head;

    public SLinked_ListOfString() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(int i, String str) throws IndexOutOfBoundsException {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }

        
        for (int j = size; j > i; j--) {
            elements[j] = elements[j - 1];
        }

        elements[i] = str;
        size++;
    }


    @Override
    public void addFirst(String str) {
        add(0, str);
    }

    @Override
    public void addLast(String str) {
        add(size, str);
    }

    @Override
    public void add(String str) {
        add(size, str);
    }

    @Override
    public String remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
    
        String removedElement = elements[i]; 
    
        for (int j = i; j < size - 1; j++) {
            elements[j] = elements[j + 1];
        }
    
        elements[size - 1] = null; 
        size--; 
        return removedElement;
    }
    

    @Override
    public String removeFirst() throws IllegalStateException {
    if (size > 0) {
        remove(0); 
        }
    return null;
    
    }

    public String removeLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }

        String removedElement = elements[size - 1]; 
        elements[size - 1] = null; 
        size--; 
        return removedElement;
    }

    @Override
    public String remove(String str)throws IndexOutOfBoundsException {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(str)) {
                String removedElement = elements[i];
                remove(i); 
                return removedElement;
            }
        }
        return null; 
    }

    @Override
    public String get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
       
        return this.get(i); 
    }

    @Override
    public String getFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
       
        return this.get(0);
    }

    @Override
    public String getLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        
        return this.get(this.size() - 1); 
    }

    @Override
    public String set(int i, String str) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        String oldElement = this.get(i); 
        this.set(i, str); 
        return oldElement;
    }

    @Override
    public String setFirst(String str) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
       
        String oldElement = this.get(0); 
        this.set(0, str); 
        return oldElement;
    }

    @Override
    public String setLast(String str) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        
        int lastIndex = this.size() - 1;
        String oldElement = this.get(lastIndex); 
        this.set(lastIndex, str);
        return oldElement;
    }
    @Override
    public boolean isEmpty() {
        return head == null;
    }







/*
 * im completely lost 
 */











    @Override
    public boolean contains(String str) {
        SNode_Stringe.current = head;
        while (current != null) {
    if (current.data.equals(str)) {
            return true;
        }
        current = current.next;
    }
        return false;
    }
    
    @Override
    public int firstIndexOf(String str) {
        SNode_Stringe.current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(str)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
    @Override
       public int lastIndexOf(String str) {
        SNode_Stringe.current = head;
        int lastIndex = -1; 
        int index = 0;

        while (current != null) {
            if (current.data.equals(str)) {
                lastIndex = index; 
            }
            current = current.next;
            index++;
        }

        return lastIndex;
    }


    @Override
    public int size() {
        int count = 0;
        SNode_Stringe.current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public void clear() {
        head = null;
    }
}
