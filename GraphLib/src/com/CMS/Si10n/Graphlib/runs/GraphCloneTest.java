package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;
import com.CMS.Si10n.Graphlib.GraphUtils;

public class GraphCloneTest {
	public void init(){
		Graph<Integer,Integer> graph = new Graph<Integer, Integer>();
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(1, 2, 10);
		graph.addEdge(1, 3, 20);
		graph.addEdge(2, 3, 5);
		Graph<Integer, Integer> second = GraphUtils.copy_graph(graph);
		System.out.println(second.toString()+"\n");
		System.out.println(graph.toString());
		
	}
	
	public static void main(String[] args) {
		GraphCloneTest main = new GraphCloneTest();
		main.init();
	}
}
