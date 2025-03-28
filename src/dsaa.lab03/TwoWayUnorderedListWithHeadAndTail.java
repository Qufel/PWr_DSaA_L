package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {

			this.object = e;

		}
		public Element(E e, Element next, Element prev) {

			this.object = e;

			this.next = next;
			this.prev = prev;

		}
		E object;
		Element next=null;
		Element prev=null;
	}
	
	Element head;
	Element tail;
	// can be realization with the field size or without
	int size;
	
	private class InnerIterator implements Iterator<E>{
		Element pos;
		
		public InnerIterator() {
			this.pos = null;
		}
		@Override
		public boolean hasNext() {

			if (isEmpty())
				return false;

			if (pos == null) {
				return head != null;
			}

			return pos.next != null;
		}
		
		@Override
		public E next() {

			Element next;

			if (pos == null)
				next = head;
			else
				next = pos.next;

			pos = next;

			return next.object;
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		Element p;
		// TODO maybe more fields....

		public InnerListIterator() {
			this.p = null;
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {

			if (isEmpty())
				return false;

			if (p == null)
				return head != null;

			return p.next != null;
		}

		@Override
		public boolean hasPrevious() {

			if (isEmpty())
				return false;

			if (p == null)
				return tail != null;

			return p.prev != null;
		}

		@Override
		public E next() {

			Element next;

			if (p == null)
				next = head;
			else
				next = p.next;

			p = next;

			return next.object;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {

			Element prev;

			if (p == null)
				prev = tail;
			else
				prev = p.prev;

			p = prev;

			return prev.object;
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E e) {
			p.object = e;
		}
	}
	
	public TwoWayUnorderedListWithHeadAndTail() {
		// Make a head and a tail
		this.head=null;
		this.tail=null;

		// Initialize size to 0
		this.size = 0;
	}
	
	@Override
	public boolean add(E e) {
		Element element = new Element(e);

		if (head == null)
			head = element;

		if (tail != null) {
			element.prev = tail;
			tail.next = element;
		}

		tail = element;

		size++;

		return true;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {

		// Check if provided index is in valid range
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		// Cover edge case when index == size
		if (index == size) {
			add(element);
			return;
		}

		Element newElement;

		// Cover edge case when index == 0
		if (index == 0) {

			newElement = new Element(element, head, null);

			head.prev = newElement;

			head = newElement;

			size++;

			return;
		}

		int i;
		int halfSize = size / 2;

		ListIterator<E> it = new InnerListIterator();

		Element e;

		if (index > halfSize) {
			// Proceed in direction from the tail to head
			e = tail;
			i = size - 1;

			while (it.hasPrevious()) {

				if (i == index) {
					break;
				}

				e = e.prev;
				i--;

				it.previous();
			}

			if (e == null)
				return;


			newElement = new Element(element, e, e.prev);

			e.prev.next = newElement;
			e.prev = newElement;

		} else {
			// Proceed in direction from the head to tail
			i = 0;
			e = head;

			while (it.hasNext()) {

				if (i == index - 1) {
					break;
				}

				e = e.next;
				i++;

				it.next();
			}

			if (e == null)
				return;

			newElement = new Element(element, e.next, e);

			e.next.prev = newElement;
			e.next = newElement;

		}

		size++;

	}

	@Override
	public void clear() {
		this.head = null;
		this.tail = null;

		this.size = 0;
	}

	@Override
	public boolean contains(E element) {

		ListIterator<E> it = new InnerListIterator();

		while (it.hasNext()) {
			if (element.equals(it.next()))
				return true;
		}

		return false;
	}

	@Override
	public E get(int index) throws NoSuchElementException {
		if (index < 0 || index >= size)
			throw new NoSuchElementException();

		return getElement(index).object;
	}

	@Override
	public E set(int index, E element) throws NoSuchElementException {

		if (index < 0 || index >= size)
			throw new NoSuchElementException();

		Element e;
		E old;

		e = getElement(index);

		old = e.object;
		e.object = element;

		return old;
	}

	@Override
	public int indexOf(E element) {

		ListIterator<E> it = new InnerListIterator();

		int index = 0;

		while (it.hasNext()) {

			E e = it.next();

			if (e.equals(element)) {
				return index;
			}

			index++;

		}

		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator(); // For unit tests only
//		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) throws NoSuchElementException {

		if (index < 0 || index >= size)
			throw new NoSuchElementException();

		//region Workaround for removal of head or tail

		// Head removal
		if (index == 0) {
			return unshift();
		}

		// Tail removal
		if (index == size - 1) {
			return pop();
		}

		//endregion

		Element e = getElement(index);

		if (e.prev != null)
			e.prev.next = e.next;

		if (e.next != null)
			e.next.prev = e.prev;

		size--;

		return e.object;
	}

	@Override
	public boolean remove(E e) {

		if (isEmpty())
			return false;

		Element element = head;
		int i = 0;

		while (element != null) {

			if (e.equals(element.object)) {
				break;
			}

			element = element.next;

			i++;
		}

		// Object not found
		if (i == size)
			return false;

		//region Workaround for head and tail removal

		if (i == 0) {
			unshift();
			return true;
		}

		if (i == size - 1) {
			pop();
			return true;
		}

		//endregion

		if (element == null)
			return false;

		if (element.prev != null)
			element.prev.next = element.next;

		if (element.next != null)
			element.next.prev = element.prev;

		size--;

		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		ListIterator<E> it = new InnerListIterator();
		StringBuilder refStr = new StringBuilder();

		while (it.hasNext()) {
			refStr.append("\n").append(it.next().toString());
		}

		return refStr.toString();
	}

	public String toStringReverse() {
		ListIterator<E> it = new InnerListIterator();
		StringBuilder refStr = new StringBuilder();

		while (it.hasPrevious()) {
			refStr.append("\n").append(it.previous().toString());
		}

		return refStr.toString();
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
		// Check if given list is the same list (reference matched, not values matched!!)
		if (this == other)
			return;

		if (other == null)
			return;

		if (other.isEmpty())
			return;

		if (this.isEmpty()) {
			this.head = other.head;
			this.tail = other.tail;

			this.size = other.size;

			other.clear();

			return;
		}

		// Attach tail of other list to head
		this.tail.next = other.head;
		other.head.prev = this.tail;

		// Choose new tail
		this.tail = other.tail;

		// Increase size
		this.size += other.size;

		// Clear other list
		other.clear();
	}

	public E pop() {

		if (isEmpty())
			return null;

		Element e = tail;

		// Check if it's final element of a list
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			if (tail.prev != null)
				tail.prev.next = null;

			tail = tail.prev;

		}

		size--;

		return e.object;
	}

	public E unshift() {

		if (isEmpty())
			return null;

		Element e = head;

		// Check if it's final element of a list
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			if (head.next != null)
				head.next.prev = null;

			head = head.next;
		}

		size--;

		return e.object;
	}

	private Element getElement(int index) {
		int halfSize = size / 2;
		int i;
		Element e;
		if (index > halfSize) {

			i = size - 1;
			e = tail;

			// Search for index from tail to head
			while (i > index) {

				e = e.prev;
				i--;

			}

		} else {

			i = 0;
			e = head;

			// Search for index from head to tail
			while (i < index) {

				e = e.next;
				i++;

			}

		}
		return e;
	}

}

