package com.CMS.Si10n.Graphlib.StateSearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.CMS.Si10n.Graphlib.AbstractGraph;
import com.CMS.Si10n.Graphlib.GraphEdgeOperator;
import com.CMS.Si10n.Graphlib.IGraph;

public class Puzzle8 extends AbstractGraph<State8Puzzle, Integer> implements IGraph<State8Puzzle, Integer>{

	public State8Puzzle goal;
	public Puzzle8(State8Puzzle goal) {
		this.goal = goal;
	}
	@Override
	public Collection<State8Puzzle> getNeighbors(State8Puzzle t) {
		ArrayList<State8Puzzle> s = new ArrayList<State8Puzzle>();
		for(Action a: t.actions()){
			State8Puzzle p = new State8Puzzle(t);
			p.cost = t.cost+ p.dist(goal);
			p.performAction(a);
			s.add(p);
		}
		return s;
	}

}
