package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;
import com.CMS.Si10n.Graphlib.GraphUtils;

public class HeuristicGraphTest {
	
	public class Vec2{
		public double t1;
		public double t2;
		public Vec2(double t1, double t2){
			this.t1 = t1;
			this.t2 = t2;
		}
		public double dist(Vec2 t){
			double dx = Math.abs(t.t1 - t1);
			double dy = Math.abs(t.t2 - t2);
			return Math.sqrt(dx*dx + dy*dy);
		}
		@Override
		public String toString() {
			return "("+t1 + "," + t2 + ")";
		}
	}
	
	public void init(){
		Graph<Vec2,Double> graph = new Graph<Vec2, Double>();
		/*
		 * GraphRep:
		 * n0 n1
		 * n2 n3
		 * 
		 * with:
		 * (0,0) -- (1,0)
		 *   |        |
		 * (0,1) -- (1,1)
		 * 
		 */
		Vec2 n0 = new Vec2(0.0, 0.0);
		Vec2 n1 = new Vec2(10.0, 0.0);
		Vec2 n2 = new Vec2(0.0, 10.0);
		Vec2 n3 = new Vec2(10.0, 10.0);
		graph.addNode(n0);
		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		
		graph.addEdge(n0,n1);
		graph.addEdge(n0,n2);
		
		graph.addEdge(n1,n0);
		graph.addEdge(n1,n3);
		
		graph.addEdge(n2,n0);
		graph.addEdge(n2,n3);
		
		graph.addEdge(n3,n1);
		graph.addEdge(n3,n2);
		
		System.out.println(graph.toString());
		
		//Euclidean distance to neighbors
		graph.forEachEdge((a,b) -> Math.sqrt(Math.pow(Math.abs(a.t1 - b.t1),2) + (Math.pow(Math.abs(a.t2 - b.t2),2))));
		
		System.out.println();
		System.out.println(graph.toString());
		System.out.println("Path");
		
		for(Vec2 v: GraphUtils.a_star_search(n0, n3, graph,((a,b) -> graph.getEdgeValue(a, b) == null ? 0:graph.getEdgeValue(a, b).intValue()))){
			System.out.println(v.toString());
		}
		
		//Euclidean distance to n3
		graph.forEachEdge((a,b) -> Math.sqrt(Math.pow(Math.abs(a.t1 - n3.t1),2) + (Math.pow(Math.abs(a.t2 - n3.t2),2))));
		//Notice how the distance between n0 and n1 is the distance from n0 to n3 regardless of no edge between n0 and n3
		System.out.println();
		System.out.println(n0.toString() + " -> "+ n1.toString() + " = " + graph.getEdgeValue(n0, n1));
	}
	
	public static void main(String[] args) {
		HeuristicGraphTest main = new HeuristicGraphTest();
		main.init();
	}
}
