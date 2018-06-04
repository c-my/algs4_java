import javax.lang.model.util.ElementScanner6;

/**
 * BinarySearchST 二分查找的有序符号表
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        int i = rank(key);
        if (keys[i].compareTo(key) != 0)
            return;
        for (int j = i; j < N; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        N--;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (keys[i].equals(key))
            return keys[i];
        else
            return keys[i - 1];
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public boolean contains(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = keys[mid].compareTo(key);
            if (cmp < 0) {
                lo = mid + 1;
            } else if (cmp > 0) {
                hi = mid - 1;
            } else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BinarySearchST vst = new BinarySearchST<Integer, Integer>(13);
        for (int i = 0; i < 10; i++) {
            vst.put(i, i + 10);
        }
        if (vst.contains(3))
            System.out.println("Contains 3");
        if (!vst.contains(13))
            System.out.println("13 is not here");
        vst.delete(3);
        System.out.println("Deleting 3");
        if (!vst.contains(3))
            System.out.println("3 is not here");
    }
}