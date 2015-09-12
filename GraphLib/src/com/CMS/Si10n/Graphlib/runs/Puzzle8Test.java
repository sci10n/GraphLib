package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.GraphUtils;
import com.CMS.Si10n.Graphlib.StateSearch.Puzzle8;
import com.CMS.Si10n.Graphlib.StateSearch.State8Puzzle;

public class Puzzle8Test {

	/*
	 * DISCLAIMER
	 * This test does not work and will require some rework of the entire library
	 * 
	 * 
	 */
	public void init(){
		Puzzle8 puzzle = new Puzzle8();
		puzzle.setEdgeCalculator((a,b) -> (a.dist(b))); 
		State8Puzzle startState = new State8Puzzle(new int[]{1,2,5,3,4,0,6,7,8});
		State8Puzzle goalState  = new State8Puzzle(new int[]{0,1,2,3,4,5,6,7,8});
		for(State8Puzzle p : GraphUtils.a_star_search(startState, goalState, puzzle, (a,b) -> (a.dist(b)))){
			System.out.println(p.toString());
		}
	}
	
	public static void main(String[] args) {
		Puzzle8Test main = new Puzzle8Test();
		main.init();
	}

}
