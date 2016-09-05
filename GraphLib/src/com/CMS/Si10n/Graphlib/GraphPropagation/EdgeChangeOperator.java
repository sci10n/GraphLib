package com.CMS.Si10n.Graphlib.GraphPropagation;

@FunctionalInterface
public interface EdgeChangeOperator<T, E> {

    public void onEdgeChange(T t1, T t2, E e, GraphChanges c);
}
