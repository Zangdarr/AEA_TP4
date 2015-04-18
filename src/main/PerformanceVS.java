package main;

import exceptions.EdgeAlreadyExistException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Graphe;

import java.io.IOException;

import tools.GraphTools;

public class PerformanceVS {

    public static void main(String[] args) {
        callFight("1000x70.gph");
    }
        
        
        
        
        
        
    public static void callFight(String graphFileName){
        System.out.println("VS  VS  VS  VS  VS  VS  VS  VS  VS  VS  ");
         Graphe g1 = new Graphe();
         Graphe g2 = new Graphe();
        try {
            g1 = GraphTools.fileToGraph(graphFileName);
            g2 = GraphTools.fileToGraph(graphFileName);
        } catch (NumberFormatException | IOException
                | VertexAlreadyExistException | VertexNotFoundException
                | EdgeAlreadyExistException e2) {
            System.out.println(e2.getMessage());
        }
        
        Graphe     PRIMresult    = PRIMmain.callPRIM(g1),
                   KRUSKALresult = KRUSKALmain.callKRUSKAL(g2);
        
        System.out.println(toString(PRIMresult,KRUSKALresult));
        

    }






    private static String toString(Graphe pRIMresult,
            Graphe kRUSKALresult) {
            
            StringBuffer result = new StringBuffer("[[  PERFORMANCES  ]] \n\n");
            
            result.append("---  EXECUTION TIME  ---\n");
            result.append(" - Kruskal MST time : ");
            result.append(kRUSKALresult.getExecutionTime() + "\n");
            result.append(" - PRIM    MST time : ");
            result.append(pRIMresult.getExecutionTime() + "\n");
            
            result.append("\n---  MST  ---\n");
            result.append(" - KRUSKAL : ");
            result.append("   Edges quantity : " + kRUSKALresult.getEdgeQuantity());
            result.append("   Weight : " + kRUSKALresult.getPoids() +"\n");
            result.append(" - PRIM    : ");
            result.append("   Edges quantity : " + pRIMresult.getEdgeQuantity());
            result.append("   Weight : " + pRIMresult.getPoids() +"\n");

            
            

            
        
        return result.toString();
    }

}
