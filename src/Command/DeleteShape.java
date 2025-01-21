package Command;

import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 *
 
@author cassd*/
public class DeleteShape extends Command{

    public DeleteShape(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
    }

    @Override
    public void execute() {
        assert super.drawingPaper != null : "AddShaoe: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        super.drawingPaper.removeOnPaper(super.shape);
    }

    @Override
    public void undo() {

    }

}