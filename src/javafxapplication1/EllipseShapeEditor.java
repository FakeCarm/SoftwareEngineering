package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * Classe che estende ShapeEditor per fornire funzionalità di modifica specifiche per un'ellisse.
 * Consente di modificare la posizione di una Ellipse all'interno di un foglio di disegno.
 * 
 * @author vinjs
 */
public class EllipseShapeEditor extends ShapeEditor {

    private Ellipse ellipse;

    /**
     * Crea un'istanza di EllipseShapeEditor per modificare una Ellipse.
     * 
     * @param shape la forma ellittica da modificare.
     * @param drawingPaper il foglio di disegno su cui la forma è presente.
     * @param paneEditor il pannello su cui vengono gestite le operazioni di modifica della forma.
     * @param startX la posizione di partenza sull'asse X per la forma.
     * @param startY la posizione di partenza sull'asse Y per la forma.
     */
    public EllipseShapeEditor(Shape shape, Pane paneEditor, double startX, double startY) {
        super(shape, paneEditor, startX, startY);
        this.ellipse = (Ellipse) shape;
    }

    /**
     * Esegue l'operazione di drag sulla Ellipse.
     * Modifica le coordinate centrali della forma applicando gli offset orizzontale e verticale.
     * 
     * @param offsetX l'offset orizzontale per spostare la forma.
     * @param offsetY l'offset verticale per spostare la forma.
     */
    @Override
    public void dragShape(double offsetX, double offsetY) {
        ellipse.setCenterX(ellipse.getCenterX() + offsetX);
        ellipse.setCenterY(ellipse.getCenterY() + offsetY);
    }
}
