package main;

import graphe.Graphe;
import tools.GraphTools;
import tools.MSTTools;


public class Exercice1_Main {

    
    
    public static void main(String[] args) {
        try {
            Graphe g = GraphTools.fileToGraph("graphe_test");
            Graphe result = MSTTools.runPRIM(g);
            
            System.out.println("START " + g.toString() + "\n\n\n" + "RESULT " + result.toString());
            //System.out.println("GRAPHE G : \n" + g.toString() + "\n\n\nGRAPHE RESULT : \n" + result.toString());
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }

}
