package project1;

import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Node<T> {
	T data;
	Node<T> next;

	public Node(T data) {
		this.data = data;
		this.next = null;
	}
}
