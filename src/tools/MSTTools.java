package tools;

import exceptions.EdgeAlreadyExistException;
import exceptions.GrapheException;
import exceptions.VertexAlreadyExistException;
import exceptions.VertexNotFoundException;
import graphe.Edge;
import graphe.Graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MSTTools {

    /**
     * Applique un algorithme de calcul d'arbre recouvrant de poids minimum sur un graphe. Cette algorithme est inspiré de PRIM et KRUSKAL
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     */
    public static Graphe runVERKYNDT(final Graphe g) {
        Graphe result = new Graphe();
        /****** INIT *****/
        Iterator<Edge> it = g.getSortedEdgeIterator();

        if(!it.hasNext()){
            System.out.println("ERROR : Liste vide");
        }
        //on prend l'arête de poids le plus faible    
        Edge first_e = (Edge)it.next();
        try {
            result.addVertexNumber(first_e.getVertex_1().getId());
            result.addVertexNumber(first_e.getVertex_2().getId());
            result.addEdge(first_e.getVertex_1(), first_e.getVertex_2(), first_e.getPoids());
        } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e2) {
            // TODO Auto-generated catch block
            System.out.println(e2.getMessage());
        }


        /******* MAIN ******/
        //on prend l'arête de poids la plus faible qui possède un sommet appartenant à l'ensemble déjà présent dans result

        for (Iterator<Edge> iterator = it ; iterator.hasNext() && g.getVertexQuantity() > result.getVertexQuantity();) {
            Edge e = (Edge) iterator.next();

            boolean v1_exist_inside = result.vertexContains(e.getVertex_1().getId()),
                    v2_exist_inside = result.vertexContains(e.getVertex_2().getId());

            //si on a un sommet présent dans l'ensemble et l'autre non
            if( (v1_exist_inside && !v2_exist_inside) || (! v1_exist_inside && v2_exist_inside) ){
                //si on a v1 alors on rajoute v2
                if(v1_exist_inside){
                    try {
                        result.addVertexNumber(e.getVertex_2().getId());
                        result.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    iterator = g.getSortedEdgeIterator();
                }
                else{
                    try {
                        result.addVertexNumber(e.getVertex_1().getId());
                        result.addEdge(e.getVertex_1(), e.getVertex_2(), e.getPoids());
                    } catch (VertexAlreadyExistException | VertexNotFoundException | EdgeAlreadyExistException e1) {System.out.println(e1.getMessage());}
                    iterator = g.getSortedEdgeIterator();
                }
            }
        }

        return result;

    }


    /**
     * Applique l'algorithme de calcul d'arbre recouvrant de poids minimum de KRUSKAL sur un graphe
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     */
    public static Graphe runKRUSKAL(final Graphe g) {
        Graphe result = new Graphe();





        return result;
    }
    /**
     * Applique l'algorithme de calcul d'arbre recouvrant de poids minimum de KRUSKAL sur un graphe
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     * @throws EdgeAlreadyExistException 
     * @throws VertexNotFoundException 
     */
    public static Graphe runPRIM(final Graphe g) throws VertexNotFoundException, EdgeAlreadyExistException {
        Graphe result = new Graphe();

        ArrayList<Edge> list_edge = g.getEdgeList();
        Collections.shuffle(list_edge);

        ArrayList<Integer> vertex_marked = new ArrayList<Integer>();
        vertex_marked.add(list_edge.get(0).getVertex_1().getId());

        boolean notEND = true;

        int bestWeight = Integer.MAX_VALUE;
        int bestEdge   = -1;

        //tant qu'on a pas finit
        while(notEND){

            //si on ne trouve pas d'arête lors de la boucle for -> FIN
            notEND = false;

            //on cherche la prochaine arête extérieure de point minimum adjacente à l'ensemble de sommet déjà présent.
            for(int i = 0; i<list_edge.size(); ++i){
                Edge tmp_edge = list_edge.get(i);
                boolean containsV1 = vertex_marked.contains(tmp_edge.getVertex_1().getId()),
                        containsV2 = vertex_marked.contains(tmp_edge.getVertex_2().getId());
                if((containsV1 || containsV2) && (!containsV1 || !containsV2)){
                    int tmp_poids = tmp_edge.getPoids();
                    if(tmp_poids < bestWeight){
                        bestWeight = tmp_poids;
                        bestEdge = i;
                        notEND = true;
                    }
                }
            }
            
            if(notEND){
                //l'arête optimal
                Edge selected_edge = list_edge.get(bestEdge);
                System.out.println(selected_edge.toString());
                //on l'ajoute au graphe
                result.addEdge(selected_edge);
                //on la retire des arêtes restantes
                list_edge.remove(bestEdge);
                //on met à jour la liste des vertex marqués
                int newVertexId = (vertex_marked.contains(selected_edge.getVertex_1().getId()))?selected_edge.getVertex_2().getId():selected_edge.getVertex_1().getId(); 
                vertex_marked.add(newVertexId);
                //réinitisation du meilleur poids au max
                bestWeight = Integer.MAX_VALUE;
            }
            
        }
        
        
        
        
        return result;
    }
}
