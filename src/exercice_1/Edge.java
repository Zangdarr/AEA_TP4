package exercice_1;

public class Edge {
    
    //ATTRIBUTS
    
    private Vertex vertex_1,
                   vertex_2;
    private int    poids,
                   id;
    
    
    //CONSTRUCTEUR
    
    /**
     * @param vertex_1
     * @param vertex_2
     */
    public Edge(Vertex vertex_1, Vertex vertex_2, int poids, int id) {
        super();
        this.vertex_1 = vertex_1;
        this.vertex_2 = vertex_2;
        this.poids    = poids;
        this.id = id;
    }
    
    
    //GETTER
    
    protected Vertex getVertex_1() {
        return vertex_1;
    }
    protected Vertex getVertex_2() {
        return vertex_2;
    }
    protected int getPoids() {
        return poids;
    }
    protected int getId() {
        return id;
    }
    
    
    
    
}
