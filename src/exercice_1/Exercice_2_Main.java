package exercice_1;

import java.util.Iterator;

public class Exercice_2_Main {

    
    
    public static void main(String[] args) {
        
    }
    
    
    public static Graphe Algo_PRIM(final Graphe g) {
        Graphe tmp = new Graphe();
        
        Iterator it = g.getSortedEdgeIterator();
        if(!it.hasNext()){
            System.out.println("ERROR : Liste vide");
        }
        //on prend l'arête de poids le plus faible    
        Edge first_e = (Edge)it.next();
        try {
            tmp.addVertexNumber(first_e.getVertex_1().getId());
            tmp.addVertexNumber(first_e.getVertex_2().getId());
            tmp.addEdge(first_e.getVertex_1(), first_e.getVertex_2(), first_e.getPoids());
        } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e2) {
            // TODO Auto-generated catch block
            System.out.println(e2.getMessage());
        }
        
        //on prend l'arête de poids la plus faible qui appartient à l'ensemble déjà présent dans tmp
        
        for (Iterator<Edge> iterator = it ;iterator.hasNext() && g.getVertexQuantity() > tmp.getVertexQuantity();) {
            Edge e = (Edge) iterator.next();
            boolean v1_exist_inside = tmp.vertexContains(e.getVertex_1().getId()),
                    v2_exist_inside = tmp.vertexContains(e.getVertex_2().getId());
            //si on a un sommet présent dans l'ensemble et l'autre non
            if( (v1_exist_inside && !v2_exist_inside) || (! v1_exist_inside && v2_exist_inside) ){
                //si on a v1 alors on rajoute v2
                if(v1_exist_inside){
                    try {
                        tmp.addVertexNumber(e.getVertex_2().getId());
                        tmp.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    it = g.getSortedEdgeIterator();
                }
                else{
                    try {
                        tmp.addVertexNumber(e.getVertex_1().getId());
                        tmp.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    it = g.getSortedEdgeIterator();
                }
            }
            
            
            
            
        }
        
        return g;
        
    }

}
