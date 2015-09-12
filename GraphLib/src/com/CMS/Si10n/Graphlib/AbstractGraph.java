package com.CMS.Si10n.Graphlib;

import java.util.Collection;

public class AbstractGraph<T,S> implements IGraph<T,S>{

	@Override
	public boolean hasAdjacent(T t1, T t2) {
		return false;
	}

	@Override
	public Collection<T> getNeighbors(T t) {
		return null;
	}

	@Override
	public T addNode(T t) {
		return null;
	}

	@Override
	public void deleteNode(T t) {
	}

	@Override
	public void addEdge(T t1, T t2) {
		
	}

	@Override
	public S getEdgeValue(T t1, T t2) {
		return null;
	}

	@Override
	public void setEdgeValue(T t1, T t2, S e) {
		
	}

	@Override
	public void setEdgeCalculator(GraphEdgeOperator<T, S> op) {
		
	}

	@Override
	public void setNodeValue(T t) {

	}

	@Override
	public void deleteEdge(T t1, T t2) {

	}


}
