package com.CMS.Si10n.Graphlib.StateSearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.CMS.Si10n.Graphlib.AbstractGraph;
import com.CMS.Si10n.Graphlib.GraphEdgeOperator;
import com.CMS.Si10n.Graphlib.IGraph;

/**
 * 
 * @author Sci10n This is an example of a graph without any data structure for
 *         nodes or edges, it simply generates new states and return them. works
 *         for most of the search algorithms since they them self keep track of
 *         searched nods
 *
 */
public class Puzzle8 extends AbstractGraph<State8Puzzle, Integer> implements IGraph<State8Puzzle, Integer> {

    public State8Puzzle goal;

    public Puzzle8(State8Puzzle goal) {
	this.goal = goal;
    }

    @Override
    public Collection<State8Puzzle> getNeighbors(State8Puzzle t) {
	ArrayList<State8Puzzle> s = new ArrayList<State8Puzzle>();
	for (Action a : t.actions()) {
	    State8Puzzle p = new State8Puzzle(t);
	    p.cost = t.cost + p.dist(goal);
	    p.performAction(a);
	    s.add(p);
	}
	return s;
    }

}
