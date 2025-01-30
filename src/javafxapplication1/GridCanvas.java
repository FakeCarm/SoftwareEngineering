/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author vinjs
 */
public class GridCanvas extends Canvas {
    
    private double spacing = 20.0;
    
    public GridCanvas(AnchorPane anchorPane) {
        super(anchorPane.getWidth(), anchorPane.getHeight());
        draw();
    }
    
    public void draw() {
        double w = getWidth();
        double h = getHeight();
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, w, h);
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);


        for (double x = 0; x <= w; x += spacing) {
            gc.strokeLine(x, 0, x, h);
        }


        for (double y = 0; y <= h; y += spacing) {
            gc.strokeLine(0, y, w, y);
        }
    }
    
    public void setSpacing(double newSpacing) {
        if (newSpacing > 5 && newSpacing < 100) { 
            this.spacing = newSpacing;
            draw(); 
        }
    }
    
    public double getSpacing() {
        return spacing;
    }
    
    public void resizeGrid(double newWidth, double newHeight) {
        setWidth(Math.max(newWidth, getWidth()));
        setHeight(Math.max(newHeight, getHeight()));
        draw();
    }
    
}
