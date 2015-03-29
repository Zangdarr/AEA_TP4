package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exceptions.EdgeAlreadyExistException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Graphe;

public class GraphTools {

    /**
     * Produit un graphe à partir d'un fichier
     * @param filename : nom du fichier
     * @return
     * @throws IOException
     * @throws NumberFormatException : si le fichier ne contient pas que des nombres avec des espace entre eux
     * @throws VertexAlreadyExistException : si un vertex est placé deux fois en première colonne.
     * @throws VertexNotFoundException
     * @throws EdgeAlreadyExistException : si deux arête identique sont écrite dans le fichier.
     */
    public static Graphe fileToGraph(String filename) throws IOException, NumberFormatException, VertexAlreadyExistException, VertexNotFoundException, EdgeAlreadyExistException{
        Graphe tmp = new Graphe();
        
        BufferedReader buff = new BufferedReader(new FileReader(filename));
        String line = "";
        String[] tab;

        while ((line = buff.readLine()) != null) {
            tab = line.split(" ");
            int vertex_1 = Integer.parseInt(tab[0]),
                vertex_2 = -1;
            if(!tmp.vertexContains(vertex_1))
                tmp.addVertexNumber(vertex_1);
            for (int i = 1; i < tab.length-1; i+=2) {
                vertex_2 = Integer.parseInt(tab[i]);
                if(!tmp.vertexContains(vertex_2))
                    tmp.addVertexNumber(vertex_2);
                tmp.addEdge(tmp.getVertex(vertex_1), tmp.getVertex(vertex_2), Integer.parseInt(tab[i+1]));
            }
            
        }
        
        buff.close();
        
        return tmp;
    }
}
