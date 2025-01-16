package javafxapplication1;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cassd
 */
public class Paper {
    
    private AnchorPane anchorPanePaper;
   
    public Paper(AnchorPane anchorPanePaper) {
        this.anchorPanePaper = anchorPanePaper;
        
    } 
    
    public void addOnPaper(Shape shape){
        this.anchorPanePaper.getChildren().add(shape);
    }
    
    public void deleteFromPaper(){
        
    }

    public AnchorPane getAnchorPanePaper() {
        return anchorPanePaper;
    }

    public void setAnchorPanePaper(AnchorPane anchorPanePaper) {
        this.anchorPanePaper = anchorPanePaper;
    }
    
    
    
}
