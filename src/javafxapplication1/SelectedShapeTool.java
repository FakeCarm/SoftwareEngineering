package javafxapplication1;

import javafx.scene.paint.Color;

/**
 * Classe astratta che rappresenta uno strumento per la gestione della forma selezionata.
 * Consente di modificare i colori di riempimento e di bordo.
 */
public abstract class SelectedShapeTool extends ToolState {

    private Color strokeColor;
    private Color fillColor;

    /**
     * Costruttore per inizializzare i colori di bordo e riempimento dello strumento.
     *
     * @param strokeColor il colore del bordo.
     * @param fillColor il colore di riempimento.
     */
    public SelectedShapeTool(Color strokeColor, Color fillColor) {
        this.strokeColor = strokeColor;
        this.fillColor = strokeColor;
    }

    /**
     * Restituisce il colore del bordo.
     *
     * @return il colore del bordo.
     */
    public Color getStrokeColor() {
        return strokeColor;
    }

    /**
     * Restituisce il colore di riempimento.
     *
     * @return il colore di riempimento.
     */
    public Color getFillColor() {
        return fillColor;
    }
    /**
     * setta il colore di riempimento
     *
     */
    public void setFillColor(Color color){
        this.fillColor = color;
    }
    
    /**
     * Setta il colore del bordo
     * @param color 
     */
    public void setStrokeColor(Color color){
        this.strokeColor = color;
    }

    
}
