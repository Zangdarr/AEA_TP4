package exercice_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;


public class Graphe implements GrapheInt {

    //ATTRIBUTS
    private HashMap<Integer,Vertex> list_vertex;
    private ArrayList<Edge> list_edges;
    
    
    //CONSTRUCTEURS
    
    /**
     * Initialize the edges list and the vertex list
     */
    public Graphe() {
        super();
        this.list_edges  = new ArrayList<Edge>();
        this.list_vertex = new HashMap<Integer,Vertex>();
    }

    /**
     * @param list_edge : liste des arêtes du graphe
     */
    public Graphe(ArrayList<Edge> list_Edges, HashMap<Integer,Vertex> list_Vertex) {
        super();
        this.list_edges  = list_Edges;
        this.list_vertex = list_Vertex;
    }
    
    
    
    //METHODES

    @Override
    public void addVertex() {
        int size = list_vertex.size();
        list_vertex.put(size, new Vertex(size,"" + size));
    }

    @Override
    public void addVertexNumber(int i) throws VertexAlreadyExistException {
        this.list_vertex.put(i,new Vertex(i,"" + i));
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2,int p) throws VertexNotFoundException {
        this.list_edges.add(new Edge(v1,v2, p));
    }

    @Override
    public void addEdge(int i, int j, int p) throws VertexNotFoundException {
        this.list_edges.add(new Edge(list_vertex.get(i), list_vertex.get(j), p));
    }

    @Override
    public Vertex getVertex(int i) {
        return list_vertex.get(i);
    }

    @Override
    public Iterator<Edge> getSortedEdgeIterator() {
        Collections.sort(list_edges, new EdgesComparator());
        return list_edges.iterator();
    }
    

}

class EdgesComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge a, Edge b) {
        return a.getPoids() < b.getPoids() ? -1 : a.getPoids() == b.getPoids() ? 0 : 1;         
    }
}