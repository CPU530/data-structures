public class SLinked_ListOfString implements ListOfString_Interface {

    private String[] elements;
    private int size;

    public SLinked_ListOfString(int initialCapacity) {
        elements = new String[initialCapacity];
        size = 0;
    }

    @Override
    public void add(int i, String str) throws IndexOutOfBoundsException {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }

        // Shift elements to make space for the new one
        for (int j = size; j > i; j--) {
            elements[j] = elements[j - 1];
        }

        // Add the new element
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
    
        String removedElement = elements[i]; // Store the element to be removed
    
        // Shift elements to fill the gap left by the removed element
        for (int j = i; j < size - 1; j++) {
            elements[j] = elements[j + 1];
        }
    
        elements[size - 1] = null; // Set the last element to null (optional)
    
        size--; // Decrease the size of the list
    
        return removedElement;
    }
    

    @Override
    public String removeFirst() throws IllegalStateException {
    if (size > 0) {
        remove(0); // Remove the first element
        }
            // If the list is empty, no action needed
    return null;
    
    }

    public String removeLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }

        String removedElement = elements[size - 1]; // Get the last element
        elements[size - 1] = null; // Set the last element to null (optional)
        size--; // Decrease the size of the list

        return removedElement;
    }

    @Override
    public String remove(String str)throws IndexOutOfBoundsException {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(str)) {
                String removedElement = elements[i];
                remove(i); // Remove the element at index i
                return removedElement;
            }
        }
        return null; // Element not found
    }

    @Override
    public String get(int i) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public String getFirst() throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFirst'");
    }

    @Override
    public String getLast() throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLast'");
    }

    @Override
    public String set(int i, String str) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public String setFirst(String str) throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFirst'");
    }

    @Override
    public String setLast(String str) throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLast'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public boolean contains(String str) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public int firstIndexOf(String str) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'firstIndexOf'");
    }

    @Override
    public int lastIndexOf(String str) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
}
