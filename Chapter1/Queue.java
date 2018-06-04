import edu.princeton.cs.introcs.StdOut;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int N;
    private class Node
    {
        Item item;
        Node next;
    }
    public boolean isEmpty() {return first==null;}
    public int size()   {return N;}
    public void enqueue(Item item)
    {
//        last.next = new Node();
//        last = last.next;
//        last.item = item;
//        N++;
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty())   first = last;
        else            oldlast.next = last;
        N++;
    }
    public Item dequeue()
    {
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        N--;
        return item;
    }
    public Iterator<Item> iterator()
    {
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item>
    {
        int i = 0;
        Node nextnode = first;
        @Override
        public boolean hasNext() {
            return i<N;
        }

        @Override
        public Item next() {

            if(i!=0)
                nextnode = nextnode.next;
            i++;
            return nextnode.item;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args)
    {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(5);
        for(int i:q)
            StdOut.println(i);
    }
}
