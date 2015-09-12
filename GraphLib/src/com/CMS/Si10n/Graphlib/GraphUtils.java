package com.CMS.Si10n.Graphlib;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 
 * @author HQ
 * Utility methods to use with Graphs. Mostly search but some sort functions might be added
 * Both start and goal needs to be in the same AbstractGraph for the search to find a connection.
 */

public class GraphUtils {

	/**
	 * If goal can't be found it will return null.
	 * @param start
	 * @param goal
	 * @return
	 */
	public static <T, E> Collection<T> depth_first_search(T start, T goal, AbstractGraph<T, E> graph){
		LinkedList<T> frontier = new LinkedList<T>();
		HashSet<T> explored = new HashSet<T>();
		HashMap<T,T> backtrack = new HashMap<T,T>();
		frontier.addFirst(start);
		backtrack.put(start, null);
		
		while(!frontier.isEmpty()){
			T c = frontier.removeFirst();
			explored.add(c);
			if(c.equals(goal)){
				break;
			}
			for(T n: graph.getNeighbors(c)){
				if(!explored.contains(n)){
					frontier.addFirst(n);
					backtrack.put(n, c);
				}
			}
		}
		return reconstruct(goal, backtrack,graph);
	}
	/**
	 * If goal can't be found it will return null.
	 * @param start
	 * @param goal
	 * @return
	 */
	public static <T, E> Collection<T> breadth_first_search(T start, T goal, AbstractGraph<T, E> graph){
		LinkedList<T> frontier = new LinkedList<T>();
		HashSet<T> explored = new HashSet<T>();
		HashMap<T,T> backtrack = new HashMap<T,T>();
		frontier.addLast(start);
		backtrack.put(start, null);
		
		while(!frontier.isEmpty()){
			T c = frontier.removeFirst();
			explored.add(c);
			if(c.equals(goal)){
				break;
			}
			for(T n: graph.getNeighbors(c)){
				if(!explored.contains(n)){
					frontier.addLast(n);
					backtrack.put(n, c);
				}
			}
		}
		return reconstruct(goal, backtrack,graph);
	}
	
	/**
	 * Get all nodes accessible from the start node.
	 * @param start
	 * @param graph
	 * @return
	 */
	public static <T, E> Collection<T> flood_fill(T start, AbstractGraph<T,E> graph){
		LinkedList<T> frontier = new LinkedList<T>();
		HashSet<T> explored = new HashSet<T>();
		HashMap<T,T> backtrack = new HashMap<T,T>();
		frontier.addLast(start);
		backtrack.put(start, null);
		
		while(!frontier.isEmpty()){
			T c = frontier.removeFirst();
			explored.add(c);
			for(T n: graph.getNeighbors(c)){
				if(!explored.contains(n)){
					frontier.addLast(n);
					backtrack.put(n, c);
				}
			}
		}
		return explored;
	}
	/**
	 * If the AbstractGraph doesn't have edges recalculated to fit the heuristic, change it!
	 * <br>
	 * @param start
	 * @param goal
	 * @param graph
	 * @param comp Comparator used to order the nodes in the frontier. is a parameter because of dynamics
	 * @return
	 */
	public static <T, E> Collection<T> a_star_search(T start, T goal, AbstractGraph<T,E> graph,Comparator<T> comp){
		PriorityQueue<T> frontier = new PriorityQueue<T>(comp);
		HashSet<T> explored = new HashSet<T>();
		HashMap<T,T> backtrack = new HashMap<T,T>();
		frontier.add(start);
		backtrack.put(start, null);
		
		while(!frontier.isEmpty()){
			T c = frontier.poll();
			explored.add(c);
			if(c.equals(goal)){
				goal = c;
				break;
			}
			for(T n: graph.getNeighbors(c)){
				if(!explored.contains(n)){
					frontier.add(n);
					backtrack.put(n, c);
				}
			}
		}
		return reconstruct(goal, backtrack,graph);
	}
	
	private static <T, E> Collection<T>  reconstruct(T start, HashMap<T,T> backtrack, AbstractGraph<T,E> graph){
		Stack<T> path = new Stack<T>();
		T c = start;
		while(c != null){
			path.push(c);
			c = backtrack.get(c);
		}
		
		return path;
	}
}
