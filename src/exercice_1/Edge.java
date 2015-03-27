package exercice_1;

public class Edge {
    
    //ATTRIBUTS
    
    private int vertex_1;
    private int vertex_2;
    
    
    //CONSTRUCTEUR
    
    /**
     * @param vertex_1
     * @param vertex_2
     */
    public Edge(int vertex_1, int vertex_2) {
        super();
        this.vertex_1 = vertex_1;
        this.vertex_2 = vertex_2;
    }
    
    
    //GETTER
    
    protected int getVertex_1() {
        return vertex_1;
    }
    protected int getVertex_2() {
        return vertex_2;
    }
    
    
    
    
}
