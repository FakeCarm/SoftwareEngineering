package javafxapplication1;

import Command.AddShape;
import Command.Invoker;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleTool extends SelectedShapeTool {
    
    Rectangle currentRectangle;
    private Paper anchorPanePaper;
    public int count = 0;  // Per assegnare un ID univoco a ogni rettangolo
    private double startX;
    private double startY;

    public RectangleTool(Paper anchorPanePaper, ToolBar toolBar, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        super.toolBar = toolBar;
        this.anchorPanePaper = anchorPanePaper;
    }

    @Override
    public void onMousePressed(MouseEvent event) {
        currentRectangle = new Rectangle();
        currentRectangle.setId("rectangle" + (count++));
        this.startX = event.getX();
        this.startY = event.getY();
        currentRectangle.setX(this.startX);
        currentRectangle.setY(this.startY);

        // Colori aggiornati automaticamente
        currentRectangle.setStroke(strokeColor.get());
        currentRectangle.setFill(fillColor.get());
        currentRectangle.setStrokeWidth(5);
        
        Invoker invoker = Invoker.getInvoker();
        if (invoker != null) {
            invoker.executeCommand(new AddShape(this.anchorPanePaper, currentRectangle));
        }
        
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentRectangle != null) {
            double actualX = event.getX();
            double actualY = event.getY();
            double width = Math.abs(this.startX - actualX);
            double height = Math.abs(this.startY - actualY);
            this.currentRectangle.setWidth(width);
            this.currentRectangle.setHeight(height);
            currentRectangle.setX(Math.min(this.startX, actualX));
            currentRectangle.setY(Math.min(this.startY, actualY));
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        currentRectangle = null;  // Resetta il rettangolo corrente
    }
}
