package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import exercice_1.Edge;
import exercice_1.EdgeAlreadyExistException;
import exercice_1.Graphe;
import exercice_1.VertexAlreadyExistException;
import exercice_1.VertexNotFoundException;


public class Exercice1_Main {

    
    
    public static void main(String[] args) {
        try {
            Graphe g = fileToGraph("graphe_test");
            Graphe result = Algo_PRIM(g);
            
            System.out.println("START " + g.toString() + "\n\n\n" + "RESULT " + result.toString());
            //System.out.println("GRAPHE G : \n" + g.toString() + "\n\n\nGRAPHE RESULT : \n" + result.toString());
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
    /**
     * Applique l'algorithme de calcul d'arbre recouvrant de poids minimum PRIM sur un graphe
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     */
    public static Graphe Algo_PRIM(final Graphe g) {
        Graphe tmp = new Graphe();
        /****** INIT *****/
        Iterator<Edge> it = g.getSortedEdgeIterator();
        
        if(!it.hasNext()){
            System.out.println("ERROR : Liste vide");
        }
        //on prend l'arête de poids le plus faible    
        Edge first_e = (Edge)it.next();
        try {
            tmp.addVertexNumber(first_e.getVertex_1().getId());
            tmp.addVertexNumber(first_e.getVertex_2().getId());
            tmp.addEdge(first_e.getVertex_1(), first_e.getVertex_2(), first_e.getPoids());
        } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e2) {
            // TODO Auto-generated catch block
            System.out.println(e2.getMessage());
        }
        
        
        /******* MAIN ******/
        //on prend l'arête de poids la plus faible qui appartient à l'ensemble déjà présent dans tmp
                                                                                                                                                                                                                                                                                
        for (Iterator<Edge> iterator = it ; iterator.hasNext() && g.getVertexQuantity() > tmp.getVertexQuantity();) {
            Edge e = (Edge) iterator.next();
            
            boolean v1_exist_inside = tmp.vertexContains(e.getVertex_1().getId()),
                    v2_exist_inside = tmp.vertexContains(e.getVertex_2().getId());

            //si on a un sommet présent dans l'ensemble et l'autre non
            if( (v1_exist_inside && !v2_exist_inside) || (! v1_exist_inside && v2_exist_inside) ){
                //si on a v1 alors on rajoute v2
                if(v1_exist_inside){
                    try {
                        tmp.addVertexNumber(e.getVertex_2().getId());
                        tmp.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    iterator = g.getSortedEdgeIterator();
                }
                else{
                    try {
                        tmp.addVertexNumber(e.getVertex_1().getId());
                        tmp.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    iterator = g.getSortedEdgeIterator();
                }
            }
        }
        
        return tmp;
        
    }

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
