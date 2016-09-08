package com.CMS.Si10n.Graphlib.StateMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.CMS.Si10n.Graphlib.Graph;

public class MarkovChain<T> {

    private Graph<T, Double> graph;
    private T currentState;

    public MarkovChain() {
	graph = new Graph<T, Double>();
    }

    public void setCurrentState(T t) {
	currentState = t;
    }

    public void register() {
	HashMap<Double, ArrayList<T>> probs = new HashMap<Double, ArrayList<T>>();
	for (T t1 : graph.getNeighbors(currentState)) {
	    double v = graph.getEdgeValue(currentState, t1);
	    if (probs.containsKey(v)) {
		probs.get(v).add(t1);
	    } else {
		ArrayList<T> vv = new ArrayList<T>();
		vv.add(t1);
		probs.put(v, vv);
	    }
	}
	ArrayList<Double> l_probs = new ArrayList<Double>();
	l_probs.addAll(probs.keySet());
	l_probs.sort((Double d1, Double d2) -> (int) Math.signum(d1 - d2));
	double r = new Random().nextDouble();
	double prev = 0;
	double res = -1;
	for (double d : l_probs) {
	    if (r >= prev && r < prev+d) {
		res = d;
		break;
	    } else {
		prev = prev + d;
	    }
	}
	if (res != -1) {
	    currentState = probs.get(res).get(new Random().nextInt(probs.get(res).size()));
	}
    }

    public T getCurrentState() {
	return currentState;
    }

    public void addState(T t) {
	graph.addNode(t);
    }

    public void removeState(T t) {
	graph.deleteNode(t);
    }

    public void addTransition(T t1, T t2, double prob) {
	graph.addEdge(t1, t2, prob);
    }

    public void removeTransition(T t1, T t2) {
	graph.deleteEdge(t1, t2);
    }
    
    @Override
    public String toString() {
        return graph.toString();
    }
}
