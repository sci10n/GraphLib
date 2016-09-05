package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.GraphPropagation.GraphChanges;
import com.CMS.Si10n.Graphlib.GraphPropagation.PropagationGraph;

public class PrintPropagationGraph {

    private static class Node {
	private int value;
	private int id;

	public Node(int v, int id) {
	    this.value = v;
	    this.id = id;
	}

	@Override
	public String toString() {
	    return "" + id;
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
	    Node obj2 = (Node) obj;
	    if (obj2 != null) {
		return obj2.id == id;
	    }

	    return false;
	}
    }

    // ======================
    // Outputs the graph in .csv format with all edges given a timestep
    // ======================
    private static int timeline = 0;
    private static void graph2CSV(PropagationGraph<Node, Integer> graph) {
	    for(Node n1: graph.getEdges().keySet()){
	        for(Node n2: graph.getEdges().get(n1).keySet()){
	    	System.out.println(n1.toString() + "," + n2.toString() + "," + graph.getEdges().get(n1).get(n2).toString() + "," + timeline);
	    	} 
	    }
    }

    public static void main(String[] args) {


	    System.out.println("source,target,weight,timestep");

	    PropagationGraph<Node, Integer> graph = new PropagationGraph<Node, Integer>();

	    // ======================
	    // Creating topology
	    // ======================
	    Node node1 = graph.addNode(new Node(0, 1));
	    Node node2 = graph.addNode(new Node(0, 2));
	    Node node3 = graph.addNode(new Node(0, 3));
	    graph.addEdge(node1, node2, 0);
	    graph.addEdge(node2, node3, 0);
	    graph.addEdge(node3, node1, 0);
	    
	    // ======================
	    // Adding graph listeners
	    // ======================
	    graph.addNodeListener((t1, c) -> {
		    if (c == GraphChanges.CHANGE) {
			for (Node n : graph.getNeighbors(t1)) {
			    graph.setEdgeValue(t1, n, t1.value);
			}
		    }

	    });
	    graph.addEdgeListener((t1, t2, e, c) -> {
		timeline++;
		if(e >= 10)
		    return;
		    graph2CSV(graph);
		    if (c == GraphChanges.CHANGE) {
			    t2.value = e +1;
			    graph.setNodeValue(t2, t2);
		    }

	    });
	    
	    //Prints the first timestep
	    graph2CSV(graph);
	    //Start the graph propagation
	    graph.setNodeValue(node1, new Node(1, node1.id));
	    //Prints the resulting graph
	    graph2CSV(graph);
    }
}
