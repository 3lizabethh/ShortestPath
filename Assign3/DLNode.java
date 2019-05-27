/**
 * This class represents the nodes of a doubly linked list. Each node contains a data item and an associated integer value.
 * @author Dongzheng (Elizabeth) Xu
 */
public class DLNode<T> {

	private T dataItem; //reference to data item stored in this node
	private DLNode<T> next;//reference to next node in linked list
	private DLNode<T> previous;//reference to previous node in linked list
	private int value; // this is the value of the data item stored in this node

	/**
	 * Constructor for class and initializes a node storing the given data and value.
	 * @param data is the reference to item stored in node
	 * @param value is the value of the item stored in node
	 */

	public DLNode(T data, int value) {
		dataItem = data;
		this.value=value;
		next=null;
		previous=null;

	}

	/**
	 * Accessor gets value stored in node.
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Accessor gets data reference
	 * @return dataItem
	 */
	public T getData() {
		return dataItem;
	}

	/**
	 * Accessor gets next node
	 * @return next
	 */
	public DLNode<T> getNext() {
		return next;
	}

	/**
	 * Accessor gets previous node
	 * @return value
	 */
	public DLNode<T> getPrevious() {
		return previous;
	}

	/**
	 * Setter method changes data value.
	 * @param new data value
	 */
	public void setData(T newValue) {
		dataItem = newValue;
	}

	/**
	 * Setter method changes next node.
	 * @param next node
	 */
	public void setNext(DLNode<T> newValue) {
		next= newValue;
	}

	/**
	 * Setter method changes previous node.
	 * @param node to be set as previous
	 */
	public void setPrevious(DLNode<T> newValue) {
		previous = (DLNode<T>) newValue;
	}

	/**
	 * Setter method changes value of node.
	 * @param new value
	 */
	public void setValue(int newValue) {
		value = (int) newValue;

	}

}
