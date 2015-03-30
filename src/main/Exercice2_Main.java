package main;

import java.io.IOException;

import exceptions.EdgeAlreadyExistException;
import exceptions.GrapheException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Graphe;
import graphe.RandomGraphGenerator;
import tools.GraphTools;
import tools.MSTTools;

public class Exercice2_Main {

    public static void main(String[] args) {
        exemplePRIM();
    }
    
    
    
    
    
    
    public static void exemplePRIM(){
        System.out.println("PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  ");
        Graphe g = new Graphe();
        try {
            g = GraphTools.fileToGraph("1000vertex_100pctedges.gph");
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
        
        
        try {
            GraphTools.graphToFile("init_PRIM.gph", g);
            GraphTools.graphToFile("result_PRIM.gph", result);
        } catch (IOException e) {System.err.println("Erreur lors de la création d'un fichier graphe. " + e.getMessage());}
    
        System.out.println("PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  ");

    }
        
        
        try {
            GraphTools.graphToFile("init_graphe", g);
            GraphTools.graphToFile("export_exo2", result);
        } catch (IOException e) {System.err.println("Erreur lors de la création d'un fichier graphe. " + e.getMessage());}
    }
    
    

}
