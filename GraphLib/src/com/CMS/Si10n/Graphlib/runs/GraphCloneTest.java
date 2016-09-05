package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;
import com.CMS.Si10n.Graphlib.GraphUtils;

class Node{
	public int value;
	public Node(int value){
		this.value = value;
	}
	@Override
	public String toString() {
		return ""+value;
	}
}

public class GraphCloneTest {
	public void init(){
		Node n1 = new Node(1);
		Node n2 = new Node(5);
		Graph<Node,Integer> graph = new Graph<Node, Integer>();
		graph.addNode(n1);
		graph.addNode(n2);

		Graph<Node, Integer> second = GraphUtils.copy_graph(graph);
		System.out.println(second.toString());
		System.out.println(graph.toString());
		System.out.println("Setting node 1 to 10");
		n1.value = 10;
		System.out.println(second.toString());
		System.out.println(graph.toString());
		
	}
	
	public static void main(String[] args) {
		GraphCloneTest main = new GraphCloneTest();
		main.init();
	}
}
