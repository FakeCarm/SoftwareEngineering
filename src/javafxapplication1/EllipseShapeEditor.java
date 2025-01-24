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
     * @param paneEditor il pannello su cui vengono gestite le operazioni di modifica della forma.
     * @param startX la posizione di partenza sull'asse X per la forma.
     * @param startY la posizione di partenza sull'asse Y per la forma.
     */
    public EllipseShapeEditor(Shape shape,double startX, double startY) {
        super(shape,startX, startY);
        this.ellipse = (Ellipse) shape;
    }

    
   /**
    * Metodo che si occupa di cambiare l'altezza di una forma
    * @param size 
    */
    @Override
    public void changeHeightSize(double size){
        this.ellipse.setRadiusY(size);
       
    }
    
    /**
     * Metodo che si occupa di cambiare la lunghezza i una forma
     * @param size 
     */
    @Override
    public void changeWidthSize(double size){
        this.ellipse.setRadiusX(size);
    }

    /**
     * Metodo che consente di ottenre la lunghezza di un'ellisse.
     * @return 
     */
    @Override
    public double getWidth() {
        return this.ellipse.getRadiusX();
    }
    
    /**
     * Metodo che consente di ottenere l'altezza di un'ellisse.
     * @return 
     */
    @Override
    public double getHeight() {
        return this.ellipse.getRadiusY();
    }
        
}
