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
     * @param drawingPaper  l'area di lavoro associata.
     * @param paneEditor    il pannello editor utilizzato per la modifica.
     * @param startX        coordinata X iniziale.
     * @param startY        coordinata Y iniziale.
     */
    public LineShapeEditor(Shape shape, Pane paneEditor, double startX, double startY) {
        super(shape,paneEditor, startX, startY);
        this.line = (Line) shape;
    }

    /**
     * Aggiorna le coordinate della linea per trascinarla di un offset specifico.
     *
     * @param offsetX l'offset orizzontale per il trascinamento.
     * @param offsetY l'offset verticale per il trascinamento.
     */
    
    @Override
    public void changeHeightSize(double size){
        this.line.setStrokeWidth(size);
    }
    
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

    @Override
    public double getWidth() {
        double startX = line.getStartX();
        double startY = line.getStartY();
        double endX = line.getEndX();
        double endY = line.getEndY();

        return (Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)));
    }
    
    @Override
    public double getHeight() {
        return line.getStrokeWidth();
    }
    
}
