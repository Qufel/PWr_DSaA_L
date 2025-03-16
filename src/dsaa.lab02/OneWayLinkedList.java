package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			this.object=e;
		}
		E object;
		Element next=null;
	}
	
	Element sentinel;
	
	private class InnerIterator implements Iterator<E>{

		Element current;

		public InnerIterator() {
			this.current = sentinel;
		}
		@Override
		public boolean hasNext() {
			return current.next != null;
		}
		
		@Override
		public E next() {

			Element next = current.next;
			current = next;
			return next.object;

		}
	}
	
	public OneWayLinkedList() {
		this.sentinel = new Element(null);
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds an element to the end of the list.
	 * @param e an element that will be added
	 * @return {@code true} if value was added
	 */
	@Override
	public boolean add(E e) {
		Element newElement = new Element(e);

		if (isEmpty()) {
			sentinel.next = newElement;
		} else {

			Element last = sentinel.next;

			while (last.next != null) {
				last = last.next;
			}

			last.next = newElement;
		}

		return true;
	}

	/**
	 * Adds a given element at the provided index.
	 * @param index an index where the element will be added
	 * @param element an element that will be added
	 * @throws NoSuchElementException
	 */
	@Override
	public void add(int index, E element) throws NoSuchElementException {
		if (index < 0 || index > size()) throw new NoSuchElementException();

		Element current = sentinel;

		int i = 0;

		while (current != null) {

			if (i == index) {

				Element newElement = new Element(element);

				newElement.next = current.next;
				current.next = newElement;

			}

			current = current.next;

			i++;
		}
	}

	/**
	 * List is cleared by setting next element in sentinel to {@code null}.
	 */
	@Override
	public void clear() {
		sentinel.next = null;
	}

	/**
	 * @param element an element for which we are searching
	 * @return {@code true} if searched element is found
	 */
	@Override
	public boolean contains(E element) {
		if (isEmpty()) return false;

		InnerIterator it = new InnerIterator();

		while (it.hasNext()) {
			if (it.next().equals(element)) return true;
		}

		return false;
	}

	/**
	 * @param index
	 * @return {@link E} {@code element} an element of given index
	 * @throws NoSuchElementException
	 */
	@Override
	public E get(int index) throws NoSuchElementException {
		if (isEmpty()) throw new NoSuchElementException();
		if (index < 0 || index >= size()) throw new NoSuchElementException();

		InnerIterator it = new InnerIterator();

		int i = 0;

		E element = it.next();

		while (it.hasNext()) {
			if (i == index) break;
			element = it.next();
			i++;
		}

		return element;
	}

	/**
	 * Sets a given element at the given index.
	 * @param index an index where to set an element
	 * @param element an element that will be set
	 * @return {@link E} {@code element} previous element in the given index
	 * @throws NoSuchElementException
	 */
	@Override
	public E set(int index, E element) throws NoSuchElementException {
		if (isEmpty()) throw new NoSuchElementException();
		if (index < 0 || index >= size()) throw new NoSuchElementException();

		Element current = sentinel;
		int i = 0;

		E e = null;

		while (current != null) {
			if (i == index) {
				e = current.next.object;
				current.next.object = element;
			}
			current = current.next;
			i++;
		}

		return e;
	}

	/**
	 * @param element an element for which we're looking for an index of it
	 * @return {@code index} an index of an element, {@code -1} if no index found
	 */
	@Override
	public int indexOf(E element) {
		if (isEmpty()) return -1;

		int index = 0;

		InnerIterator it = new InnerIterator();

		E e;

		while (it.hasNext()) {
			e = it.next();
			if (e.equals(element)) {
				return index;
			}
			index++;
		}

		return -1;
	}

	/**
	 * @return {@code true} if sentinel has no next element
	 */
	@Override
	public boolean isEmpty() {
		return sentinel.next == null;
	}

	/**
	 * @param index
	 * @return {@link E} {@code element} a removed element
	 * @throws NoSuchElementException
	 */
	@Override
	public E remove(int index) throws NoSuchElementException {
		if (isEmpty()) throw new NoSuchElementException();
		if (index < 0 || index >= size()) throw new NoSuchElementException();

		int i = 0;

		Element previous = sentinel;
		Element element = sentinel.next;

		while (element != null) {

			if (i == index) {

				E e = element.object;

				previous.next = element.next;

				return e;
			}

			previous = element;
			element = element.next;

			i++;
		}

		return null;
	}

	/**
	 * Removes the first occurrence of a given element.
	 * @param e an element to be removed
	 * @return {@code true} if element was removed successfully
	 */
	@Override
	public boolean remove(E e) {

		if (isEmpty()) return false;
		if (!contains(e)) return false;

		InnerIterator it = new InnerIterator();

		E element;

		while (it.hasNext()) {
			element = it.next();
			if (element.equals(e)) {

				remove(indexOf(element));
				return true;

			}
		}

		return false;
	}

	/**
	 * @return the size of a list
	 */
	@Override
	public int size() {

		if (isEmpty()) return 0;

		int size = 0;

		InnerIterator it = new InnerIterator();

		while (it.hasNext()) {
			it.next();
			size++;
		}

		return size;
	}
	
}

