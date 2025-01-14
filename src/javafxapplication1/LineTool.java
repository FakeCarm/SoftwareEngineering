
package javafxapplication1;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.AnchorPane;

/**
 * 
 *
 * @author cassd
 */
public class LineTool extends SelectedShapeTool{
    
    public Line currentLine;
    private Pane anchorPanePaper;
    public int count = 0;
    
    /*
     public LineTool(Pane drawingPane, Color lineStrokeColor) {
        this.drawingPane = drawingPane;
        if (lineStrokeColor != null){
            this.lineStrokeColor = lineStrokeColor;
        }
        else{
            this.lineStrokeColor = Color.BLACK; // Default color
        }
    */
   
        
     public LineTool(Pane anchorPanePaper, AnchorPane anchorPaneBar, Color strokeColor, Color fillColor) {
        super(strokeColor,fillColor);
        this.anchorPanePaper = anchorPanePaper;  
        super.anchorPaneBar = anchorPaneBar;

    }
    /*
    public void setLineColor(Color color) {
        this.lineColor = color;
    }
    */
    
    
    
    @Override
    public void onMousePressed(MouseEvent event) {
        currentLine = new Line();
        currentLine.setId("line"+ (count++));
        currentLine.setStartX(event.getX());
        currentLine.setStartY(event.getY());
        currentLine.setEndX(event.getX());
        currentLine.setEndY(event.getY());
        Color color = super.getStrokeColorProperty();
        if (color != null){
            currentLine.setStroke(super.getStrokeColorProperty());
            System.out.println("COLORE AGGIUNTO");
        }
        
        currentLine.setStrokeWidth(10);
        anchorPanePaper.getChildren().add(currentLine);
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentLine != null) {
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
            
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
    
    /*
    
     @Override
    public void setStrokeColor(Color color){
        this.lineStrokeColor = color;
    }
    
    */
   
   
}
