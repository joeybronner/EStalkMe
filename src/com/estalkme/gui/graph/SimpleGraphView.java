/*
 * SimpleGraphView.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */

package com.estalkme.gui.graph;

import com.estalkme.tools.Constants;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class SimpleGraphView {
	
    //public Graph<Integer, String> g;
	public DirectedSparseGraph<String, String> g;
	
    
    /** Creates a new instance of SimpleGraphView */
    public SimpleGraphView() {
    	g = new DirectedSparseGraph<String, String>();
    	
    	Constants.name = Constants.firstName + " " + Constants.lastName;
    	
    	// TODO: get all links.
    	
    	// Create stalked name node.
    	g.addVertex(Constants.name);
    	
        // Add some vertices. From above we defined these to be type Integer.
        g.addVertex("Square");
        g.addVertex("Rectangle");
        g.addVertex("Circle");        
        
        // Note that the default is for undirected edges, our Edges are Strings.
        g.addEdge("Lien1", Constants.name, "Rectangle");
        g.addEdge("Lien2", Constants.name, "Circle");
        
    }
}
