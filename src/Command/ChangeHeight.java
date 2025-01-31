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
 * Classe che consente di cambiare l'altezza di una figura
 * @author cassd
 */
public class ChangeHeight extends Command{
    
    private double lastsize;
    private double size;
    private ShapeEditor editor;

    public ChangeHeight(ShapeEditor editor, double size) {
        
        this.editor = editor;
        this.size = size;
        
    }
    
    @Override
    public void execute() {
        assert this.editor != null: "Editor: è nullo";
        this.lastsize = this.editor.getHeight();
        this.editor.changeHeightSize(size);
    }

    @Override
    public void undo() {
       this.editor.changeHeightSize(this.lastsize);
    }

    @Override
    public void redo() {
        execute();
    }
    
}
