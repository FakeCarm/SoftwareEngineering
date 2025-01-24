package javafxapplication1;

import javafx.scene.shape.Shape;
/**
 * Classe che si occupa di gestire gli oggetti copiati. SINGLETON
 * @author cassd
 */
public class Clipboard {
    
    private static Clipboard instance;
    private Shape copiedShape;

    private Clipboard() {}
    
    
    /**
     * Funziona che ritorna l'istanza clipboard.
     * @return Clipboard instance
     */
    public static Clipboard getInstance() {
        if (instance == null) {
            instance = new Clipboard();
        }
        return instance;
    }
    
    /**
     * Funzione che si occupa di salvare la forma copiata.
     * @param shape 
     */
    public void copy(Shape shape) {
        this.copiedShape = shape;
    }

    /**
     * Funzione che si occupa di ritornare la forma copiata.
     * @return Shape copiedShape
     */
    public Shape getCopiedShape() {
        return copiedShape;
    }
    
    /**
     * Funzione che si occupa di ripulire la clipboard.
     */
    public void clear() {
        this.copiedShape = null;
    }
}