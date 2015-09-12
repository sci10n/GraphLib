package com.CMS.Si10n.Graphlib.StateSearch;

import java.util.Collection;
import java.util.HashSet;

import com.CMS.Si10n.Graphlib.AbstractGraph;
import com.CMS.Si10n.Graphlib.GraphEdgeOperator;

public class Puzzle8 implements AbstractGraph<State8Puzzle, Integer>{


	private StateSpace8Puzzle stateSpace;
	public Puzzle8() {
		stateSpace = new StateSpace8Puzzle();
		}
	@Override
	public boolean hasAdjacent(State8Puzzle t1, State8Puzzle t2) {
		return false;
	}


	@Override
	public Collection<State8Puzzle> getNeighbors(State8Puzzle t) {
		HashSet<State8Puzzle> s = new HashSet<State8Puzzle>();
		for(Action a: stateSpace.actions(t)){
			s.add(stateSpace.result(a, t));
		}
		return s;
	}

	@Override
	public State8Puzzle addNode(State8Puzzle t) {
		return t;
	}

	@Override
	public void deleteNode(State8Puzzle t) {
		
	}

	@Override
	public <S> State8Puzzle getNodeReference(S s) {
		return null;
	}

	@Override
	public void addEdge(State8Puzzle t1, State8Puzzle t2) {
		
	}

	@Override
	public void delete(State8Puzzle t1, State8Puzzle t2) {
		
	}

	@Override
	public Integer getEdgeValue(State8Puzzle t1, State8Puzzle t2) {
		return null;
	}

	@Override
	public void setEdgeValue(State8Puzzle t1, State8Puzzle t2, Integer e) {
		
	}

	@Override
	public void setEdgeCalculator(GraphEdgeOperator<State8Puzzle, Integer> op) {
		
	}

	@Override
	public Collection<State8Puzzle> getNodes() {
		return null;
	}

}
