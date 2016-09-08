package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.StateMachine.FSM;

public class FSMTest {

    
    public void init(){
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
	String sequence = "0110001111";
	for(int i = 0; i < sequence.length(); i++){
	    System.out.print("In state: " + fsm.getCurrentState());
	    fsm.register(sequence.charAt(i) - '0');
	    System.out.println("\tRegister: " + (sequence.charAt(i) - '0') + " resulting in state: " +  fsm.getCurrentState());
	    
	}
    }
    
    public static void main(String[] args) {
	FSMTest test = new FSMTest();
	test.init();
    }
}
