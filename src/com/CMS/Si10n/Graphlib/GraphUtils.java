package com.CMS.Si10n.Graphlib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 
 * @author Sciion Utility methods to use with Graphs.
 */

public class GraphUtils {

    /**
     * Super dirty way of cloning a Graph Shallow copy of the nodes and edges.
     * 
     * @param graph
     * @return null if fail, otherwise the new copy
     */
    public static <T, E, S extends IGraph<T, E>> S copy_graph(S graph) {
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

    /**
     * Finds all directed edges going in to the node t.
     * 
     * @param t
     *            - Node for which the in-edges will be evaluated.
     * @param graph
     * @return collection of nodes with t as adjacent.
     */
    public static <T, E> Collection<T> getInNeighbours(T t, IGraph<T, E> graph) {
	ArrayList<T> in = new ArrayList<T>();
	for (T t1 : graph.getNodes()) {
	    if (graph.hasAdjacent(t1, t))
		in.add(t1);
	}
	return in;
    }

    private static <T, E> void visit(T t, IGraph<T, E> g, HashSet<T> u, HashSet<T> l) {
	if (u.contains(t)) {
	    u.remove(t);
	    for (T t2 : g.getNeighbors(t))
		visit(t2, g, u, l);
	    l.add(t);
	}
    }

    private static <T, E> void assign(T u, T root, IGraph<T, E> g, HashMap<T, HashSet<T>> l) {
	if (!l.values().isEmpty())
	    for (HashSet<T> t : l.values())
		if (t.contains(u))
		    return;
	if (l.containsKey(root))
	    l.get(root).add(u);
	else {
	    l.put(root, new HashSet<T>());
	    l.get(root).add(u);
	}
	for (T v : getInNeighbours(u, g))
	    assign(v, root, g, l);

    }

    /**
     * Algorithm for finding the strongest connected components in a graph
     * 
     * @param graph
     * @return
     */
    public static <T, E> Collection<? extends Collection<T>> kosarajus(IGraph<T, E> graph) {
	HashMap<T, HashSet<T>> sccs = new HashMap<T, HashSet<T>>();
	HashSet<T> L = new HashSet<T>();
	HashSet<T> unvisited = new HashSet<T>();
	for (T t : graph.getNodes()) {
	    unvisited.add(t);
	}
	for (T t : graph.getNodes()) {
	    visit(t, graph, unvisited, L);
	}
	for (T t : L) {
	    assign(t, t, graph, sccs);
	}
	return sccs.values();
    }

    private static <T, E> void strongconnect(T v, IGraph<T, E> g, Stack<T> S, int index,
	    HashMap<T, Integer> defined_index, HashMap<T, Integer> defined_lowlinked, Stack<HashSet<T>> sccs) {
	defined_index.put(v, index);
	defined_lowlinked.put(v, index);
	index++;
	S.push(v);

	for (T w : g.getNeighbors(v)) {
	    if (!defined_index.containsKey(w)) {
		strongconnect(w, g, S, index, defined_index, defined_lowlinked, sccs);
		defined_lowlinked.put(v, Math.min(defined_lowlinked.get(v), defined_lowlinked.get(w)));
	    } else if (S.contains(w)) {
		defined_lowlinked.put(v, Math.min(defined_lowlinked.get(v), defined_index.get(w)));
	    }
	}
	if (defined_lowlinked.get(v) == defined_index.get(v)) {
	    sccs.push(new HashSet<T>());
	    T w;
	    do {
		w = S.pop();
		sccs.peek().add(w);
	    } while (!w.equals(v));
	}
    }

    /**
     * Algorithm for finding the strongest connected components in a graph.
     * 
     * @param graph
     * @return
     */

    public static <T, E> Collection<? extends Collection<T>> tarjan(IGraph<T, E> graph) {
	HashMap<T, Integer> defined_indexes = new HashMap<T, Integer>();
	HashMap<T, Integer> defined_lowlinked = new HashMap<T, Integer>();
	Stack<HashSet<T>> sccs = new Stack<HashSet<T>>();
	Stack<T> S = new Stack<T>();
	int index = 0;
	for (T v : graph.getNodes()) {
	    if (!defined_indexes.containsKey(v)) {
		strongconnect(v, graph, S, index, defined_indexes, defined_lowlinked, sccs);
	    }

	}
	return sccs;

    }

    public static<T,E> Collection<T> topsort(IGraph<T,E> graph){
	IGraph<T,E> g = copy_graph(graph);
	ArrayList<T> L = new ArrayList<T>();
	Stack<T> S = new Stack<T>();
	for(T t: g.getNodes())
	    if(getInNeighbours(t, g).isEmpty())
		S.push(t);
	while(!S.isEmpty()){
	    T n = S.pop();
	    L.add(n);
	    for(T m : graph.getNeighbors(n)){
		 g.deleteEdge(n, m);
		if(getInNeighbours(m, g).isEmpty())
		    S.push(m);
	    }
	}
	boolean success = true;
	for(T t: g.getNodes()){
	    if(!g.getNeighbors(t).isEmpty())
		success = false;
	}
	    
	if(success){
	    return L;
	}
	else   
	return null;
	
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
