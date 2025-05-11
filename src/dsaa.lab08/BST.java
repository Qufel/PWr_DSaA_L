package dsaa.lab08;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
		public Node(T value, Node left, Node right, Node parent) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}		
	private Node root=null;

	int size = 0;

	public BST() {
		this.root = null;
		this.size = 0;
	}

	public T getElement(T toFind) {
        Node node = getNode(toFind);
		if (node == null)
			return null;
		return node.value;
	}

	private Node getNode(T element) {
		Node current = root;
		while(current != null) {
			if (current.value.compareTo(element) > 0) {
				current = current.left;
			} else if (current.value.compareTo(element) < 0) {
				current = current.right;
			} else {
				return current;
			}
		}

		return null;
	}

	public T successor(T elem) {
		Node node = getNode(elem);
		if (node == null)
			return null;
		Node successor = successor(node);
		if (successor == null)
			return null;
		return successor.value;
	}

	private Node successor(Node node) {
		if (node.right != null)
			return min(node.right);

		Node parent = node.parent;
		while (parent != null && node == parent.right) {
			node = parent;
			parent = parent.parent;
		}

		if (parent == null)
			return null;

		return parent;
	}

	private Node min(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public String toStringInOrder() {
		LinkedList<T> stack = new LinkedList<T>();
		inOrder(root, stack);
		String[] elements = new String[stack.size()];
		int i = 0;
		while (!stack.isEmpty()) {
			elements[i] = stack.removeLast().toString();
			i++;
		}
		return String.join(", ", elements);
	}

	private void inOrder(Node node, LinkedList<T> stack) {
		if (node != null) {
			inOrder(node.left, stack);
			stack.push(node.value);
			inOrder(node.right, stack);
		}
	}

	public String toStringPreOrder() {
		LinkedList<T> stack = new LinkedList<T>();
		preOrder(root, stack);
		String[] elements = new String[stack.size()];
		int i = 0;
		while (!stack.isEmpty()) {
			elements[i] = stack.removeLast().toString();
			i++;
		}
		return String.join(", ", elements);
	}

	private void preOrder(Node node, LinkedList<T> stack) {
		if (node != null) {
			stack.push(node.value);
			preOrder(node.left, stack);
			preOrder(node.right, stack);
		}
	}

	public String toStringPostOrder() {
		LinkedList<T> stack = new LinkedList<T>();
		postOrder(root, stack);
		String[] elements = new String[stack.size()];
		int i = 0;
		while (!stack.isEmpty()) {
			elements[i] = stack.removeLast().toString();
			i++;
		}
		return String.join(", ", elements);
	}

	private void postOrder(Node node, LinkedList<T> stack) {
		if (node != null) {
			postOrder(node.left, stack);
			postOrder(node.right, stack);
			stack.push(node.value);
		}
	}


	public boolean add(T elem) {

		Node previous = null;
		Node current = root;

		while(current != null) {
			previous = current;
			if (previous.value.compareTo(elem) > 0) {
				current = previous.left;
			} else {
				current = previous.right;
			}
		}

		if (previous == null) {
			root = new Node(elem, null, null, null);
		} else if (previous.value.compareTo(elem) > 0) {
			previous.left = new Node(elem, null, null, previous);
		} else {
			previous.right = new Node(elem, null, null, previous);
		}

		size++;
		return true;
	}


	public T remove(T value) {
		Node node = getNode(value);
		if (node == null)
			return null;
		size--;
		Node removed = remove(node);
		if (removed == null)
			return null;
		return removed.value;
	}

	private Node remove(Node node) {

		Node x;
		Node y;

		// Case 1
		y = successor(node);
		if (y == null)
			y = node;

		// Case 2
		if (y.left != null)
			x = y.left;
		else
			x = y.right;

		if (x != null)
			x.parent = y.parent;

		// Case 3
		if (y.parent == null)
			root = x;
		else if (y == y.parent.left)
			y.parent.left = x;
		else
			y.parent.right = x;

		if (!y.equals(node))
			swap(node, y);

		return y;
	}

	private void swap(Node a, Node b) {
		T temp = a.value;
		a.value = b.value;
		b.value = temp;
	}

	public void clear() {
		this.root = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

}
