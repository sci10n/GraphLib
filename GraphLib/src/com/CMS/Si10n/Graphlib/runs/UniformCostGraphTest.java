package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;

public class UniformCostGraphTest {

	public void init(){
		Graph<Integer,Integer> graph = new Graph<Integer, Integer>();
		Integer n0 = new Integer(1);
		Integer n1 = new Integer(2);
		Integer n2 = new Integer(3);
		Integer n3 = new Integer(4);
		Integer n4 = new Integer(5);
		graph.addNode(n0);
		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		graph.addNode(n4);
		graph.addEdge(n0,n1);
		graph.addEdge(n0,n2);
		graph.addEdge(n1,n3);
		graph.addEdge(n2,n3);
		graph.addEdge(n0,n4);
		
		//Instead of setting all edges manually
		graph.setEdgeCalculator((a,b) -> 1);
		
		System.out.println(graph.toString());
		
		
		


	}
	
	public static void main(String[] args) {
		UniformCostGraphTest main = new UniformCostGraphTest();
		main.init();
	}
}
