/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author vinjs
 */
public class FileManager {
    
    private final Pane paper;
    
    public FileManager(Pane paper) {
        this.paper = paper;
    }

    public void load(File f) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        if (f == null || f.length() <= 0) {
            return;
        }
        
        paper.getChildren().clear();
        
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))){
            int size = in.readInt();
            for(int i=0; i<size; i++){
                Class c = (Class) in.readObject();
                Shape shape = (Shape) in.readObject();
                paper.getChildren().add(shape);
            }
        }
    }
    public void save(File f) throws IOException {
        
        if (f == null) {
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))){
            out.writeInt(paper.getChildren().size());
            for (Node n : paper.getChildren()){
                if (n instanceof Shape) {
                    out.writeObject(n);
                }
            }
        }
        
    }

    
}
