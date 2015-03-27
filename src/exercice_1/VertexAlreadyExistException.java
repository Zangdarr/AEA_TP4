package exercice_1;

public class VertexAlreadyExistException extends Exception {

    /**
     * 
     */
    public VertexAlreadyExistException() {
        super("ERROR : Le vertex existe déjà.");
    }

    /**
     * @param message
     */
    public VertexAlreadyExistException(String message) {
        super(message);
    }
    
    
}
