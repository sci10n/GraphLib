package com.CMS.Si10n.Graphlib.runs;

import com.CMS.Si10n.Graphlib.StateMachine.MarkovChain;

public class MarkovChainTest {

    
    public void init(){
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
	System.out.println(mc);
    }
    
    public static void main(String[] args) {
	MarkovChainTest test = new MarkovChainTest();
	test.init();
    }
}
