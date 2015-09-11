# GraphLib
A Java Library for the Graph data structure
#### Work in progress
This is a small library i'm working on for personal use.
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
graph.setEdgeCalculator((a,b) -> a+b);
```
![Example](https://github.com/sci10n/GraphLib/blob/master/web/Graph_ex2.png "Example Graph nr2")
***
