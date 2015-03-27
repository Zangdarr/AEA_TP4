package exercice_1;

import java.util.ArrayList;
import java.util.Iterator;


public class Graphe implements GrapheInt {

    //ATTRIBUTS
    private ArrayList<Vertex> list_vertex;
    private ArrayList<Edge> list_edges;
    
    
    //CONSTRUCTEURS
    
    /**
     * Initialize the edges list and the vertex list
     */
    public Graphe() {
        super();
        this.list_edges  = new ArrayList<Edge>();
        this.list_vertex = new ArrayList<Vertex>();
    }

    /**
     * @param list_edge : liste des arêtes du graphe
     */
    public Graphe(ArrayList<Edge> list_Edges, ArrayList<Vertex> list_Vertex) {
        super();
        this.list_edges  = list_Edges;
        this.list_vertex = list_Vertex;
    }
    
    
    
    //METHODES

    @Override
    public void addVertex() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addVertexNumber(int i) throws VertexAlreadyExistException {
        this.list_vertex.add(new Vertex(i));
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException {
        this.list_edges.add(new Edge(v1,v2));
    }

    @Override
    public void addEdge(int i, int j) throws VertexNotFoundException {
        this.list_edges.add(new Edge(new Vertex(i), new Vertex(j)));
    }

    @Override
    public Vertex getVertex(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Edge> getSortedEdgeIterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
