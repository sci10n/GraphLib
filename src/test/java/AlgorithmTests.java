package test.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import com.CMS.Si10n.Graphlib.Graph;
import com.CMS.Si10n.Graphlib.GraphUtils;

import junit.framework.TestCase;

public class AlgorithmTests extends TestCase {

    // =============================================================
    // Tests for the different algorithms implemented in the library
    // =============================================================
    @Test
    public void testGraphAlgorithms() {
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
	    ArrayList<Integer> list = new ArrayList<Integer>(
		    GraphUtils.a_star_search(1, 4, graph, (Integer a, Integer b) -> {
			return graph.getEdgeValue(a, b) == null ? Integer.MAX_VALUE : graph.getEdgeValue(a, b);
		    }));
	    assertTrue(list.size() == 3);
	    assertTrue(list.get(0) == 1);
	    assertTrue(list.get(1) == 2);
	    assertTrue(list.get(2) == 4);
	}
	// Test Kosaraju's and Tarjan's algorithm
	{
	    Graph<Character, Integer> sccGraph = new Graph<Character, Integer>();
	    sccGraph.addNode('a');
	    sccGraph.addNode('b');
	    sccGraph.addNode('c');
	    sccGraph.addNode('d');
	    sccGraph.addNode('e');
	    sccGraph.addNode('f');
	    sccGraph.addNode('g');
	    sccGraph.addNode('h');

	    sccGraph.addEdge('a', 'b');
	    sccGraph.addEdge('b', 'f');
	    sccGraph.addEdge('b', 'c');
	    sccGraph.addEdge('b', 'e');
	    sccGraph.addEdge('c', 'g');
	    sccGraph.addEdge('c', 'd');
	    sccGraph.addEdge('d', 'c');
	    sccGraph.addEdge('d', 'h');
	    sccGraph.addEdge('e', 'a');
	    sccGraph.addEdge('e', 'f');
	    sccGraph.addEdge('f', 'g');
	    sccGraph.addEdge('g', 'f');
	    sccGraph.addEdge('h', 'g');
	    sccGraph.addEdge('h', 'd');

	    HashSet<Character> c1 = new HashSet<Character>();
	    c1.add('a');
	    c1.add('b');
	    c1.add('e');
	    HashSet<Character> c2 = new HashSet<Character>();
	    c2.add('c');
	    c2.add('d');
	    c2.add('h');
	    HashSet<Character> c3 = new HashSet<Character>();
	    c3.add('f');
	    c3.add('g');
	    int success = 0;
	    for (Collection<Character> scc : GraphUtils.kosarajus(sccGraph)) {
		if (scc.containsAll(c1) || scc.containsAll(c2) || scc.containsAll(c3))
		    success++;
	    }
	    assertEquals("Kosarajus algorithm did not find the correct SCCs!", 3, success);
	    success = 0;
	    for (Collection<Character> scc : GraphUtils.tarjan(sccGraph)) {
		if (scc.containsAll(c1) || scc.containsAll(c2) || scc.containsAll(c3))
		    success++;
	    }
	    assertEquals("Tarjans algorithm did not find the correct SCCs!", 3, success);
	}
	// Test Topological Sort
	{
	    
	    Graph<Character, Integer> topGraph = new Graph<Character, Integer>();
	    topGraph.addNode('a');
	    topGraph.addNode('b');
	    topGraph.addNode('c');
	    topGraph.addNode('d');
	    topGraph.addNode('e');
	    topGraph.addNode('f');

	    topGraph.addEdge('a', 'b');
	    topGraph.addEdge('a', 'd');
	    topGraph.addEdge('b', 'c');
	    topGraph.addEdge('c', 'd');
	    topGraph.addEdge('c', 'e');
	    topGraph.addEdge('d', 'e');

	    ArrayList<Character> c1 = new ArrayList<Character>();
	    c1.add('f');
	    c1.add('a');
	    c1.add('b');
	    c1.add('c');
	    c1.add('d');
	    c1.add('e');
	    ArrayList<Character> r = (ArrayList<Character>) GraphUtils.topsort(topGraph);
	    assertTrue(c1.size() == r.size());
	    for (int i = 0; i < c1.size(); i++) {
		assertTrue(c1.get(i) == r.get(i));
	    }
	}
    }
}
