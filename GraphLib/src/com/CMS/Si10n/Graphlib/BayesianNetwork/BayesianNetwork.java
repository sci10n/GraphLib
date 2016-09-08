package com.CMS.Si10n.Graphlib.BayesianNetwork;

import com.CMS.Si10n.Graphlib.GraphPropagation.EdgeChangeOperator;
import com.CMS.Si10n.Graphlib.GraphPropagation.GraphChanges;
import com.CMS.Si10n.Graphlib.GraphPropagation.PropagationGraph;

public class BayesianNetwork {

    private EdgeChangeOperator<BayesianNode, Boolean> propagationOperator = new EdgeChangeOperator<BayesianNode, Boolean>() {

	@Override
	public void onEdgeChange(BayesianNode t1, BayesianNode t2, Boolean e, GraphChanges c) {
	    if (c == GraphChanges.ADD)
		for (String s1 : t1.getProbabilites().keySet()) {
		    t2.addParentValue( t1.getName() + ":" +s1);

		}
	}
    };

    public BayesianNetwork() {

    }

    public void init() {
	PropagationGraph<BayesianNode, Boolean> network = new PropagationGraph<BayesianNode, Boolean>();
	BayesianNode node1 = new BayesianNode("A");
	node1.addValue("True");
	node1.addValue("False");
	BayesianNode node2 = new BayesianNode("B");
	node2.addValue("True");
	node2.addValue("False");
	BayesianNode node3 = new BayesianNode("C");
	node3.addValue("True");
	node3.addValue("False");
	network.addEdgeListener(propagationOperator);
	network.addNode(node1);
	network.addNode(node2);
	network.addNode(node3);
	network.addEdge(node1, node3, true);
	network.addEdge(node2, node3, true);
	System.out.println(network);
	node1.print();
	node2.print();
    }

    public static void main(String[] args) {
	BayesianNetwork network = new BayesianNetwork();
	network.init();
    }
}
