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


    
    
    
    
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    
    
    /**
     * Applique l'algorithme de calcul d'arbre recouvrant de poids minimum de KRUSKAL sur un graphe
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     * @throws EdgeAlreadyExistException 
     * @throws VertexNotFoundException 
     * @throws GrapheException 
     */
    public static Graphe runKRUSKAL(final Graphe g) throws VertexNotFoundException, EdgeAlreadyExistException, GrapheException {
        long start = System.nanoTime();
        
        /****** INIT *****/
        Graphe result = new Graphe();
        Iterator<Edge> it = g.getSortedEdgeIterator();
        if(!it.hasNext())
            throw new GrapheException();
        result.addEdge(it.next());

        for (Iterator<Edge> iterator = it;iterator.hasNext();) {
            Edge edge = iterator.next();
            if(isAcyclique(result.getEdgeList(), edge))
                result.addEdge(edge);
        }

        long end = System.nanoTime();
        System.out.println("Temps d'exécution de PRIM : " + (end-start)/Math.pow(10, 9));

        return result;
    }
    private static boolean isAcyclique(ArrayList<Edge> edgeList, Edge edge) {
        edgeList.add(edge);
        boolean hasCycle = searchCycle(edge.getVertex_1().getId(), edge.getVertex_1().getId(), edge.getVertex_2().getId(), edgeList);

        return !hasCycle;
    }


    private static boolean searchCycle(int origin, int current_id, int previous_id,  ArrayList<Edge> edgeList) {
        for (Iterator<Integer> iterator = getVertexNeighbors(edgeList,current_id ,previous_id); iterator.hasNext();){
            int id = iterator.next();
            if(origin == id)
                return true;
            else
                return searchCycle(origin, id, current_id, edgeList);
        }
        return false;
    }

    public static Iterator<Integer> getVertexNeighbors(ArrayList<Edge> edgeList, int current_id, int previous_id){
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (Iterator<Edge> iterator = edgeList.iterator(); iterator.hasNext();) {
            Edge edge = iterator.next();
            if(edge.getVertex_1().getId() == current_id && edge.getVertex_2().getId() != previous_id)
                result.add(edge.getVertex_2().getId());
            if(edge.getVertex_2().getId() == current_id && edge.getVertex_2().getId() != previous_id)
                result.add(edge.getVertex_1().getId());
        }
        return result.iterator();
    }


    
    
    
    
    
    
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    
    
    
    
    
    
    /**
     * Applique l'algorithme de calcul d'arbre recouvrant de poids minimum de KRUSKAL sur un graphe
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     * @throws EdgeAlreadyExistException 
     * @throws VertexNotFoundException 
     */
    public static Graphe runPRIM(final Graphe g) throws VertexNotFoundException, EdgeAlreadyExistException {
        System.out.println("PRIM's running...");
        long start = System.nanoTime();
        Graphe result = new Graphe();

        ArrayList<Edge> list_edge = g.getEdgeList();
        Collections.shuffle(list_edge);

        ArrayList<Integer> vertex_marked = new ArrayList<Integer>();
        vertex_marked.add(list_edge.get(0).getVertex_1().getId());

        boolean notEND = true;

        int bestWeight = Integer.MAX_VALUE;
        Edge bestEdge = null;
        boolean v1 = false;

        //tant qu'on a pas finit
        int y = 0;
        while(notEND){
            int pos_current_edge = 0,
                pos_best_edge =0;
            System.out.println("Nombre d'arêtes trouvées : " + y++);
            //si on ne trouve pas d'arête lors de la boucle for -> FIN
            notEND = false;

            //on cherche la prochaine arête extérieure de point minimum adjacente à l'ensemble de sommet déjà présent.
            for(Iterator<Edge> it = list_edge.iterator(); it.hasNext();){
                Edge tmp_edge = it.next(); 
                pos_current_edge++;
                boolean containsV1 = vertex_marked.contains(tmp_edge.getVertex_1().getId()),
                        containsV2 = vertex_marked.contains(tmp_edge.getVertex_2().getId());
                if((containsV1 || containsV2) && (!containsV1 || !containsV2)){
                    int tmp_poids = tmp_edge.getPoids();
                    if(tmp_poids < bestWeight){
                        v1 = containsV1;
                        bestWeight = tmp_poids;
                        bestEdge = tmp_edge;
                        pos_best_edge = pos_current_edge;
                        notEND = true;
                    }
                }
            }

            if(notEND){
                //on ajoute bestEdge au graphe
                result.addEdge(bestEdge);
                //on la retire des arêtes restantes
                list_edge.remove(pos_best_edge);
                //on met à jour la liste des vertex marqués
                int newVertexId = (v1)?bestEdge.getVertex_2().getId():bestEdge.getVertex_1().getId(); 
                vertex_marked.add(newVertexId);
                //réinitisation du meilleur poids au max
                bestWeight = Integer.MAX_VALUE;
            }

        }
        long end = System.nanoTime();
        System.out.println("done.\nExecution time : " + (end-start)/Math.pow(10, 9));
        return result;
    }
}
