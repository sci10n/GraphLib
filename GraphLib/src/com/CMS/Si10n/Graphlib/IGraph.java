package com.CMS.Si10n.Graphlib;

import java.util.Collection;

/**
 * 
 * @author Sci10n
 *
 * @param <T>	Node
 * @param <E>	Edge type
 */
public interface IGraph<T, E> {

	/**
	 * Inputs two AbstractNode:s and returns if there's a edge running from t1 to t2
	 * @param t1
	 * @param t2
	 * @return
	 */
	public boolean hasAdjacent(T t1, T t2);
	
	/**
	 * Returns all AbstractNode:s with a edge between them and t
	 * @param t
	 * @return
	 */
	public Collection<T> getNeighbors(T t);
	
	public boolean containsNode(T t);
	/**
	 * Adds Node t to the graph. Doesn't add any edges. Stored by hashCode value so make sure you keep a reference to that specific instance
	 * @param t
	 */
	public T addNode(T t);
	
	/**
	 * Removes Node t to the graph. Doesn't remove any edges (probably should). Removed by hashCode value.
	 * @param t
	 */
	public void deleteNode(T t);
	
	
	/**
	 * Sets A specific Node (t):s value to s
	 * @param t Node
	 * @param s Value
	 */
	public void setNodeValue(T t);
	
	public boolean containsEdge(T t1, T t2);
	/**
	 * Add directed edge between t1 ---> t2
	 * @param t1 from
	 * @param t2 to
	 */
	public void addEdge(T t1, T t2);
	
	/**
	 * Add directed edge between t1 -e-> t2 with edge cost of e
	 * @param t1 from
	 * @param t2 to
	 */
	public void addEdge(T t1, T t2,E e);
	
	/**
	 * Removes directed edge t1 -/-> t3;
	 * @param t1 from
	 * @param t2 to
	 */
	public void deleteEdge(T t1, T t2);
	
	/**
	 * Get edge value between t1 ---> t2
	 * @param t1 from
	 * @param t2 to
	 * @returns value of type <E>
	 */
	public E getEdgeValue(T t1, T t2);
	
	/**
	 *  Set value between t1 --> t2 to e
	 *  
	 * @param t1
	 * @param t2
	 * @param e
	 */
	public void setEdgeValue(T t1, T t2, E e);
	
	/**
	 * Loops through graph and sets all edges to the value returned from op.
	 * <br>
	 * @param op
	 */
	public void forEachEdge(GraphEdgeOperator<T, E> op);
	
	/**
	 * Loops through graph and sets all nodes to the value returned from op.
	 * <br>
	 * @param op
	 */
	public void forEachNode(GraphNodeOperator<T> op);
	
}
