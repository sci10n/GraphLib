package com.CMS.Si10n.Graphlib;

import java.util.Collection;

public class AbstractGraph<T,S> implements IGraph<T,S>{

	@Override
	public boolean hasAdjacent(T t1, T t2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<T> getNeighbors(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addNode(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNode(T t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addEdge(T t1, T t2) {
		// TODO Auto-generated method stub
	}

	@Override
	public S getEdgeValue(T t1, T t2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEdgeValue(T t1, T t2, S e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setNodeValue(T t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteEdge(T t1, T t2) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean containsNode(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsEdge(T t1, T t2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEdge(T t1, T t2, S e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachEdge(GraphEdgeOperator<T, S> op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachNode(GraphNodeOperator<T> op) {
		// TODO Auto-generated method stub
		
	}


}
