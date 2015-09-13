package com.CMS.Si10n.Graphlib;

@FunctionalInterface
public interface GraphNodeOperator<T> {

	public T process(T t);
}
