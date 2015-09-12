package com.CMS.Si10n.Graphlib.StateSearch;

import java.util.Collection;

public interface StateSpace<S,A,C> {

	public Collection<A> actions(S s);
	public S result(A a, S s);
	/**
	 * Might need a functional interface as input for more abstraction
	 * @param s1
	 * @param s2
	 * @return
	 */
	public C cost(S s1,S s2);
}
