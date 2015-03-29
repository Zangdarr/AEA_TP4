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
            for (int i = 0; i < n; i++) {
                result.addVertexNumber(i);
            }
        } catch (VertexAlreadyExistException e) {
            //ne peut pas se produire normalement.
            System.out.println("Erreur lors de la génération du graphe : " + e.getMessage());
        }

        //Ajout des arêtes
        SecureRandom randGen = new SecureRandom();
        int N = (int)Math.pow(n, 4);
        float proba = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                proba = randGen.nextFloat();
                if(proba < p){
                    try {
                        result.addEdge(i, j, randGen.nextInt(N));
                    } catch (VertexNotFoundException | EdgeAlreadyExistException e) {System.out.println("Erreur lors de la génération des arêtes : " + e.getMessage()); }
                }

            }
        }
        return result;
    }

}
