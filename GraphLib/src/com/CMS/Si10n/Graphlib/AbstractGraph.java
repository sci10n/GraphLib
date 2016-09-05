package com.CMS.Si10n.Graphlib;

import java.util.Collection;

public class AbstractGraph<T, S> implements IGraph<T, S> {

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
    public void setNodeValue(T t, T s) {
    }

    @Override
    public void deleteEdge(T t1, T t2) {
    }

    @Override
    public boolean containsNode(T t) {
	return false;
    }

    @Override
    public boolean containsEdge(T t1, T t2) {
	return false;
    }

    @Override
    public void addEdge(T t1, T t2, S e) {

    }

    @Override
    public void forEachEdge(GraphEdgeOperator<T, S> op) {

    }

    @Override
    public void forEachNode(GraphNodeOperator<T> op) {

    }

}
