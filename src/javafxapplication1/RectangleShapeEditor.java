package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Gestisce l'editor per la modifica delle forme di tipo Rectangle.
 */
public class RectangleShapeEditor extends ShapeEditor {

    private Rectangle rectangle;

    /**
     * Costruttore che inizializza l'editor per il rettangolo.
     *
     * @param shape       la forma {@link Rectangle} da modificare.
     * @param startX      la coordinata X iniziale del rettangolo.
     * @param startY      la coordinata Y iniziale del rettangolo.
     */
    public RectangleShapeEditor(Shape shape,double startX, double startY) {
        super(shape, startX, startY);
        this.rectangle = (Rectangle) shape;
    }

   /**
    * Metodo che cambia l'altezza dell rettangolo.
    * @param size 
    */
    @Override
    public void changeHeightSize(double size){
        this.rectangle.setHeight(size);
    }
    
    /**
     * Metodo che cambia lunghezza del rettangolo.
     * @param size 
     */
    @Override
    public void changeWidthSize(double size){
        this.rectangle.setWidth(size);
    }
    
    /**
     * Metodo che ritorna la lunghezza del rettangolo.
     * @return 
     */
    @Override
    public double getWidth(){
        return this.rectangle.getWidth();
    }

    
    /**
     * Metodo che ritorna l'altezza del rettangolo.
     * @return 
     */
    @Override
    public double getHeight() {
        return this.rectangle.getHeight();
    }
}
