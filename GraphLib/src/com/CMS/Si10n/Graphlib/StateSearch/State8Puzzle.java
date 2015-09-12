package com.CMS.Si10n.Graphlib.StateSearch;

import java.util.HashSet;

public class State8Puzzle {

	public int[][] state = new int[3][3];
	private int emptyX,emptyY;
	public State8Puzzle(State8Puzzle parent){
		for(int x = 0; x < state.length; x++)
			for(int y =0 ; y < state[0].length;y++)
				state[x][y] = parent.state[x][y];
		emptyX = parent.emptyX;
		emptyY = parent.emptyY;
	}
	
	public HashSet<Action> actions(){
		HashSet<Action> actions = new HashSet<Action>();
		for(Action a: Action.values()){
			if(isValid(a))
				actions.add(a);
		}
		return actions;
	}
	
	public State8Puzzle(int[] values){
		for(int i = 0; i < values.length; i++){
			state[i % state.length][i / state.length] = values[i];
			if(values[i] == 0){
				emptyX = i % state.length;
				emptyY = i / state.length;
			}
		}
	}
	
	public int dist(State8Puzzle s){
	int tmp = 0;
		for(int x = 0; x < state.length; x++)
			for(int y =0 ; y < state[0].length;y++)
				if(state[x][y] != s.state[x][y])
					tmp++;
		return tmp;
	}
	
	public State8Puzzle performAction(Action a) {
		if(isValid(a)){
			switch(a){
			case MOVE_EAST:
			{
				int tmp = state[emptyX-1][emptyY];
				state[emptyX][emptyY] = tmp;
				state[emptyX-1][emptyY] = 0;
				emptyX--;
				break;
			}
			case MOVE_NORTH:
			{
				int tmp = state[emptyX][emptyY+1];
				state[emptyX][emptyY] = tmp;
				state[emptyX][emptyY+1] = 0;
				emptyY++;
				break;
			}
			case MOVE_SOUTH:
			{
				int tmp = state[emptyX][emptyY-1];
				state[emptyX][emptyY] = tmp;
				state[emptyX][emptyY-1] = 0;
				emptyY--;
				break;
			}
			case MOVE_WEST:
			{
				int tmp = state[emptyX+1][emptyY];
				state[emptyX][emptyY] = tmp;
				state[emptyX+1][emptyY] = 0;
				emptyX++;
				break;
			}
		}
		return this;
		}
		return null;
	}

	public boolean isValid(Action a){
		switch(a){
		case MOVE_EAST:
			if(emptyX > 0)
				return true;
			break;
		case MOVE_NORTH:
			if(emptyY < 2)
				return true;
			break;
		case MOVE_SOUTH:
			if(emptyY > 0)
				return true;
			break;
		case MOVE_WEST:
			if(emptyX < 2)
				return true;
			break;
		default:
			return false;
		}
		return false;
	}
	
	//Methods like this gives me an awkward feeling
	@Override
	public String toString() {
		String tmp = "";
		for(int y = 0; y < state[0].length; y++){
			for(int x = 0; x < state.length; x++)
				tmp += state[x][y] + " ";
			tmp +="\n";
		}
		return tmp;
	}
	
	@Override
	public boolean equals(Object obj) {
	if(obj == null)
		return false;
	if(obj == this)
		return true;
	if(obj instanceof State8Puzzle){
		State8Puzzle o = (State8Puzzle) obj;
		return o.dist(this) == 0;
	}
		return super.equals(obj);
	}
}
