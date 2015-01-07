/*
 * SimpleGraphView.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */

package com.estalkme.gui.graph;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class SimpleGraphView {
    public Graph<Integer, String> g;
    /** Creates a new instance of SimpleGraphView */
    public SimpleGraphView() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        g = new SparseMultigraph<Integer, String>();
        
        // Add some vertices. From above we defined these to be type Integer.
        g.addVertex((Integer)1);
        g.addVertex((Integer)2);
        g.addVertex((Integer)3); 
        g.addVertex((Integer)4);
        g.addVertex((Integer)5);
        g.addVertex((Integer)6);
        g.addVertex((Integer)7);
        g.addVertex((Integer)8);
        
        // Note that the default is for undirected edges, our Edges are Strings.
        g.addEdge("Facebook", 1, 2);
        g.addEdge("Twitter", 1, 3);
        g.addEdge("Google+", 1, 4);
        g.addEdge("Test", 1, 5);
        g.addEdge("Test2", 2, 6);
        g.addEdge("Test3", 6, 7);
        g.addEdge("Test4", 6, 8);
        g.addEdge("Wow", 1, 8);
    }
}
