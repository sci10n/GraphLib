package com.CMS.Si10n.Graphlib.StateSearch;

import java.util.Collection;

public class StateSpace8Puzzle implements StateSpace<State8Puzzle, Action, Integer> {

	@Override
	public Collection<Action> actions(State8Puzzle s) {
		return s.actions();
	}

	@Override
	public State8Puzzle result(Action a, State8Puzzle s) {
		State8Puzzle cs = new State8Puzzle(s);
		cs.performAction(a);
		return cs;
	}

	@Override
	public Integer cost(State8Puzzle s1, State8Puzzle s2) {
		return s1.dist(s2);
	}


}
