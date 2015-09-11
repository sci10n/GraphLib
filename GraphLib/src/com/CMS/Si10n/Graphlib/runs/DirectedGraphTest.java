package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;
import com.CMS.Si10n.Graphlib.GraphSearch;

public class DirectedGraphTest {

	public void init(){
		Graph<Integer,Integer> graph = new Graph<Integer, Integer>();
		/*
		 * GraphRep:
		 * with:
		 * 		   (2)
		 *(5)<-(1)<   >(4)
		 * 	       (3)
		 * 
		 */
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1,2);
		graph.addEdge(1,3);
		graph.addEdge(1,5);
		graph.addEdge(2,4);
		graph.addEdge(3,4);
		
		System.out.println(graph.toString());
		graph.setEdgeCalculator((a,b) -> a+b);
		
		System.out.println("\n");
		System.out.println(graph.toString());
		graph.setEdgeCalculator((a,b) -> a);
		
		System.out.println("\n");
		System.out.println(graph.toString());
		
		System.out.println("\nFloodFill from 2");
		for(Integer i: GraphSearch.flood_fill(2, graph))
			System.out.println(i);
		
		System.out.println("\nFloodFill from 1");
		for(Integer i: GraphSearch.flood_fill(1, graph))
			System.out.println(i);
		
		System.out.println("\nBFS from 1 to 4");
		for(Integer i: GraphSearch.breadth_first_search(1, 4, graph))
			System.out.println(i);
	}
	
	public static void main(String[] args) {
		DirectedGraphTest main = new DirectedGraphTest();
		main.init();
	}
}
