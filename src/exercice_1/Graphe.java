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
        this.list_vertex.put(i,new Vertex(i,"i"));
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException {
        this.list_edges.add(new Edge(v1,v2));
    }

    @Override
    public void addEdge(int i, int j) throws VertexNotFoundException {
        this.list_edges.add(new Edge(list_vertex.get(i), list_vertex.get(j)));
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
        
        boolean a1_inf_b1   = a.getVertex_1().getId() < b.getVertex_1().getId(),
                a2_inf_b2   = a.getVertex_2().getId() < b.getVertex_2().getId(),
                a1_equal_b1 = a.getVertex_1().getId() == b.getVertex_1().getId(),
                a2_equal_b2 = a.getVertex_2().getId() == b.getVertex_2().getId();

        //impossible to be equal
        if(a1_equal_b1 && a2_equal_b2)
            try {
                throw new ComparatorException("Les deux arêtes comparé sont les mêmes");
            } catch (ComparatorException e) {
                System.out.println(e.getMessage());
                return -1;
            }
        return (a1_inf_b1) ? -1 : (a1_equal_b1)? (a2_inf_b2)? -1 : 1 : 1; 
    }
}