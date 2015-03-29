package main;

import java.io.IOException;

import exceptions.EdgeAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Graphe;
import graphe.RandomGraphGenerator;
import tools.GraphTools;
import tools.MSTTools;

public class Exercice2_Main {

    public static void main(String[] args) {
        RandomGraphGenerator gen = new RandomGraphGenerator();
        System.out.println("Génération du graphe en cours...\n");
        Graphe g = gen.generateErdosRenyiGraph(100, (float) 0.5);
        
        System.out.println("\nApplication de l'algorithme PRIM...\n\n");
        Graphe result = new Graphe();
        try {
            result = MSTTools.runPRIM(g);
            System.out.println("Initialisation, " + g.toStringMinimal() + "\n\nRésultat du PRIM, " + result.toStringMinimal());
        
        
        } catch (VertexNotFoundException | EdgeAlreadyExistException e1) {System.err.println("Erreur lors de l'application de l'algorithme PRIM  : " + e1.getMessage());}
        
        
        try {
            GraphTools.graphToFile("init_graphe", g);
            GraphTools.graphToFile("export_exo2", result);
        } catch (IOException e) {System.err.println("Erreur lors de la création d'un fichier graphe. " + e.getMessage());}
    }
    
    

}
