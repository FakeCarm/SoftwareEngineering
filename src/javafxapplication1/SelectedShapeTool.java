package javafxapplication1;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 * Classe astratta che rappresenta uno strumento per la gestione della forma selezionata.
 * Consente di modificare i colori di riempimento e di bordo.
 */
public abstract class SelectedShapeTool extends ToolState {

    protected ObjectProperty<Color> strokeColor = new SimpleObjectProperty<>();
    protected ObjectProperty<Color> fillColor = new SimpleObjectProperty<>();

    /**
     * Costruttore per inizializzare i colori di bordo e riempimento dello strumento.
     *
     * @param strokeColor il colore del bordo.
     * @param fillColor il colore di riempimento.
     */
    public SelectedShapeTool(Color strokeColor, Color fillColor) {
        this.strokeColor.set(strokeColor);
        this.fillColor.set(fillColor);
    }

    /**
     * Restituisce il colore del bordo.
     *
     * @return il colore del bordo.
     */
    public Color getStrokeColor() {
        return strokeColor.get();
    }

    /**
     * Restituisce il colore di riempimento.
     *
     * @return il colore di riempimento.
     */
    public Color getFillColor() {
        return fillColor.get();
    }

    

    public ObjectProperty<Color> strokeColorProperty() {
        return strokeColor;
    }



    public ObjectProperty<Color> fillColorProperty() {
        return fillColor;
    }
}
