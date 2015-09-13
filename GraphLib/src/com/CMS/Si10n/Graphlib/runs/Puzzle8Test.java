package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.GraphUtils;
import com.CMS.Si10n.Graphlib.StateSearch.Puzzle8;
import com.CMS.Si10n.Graphlib.StateSearch.State8Puzzle;

public class Puzzle8Test {

	/*
	 * DISCLAIMER
	 * Still in development
	 * 
	 * 
	 */
	public void init(){

	
		State8Puzzle startState = new State8Puzzle(new int[]{1,2,5,3,4,0,6,7,8});
		State8Puzzle goalState  = new State8Puzzle(new int[]{0,1,2,3,4,5,6,7,8});
		Puzzle8 puzzle = new Puzzle8(goalState);
		//puzzle.setEdgeCalculator((a,b) -> (a.dist(b))); 
		//State8Puzzle startState = new State8Puzzle(new int[]{1,0,3,4,5,6,7,2,8});
		//State8Puzzle goalState  = new State8Puzzle(new int[]{1,2,3,4,5,6,7,8,0});
		
		
		for(State8Puzzle p : GraphUtils.a_star_search(startState, goalState, puzzle, (a,b) -> (a.cost + a.dist(goalState) - b.cost + b.dist(goalState)))){
			System.out.println(p.toString());
		}
	}
	
	public static void main(String[] args) {
		Puzzle8Test main = new Puzzle8Test();
		main.init();
	}

}
