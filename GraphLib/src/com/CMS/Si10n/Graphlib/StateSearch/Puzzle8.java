package com.CMS.Si10n.Graphlib.StateSearch;

import java.util.Collection;
import java.util.HashSet;

import com.CMS.Si10n.Graphlib.AbstractGraph;
import com.CMS.Si10n.Graphlib.GraphEdgeOperator;
import com.CMS.Si10n.Graphlib.IGraph;

public class Puzzle8 extends AbstractGraph<State8Puzzle, Integer> implements IGraph<State8Puzzle, Integer>{

	@Override
	public Collection<State8Puzzle> getNeighbors(State8Puzzle t) {
		HashSet<State8Puzzle> s = new HashSet<State8Puzzle>();
		for(Action a: t.actions()){
			State8Puzzle p = new State8Puzzle(t);
			p.performAction(a);
			s.add(p);
		}
		return s;
	}

}
