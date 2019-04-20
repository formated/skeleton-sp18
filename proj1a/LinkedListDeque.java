import javax.lang.model.type.NullType;

public class LinkedListDeque<T> {

    private class SList
    {
        T Data;
        SList nextNode ;
        SList prevNode ;
        public SList (T D,SList nextN, SList prevN)
        {
            Data = D;
            nextNode = nextN;
            prevNode = prevN;
        }
    }
    private SList SentinelNode;
    int size = 0;
    public LinkedListDeque()
    {
        SentinelNode = new SList(null,null,null);
        SentinelNode.nextNode = SentinelNode;
        SentinelNode.prevNode = SentinelNode;
    }
    /* Adds an item of type T to the front of the deque.*/
    public void addFirst(T item)
    {
        SList TempNode = SentinelNode.nextNode;
        SentinelNode.nextNode = new SList(item, SentinelNode.nextNode, SentinelNode);
        TempNode.prevNode = SentinelNode.nextNode;
        if (size == 0)
        {
            SentinelNode.prevNode =  SentinelNode.nextNode;
        }
        size++;
    }

    /* Adds an item of type T to the back of the deque.*/
    public void addLast(T item)
    {
        SList TempNode = SentinelNode.prevNode;
        SentinelNode.prevNode = new SList(item, SentinelNode, SentinelNode.prevNode);
        TempNode.nextNode = SentinelNode.prevNode;
        if (size == 0)
        {
            SentinelNode.nextNode =  SentinelNode.prevNode;
        }
        size++;
    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty()
    {
        if (size == 0)
            return true;
        return false;
    }

    /*Returns the number of items in the deque.*/
    public int size()
    {
        return size;
    }

    /*Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque()
    {
        SList TempNode =  SentinelNode.nextNode;

        while (TempNode != SentinelNode)
        {
            System.out.print(TempNode.Data + " ");
            TempNode = TempNode.nextNode;
        }
    }

    /*Removes and returns the item at the front of the deque.
     If no such item exists, returns null.
     */
    public T removeFirst()
    {
        T TempNode =  null;
        if (size > 0)
        {
            TempNode =  SentinelNode.nextNode.Data;
            SentinelNode.nextNode = SentinelNode.nextNode.nextNode;
            SentinelNode.nextNode.prevNode = SentinelNode;
            size--;
        }


        return TempNode;
    }

    /*Removes and returns the item at the back of the deque.
    If no such item exists, returns null.*/
    public T removeLast()
    {
        T TempNode =  null;
        if (size > 0)
        {
            TempNode =  SentinelNode.prevNode.Data;
            SentinelNode.prevNode = SentinelNode.prevNode.prevNode;
            SentinelNode.prevNode.nextNode = SentinelNode;
            size--;
        }

        return TempNode;
    }

    /* Gets the item at the given index, where 0 is the front,
       1 is the next item, and so forth. If no such item exists, returns null.
       Must not alter the deque!
    */
    public T get(int index)
    {
        SList TempNode =  SentinelNode;
        T  Data = null;
        int i = 0;
        while (i <= index && size > 0)
        {
            TempNode = TempNode.nextNode;
            i++;
            Data =  TempNode.Data;
        }
        return Data;
    }




}
