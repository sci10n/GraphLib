package com.CMS.Si10n.Graphlib.GraphPropagation;

@FunctionalInterface
public interface NodeChangeOperator<T> {
	public void onNodeChange(T t, GraphChanges c);

}
