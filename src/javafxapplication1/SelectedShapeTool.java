/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;

/**
 *
 * @author cassd
 */

public abstract class SelectedShapeTool extends ToolState{

    private Color strokeColor;
    private Color fillColor;
    
    public SelectedShapeTool(Color strokeColor, Color fillColor){
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }
    
    public Color getStrokeColorProperty(){
        ColorPicker colorPickerStroke = (ColorPicker) super.anchorPaneBar.lookup("#colorPickerStroke");
        this.strokeColor = colorPickerStroke.getValue();
        System.out.println("Colore selezionato" + this.strokeColor);
        return this.strokeColor;
    }
    
    public Color getFillColorProperty(){
        ColorPicker colorPickerFill = (ColorPicker) super.anchorPaneBar.lookup("#colorPickerFill");
        this.fillColor = colorPickerFill.getValue();
        return this.fillColor;
    }
    
}
