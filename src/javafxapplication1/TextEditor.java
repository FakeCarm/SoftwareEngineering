/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author cassd
 */
public class TextEditor extends ShapeEditor{

    private Text text;
    
    public TextEditor(Shape shape, double startX, double startY) {
        super(shape, startX, startY);
        this.text = (Text) shape;
    }

    @Override
    public double getWidth() {
        return this.text.getBoundsInLocal().getWidth();
    }

    @Override
    public double getHeight() {
        return this.text.getFont().getSize();
    }

    @Override
    public void changeHeightSize(double size) {
        this.text.setFont(new Font(size));
    }

    @Override
    public void changeWidthSize(double size) {
        
    }
    
}
