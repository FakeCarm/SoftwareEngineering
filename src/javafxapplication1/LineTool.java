
package javafxapplication1;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * 
 *
 * @author cassd
 */
public class LineTool extends ToolState{
    
    private Line currentLine;
    private Color lineColor;
    private Pane drawingPane;
    
    public LineTool(Pane drawingPane) {
        this.drawingPane = drawingPane;
        this.lineColor = Color.BLACK; // Default color
    }
    /*
    public void setLineColor(Color color) {
        this.lineColor = color;
    }
    */
    
    
    
    @Override
    public void onMousePressed(MouseEvent event) {
        currentLine = new Line();
        currentLine.setStartX(event.getX());
        currentLine.setStartY(event.getY());
        currentLine.setStroke(lineColor);
        currentLine.setStrokeWidth(3);
        
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentLine != null) {
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
            drawingPane.getChildren().add(currentLine);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        if (currentLine != null) {
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
            currentLine = null; 
        }
    }
    
    @Override
    public void setStrokeColor(Color color){
        this.lineColor = color;
    }
   
}
