package main;

import java.io.IOException;

import tools.GraphTools;
import tools.MSTTools;
import exceptions.EdgeAlreadyExistException;
import exceptions.GrapheException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Graphe;

public class KRUSKALmain {

    public static void main(String[] args) {
        callKRUSKAL("500x50.gph");
    }
    
    
    public static void callKRUSKAL(String graphFileName){
        System.out.println("KRUSKAL OPTI  KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL  ");
        Graphe g = null;
        try {
            g = GraphTools.fileToGraph(graphFileName);
        } catch (NumberFormatException | IOException
                | VertexAlreadyExistException | VertexNotFoundException
                | EdgeAlreadyExistException e2) {
            System.out.println(e2.getMessage());
        }
        
        Graphe result = new Graphe();
        try {
            result = MSTTools.runKRUSKAL_OPTIMAL(g);
            System.out.println("Initialisation, " + g.toStringMinimal() + "\n\nResul of KRUSKAL OPTI, " + result.toStringMinimal());
        } catch (VertexNotFoundException | EdgeAlreadyExistException | GrapheException e1) {System.err.println("Erreur lors de l'application de l'algorithme KRUSKAL OPTI : " + e1.getMessage());}
        
    
        System.out.println("KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL  ");

    }
}
