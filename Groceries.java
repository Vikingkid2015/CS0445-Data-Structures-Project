package cs445.a1;

import cs445.a1.GroceryItem;
import cs445.a1.Set;
import cs445.a1.GroceriesInterface;

public class Groceries implements GroceriesInterface
{
    private Set<GroceryItem> gBag;

    // Constructor
    public Groceries()
    {
        gBag = new Set<GroceryItem>();
    }

    public void addItem(GroceryItem item) throws NullPointerException, IllegalArgumentException
    {
        // if the item does not have a description, then throw exception
        if(item.getDescription() == null)
        {
            throw new NullPointerException("Null Description");
        }

        // if the item already exists in the gBag, then remove the item,
        // change the quantity, set item's quantity to the new quantity,
        // and add the item (which now has the new quantity) back into gBag
        if(gBag.contains(item))
        {
            GroceryItem removed = gBag.remove(item);
            int oldQ = removed.getQuantity();
            int newQ = oldQ + item.getQuantity();
            item.setQuantity(newQ);
        }

        // add is outside the if statement so that the item can still be added if it isnt already contained
        gBag.add(item);
        
    }

    public void removeItem(GroceryItem item)
    {
        // if the item does not have a description, then throw exception
        if(item.getDescription() == null)
        {
            throw new NullPointerException("Null Description");
        }

        // if the item already exists in the gBag, then remove the item,
        // change the quantity, check the quantity to ensure it is greater than 0
        // and if it is then add the item (which now has the new quantity) back into gBag
        // if the new quantity is 0 or less, or the item wans't contained in gBag, then the method does nothing
        if(gBag.contains(item))
        {
            GroceryItem removed = gBag.remove(item);
            int oldQ = removed.getQuantity();
            int newQ = oldQ - item.getQuantity();
            if(newQ > 0)
            {
                item.setQuantity(newQ);
                gBag.add(item);
            }     
        }
    }

    public int modifyQuantity(GroceryItem item)
    {
        // check that the item is in gBag, if so then remove the item from gBag, save it's original quantity in
        // oldQ, set the new quantity to newQ, and add the removed item (which has the newQ) back into gBag and return oldQ
        // if the item is not in gBag, then the method returns -1
        if(gBag.contains(item))
        {
            GroceryItem removed = gBag.remove(item);
            int oldQ = removed.getQuantity();
            int newQ = item.getQuantity();
            removed.setQuantity(newQ);
            gBag.add(removed);
            return oldQ;
        }
        return -1;
    }

    public void printAll()
    {
        // use toArray to create an easily iterable Object Array that is only as large as the logical size
        Object[] printArr = gBag.toArray();

        // print Groceries
        System.out.println("Groceries:");

        // iterate through the printArr, printing each element
        for(Object o : printArr)
        {
            System.out.println(o);
        }

    }
}