package test.java;

import java.util.ArrayList;

import org.junit.Test;

import com.CMS.Si10n.Graphlib.Graph;
import com.CMS.Si10n.Graphlib.GraphUtils;

import junit.framework.TestCase;

public class AlgorithmTests extends TestCase {

    // =============================================================
    // Tests for the different algorithms implemented in the library
    // =============================================================
    @Test
    public void testGraphCreation() {
	// Create a graph and populate with edges and nodes
	Graph<Integer, Integer> graph = new Graph<Integer, Integer>();

	graph.addNode(1);
	graph.addNode(2);
	graph.addNode(3);
	graph.addNode(4);
	graph.addNode(5);

	graph.addEdge(1, 2, 5);
	graph.addEdge(1, 3, 0);
	graph.addEdge(2, 4, 1);
	graph.addEdge(3, 5, 10);
	graph.addEdge(5, 4, 2);
	// Test DFS
	// Expected {1,3,5,4}
	{
	    ArrayList<Integer> list = new ArrayList<Integer>(GraphUtils.depth_first_search(1, 4, graph));
	    assertTrue(list.size() == 4);
	    assertTrue(list.get(0) == 1);
	    assertTrue(list.get(1) == 3);
	    assertTrue(list.get(2) == 5);
	    assertTrue(list.get(3) == 4);
	}
	// Test BFS
	// Expected {1,2,4}
	{
	    ArrayList<Integer> list = new ArrayList<Integer>(GraphUtils.breadth_first_search(1, 4, graph));
	    assertTrue(list.size() == 3);
	    assertTrue(list.get(0) == 1);
	    assertTrue(list.get(1) == 2);
	    assertTrue(list.get(2) == 4);
	}
	// Test FloodFill
	{
	    ArrayList<Integer> list = new ArrayList<Integer>(GraphUtils.flood_fill(1, graph));
	    assertTrue(list.size() == 5);
	    for (int i : list) {
		assertTrue(graph.containsNode(i));
	    }
	}
	// Test A*
	{
	    ArrayList<Integer> list = new ArrayList<Integer>(GraphUtils.a_star_search(1, 4, graph, (Integer a,Integer b) ->{
		return graph.getEdgeValue(a, b) == null ? Integer.MAX_VALUE : graph.getEdgeValue(a, b);
	    }));
	    assertTrue(list.size() == 3);
	    assertTrue(list.get(0) == 1);
	    assertTrue(list.get(1) == 2);
	    assertTrue(list.get(2) == 4);
	}
    }
}
