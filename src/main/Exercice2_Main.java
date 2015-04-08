package main;

import exceptions.EdgeAlreadyExistException;
import exceptions.GrapheException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Graphe;

import java.io.IOException;

import tools.GraphTools;
import tools.MSTTools;

public class Exercice2_Main {

    public static void main(String[] args) {
        callKRUSKAL_OPTIMAL("2000x2000.gph");
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
        
        
        try {
            GraphTools.graphToFile("init_PRIM.gph", g);
            GraphTools.graphToFile("result_PRIM.gph", result);
        } catch (IOException e) {System.err.println("Erreur lors de la création d'un fichier graphe. " + e.getMessage());}
    
        System.out.println("PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  PRIM  ");

    }
    
    public static void callKRUSKAL(String graphFileName){
        System.out.println("KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  ");
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
            result = MSTTools.runKRUSKAL(g);
            System.out.println("Initialisation, " + g.toStringMinimal() + "\n\nResul of KRUSKAL, " + result.toStringMinimal());
        } catch (VertexNotFoundException | EdgeAlreadyExistException | GrapheException e1) {System.err.println("Erreur lors de l'application de l'algorithme KRUSKAL  : " + e1.getMessage());}
        
        
        try {
            GraphTools.graphToFile("init_KRUSKAL.gph", g);
            GraphTools.graphToFile("result_KRUSKAL.gph", result);
        } catch (IOException e) {System.err.println("Erreur lors de la création d'un fichier graphe. " + e.getMessage());}
    
        System.out.println("KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  KRUSKAL  ");

    }
    
    public static void callKRUSKAL_OPTIMAL(String graphFileName){
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
        
        
        try {
            GraphTools.graphToFile("init_KRUSKAL.gph", g);
            GraphTools.graphToFile("result_KRUSKAL.gph", result);
        } catch (IOException e) {System.err.println("Erreur lors de la création d'un fichier graphe. " + e.getMessage());}
    
        System.out.println("KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL  ");

    }
    
    
    

}
