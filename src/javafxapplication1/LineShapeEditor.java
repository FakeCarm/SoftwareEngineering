package javafxapplication1;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 * Editor specifico per la gestione e la modifica di una linea.
 */
public class LineShapeEditor extends ShapeEditor {

    private Line line;

    /**
     * Costruttore che inizializza l'editor per una linea.
     *
     * @param shape         la forma (linea) da modificare.
     * @param startX        coordinata X iniziale.
     * @param startY        coordinata Y iniziale.
     */
    public LineShapeEditor(Shape shape,double startX, double startY) {
        super(shape, startX, startY);
        this.line = (Line) shape;
    }

    /**
     * Metodo che cambia l'altezza della linea
     * @param size 
     */
    @Override
    public void changeHeightSize(double size){
        line.setEndY(line.getStartY() + size);
    }
    
    /**
     * Metodo che cambia la lunghezza della linea
     * @param size 
     */
    @Override
    public void changeWidthSize(double size){
        line.setEndX(line.getStartX() + size);
    }
    
    /*
    Metodo che ritorna la lunghezza della linea
    */
    @Override
    public double getWidth() {
        return Math.abs(line.getEndX() - line.getStartX());
    }
    
    /**
     * Metodo che torna lo spessore della linea
    */
    @Override
    public double getHeight() {
        return Math.abs(line.getEndY() - line.getStartY());
    }
    
}
