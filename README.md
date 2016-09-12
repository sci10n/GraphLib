# GraphLib
Graphs in a .jar

#### Work in progress

***
## Index
* [__Build state__](https://github.com/sci10n/GraphLib#content)
* [__Content__](https://github.com/sci10n/GraphLib#content)
* [__Examples__](https://github.com/sci10n/GraphLib#examples)

***
## Build state
[![Build Status](https://travis-ci.org/sci10n/GraphLib.svg?branch=master)](https://travis-ci.org/sci10n/GraphLib)

## Content
The library consists of three main parts
* Fully generic graph interface with one concrete [Directed Graph](https://en.wikipedia.org/wiki/Directed_graph) class implemented using adjacency matrix. 
* A GraphUtils class with common graph-algorithms such as: 
    * [DFS](https://en.wikipedia.org/wiki/Depth-first_search)
    * [BFS](https://en.wikipedia.org/wiki/Breadth-first_search)
    * [A*](https://en.wikipedia.org/wiki/A*_search_algorithm)
    * [FloodFill](https://en.wikipedia.org/wiki/Flood_fill)
    * [Tarjan's algorithm](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm)
    * [Kosaraju's algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm)
    * [Topological Sorting](https://en.wikipedia.org/wiki/Topological_sorting)
* Usage examples

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

####Proppagation Graph

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

####Finite State Machine
The library contains a wrapper class around a Finite State Machine (FSM). The Finite State Machine uses a wrapper class (`FSM`) around the Graph data structure to limit the functionality of the Graph data structure to the constraints of a Finite State Machine.

The following code exemplifies how the FSM wrapper class can be used. 
```java
FSM<Integer,Integer> fsm = new FSM<Integer,Integer>();
fsm.addState(1);
fsm.addState(2);
fsm.addState(3);
fsm.addTransition(1,2,1);
fsm.addTransition(1,1,0);
fsm.addTransition(2,3,1);
fsm.addTransition(2,2,0);
fsm.addTransition(3,1,1);
fsm.addTransition(3,3,0);
fsm.setCurrentState(1);
```

To run the FSM you only need to present an input-sequence and a loop.

```java
String sequence = "0110001111";
for(int i = 0; i < sequence.length(); i++)
	fsm.register(sequence.charAt(i) - '0');
```

The following image represents the FSM from the example above.

![Example](https://github.com/sci10n/GraphLib/blob/master/web/FiniteStateMachine.png "Example of a Finite State Machine")

####Markov chain

The library also contains a wrapper class around the model of a Markov chain.

Example of how the `MarkovChain` wrapper class can be used

```java
MarkovChain<String> mc = new MarkovChain<String>();
mc.addState("Sunny");
mc.addState("Rainy");
mc.addTransition("Sunny","Rainy",0.1);
mc.addTransition("Sunny","Sunny",0.9);
mc.addTransition("Rainy","Sunny",0.5);
mc.addTransition("Rainy","Rainy",0.5);
mc.setCurrentState("Sunny");
for(int i = 0; i <100; i++){
    mc.register();
   System.out.println(mc.getCurrentState());
}
```
The example will produce the following Markov chain and print the result of 100 iterations of the model. 
![Example](https://github.com/sci10n/GraphLib/blob/master/web/MarkovChain.png "Example of a Finite State Machine")

#### State-searching
An example would be if you want to use the `GraphUtils`for searching in a state graph like [__n-puzzle__](https://en.wikipedia.org/wiki/15_puzzle) solving, you would need to implement everything in `AbstractGraph` even thought [A*](https://en.wikipedia.org/wiki/A*_search_algorithm) only needs a `Comparator<State>` and the `.getNeighbors()`. 

What the example looks like using [_Hamming Distance_](https://en.wikipedia.org/wiki/Hamming_distance).

![Example](https://github.com/sci10n/GraphLib/blob/master/web/8Puzzle_best.png "Example Graph nr2")

***
## Javadocs
Javadocs can be found in the [__repository__](https://github.com/sci10n/GraphLib/blob/master/GraphLib/doc/)
