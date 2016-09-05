package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.GraphPropagation.GraphChanges;
import com.CMS.Si10n.Graphlib.GraphPropagation.PropagationGraph;

public class GraphPropagationTest {

    //=============================
    //Wrapper class for Graph Nodes
    //=============================
    private static class Node {
	private int id;

	public Node(int id) {
	    this.id = id;
	}

	@Override
	public String toString() {
	    return ""+this.id;
	}
	
	@Override
	public int hashCode() {
	    return Integer.hashCode(id);
	}
    }

    public static void main(String[] args) {

	PropagationGraph<Node, Integer> graph = new PropagationGraph<Node, Integer>();
	
	//======================
	//Adding graph listeners
	//======================
	graph.addNodeListener((t1, c) -> {
	    if (c == GraphChanges.CHANGE) {
		for (Node n : graph.getNeighbors(t1)) {
		    graph.setEdgeValue(t1, n, 1);
		}
	    }

	});
	graph.addEdgeListener((t1, t2, e, c) -> {
	    if (c == GraphChanges.CHANGE) {
		    graph.setNodeValue(t2, new Node(t2.id));
	    }
	    
	});
	
	//======================
	//Creating topology
	//======================
	Node node1 = graph.addNode(new Node(1));
	Node node2 = graph.addNode(new Node(2));
	Node node3 = graph.addNode(new Node(3));
	Node node4 = graph.addNode(new Node(4));
	Node node5 = graph.addNode(new Node(5));
	Node node6 = graph.addNode(new Node(6));
	Node node7 = graph.addNode(new Node(7));
	Node node8 = graph.addNode(new Node(8));
	graph.addEdge(node1, node2, 0);
	graph.addEdge(node1, node3, 0);
	graph.addEdge(node2, node4, 0);
	graph.addEdge(node3, node5, 0);
	graph.addEdge(node5, node6, 0);
	graph.addEdge(node3, node6, 0);
	graph.addEdge(node6, node7, 0);
	graph.addEdge(node6, node8, 0);
	
	System.out.println(graph);
	graph.setNodeValue(node3, new Node(node3.id));
	System.out.println(graph);
    }
}
