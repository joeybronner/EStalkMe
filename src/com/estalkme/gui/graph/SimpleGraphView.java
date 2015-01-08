/*
 * SimpleGraphView.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */

package com.estalkme.gui.graph;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class SimpleGraphView {
	
    //public Graph<Integer, String> g;
	public DirectedSparseGraph<String, String> g;
	
    
    /** Creates a new instance of SimpleGraphView */
    public SimpleGraphView() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        //g = new SparseMultigraph<Integer, String>();
    	g = new DirectedSparseGraph<String, String>();
    	
        // Add some vertices. From above we defined these to be type Integer.
        g.addVertex("Square");
        g.addVertex("Square2");
        g.addVertex("Square3");
        g.addVertex("Square4");
        g.addVertex("Square5");
        g.addVertex("Square6");
        g.addVertex("Square7");
        /*
        g.addVertex((Integer)1);
        g.addVertex((Integer)2);
        g.addVertex((Integer)3); 
        g.addVertex((Integer)4);
        g.addVertex((Integer)5);
        g.addVertex((Integer)6);
        g.addVertex((Integer)7);
        g.addVertex((Integer)8);
        g.addVertex((Integer)9);
        g.addVertex((Integer)10);
        g.addVertex((Integer)11);
        g.addVertex((Integer)12);
        g.addVertex((Integer)13);
        */
        
        
        // Note that the default is for undirected edges, our Edges are Strings.
        g.addEdge("Lien1", "Square1", "Square2");
        g.addEdge("Lien2", "Square1", "Square3");
        g.addEdge("Lien3", "Square1", "Square4");
        g.addEdge("Lien4", "Square1", "Square5");
        g.addEdge("Lien5", "Square1", "Square6");
        g.addEdge("Lien6", "Square1", "Square7");
        
        /*
        g.addEdge("Facebook", 1, 2);
        g.addEdge("Twitter", 1, 3);
        g.addEdge("Google+", 1, 4);
        g.addEdge("Test", 1, 5);
        g.addEdge("Test2", 2, 6);
        g.addEdge("Test3", 6, 7);
        g.addEdge("Test4", 6, 8);
        g.addEdge("Wow", 1, 8);
        g.addEdge("Wodw", 1, 11);
        g.addEdge("Wowd", 11, 12);
        g.addEdge("Wowg", 11, 13);
        g.addEdge("Woqw", 11, 8);
        g.addEdge("Woqsw", 13, 9);
        g.addEdge("Woqdew", 9, 10);*/
    }
}
