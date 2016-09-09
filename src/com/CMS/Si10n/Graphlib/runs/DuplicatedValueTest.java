package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;

public class DuplicatedValueTest {

    public class Node<T> {
	public T value;

	public Node(T value) {
	    this.value = value;
	}

	@Override
	public String toString() {

	    return value.toString();
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Node<?>) {
		return ((Node<?>) obj).value.equals(value) && super.equals(obj);
	    }
	    return super.equals(obj);
	}
    }

    public void init() {
	Graph<Node<Integer>, Integer> graph = new Graph<Node<Integer>, Integer>();
	Node<Integer> n1 = graph.addNode(new Node<Integer>(1));
	Node<Integer> n2 = graph.addNode(new Node<Integer>(1));
	Node<Integer> n3 = graph.addNode(new Node<Integer>(2));
	Node<Integer> n4 = graph.addNode(new Node<Integer>(3));
	System.out.println(graph.toString());
	System.out.println("Values in graph: " + n1 + " " + n2 + " " + n3 + " " + n4);
	System.out.println("Nodes: " + Integer.toHexString(n1.hashCode()) + " " + Integer.toHexString(n2.hashCode())
		+ " " + Integer.toHexString(n3.hashCode()) + " " + Integer.toHexString(n4.hashCode()));
    }

    public static void main(String[] args) {
	DuplicatedValueTest main = new DuplicatedValueTest();
	main.init();
    }
}
