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
        this.line.setStrokeWidth(size);
    }
    
    /**
     * Metodo che cambia la lunghezza della linea
     * @param size 
     */
    @Override
    public void changeWidthSize(double size){
        double startX = line.getStartX();
        double startY = line.getStartY();
        double endX = line.getEndX();
        double endY = line.getEndY();

        double currentLength = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)); //calcolo la lunghezza della linea
        double scaleFactor = size / currentLength; //calcolo il fattore di scala da applicare alla differenza tra xfinale e xiniziale

        double newEndX = startX + (endX - startX) * scaleFactor; //calcolo la nuova xfinale
        double newEndY = startY + (endY - startY) * scaleFactor; //calcolo la nuova xfinale

        line.setEndX(newEndX);
        line.setEndY(newEndY);
        //line.setEndY(line.getStartY() + size);
        
    }
    
    /*
    Metodo che ritorna la lunghezza della linea
    */
    @Override
    public double getWidth() {
        double startX = line.getStartX();
        double startY = line.getStartY();
        double endX = line.getEndX();
        double endY = line.getEndY();

        return (Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)));
    }
    
    /**
     * Metodo che torna lo spessore della linea
    */
    @Override
    public double getHeight() {
        return line.getStrokeWidth();
    }
    
}
