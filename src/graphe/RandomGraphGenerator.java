package graphe;

import java.security.SecureRandom;

import exceptions.EdgeAlreadyExistException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;

public class RandomGraphGenerator implements RandomGraphGeneratorInt {

    @Override
    public Graphe generateErdosRenyiGraph(int n, float p) throws IllegalArgumentException {
        //Initialisation du graphe
        Graphe result = new Graphe();

        //Ajout des n sommets
        try {
            for (int i = 1; i < n+1; i++) {
                result.addVertexNumber(i);
            }
        } catch (VertexAlreadyExistException e) {
            //ne peut pas se produire normalement.
            System.out.println("Erreur lors de la génération du graphe : " + e.getMessage());
        }

        //Ajout des arêtes
        SecureRandom randGen = new SecureRandom();
        int N = 0;
        if(n < 10)
            N = (int)Math.pow(n, 4);
        else 
            N = (int)Math.pow(10, 4);
        float proba = 0;
        for (int i = 1; i < n; ++i) {
            //System.out.println("c " +  i + " " + result.getEdgeQuantity());
            for (int j = i+1; j < n+1; ++j) {
                proba = randGen.nextFloat();
                if(proba < p){
                    try {
                        //System.out.print("a");
                        result.addEdge(i, j, 1 + randGen.nextInt(N));
                    } catch (VertexNotFoundException | EdgeAlreadyExistException e) {System.out.println("Erreur lors de la génération des arêtes." + e.getMessage()); }
                }

            }
        }
        return result;
    }

}
