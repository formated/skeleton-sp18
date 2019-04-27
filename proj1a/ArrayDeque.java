import java.awt.*;

public class ArrayDeque<T> {

    T[] List;
    public int size ;
    private int firstIndex;
    private int lastIndex;
    public ArrayDeque()
    {
        List = (T[]) new Object[8];
        firstIndex = List.length / 2 ;
        lastIndex = firstIndex + 1 ;
        size = 0;
    }
    /* Adds an item of type T to the front of the deque.*/
    public void addFirst(T item)
    {
        /*add the new item*/
        List[firstIndex] = item;
        /*increase the size */
        size++;
        if (size >= List.length)
        {
            resizeListUp();
        }
        else
        {
            firstIndex = (firstIndex - 1) % List.length;
        }
    }

    /* Adds an item of type T to the back of the deque.*/
    public void addLast(T item)
    {
        List[lastIndex] = item;
        /*increase the size */
        size++;
        if (size >= List.length)
        {
            resizeListUp();
        }
        else
        {
            lastIndex = (lastIndex + 1) % List.length;
        }
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
        int index = (firstIndex + 1) % List.length;

        while (index < size)
        {
            System.out.print(List[index] + " ");
            index = (index + 1) % List.length;
        }
    }

    /*Removes and returns the item at the front of the deque.
     If no such item exists, returns null.
     */
    public T removeFirst()
    {
        T temp = null;

        if (size > 0)
        {
            temp = List[(firstIndex - 1) % List.length];
            firstIndex = (firstIndex - 1) % List.length;
            size--;
            if ((List.length / size) > 4 && List.length > 100 )
            {
                resizeListDown();
            }
        }
        return temp;
    }

    /*Removes and returns the item at the back of the deque.
    If no such item exists, returns null.*/
    public T removeLast()
    {
        T temp = null;

        if (size > 0)
        {
            temp = List[(lastIndex - 1) % List.length];
            lastIndex = (lastIndex - 1) % List.length;
            size--;
            if ((List.length / size) > 4 && List.length > 100 )
            {
                resizeListDown();
            }
        }
        return temp;
    }

    /* Gets the item at the given index, where 0 is the front,
       1 is the next item, and so forth. If no such item exists, returns null.
       Must not alter the deque!
    */
    public T get(int index)
    {
        if (size > 0)
        {
            return List[(firstIndex +  index + 1) % List.length];
        }
        return null;
    }

    private void resizeListUp()
    {
        int oldSize  = List.length;
        int startIndex = oldSize * 2 / 4;
        T[] newList = (T[]) new Object[oldSize*2];

        for(int i = 0; i < size; i++)
        {
            newList[startIndex+i] = List[(firstIndex + i + 1) % oldSize];
        }
        firstIndex = startIndex - 1;
        lastIndex = startIndex + size  ;
        List  = newList;
    }

    private void resizeListDown()
    {
        int oldSize  = List.length;
        int startIndex = (oldSize / 2) / 4;
        T[] newList = (T[]) new Object[oldSize / 2];

        for(int i = 0; i < size; i++)
        {
            newList[startIndex+i] = List[(firstIndex + i + 1) % oldSize];
        }

        firstIndex = startIndex - 1;
        lastIndex = startIndex + size  ;
        List  = newList;
    }
}
