package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;

public class UniformCostGraphTest {

	public void init(){
		Graph<Integer,Integer> graph = new Graph<Integer, Integer>();
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(10);
		graph.addEdge(1,2);
		graph.addEdge(1,3);
		graph.addEdge(3,2);
		graph.addEdge(2,10);

		//Instead of setting all edges manually
		graph.forEachEdge((a,b,e) -> 1);
		System.out.println(graph.toString());
		
	}
	
	public static void main(String[] args) {
		UniformCostGraphTest main = new UniformCostGraphTest();
		main.init();
	}
}
