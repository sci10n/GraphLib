package com.CMS.Si10n.Graphlib.GraphPropagation;

import java.util.Vector;

import com.CMS.Si10n.Graphlib.Graph;

public class PropagationGraph<T, E> extends Graph<T, E> {

    private Vector<EdgeChangeOperator<T, E>> edgesListeners;
    private Vector<NodeChangeOperator<T>> nodeListeners;

    public PropagationGraph() {
	edgesListeners = new Vector<EdgeChangeOperator<T, E>>();
	nodeListeners = new Vector<NodeChangeOperator<T>>();
    }

    public void addNodeListener(NodeChangeOperator<T> l) {
	if (!nodeListeners.contains(l)) {
	    nodeListeners.add(l);
	}
    }

    public void removeNodeListener(NodeChangeOperator<T> l) {
	if (nodeListeners.contains(l)) {
	    nodeListeners.remove(l);
	}
    }

    public void addEdgeListener(EdgeChangeOperator<T, E> l) {
	if (!edgesListeners.contains(l)) {
	    edgesListeners.add(l);
	}
    }

    public void removeEdgeListener(EdgeChangeOperator<T, E> l) {
	if (edgesListeners.contains(l)) {
	    edgesListeners.remove(l);
	}
    }

    @Override
    public T addNode(T t) {
	T r = super.addNode(t);
	for (NodeChangeOperator<T> l : nodeListeners) {
	    l.onNodeChange(t, GraphChanges.ADD);
	}
	return r;
    }

    @Override
    public void deleteNode(T t) {
	super.deleteNode(t);
	for (NodeChangeOperator<T> l : nodeListeners) {
	    l.onNodeChange(t, GraphChanges.DELETE);
	}
    }

    @Override
    public void setNodeValue(T t, T s) {
	super.setNodeValue(t, s);
	for (NodeChangeOperator<T> l : nodeListeners) {
	    l.onNodeChange(s, GraphChanges.CHANGE);
	}
    }

    @Override
    public void addEdge(T t1, T t2) {
	super.addEdge(t1, t2);
	for (EdgeChangeOperator<T, E> l : edgesListeners) {
	    l.onEdgeChange(t1, t2, null, GraphChanges.ADD);
	}
    }

    @Override
    public void addEdge(T t1, T t2, E e) {
	super.addEdge(t1, t2, e);
	for (EdgeChangeOperator<T, E> l : edgesListeners) {
	    l.onEdgeChange(t1, t2, e, GraphChanges.ADD);
	}
    }

    @Override
    public void deleteEdge(T t1, T t2) {
	E e = super.getEdgeValue(t1, t2);
	super.deleteEdge(t1, t2);
	for (EdgeChangeOperator<T, E> l : edgesListeners) {
	    l.onEdgeChange(t1, t2, e, GraphChanges.DELETE);
	}
    }

    @Override
    public void setEdgeValue(T t1, T t2, E e) {
	super.setEdgeValue(t1, t2, e);
	for (EdgeChangeOperator<T, E> l : edgesListeners) {
	    l.onEdgeChange(t1, t2, e, GraphChanges.CHANGE);
	}
    }

}
