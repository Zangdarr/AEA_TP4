package exercice_1;

import java.util.Iterator;


public interface GrapheInt {
    public void addVertex();
    public void addVertexNumber(int i)                   throws VertexAlreadyExistException;
    public void addEdge(Vertex v1, Vertex v2, int poids) throws VertexNotFoundException, EdgeAlreadyExistException;
    public void addEdge(int i, int j, int p)             throws VertexNotFoundException, EdgeAlreadyExistException;
    //...
    public Vertex getVertex(int i);
    //...
    public Iterator<Edge> getSortedEdgeIterator();
    //...
}

