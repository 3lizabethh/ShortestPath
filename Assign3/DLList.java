import com.sun.media.sound.InvalidDataException;

/**
 * This class implements a doubly linked list (DLL) in which the nodes are of the class DLNode
 * @author Dongzheng (Elizabeth) Xu
 */
public class DLList<T> implements DLListADT<T> {

	private DLNode<T> front; //reference to first node of DLL
	private DLNode<T> rear; //reference to the last node of DLL
	private int count; //value is number of data items in linked list

	public DLList() {
		front =null;
		count=0;
	}

	@Override
	public void insert(T dataItem, int value) {

		DLNode<T> temp = new DLNode<T> (dataItem, value); //create a new node w/ desired values

		if(isEmpty()) {//if dll is empty set the front and rear var to point to new value
			front =temp; //makes 'front' point to address of node
		}
		else {
			rear.setNext(temp);
			temp.setPrevious(rear);
		}

		rear = temp;
		count++;

	}


	/**
	 *This method returns integer value associated to specified data item
	 * @return value of specified dataItem
	 */
	@Override
	public int getDataValue(T dataItem) throws InvalidDataItemException { 
		DLNode<T> temp=findDataItem(dataItem);
		int desiredValue= temp.getValue();
		return desiredValue;
	}


	/**
	 * This method changes the value of data item to new value, an invalid error is thrown if data item is not in list.
	 * @param dataItem to change
	 * @param newValue that is to be set
	 */
	@Override
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException {
		DLNode<T> temp=findDataItem(dataItem);
		temp.setValue(newValue);

	}


	/**SELF WRITTEN METHOD
	 * This method finds the node with given dataItem:
	 * @param dataItem that is being looked for
	 * @return a copy of the node value of desired data item
	 */
	private DLNode<T> findDataItem(T dataItem) throws InvalidDataItemException{
		boolean check = true; //true/false whether to continue checking for desired
		DLNode<T> temp = front; //sets a variable to check nodes

		while (temp!=null) { //check in linked list
			if (temp.getData().equals(dataItem))
				return temp; //return the node

			temp=temp.getNext();
		}
		throw new InvalidDataItemException("dataItem");
	}


	/* This method removes and returns the data item in the list with the smallest associated value.
	 * If many have same value then any one is returned.
	 * @return data item with smallest associated value
	 */
	@Override
	public T getSmallest() throws EmptyListException {
		DLNode<T> temp = front; //sets a variable to check nodes
		DLNode <T> smallest= temp; //holds dataItem that holds smallest value so far

		if (isEmpty()) {
			throw new EmptyListException("List");
		}

		while (temp!=null) {//if not at end 
			if (temp.getValue()<smallest.getValue()) {
				smallest = temp;
			}
			else
				temp=temp.getNext();
		}


		//first case, list is empty other than smallest
		if (front==rear) {
			front=null;
			rear=null;
		}

		//smallest value is at front
		else if(front==smallest) {
			front= smallest.getNext(); // sets link of front away from smallest
			front.setPrevious(null); //front is now  next node of smallest node (has previous pointing to a node,so set to null)
		}

		//smallest is at back
		else if(rear==smallest) {
			rear= smallest.getPrevious(); // sets link of front away from smallest
			rear.setNext(null); //front is now  next node of smallest node (has previous pointing to a node,so set to null)
		}

		//smallest is in middle
		else {
			smallest.getNext().setPrevious(smallest.getPrevious());
			smallest.getPrevious().setNext(smallest.getNext());

		}
		count--;
		return smallest.getData();
	}

	/**
	 * This method returns whether the list is empty.
	 * @return boolean true if empty,false if not
	 */
	@Override
	public boolean isEmpty() {
		if (front==null) 
			return true;
		return false;
	}

	/**
	 * Accessor returns whether the  number of items in list.
	 * @return count:the number of items in list
	 */
	@Override
	public int size() {
		return count;
	}
	
	/**
	 * Displays list as a string.
	 * @return string that represents 
	 */
	public String toString() {
		String result="List:";
		DLNode<T> temp = front; //sets a variable to check nodes

		while(temp.getNext()!=null) {
			result = result +" "+temp.getData()+","+temp.getValue()+";";
			temp=temp.getNext();
		}

		System.out.println(result);
		return result;
	}

}
