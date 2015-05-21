package ListPackage;


/**
   A class that implements the ADT list by using a chain of nodes.
   @author Frank M. Carrano
   @version 3.0
*/
public class LList<T> implements ListInterface<T>
{
	private Node<T> firstNode; // reference to first node
	private int     numberOfEntries;

	public LList()
	{
		clear();
	} // end default constructor

	public final void clear() // NOTICE clear is not final in interface and that is OK
	{
		firstNode = null;
		numberOfEntries = 0;
	} // end clear
  
	public void add(T newEntry) 	  // OutOfMemoryError possible
	{
		Node<T> newNode = new Node<T>(newEntry);             // create new node

		if (isEmpty())
			firstNode = newNode;
		else                                           // add to end of non-empty list
		{
			Node<T> lastNode = getNodeAt(numberOfEntries); // get last node
			lastNode.setNextNode(newNode);		        // make last node reference new node
		} // end if	
		
		numberOfEntries++;
	}  // end add

   public boolean add(int newPosition, T newEntry)  // OutOfMemoryError possible	                                                 
	{
		boolean isSuccessful = true;

		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) 
		{	
			Node<T> newNode = new Node<T>(newEntry);  	// create new node

			if (newPosition == 1)	// case 1: add to beginning list
			{
				newNode.setNextNode(firstNode);							
				firstNode = newNode;
			}
			else									// case 2: list is not empty and newPosition > 1
			{
				Node<T> nodeBefore = getNodeAt(newPosition - 1);
				Node<T> nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if	
		
			numberOfEntries++;
		}
		else
			isSuccessful = false;
			
		return isSuccessful;
	} // end add

	public T remove(int givenPosition)
	{
      T result = null;                 // return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();

         if (givenPosition == 1)               // case 1: remove first entry
         {
            result = (T)firstNode.getData();       // save entry to be removed 
            firstNode = firstNode.getNextNode();
         }
         else                                  // case 2: not first entry
         {
            Node<T> nodeBefore = getNodeAt(givenPosition - 1);
            Node<T> nodeToRemove = nodeBefore.getNextNode();
            Node<T> nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);  
            result = (T)nodeToRemove.getData();    // save entry to be removed
         } // end if

         numberOfEntries--;
      } // end if
  
      return result;                          // return removed entry, or 
                                          // null if operation fails
	} // end remove

	public boolean replace(int givenPosition, T newEntry)
	{
		boolean isSuccessful = true;

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {   
      	assert !isEmpty();

			Node<T> desiredNode = getNodeAt(givenPosition);
			desiredNode.setData(newEntry);
      }    
		else
			isSuccessful = false;
			
		return isSuccessful;
   } // end replace

   public T getEntry(int givenPosition)
   {
      T result = null;  // result to return
      
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
			result = (T)getNodeAt(givenPosition).getData();
     	} // end if
      
      return result;
   } // end getEntry

	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node<T> currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
   		boolean result;
   		
      	if (numberOfEntries == 0) // or getLength() == 0
      	{
      		assert firstNode == null;
      		result = true;
      	}
      	else
      	{
      		assert firstNode != null;
      		result = false;
      	} // end if
      	
      	return result;
   } // end isEmpty
	
   public T[] toArray()
   {
      // the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // warning: [unchecked] unchecked cast

	  int index = 0;
	  Node<T> currentNode = firstNode;
	  while ((index < numberOfEntries) && (currentNode != null))
	  { 
	     result[index] = (T)currentNode.getData();
	     currentNode = currentNode.getNextNode();
        index++; 
	  } // end while
     
     return result;
   } // end toArray

	// Returns a reference to the node at a given position.
	// Precondition:  List is not empty; 1 <= givenPosition <= numberOfEntries.	
	private Node<T> getNodeAt(int givenPosition)
	{
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node<T> currentNode = firstNode;
		
      // traverse the chain to locate the desired node
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		
		assert currentNode != null;
		return currentNode;
	} // end getNodeAt
} // end LList

