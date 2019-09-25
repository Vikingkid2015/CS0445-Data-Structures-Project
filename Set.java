package cs445.a1;

import cs445.a1.SetInterface;

public class Set<E> implements SetInterface<E>
{
    private E[] array;
    private int size = 0;

    // suppress the uncheck warnings so that the message doesn't pop up when compiling program
    @SuppressWarnings("unchecked")
    public Set(int capacity)
    {
        array = (E[]) new Object[capacity];
    }

    public Set()
    {
        //this(10)  reuses the code from the first contructor so that it doe not hav eto be rewritten
        this(10);
    }

    public Set(E[] entries)
    {
        this(entries.length);
        for(int i=0; i<entries.length;i++)
        {
            array[i] = entries[i];
        }
    }

    // return the logical size of the array
    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        // if the size (logical size) of the array is 0, then the bag is empty, return true
        if(size == 0)
        {
            return true;
        }

        //if the size is not 0, then the bag cannot be empty, return false
        return false;
    }

    // same warning suppression as above
    @SuppressWarnings("unchecked")
    public boolean add(E newEntry) throws NullPointerException
    {
        // if client tries to add null, return a NullPointerException
        if(newEntry == null)
        {
            throw new NullPointerException("Null is not a valid input.\n");
        }
        
        // if client tries to add an existing element, don't add it and return false
        if(this.contains(newEntry))
        {
            return false;
        }

        // if client tries to add a valid entry, but the array is full, then double the size of the array
        if(size == array.length)
        {
            //create an auxillary array that is of the same length of array
            E[] auxArr = array;

            // after elements are copied, point array to a new E[] that is twice its old size
            array = (E[]) new Object[auxArr.length * 2];

            // after douling the size, loop through the array, copying all the elements of auxillary array to the new array
            for(int i = 0; i < auxArr.length; i++)
            {
                array[i] = auxArr[i];
            }    
        }

        //after checking that newEntry is a valid entry, and dynamically resizing array to allow for more entries (if necessary),
        //add newEntry to array, increase size, and return true
        array[size] = newEntry;
        size++;
        return true;
    }

    public E remove(E entry) throws NullPointerException
    {
        //check if the array is empty, if so then return null
        if(isEmpty())
        {
            return null;
        }

        // check if the client entry is null, if so then throw exception
        if(entry == null)
        {
            throw new NullPointerException("Null is not a valid input. \n");
        }

        // if the array is not empty and the client entered a valid value, 
        // then loop the array for the index of entry, copy last entry to that position
        // remove the last entry, and decriment the logical size of the array
        for(int i = 0; i < size; i++)
        {
            if(array[i].equals(entry))
            {
                E removed = array[i];
                array[i] = array[size - 1];
                array[size - 1] = null;
                size--;
                return removed;
            }
        }

        // if the array is not empty, the client entered a valid entry, and the entry does not appear in the array, then return null
        return null;
    }

    public E remove()
    {
        //if the set is empty, return null, if not then remove last item and decriment size
        if(isEmpty())
        {
            return null;
        }

        //if the array is not empty, then return the last element to the client, set last element to null, and decriment logical size
        E removed = array[size-1];
        array[size-1] = null;
        size--;
        return removed;
    }

    public void clear()
    {
        // I could also just change the logical size (size variable) to 0 if I don't have to worry about
        // the client having pointers to any of the old elements.  If I can't guarentee that, then
        // I need to iterate through the array (just from the beginning to the logical size) and change all 
        // the elements to null
        for(int i = 0; i < size; i++)
        {
            array[i] = null;
        }
        size = 0;
    }

    public boolean contains(E entry) throws NullPointerException
    {
        // if the client enters null, throw exception
        if(entry == null)
        {
            throw new NullPointerException("Null is not a valid input. \n");
        }

        // if client gives a valid entry, then loop through array and test if any element is equal to the entry
        // if the entry is within the array, then return true
        for(int i = 0; i < size; i++)
        {
            if(array[i].equals(entry))
            {
                return true;
            }
        }

        // if the entry is valid, but not in the array, then return false
        return false;
    }

    public Object[] toArray()
    {
        // create a new Object array with capacity = logical size of array
        Object[] returnArray = new Object[size];

        // loop through the array, adding each element from the array to returnArray
        for(int i = 0; i < size; i++)
        {
            returnArray[i] = array[i];
        }
        
        // return returnArray after filling it with the elements of array
        return returnArray;
    }

    public String toString()
    {
        // use the java.util.Arrays to create a toString that returns the array and the logical size of the array
        return java.util.Arrays.toString(array) + " size: " + size;
    }
}