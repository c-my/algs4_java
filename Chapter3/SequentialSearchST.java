/**
 * SequentialSearchST
 */
public class SequentialSearchST<Key, Value> {

    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public int size() {
        int size = 0;
        for (Node x = first; x != null; x = x.next)
            size++;
        return size;
    }

    public void delete(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (x.next.key.equals(key)) {
                x.next = x.next.next;
                return;
            }
    }

    public Iterable<Key> Keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }

    public static void main(String[] args) {
        SequentialSearchST<Integer, Integer> st = new SequentialSearchST<Integer, Integer>();
        for (int i = 0; i < 13; i++)
            st.put(i, i + 10);
        System.out.printf("value of Key(3) is %d\n", st.get(3));
        st.delete(3);
        System.out.println("Deleting key(3)");
        System.out.printf("The size now is %d\n", st.size());

    }
}