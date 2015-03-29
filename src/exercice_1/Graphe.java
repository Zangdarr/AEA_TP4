package exercice_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import exceptions.EdgeAlreadyExistException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;


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
        int key = 0;
        while(list_vertex.containsKey(key))
            key++;
        list_vertex.put(key, new Vertex(key,"" + key));
    }

    @Override
    public void addVertexNumber(int i) throws VertexAlreadyExistException {
        //if the vertex already exist
        if(list_vertex.containsKey(i))
            throw new VertexAlreadyExistException();
        
        //add this vertex
        this.list_vertex.put(i,new Vertex(i,"" + i));
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2,int p) throws VertexNotFoundException, EdgeAlreadyExistException {
        //If the vertex does not exist
        if(! (list_vertex.containsKey(v1.getId()) && list_vertex.containsKey(v2.getId())))
            throw new VertexNotFoundException();
        
        //key calculator
        String key_str = (v1.getId() < v2.getId())? v1.getString() + v2.getString() : v2.getString() + v1.getString();
        int key_int = Integer.parseInt(key_str);
        
        //if the key already exist into the list
        if(edgeContains(key_int))
            throw new EdgeAlreadyExistException();
        
        //add this edge
        this.list_edges.add(new Edge(v1,v2, p,key_int));
    }

    @Override
    public void addEdge(int i, int j, int p) throws VertexNotFoundException, EdgeAlreadyExistException {
        //If the vertex does not exist
        if(! (list_vertex.containsKey(i) && list_vertex.containsKey(j)))
            throw new VertexNotFoundException();

        //key calculator
        String key_str = (i<j)? i+""+j : j+""+i;
        int key_int = Integer.parseInt(key_str);
        
        //if the key already exist into the list
        if(edgeContains(key_int))
            throw new EdgeAlreadyExistException();
        
        //add this edge
        this.list_edges.add(new Edge(list_vertex.get(i), list_vertex.get(j), p,key_int ));
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
    
    public boolean edgeContains(int id){
        for (Iterator<Edge> it = list_edges.iterator(); it.hasNext();)
            if(((Edge) it.next()).getId() == id)
                return true;
        return false;
    }
    
    public boolean vertexContains(int id){
        return list_vertex.containsKey(id);
    }
    
    public int getVertexQuantity(){
        return list_vertex.size();
    }
    
    public int getEdgeQuantity(){
        return list_edges.size();
    }
    
    public int getPoids(){
        int p = 0;
        for (Iterator<Edge> iterator = list_edges.iterator(); iterator.hasNext();) {
            Edge edge = (Edge) iterator.next();
            p += edge.getPoids();
        }
        return p;
    }
    
    public String toString(){
        
        String result = "Graphe :\n - poids : " + getPoids() + "\n - Liste des sommets : \n";
        for(Integer i : list_vertex.keySet()){
            result += i + " ";
        }
        
        result += "\n - Liste des arêtes :\n";
        for(Edge i : list_edges){
            result += i.getVertex_1().getString() + " " + i.getVertex_2().getString() + " " + i.getPoids() + "\n";
        }
        
        return result;
        
    }
}

class EdgesComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge a, Edge b) {
        return a.getPoids() < b.getPoids() ? -1 : a.getPoids() == b.getPoids() ? 0 : 1;         
    }
}