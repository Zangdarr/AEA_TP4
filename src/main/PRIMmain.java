package main;

import exceptions.EdgeAlreadyExistException;
import exceptions.GrapheException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Graphe;

import java.io.IOException;

import tools.GraphTools;
import tools.MSTTools;

public class PRIMmain {

    public static void main(String[] args) {
        callPRIM("100x70.gph");
    }
    
    public static Graphe callPRIM(Graphe g){
        
        try {
            return MSTTools.runPRIM(g);
        } catch (VertexNotFoundException | EdgeAlreadyExistException e) {
            System.err.println("Le graphe n'existe pas.");
        }
        return null;
    }
    
    public static void callPRIM(String graphFileName){
        System.out.println("PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  ");
        Graphe g = new Graphe();
        try {
            g = GraphTools.fileToGraph(graphFileName);
        } catch (NumberFormatException | IOException
                | VertexAlreadyExistException | VertexNotFoundException
                | EdgeAlreadyExistException e2) {
            System.out.println(e2.getMessage());
        }
        Graphe result = new Graphe();
        try {
            result = MSTTools.runPRIM(g);
            System.out.println("Initialisation, " + g.toStringMinimal() + "\n\nResult of PRIM, " + result.toStringMinimal());
        } catch (VertexNotFoundException | EdgeAlreadyExistException e1) {System.err.println("Erreur lors de l'application de l'algorithme PRIM  : " + e1.getMessage());}
    
        System.out.println("PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  ");

    }
}
