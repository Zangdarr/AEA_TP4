package main;

import graphe.Graphe;
import graphe.RandomGraphGenerator;
import tools.MSTTools;

public class Exercice2_Main {

    public static void main(String[] args) {
        RandomGraphGenerator gen = new RandomGraphGenerator();
        System.out.println("Génération du graphe en cours...\n");
        Graphe g = gen.generateErdosRenyiGraph(100, (float) 1.0);
        
        System.out.println("\nApplication de l'algorithme PRIM...\n\n");
        Graphe result = MSTTools.runPRIM(g);
        
        System.out.println("Initialisation, " + g.toStringMinimal() + "\n\nRésultat du PRIM, " + result.toStringMinimal());
    }
    
    

}
