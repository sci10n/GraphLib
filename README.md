# GraphLib
A Java Library for the Graph data structure
#### Work in progress

***
## Index
* [__Content__](https://github.com/sci10n/GraphLib#content)
* [__Examples__](https://github.com/sci10n/GraphLib#examples)
* [__Issues__](https://github.com/sci10n/GraphLib#issues)

***
## Content
The library consists of three main parts
* Fully templated AbstractGraph interfaces with one concrete [Directed Graph](https://en.wikipedia.org/wiki/Directed_graph)
* A GraphUtils class with static methods for some simple graph search algortihms like 
    * [DFS](https://en.wikipedia.org/wiki/Depth-first_search)
    * [BFS](https://en.wikipedia.org/wiki/Breadth-first_search)
    * [A*](https://en.wikipedia.org/wiki/A*_search_algorithm)
    * [FloodFill](https://en.wikipedia.org/wiki/Flood_fill)
* Testcases to be used for development testing and examples

***

## Examples
Creating a graph with `Integer` as Nodes and `Double` for Edges and adding some nodes and edges.
```java
Graph<Integer,Double> graph = new Graph<Integer, Double>();
graph.addNode(1);
graph.addNode(2);
graph.addNode(3);
graph.addNode(4);
graph.addNode(5);
//Cost of the edges is defaulted to null
graph.addEdge(1,2);
graph.addEdge(1,3);
graph.addEdge(1,5);
graph.addEdge(2,4);
graph.addEdge(3,4);
```
![Example](https://github.com/sci10n/GraphLib/blob/master/web/Graph_ex.png "Example Graph")

Creating a graph and setting all edge costs to the sum of the nodes
```java
Graph<Integer,Integer> graph = new Graph<Integer, Integer>();
graph.addNode(1);
graph.addNode(2);
graph.addNode(3);
graph.addNode(10);
//Cost of the edges is defaulted to null
graph.addEdge(1,2);
graph.addEdge(1,3);
graph.addEdge(3,2);
graph.addEdge(2,10);
graph.forEachEdge((a,b) -> a+b);
```
![Example](https://github.com/sci10n/GraphLib/blob/master/web/Graph_ex2.png "Example Graph nr2")

Many scenarios involves topologies with a fixed set of states but with dynamic edges. This can be represented using the `PropagationGraph` class.

What sets the `PropagationGraph` apart from the standrad `Graph` datastructure is that `PropagationGraph` allow for event listeners on modification of the graph structure. Both for nodes and edges. 

```java
PropagationGraph<Integer, Integer> graph = new PropagationGraph<Integer, Integer>();
graph.addNode(1);
graph.addNode(2);
graph.addNode(3);
graph.addEdge(1, 2, 0);
graph.addEdge(2, 3, 0);
graph.addEdge(3, 1, 0);
graph.addEdgeListener((a,b,e,c) -> 
   {
	    if (c == GraphChanges.CHANGE)
	      System.out.println("The values of edges " + a +"->" + b + " changed to " + e);
	});
graph.setEdgeValue(1,2,10);
``` 

***
### Issues
The `AbstractGraph` interface has way too many methods in it. Most of the stuff could probably be moved to subclasses/interfaces.
An example would be if you want to use the `GraphUtils`for searching in a state graph like [__n-puzzle__](https://en.wikipedia.org/wiki/15_puzzle) solving, you would need to implement everything in `AbstractGraph` even thought [A*](https://en.wikipedia.org/wiki/A*_search_algorithm) only needs a `Comparator<State>` and the `.getNeighbors()`. 

Another problem I discovered was that since state comparisons are done with the `.equals` and `.hashCode`, both needs to be overidden if new states are created on the fly.

The added example is not the best and highlights some bad design decisions but principle works. The point was that you didn't need to rewrite the search algorithm and that principle stands even though the solutions don't feel completely natural.

What the example looks like using [_Hamming Distance_](https://en.wikipedia.org/wiki/Hamming_distance).

![Example](https://github.com/sci10n/GraphLib/blob/master/web/8Puzzle_best.png "Example Graph nr2")

***
## Javadocs
Javadocs can be found in the [__repository__](https://github.com/sci10n/GraphLib/blob/master/GraphLib/doc/)
