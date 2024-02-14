public class SNode_String {
    protected String data;
    protected SNode_String next;

    public SNode_String(String data, SNode_String next) {
        this.data = data;
        this.next = next;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public SNode_String getNext() {
        return next;
    }

    public void setNext(SNode_String next) {
        this.next = next;
    }
}