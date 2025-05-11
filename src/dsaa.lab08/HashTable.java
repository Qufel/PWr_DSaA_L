package dsaa.lab08;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTable{
	LinkedList arr[]; 
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;	
	private final double maxLoadFactor;

	public HashTable() {
		this(defaultInitSize);
	}

	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}

	public HashTable(int initCapacity, double maxLF) {
		this.size = 0;
		this.maxLoadFactor=maxLF;

		// Initialize an array
		arr = new LinkedList[initCapacity];
		for (int i = 0; i < initCapacity; i++) {
			arr[i] = new LinkedList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean add(Object elem) {
		if (elem == null)
			return false;

		int index = getIndex(elem.hashCode());

		if (arr[index].contains(elem))
			return false;

		arr[index].add(elem);

		size++;

		ensureLoadFactor();

		return true;
	}

	private void ensureLoadFactor() {
		float loadFactor = (float) size / arr.length;
		// If load factor is lower than max return. Most probable case.
		if (loadFactor < maxLoadFactor)
			return;

		doubleArray(); // Double the size of array
		relocateElements(); // Relocate all elements to new positions
	}

	private void doubleArray() {
		LinkedList[] aux = new LinkedList[arr.length * 2];
		// Copy what was in smaller array
		System.arraycopy(arr, 0, aux, 0, arr.length);

		// Fill new indices in array
		for (int i = arr.length; i < aux.length; i++) {
			aux[i] = new LinkedList<>();
		}

		// Change reference to array
		arr = aux;

	}

	private void relocateElements() {
		int index;
		for (int i = 0; i < arr.length; i++) {

			// Check if array is empty
			if (arr[i].isEmpty())
				continue;

			moveElements(i);
		}
	}

	@SuppressWarnings("unchecked")
	public void moveElements(int origin) {
		int sizeLeft = 0;
		int index;
		while (arr[origin].size() > sizeLeft) {
			index = getIndex(arr[origin].peek().hashCode());
			if (origin == index) {
				sizeLeft++;
				continue;
			}
			Object obj = arr[origin].removeFirst();
			arr[index].addLast(obj);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Iterate for every index in array
		for (int i = 0; i < arr.length; i++) {
			sb.append("\n").append(i).append(":");
			if (arr[i].isEmpty())
				continue;

			Iterator<IWithName> iterator = arr[i].iterator();
			while (iterator.hasNext()) {
				sb.append(" ").append(iterator.next().getName()).append(',');
			}

			sb.deleteCharAt(sb.length() - 1); // Delete comma

		}

		sb.append("\n");

		return sb.toString().stripLeading();
	}

	public Object get(Object toFind) {
		int index = getIndex(toFind.hashCode());
		if (arr[index].isEmpty())
			return null;

		for (Object obj : arr[index]) {
			if (obj.equals(toFind))
				return obj;
		}

		return null;
	}

	private int getIndex(int key) {
		return key % arr.length;
	}
	
}

