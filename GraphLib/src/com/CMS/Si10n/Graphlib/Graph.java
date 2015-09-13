package com.CMS.Si10n.Graphlib;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
/**
 * 
 * @author Sci10n
 *
 * @param <T>
 * @param <E>
 */
public class Graph<T,E> extends AbstractGraph<T,E>{
	
	private HashMap<T,HashMap<T,E>> edges;
	private HashSet<T> nodes;
	
	public Graph(){
		edges = new HashMap<T, HashMap<T,E>>();
		nodes = new HashSet<T>();
	}

	@Override
	public boolean hasAdjacent(T t1, T t2) {
		if(edges.get(t1) == null)
			return false;
		return edges.get(t1).containsKey(t2);
	}

	@Override
	public Collection<T> getNeighbors(T t) {
		HashSet<T> tmp = new HashSet<T>(); 
		if(edges.get(t) != null)
			tmp.addAll(edges.get(t).keySet());
		return tmp;
	}

	@Override
	public boolean containsNode(T t) {
		return nodes.contains(t);
	}
	@Override
	public T addNode(T t) {
		if(!nodes.contains(t)){
			nodes.add(t);
		}
		return t;
	}

	@Override
	public void deleteNode(T t) {
		if(nodes.contains(t)){
			nodes.remove(t);
		}
	}
	
	@Override
	public boolean containsEdge(T t1, T t2) {
		return edges.containsKey(t1) && edges.get(t1).containsKey(t2);
	}
	
	@Override
	public void forEachNode(GraphNodeOperator<T> op) {
		for(T n1: nodes){
			setNodeValue(op.process(n1));
		}
	}
	
	@Override
	public void forEachEdge(GraphEdgeOperator<T, E> op) {
		for(T n1: edges.keySet() )
			for(T n2: edges.get(n1).keySet()){
				setEdgeValue(n1, n2, op.process(n1,n2));
			}
	}
	@Override
	public void addEdge(T t1, T t2, E e) {
		if(!edges.containsKey(t1))
			edges.put(t1, new HashMap<T, E>());
		if(!edges.get(t1).containsKey(t2))
			edges.get(t1).put(t2,e);
	}
	
	@Override
	public void addEdge(T t1, T t2) {
		if(!edges.containsKey(t1))
			edges.put(t1, new HashMap<T, E>());
		if(!edges.get(t1).containsKey(t2))
			edges.get(t1).put(t2,null);
	}

	@Override
	public void deleteEdge(T t1, T t2) {
		edges.get(t1).remove(t2);
	}
	
	@Override
	public E getEdgeValue(T t1, T t2) {
		if(edges.get(t1) == null){
			return null;
		}
		return edges.get(t1).get(t2);
	}

	@Override
	public void setEdgeValue(T t1, T t2, E e) {
		edges.get(t1).put(t2, e);
	}


	@Override
	public String toString() {
		return super.toString() + "\n" + nodes.toString() + "\n" + edges.toString();
	}
}
