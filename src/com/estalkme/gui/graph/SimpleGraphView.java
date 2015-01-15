/*
 * SimpleGraphView.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */

package com.estalkme.gui.graph;

import java.util.List;

import com.estalkme.tools.Constants;
import com.sun.xml.internal.ws.util.StringUtils;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class SimpleGraphView {
	
    //public Graph<Integer, String> g;
	public DirectedSparseGraph<String, String> g;
	
    
    /** Creates a new instance of SimpleGraphView */
    public SimpleGraphView(List<String> links) {
    	g = new DirectedSparseGraph<String, String>();
    	
    	Constants.name = Constants.firstName + " " + Constants.lastName;
    	
    	// Create stalked name node.
    	g.addVertex(Constants.name);
    	
    	// TODO: get all links.
    	for (int i = 0; i < links.size(); i++) {
    		g.addVertex(links.get(i));
    		g.addEdge("Web" + i, Constants.name, links.get(i));
    	}        
    }
}
