package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.GraphUtils;
import com.CMS.Si10n.Graphlib.StateSearch.Puzzle8;
import com.CMS.Si10n.Graphlib.StateSearch.State8Puzzle;

public class Puzzle8Test {

	public void init(){

	
		State8Puzzle startState = new State8Puzzle(new int[]{0,1,2,3,4,5,6,7,8});
		State8Puzzle goalState  = new State8Puzzle(new int[]{1,2,3,4,5,6,7,8,0});
		Puzzle8 puzzle = new Puzzle8(goalState);
	
		for(State8Puzzle p : GraphUtils.a_star_search(startState, goalState, puzzle, (a,b) ->
		{
			if(a.equals(b))
				return 0;
			int c1 = a.cost + a.dist(goalState);
			int c2 = b.cost + b.dist(goalState);
			return c1 == c2 ? 0 : (c1 < c2 ? -1 : 1);
		})){
			System.out.println(p.toString());
		}

	}
	
	public static void main(String[] args) {
		Puzzle8Test main = new Puzzle8Test();
		main.init();
	}

}
