package com.CMS.Si10n.Graphlib;


/**
 *	 Inputs two Nodes and return the edge cost.
 * @author Sci10n
 *
 * @param <T>
 * @param <E>
 */
@FunctionalInterface
public interface GraphEdgeOperator<T, E> {
	public E process(T t1, T t2);
}
