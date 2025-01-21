/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.paint.Color;

/**
 *
 * @author cassd
 */
public class SelectionShapeManager {
    
    private ShapeEditor editor;

    public SelectionShapeManager(ShapeEditor editor) {
        this.editor = editor;
    }
    
    public void changeStrokeColor(Color color){
        editor.changeStrokeColor(Color.CORAL);
    }
    
}
