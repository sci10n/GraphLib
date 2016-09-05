package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.Graph;

public class GridToGraphTest {

    private int[][] grid;

    public void init() {
	grid = new int[10][10];
	Graph<Integer, Integer> graph = new Graph<Integer, Integer>();
	// Init grid
	for (int x = 0; x < grid.length; x++)
	    for (int y = 0; y < grid[0].length; y++)
		grid[y][x] = x + y * grid.length;

	// adding nodes
	for (int x = 0; x < grid.length; x++)
	    for (int y = 0; y < grid[0].length; y++)
		graph.addNode(grid[x][y]);

	// adding edges
	for (int x = 0; x < grid.length; x++)
	    for (int y = 0; y < grid[0].length; y++) {
		if (x != 0)
		    graph.addEdge(grid[x][y], grid[x - 1][y]);
		if (x != grid.length - 1)
		    graph.addEdge(grid[x][y], grid[x + 1][y]);
		if (y != 0)
		    graph.addEdge(grid[x][y], grid[x][y - 1]);
		if (y != grid[0].length - 1)
		    graph.addEdge(grid[x][y], grid[x][y + 1]);

	    }
	graph.forEachEdge((a, b, e) -> 0);

	System.out.println(graph.toString());
    }

    public static void main(String[] args) {
	GridToGraphTest main = new GridToGraphTest();
	main.init();
    }
}
