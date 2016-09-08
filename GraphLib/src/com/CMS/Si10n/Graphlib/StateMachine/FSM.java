package com.CMS.Si10n.Graphlib.StateMachine;

import com.CMS.Si10n.Graphlib.Graph;

public class FSM<T, E> {

    private Graph<T, E> graph;
    private T currentState;

    public FSM() {
	graph = new Graph<T, E>();
    }

    public void setCurrentState(T t) {
	currentState = t;
    }

    public void register(E e) {
	for (T t : graph.getNeighbors(currentState)) {
	    if (graph.getEdgeValue(currentState, t).equals(e)) {
		currentState = t;
		return;
	    }
	}
    }
    
    public T getCurrentState(){
	return currentState;
    }
    
    public void addState(T t) {
	graph.addNode(t);
    }

    public void removeState(T t) {
	graph.deleteNode(t);
    }

    public void addTransition(T t1, T t2, E e) {
	graph.addEdge(t1, t2, e);
    }

    public void removeTransition(T t1, T t2) {
	graph.deleteEdge(t1, t2);
    }
}
