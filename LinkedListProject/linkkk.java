package project1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class linkkk<T> {

	 Node<T> head;

	public linkkk() {
		head = null;
	}

	public void append(T data) {
		Node<T> newNode = new Node<>(data);
		if (isEmpty()) {
			head = newNode;
		} else {
			getLastNode().next = newNode;
		}
	}

	public void prepend(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.next = head;
		head = newNode;
	}

	public void delete(T data) {
		if (isEmpty()) {
			return;
		}
		if (head.data.equals(data)) {
			head = head.next;
		} else {
			Node<T> current = head;
			while (current.next != null && !current.next.data.equals(data)) {
				current = current.next;
			}
			if (current.next != null) {
				current.next = current.next.next;
			}
		}
	}

	public void clear() {
		head = null;
	}

	public void printList() {
		Node<T> current = head;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();
	}

	private boolean isEmpty() {
		return head == null;
	}

	private Node<T> getLastNode() {
		Node<T> current = head;
		while (current.next != null) {
			current = current.next;
		}
		return current;
	}

}
