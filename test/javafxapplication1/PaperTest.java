/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vinjs
 */
public class PaperTest {
    
    private Paper paper;
    private AnchorPane anchorPane;
    private Rectangle testShape;
    
    public PaperTest() {
    }
    
    
    @Before
    public void setUp() {
        
        anchorPane = new AnchorPane();
        paper = new Paper(anchorPane);
        
        testShape = new Rectangle();
    }
    

    /**
     * Test of addOnPaper method, of class Paper.
     */
    @Test
    public void testAddOnPaper() {
        paper.addOnPaper(testShape);
        assertTrue(anchorPane.getChildren().contains(testShape));
        
    }

    /**
     * Test of deleteFromPaper method, of class Paper.
     */
    @Test
    public void testDeleteFromPaper() {
        
        // TODO 
        
    }

    /**
     * Test of getAnchorPanePaper method, of class Paper.
     */
    @Test
    public void testGetAnchorPanePaper() {
        assertEquals(anchorPane, paper.getAnchorPanePaper());
    }

    /**
     * Test of setAnchorPanePaper method, of class Paper.
     */
    @Test
    public void testSetAnchorPanePaper() {
        AnchorPane newAnchorPane = new AnchorPane();
        paper.setAnchorPanePaper(newAnchorPane);
        assertEquals(newAnchorPane, paper.getAnchorPanePaper());
    }
    
}
