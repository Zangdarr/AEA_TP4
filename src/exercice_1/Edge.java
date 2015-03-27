package exercice_1;

public class Edge {
    
    //ATTRIBUTS
    
    private int from;
    private int to;
    private int id;
    
    
    //CONSTRUCTEUR
    
    /**
     * @param from
     * @param to
     * @param id
     */
    public Edge(int from, int to) {
        super();
        this.from = from;
        this.to = to;
        this.id = Integer.parseInt(from + "" + to);
    }
    
    
    //GETTER
    
    protected int getFrom() {
        return from;
    }
    protected int getTo() {
        return to;
    }
    protected int getId() {
        return id;
    }
    
    
    
    
}
