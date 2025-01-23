/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.shape.Shape;
import javafxapplication1.Paper;
import javafxapplication1.ShapeEditor;

/**
 *
 * @author cassd
 */
public class ChangeWidth extends Command{
    
    private double lastsize;
    private double size;
    private ShapeEditor editor;

    public ChangeWidth(Paper drawingPaper, Shape shape, ShapeEditor editor, double size) {
        super(drawingPaper, shape);
        this.editor = editor;
        this.size = size;
    }
    
    @Override
    public void execute() {
        assert super.drawingPaper != null : "AddShape: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        assert this.editor != null: "Editor: è nullo";
        System.out.println("ACCUAL width" + this.editor.getWidth());
        System.out.println("size scelta" + size);
        this.lastsize = this.editor.getWidth();
        this.editor.changeWidthSize(size);
    }

    @Override
    public void undo() {
        this.editor.changeWidthSize(this.lastsize);
    }

    @Override
    public void redo() {
        execute();
    }
    
}
