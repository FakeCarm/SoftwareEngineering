/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;

/**
 *
 * @author cassd
 */

public abstract class SelectedShapeTool extends ToolState{

    protected ObjectProperty<Color> strokeColor = new SimpleObjectProperty<>();
    protected ObjectProperty<Color> fillColor = new SimpleObjectProperty<>();
    
    public SelectedShapeTool(Color strokeColor, Color fillColor){
        this.strokeColor.set(strokeColor);
        this.fillColor.set(fillColor);
    }
    
    public ObjectProperty<Color> strokeColorProperty() {
        return strokeColor;
    }

    public ObjectProperty<Color> fillColorProperty() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor.get();
    }

    public Color getFillColor() {
        return fillColor.get();
    }
    
}
