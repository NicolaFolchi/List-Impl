/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
 */
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel; // this will be the entry point to your linked list (the head)
	// LinkedListImpl myList = new LinkedListImpl();
	Node head = new Node(0);
	Node curr = new Node(0);

	int numEls = 0;

	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		sentinel = new Node(0); // Note that the root's data is not a true part of your data set!
	}

	// implement all methods in interface, and include the getRoot method we made
	// for testing purposes. Feel free to implement private helper methods!

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		if (index > size()) {
			System.out.println("desired insert index is bigger than the actual linked list size");
			return false;
		}
		if (index < 0) {
			System.out.println("invalid insert index, must be greater or equal to 0");
			return false;
		}
		int counter = 0;
		head = sentinel.next;
		curr = head;
		Node myNode = new Node(elt);

		if (index == 0 && size() == 0) { // case where the element to be inserted is the first in the list.
			myNode.prev = sentinel;
			sentinel.prev = myNode;
			sentinel.next = myNode;
			myNode.next = sentinel;
			numEls++;
			return true;
		}

		while (curr != sentinel) {
			if (counter < index) {
				curr = curr.next;
				counter++;
			} else {
				break;
			}
		}
		// opening the space to insert the new node. We utilize the current local node
		myNode.prev = curr.prev; // establishes the previous pointer the same as the previous point of the
		// current.
		myNode.prev.next = myNode; // accessing the previous node's next pointer and setting it equal to myNode
		curr.prev = myNode; // making the current local previous pointer equal to myNode
		myNode.next = curr; // making the next pointer equal to current (next node)
		numEls++; // increasing linked list size due to new Node insertion
		return true;
	}

	@Override
	public boolean remove(int index) {
		if (index > size()) {
			System.out.println("desired remove index is bigger than the actual linked list size");
			return false;
		}
		if (index < 0) {
			System.out.println("invalid insert index, must be greater or equal to 0");
			return false;
		}

		int counter = 0;
		head = sentinel.next;
		curr = head;

		while (curr != sentinel) {
			if (counter < index) {
				curr = curr.next;
				counter++;
			} else {
				break;
			}
		}

		// closing the pointers linked to the node that it's being removed.
		curr.prev.next = curr.next;
		curr.next.prev = curr.prev;
		numEls--; // decreasing linked list size due to new Node remove
		return true;
	}

	@Override
	public double get(int index) {
		if (index >= size()) {
			System.out.println("desired Node index is bigger than the actual linked list size");
			return Double.NaN;
		}
		if (index < 0) {
			System.out.println("invalid insert index, must be greater or equal to 0");
			return Double.NaN;
		}

		int counter = 0;
		head = sentinel.next;
		curr = head;

		while (curr != sentinel) { //traversing through the list.
			if (counter < index) {
				curr = curr.next;
				counter++;
			} else {
				break;
			}
		}
		return curr.getData();
	}

	@Override
	public int size() {
		return numEls;
	}

	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}

	@Override
	public void clear() { // emptying the List
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		numEls = 0;
	}
}