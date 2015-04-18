package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import exceptions.EdgeAlreadyExistException;
import exceptions.GrapheException;
import exceptions.VertexNotFoundException;
import graphe.Edge;
import graphe.Graphe;

public class MSTTools {

    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/

    /** OPTIMAL
     * Applique l'algorithme de calcul d'arbre recouvrant de poids minimum de KRUSKAL sur un graphe
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     * @throws EdgeAlreadyExistException 
     * @throws VertexNotFoundException 
     * @throws GrapheException 
     */
    public static Graphe runKRUSKAL_OPTIMAL(final Graphe g) throws VertexNotFoundException, EdgeAlreadyExistException, GrapheException {
        System.out.println("KRUSKAL's running...");
        long start = System.nanoTime();

        /****** INIT *****/
        Graphe result = new Graphe();
        Iterator<Edge> it = g.getSortedEdgeIterator();

        HashMap<Integer,Integer> grouped_vertex = new HashMap<Integer,Integer>();
        AtomicInteger next_new_group_number = new AtomicInteger();

        for (Iterator<Edge> iterator = it;iterator.hasNext() && result.getEdgeQuantity()  != g.getVertexQuantity()-1;) {
            Edge edge = iterator.next();
            
            if(isAcycliqueOptimum(grouped_vertex, edge,next_new_group_number)){
                result.addEdge(edge);
            }
        }

        long end = System.nanoTime();
        System.out.println("done.\nExecution time : " + (end-start)/Math.pow(10, 9));

        return result;
    }

    private static boolean isAcycliqueOptimum(HashMap<Integer,Integer> grouped_edges, Edge edge, AtomicInteger next_new_group_number) {
        int vertexID1 = edge.getVertex_1().getId(),
            vertexID2 = edge.getVertex_2().getId();
        boolean containsVertex1 = grouped_edges.containsKey(vertexID1),
                containsVertex2 = grouped_edges.containsKey(vertexID2);
        
        if(containsVertex1 && containsVertex2){ //si contient les deux vertex
            //on recupere les groupes des deux vertex
            int groupV1 = grouped_edges.get(vertexID1),
                groupV2 = grouped_edges.get(vertexID2);
            if(groupV1 == groupV2) { //si meme groupe => cycle
                return false;
            }
            else { //groupe different => pas de cycle
                //fusion des deux groupes avec l'ID de V1
                for (Integer key : grouped_edges.keySet()) {
                    if(grouped_edges.get(key) == groupV2)
                        grouped_edges.put(key, groupV1);
                }
            }
            
        } else if(!containsVertex1 &&  !containsVertex2){ //si ne contient aucun des deux
            //creation d'un nouveau groupe pour les deux sommets
            int new_group = next_new_group_number.getAndIncrement();
            grouped_edges.put(vertexID1, new_group);
            grouped_edges.put(vertexID2, new_group);
            
        } else if(containsVertex1) { //si contient uniquement vertex1
            //on ajoute V2 au groupe de V1
            int groupV1 = grouped_edges.get(vertexID1);
            grouped_edges.put(vertexID2, groupV1);
        } else { // si contient uniquement vertex2
          //on ajoute V1 au groupe de V2
            int groupV2 = grouped_edges.get(vertexID2);
            grouped_edges.put(vertexID1, groupV2);
        }
        return true;
    }


    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/
    /*************************************************************************************************************************************/


    /** NON OPTIMALE
     * Applique l'algorithme de calcul d'arbre recouvrant de poids minimum de KRUSKAL sur un graphe
     * @param g : graphe sur lequel sera appliqué l'algorithme
     * @return
     * @throws EdgeAlreadyExistException 
     * @throws VertexNotFoundException 
     * @throws GrapheException 
     */
    public static Graphe runKRUSKAL(final Graphe g) throws VertexNotFoundException, EdgeAlreadyExistException, GrapheException {
        System.out.println("KRUSKAL's running...");
        long start = System.nanoTime();

        /****** INIT *****/
        Graphe result = new Graphe();
        Iterator<Edge> it = g.getSortedEdgeIterator();
        if(!it.hasNext())
            throw new GrapheException();
        result.addEdge(it.next());

        ArrayList<Edge> copy = new ArrayList<Edge>(result.getEdgeList());
        for (Iterator<Edge> iterator = it;iterator.hasNext() && result.getEdgeQuantity()  != g.getVertexQuantity()-1;) {
            Edge edge = iterator.next();
            boolean result_contains_v1 = result.vertexContains(edge.getVertex_1().getId()),
                    result_contains_v2 = result.vertexContains(edge.getVertex_2().getId());
            
            //si l'arête ajoutée à un sommet ou les deux non existant dans le graphe -> AC
            if(! (result_contains_v1 && result_contains_v2)){
                result.addEdge(edge);
                System.out.println(result.getEdgeQuantity());
            }
            else if(isAcyclique(copy, edge)){
                result.addEdge(edge);
                System.out.println(result.getEdgeQuantity());

            }
            copy = new ArrayList<Edge>(result.getEdgeList());

        }

        long end = System.nanoTime();
        System.out.println("done.\nExecution time : " + (end-start)/Math.pow(10, 9));

        return result;
    }
    private static boolean isAcyclique(ArrayList<Edge> edgeList, Edge edge) {
        edgeList.add(edge);
        boolean hasCycle = searchCycle(edge.getVertex_1().getId(), edge.getVertex_2().getId(), edgeList);

        return !hasCycle;
    }


    private static boolean searchCycle(int origin,int from,ArrayList<Edge> edgeList) {
        HashMap<Integer,Integer> marked_vertex_from = new HashMap<Integer,Integer>();
        ArrayList<Integer> marked_vertex_id = new ArrayList<Integer>();
        LinkedList<Integer> marked_neighbors_uncheck = new LinkedList<Integer>();
        marked_vertex_id.add(origin);
        marked_vertex_from.put(origin, from);
        marked_neighbors_uncheck.add(origin);
        while(! marked_neighbors_uncheck.isEmpty()){

            int checking_id = marked_neighbors_uncheck.pollFirst();
            for (Iterator<Integer> iterator = getVertexNeighbors(edgeList, checking_id, marked_vertex_from.get(checking_id)); iterator.hasNext();) {
                Integer ngbor = iterator.next();
                if(marked_vertex_id.contains(ngbor))
                    return true;
                marked_vertex_id.add(ngbor);
                marked_vertex_from.put(ngbor,checking_id);
                marked_neighbors_uncheck.add(ngbor);
            }
            
        }
        
        return false;
        
        
    }

    public static Iterator<Integer> getVertexNeighbors(ArrayList<Edge> edgeList, int current_id, int previous_id){
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (Iterator<Edge> iterator = edgeList.iterator(); iterator.hasNext();) {
            Edge edge = iterator.next();
            if(edge.getVertex_1().getId() == current_id && edge.getVertex_2().getId() != previous_id)
                result.add(edge.getVertex_2().getId());
            if(edge.getVertex_2().getId() == current_id && edge.getVertex_1().getId() != previous_id)
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

        ArrayList<Edge> list_edge = new ArrayList<Edge>(g.getEdgeList());
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
                     int      pos_best_edge =0;
            //System.out.println("Nombre d'arêtes trouvées : " + y++);
            //si on ne trouve pas d'arête lors de la boucle for -> FIN
            notEND = false;

            //on cherche la prochaine arête extérieure de point minimum adjacente à l'ensemble de sommet déjà présent.
            for(int pos_current_edge = 0; pos_current_edge< list_edge.size() ;pos_current_edge++){
                Edge tmp_edge = list_edge.get(pos_current_edge); 
                
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
