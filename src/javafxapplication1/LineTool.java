package javafxapplication1;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.AnchorPane;
import Command.Invoker;
import Command.AddShape;
/**
 * 
 *
 * @author cassd
 */
public class LineTool extends SelectedShapeTool{
    
    public Line currentLine;
    private Paper anchorPanePaper;
    public int count = 0;
    
       
    public LineTool(Paper anchorPanePaper, ToolBar toolBar, Color strokeColor, Color fillColor) {
        super(strokeColor,fillColor);
        this.anchorPanePaper = anchorPanePaper;  
        super.toolBar = toolBar;

    }
    
    
    @Override
    public void onMousePressed(MouseEvent event) {
        currentLine = new Line();
        currentLine.setId("line" + (count++));
        currentLine.setStartX(event.getX());
        currentLine.setStartY(event.getY());
        currentLine.setEndX(event.getX());
        currentLine.setEndY(event.getY());

        // Colore aggiornato dinamicamente dalla proprietà
        currentLine.setStroke(strokeColor.get());
        currentLine.setStrokeWidth(5);

        Invoker invoker = Invoker.getInvoker();
        if (invoker != null) {
            invoker.executeCommand(new AddShape(this.anchorPanePaper, currentLine));
        }
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
            currentLine = null; 
        }
    }
    
   
   
}
