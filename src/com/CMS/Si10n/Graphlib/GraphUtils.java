package com.CMS.Si10n.Graphlib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 
 * @author Sciion
 * 	   Utility methods to use with Graphs.
 */

public class GraphUtils {

    /**
     * Super dirty way of cloning a Graph
     * Shallow copy of the nodes and edges.
     * 
     * @param graph
     * @return null if fail, otherwise the new copy
     */
    public static <T, E, S extends AbstractGraph<T, E>> S copy_graph(S graph) {
	try {
	    S ret = (S) graph.getClass().newInstance();
	    graph.forEachNode(((a) -> ret.addNode(a)));
	    graph.forEachEdge((a, b, e) -> {
		ret.addEdge(a, b, graph.getEdgeValue(a, b));
		return graph.getEdgeValue(a, b);
	    });
	    return ret;
	} catch (InstantiationException | IllegalAccessException e) {
	    e.printStackTrace();
	}
	return null;

    }

    /**
     * If goal can't be found it will return null.
     * 
     * @param start
     * @param goal
     * @return
     */
    public static <T, E> Collection<T> depth_first_search(T start, T goal, AbstractGraph<T, E> graph) {
	LinkedList<T> frontier = new LinkedList<T>();
	HashSet<T> explored = new HashSet<T>();
	HashMap<T, T> backtrack = new HashMap<T, T>();
	frontier.addFirst(start);
	backtrack.put(start, null);

	while (!frontier.isEmpty()) {
	    T c = frontier.removeFirst();
	    explored.add(c);
	    if (c.equals(goal)) {
		break;
	    }
	    for (T n : graph.getNeighbors(c)) {
		if (!explored.contains(n)) {
		    frontier.addFirst(n);
		    backtrack.put(n, c);
		}
	    }
	}
	return reconstruct(goal, backtrack);
    }

    /**
     * If goal can't be found it will return null.
     * 
     * @param start
     * @param goal
     * @return
     */
    public static <T, E> Collection<T> breadth_first_search(T start, T goal, AbstractGraph<T, E> graph) {
	LinkedList<T> frontier = new LinkedList<T>();
	HashSet<T> explored = new HashSet<T>();
	HashMap<T, T> backtrack = new HashMap<T, T>();
	frontier.addLast(start);
	backtrack.put(start, null);

	while (!frontier.isEmpty()) {
	    T c = frontier.removeFirst();
	    explored.add(c);
	    if (c.equals(goal)) {
		break;
	    }
	    for (T n : graph.getNeighbors(c)) {
		if (!explored.contains(n)) {
		    frontier.addLast(n);
		    backtrack.put(n, c);
		}
	    }
	}
	return reconstruct(goal, backtrack);
    }

    /**
     * Get all nodes accessible from the start node.
     * 
     * @param start
     * @param graph
     * @return
     */
    public static <T, E> Collection<T> flood_fill(T start, AbstractGraph<T, E> graph) {
	LinkedList<T> frontier = new LinkedList<T>();
	HashSet<T> explored = new HashSet<T>();
	HashMap<T, T> backtrack = new HashMap<T, T>();
	frontier.addLast(start);
	backtrack.put(start, null);

	while (!frontier.isEmpty()) {
	    T c = frontier.removeFirst();
	    explored.add(c);
	    for (T n : graph.getNeighbors(c)) {
		if (!explored.contains(n)) {
		    frontier.addLast(n);
		    backtrack.put(n, c);
		}
	    }
	}
	return explored;
    }

    /**
     * 
     * @param start
     * @param goal
     * @param graph
     * @param comp
     *            Comparator used to order the nodes in the frontier.
     * @return
     */
    public static <T, E> Collection<T> a_star_search(T start, T goal, IGraph<T, E> graph, Comparator<T> comp) {
	PriorityQueue<T> frontier = new PriorityQueue<T>(comp);
	LinkedList<T> explored = new LinkedList<T>();
	HashMap<T, T> backtrack = new HashMap<T, T>();
	frontier.add(start);
	backtrack.put(start, null);

	while (!frontier.isEmpty()) {
	    T c = frontier.poll();
	    explored.add(c);

	    if (c.equals(goal)) {
		goal = c;
		break;
	    }
	    for (T n : graph.getNeighbors(c)) {
		if (!explored.contains(n) && !frontier.contains(n)) {
		    frontier.add(n);
		    backtrack.put(n, c);
		}
	    }
	}
	return reconstruct(goal, backtrack);
    }
    
    private static <T, E> void visit(T t,IGraph<T,E> g, HashSet<T> u,HashSet<T> l){
	if(u.contains(t)){
	    u.remove(t);
	    for(T t2: g.getNeighbors(t))
		visit(t2,g,u,l);
	    l.add(t);
	}
    }
    
    public static<T, E> Collection<T> getInNeighbours(T t, IGraph<T,E> graph){
	ArrayList<T> in = new ArrayList<T>();
	for(T t1: graph.getNodes()){
	    if(graph.hasAdjacent(t1, t))
		in.add(t1);
	}
	return in;
    }
    private static<T, E> void assign(T u, T root,IGraph<T,E> g, HashMap<T,HashSet<T>> l){
	if(!l.values().isEmpty())
	for(HashSet<T> t: l.values())
	    if(t.contains(u))
		return;
	    if(l.containsKey(root))
		l.get(root).add(u);
	    else{
		l.put(root, new HashSet<T>());
		l.get(root).add(u);
	    }
	    for(T v: getInNeighbours(u, g))
		assign(v,root, g, l);
	   
    }
    public static<T, E> Collection<? extends Collection<T>> kosarajus_algorithm(IGraph<T,E> graph){
	HashMap<T,HashSet<T>> sccs = new HashMap<T,HashSet<T>>();
	HashSet<T> L = new HashSet<T>();
	HashSet<T> unvisited = new HashSet<T>();
	for(T t: graph.getNodes()){
	    unvisited.add(t);
	}
	for(T t: graph.getNodes()){
	    visit(t,graph,unvisited,L);
	}
	for(T t: L){
	    assign(t,t,graph,sccs);
	}
	return sccs.values();
    }
    private static <T, E> Collection<T> reconstruct(T start, HashMap<T, T> backtrack) {
	LinkedList<T> path = new LinkedList<T>();
	T c = start;
	while (c != null) {
	    path.addFirst(c);
	    c = backtrack.get(c);
	}

	return path;
    }

}
