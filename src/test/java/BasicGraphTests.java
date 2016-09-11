package test.java;
import org.junit.Test;

import com.CMS.Si10n.Graphlib.Graph;

import junit.framework.TestCase;

public class BasicGraphOoperations extends TestCase{

    //=============================================
    //Tests graph creation and basic data functions
    //=============================================
    @Test
    public void testGraphCreation(){
	//Create a graph and check if it's empty
	Graph<Integer,Integer> graph = new Graph<Integer,Integer>();
	assertTrue("Graph not empty on instansiation!", graph.getNodes().isEmpty());
	
	//Testing adding data to the graph
	
	//Add a nodes to the graph
	graph.addNode(1);
	graph.addNode(2);
	assertTrue("Node 1 not added to the graph!", graph.containsNode(1));
	assertTrue("Node 2 not added to the graph!", graph.containsNode(2));
	
	//Add a faulty edge to the graph
	graph.addEdge(1, 0);
	assertFalse("Edge between existing and non-existing node!", graph.containsEdge(1, 0));
	
	//Add correct looping edge to the graph
	graph.addEdge(1, 1);
	assertTrue("Correct looping edge not added to graph!", graph.containsEdge(1, 1));
	
	//Add correct edge between two different nodes
	graph.addEdge(1, 2);
	assertTrue("Correct edge not added to graph!", graph.containsEdge(1, 2));
	graph.addEdge(2, 1);
	assertTrue("Correct edge not added to graph!", graph.containsEdge(1, 2));
	
	//Testing deleting data from the graph
	
	//Delete non-existent node from the graph
	graph.deleteNode(0);
	assertFalse("Graph contains deleted non-existing node!", graph.containsNode(0));
	
	//Delete existing node from the graph
	graph.deleteNode(1);
	assertFalse("Deleted node in graph!", graph.containsNode(1));
	
	//Double delete the same node
	graph.deleteNode(1);
	assertFalse("Deleted node in graph!", graph.containsNode(1));
	
	//Check for dangling edges
	assertTrue("Dangling edges after node deletion!", graph.getNeighbors(1).isEmpty());
	
	//Delete non-existing edge from the graph
	graph.deleteEdge(2, 3);
	assertFalse("Graph contains deleted non-existing edge!", graph.containsEdge(2, 3));
	
	//Delete the final node form the graph
	graph.deleteNode(2);
	assertFalse("Deleted node in graph!", graph.containsNode(2));
	
    }
}
