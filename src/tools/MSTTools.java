package tools;

import exceptions.EdgeAlreadyExistException;
import exceptions.GrapheException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Edge;
import graphe.Graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MSTTools {

    /**
     * Applique un algorithme de calcul d'arbre recouvrant de poids minimum sur un graphe. Cette algorithme est inspiré de PRIM et KRUSKAL
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     */
    public static Graphe runVERKYNDT(final Graphe g) {
        Graphe result = new Graphe();
        /****** INIT *****/
        Iterator<Edge> it = g.getSortedEdgeIterator();

        if(!it.hasNext()){
            System.out.println("ERROR : Liste vide");
        }
        //on prend l'arête de poids le plus faible    
        Edge first_e = (Edge)it.next();
        try {
            result.addVertexNumber(first_e.getVertex_1().getId());
            result.addVertexNumber(first_e.getVertex_2().getId());
            result.addEdge(first_e.getVertex_1(), first_e.getVertex_2(), first_e.getPoids());
        } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e2) {
            // TODO Auto-generated catch block
            System.out.println(e2.getMessage());
        }


        /******* MAIN ******/
        //on prend l'arête de poids la plus faible qui possède un sommet appartenant à l'ensemble déjà présent dans result

        for (Iterator<Edge> iterator = it ; iterator.hasNext() && g.getVertexQuantity() > result.getVertexQuantity();) {
            Edge e = (Edge) iterator.next();

            boolean v1_exist_inside = result.vertexContains(e.getVertex_1().getId()),
                    v2_exist_inside = result.vertexContains(e.getVertex_2().getId());

            //si on a un sommet présent dans l'ensemble et l'autre non
            if( (v1_exist_inside && !v2_exist_inside) || (! v1_exist_inside && v2_exist_inside) ){
                //si on a v1 alors on rajoute v2
                if(v1_exist_inside){
                    try {
                        result.addVertexNumber(e.getVertex_2().getId());
                        result.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    iterator = g.getSortedEdgeIterator();
                }
                else{
                    try {
                        result.addVertexNumber(e.getVertex_1().getId());
                        result.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    iterator = g.getSortedEdgeIterator();
                }
            }
        }

        return result;

    }
        
        return tmp;
        
    }
}
