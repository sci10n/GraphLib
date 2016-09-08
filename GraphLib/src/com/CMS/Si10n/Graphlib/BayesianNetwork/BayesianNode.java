package com.CMS.Si10n.Graphlib.BayesianNetwork;

import java.util.HashMap;
import java.util.Vector;

public class BayesianNode {

    private String name;
    private HashMap<String,HashMap<String,Double>> probabilites;
    public BayesianNode(String name){
	this.name = name;
	probabilites = new HashMap<String,HashMap<String,Double>>();
    }
    
    public String getName(){
	return name;
    }
    
    public HashMap<String,HashMap<String,Double>> getProbabilites(){
	return probabilites;
    }
    
    public void addValue(String value){
	probabilites.put(value,new HashMap<String,Double>());
    }
    public void addParentValue(String value){
	System.out.println("P(" + name +"|" + value +")");
	for(String s: probabilites.keySet()){
	    probabilites.get(s).put(value, 0.1);
	}
	
    }
    public void setName(String name){
	this.name = name;
    }
    public void print(){
	String output = "";
	for(String s1 : probabilites.keySet()){
	    output += name + " " + s1 + ":";
	    for(String s2: probabilites.get(s1).keySet())
	    	output += " " + s2 + ",";
	    output += "\n";
	}
	System.out.println(output);
    }
    @Override
    public String toString() {
	
        return name;
    }
}
