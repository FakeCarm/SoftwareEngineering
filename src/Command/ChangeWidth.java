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
 * Classe comando che consente di cambiare la lunghezza di una forma
 * @author cassd
 */
public class ChangeWidth extends Command{
    
    private double lastsize;
    private double size;
    private ShapeEditor editor;

    public ChangeWidth(ShapeEditor editor, double size) {
        
        this.editor = editor;
        this.size = size;
    }
    
    @Override
    public void execute() {
        assert this.editor != null: "Editor: è nullo";
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
