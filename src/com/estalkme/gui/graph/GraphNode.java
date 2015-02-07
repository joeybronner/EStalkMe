package com.estalkme.gui.graph;

import java.util.List;

import com.estalkme.tools.Constants;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class GraphNode {
	
	public DirectedSparseGraph<String, String> g;
    
    public GraphNode(List<String> links) {
    	g = new DirectedSparseGraph<String, String>();
    	
    	Constants.name = Constants.firstName + " " + Constants.lastName;
    	
    	// Create stalked name node.
    	g.addVertex(Constants.name);
    	
    	// Retrieve all links
    	for (int i = 0; i < links.size(); i++) {
    		g.addVertex(links.get(i));
    		g.addEdge(new String(new char[i]).replace("\0", " "), Constants.name, links.get(i));
    	}        
    }
}
