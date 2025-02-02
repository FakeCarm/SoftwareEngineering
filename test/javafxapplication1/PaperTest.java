/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private BorderPane borderPane;
    private AnchorPane paneEditor;
    private Rectangle testShape;
    
    public PaperTest() {
    }
    
    
    @Before
    public void setUp() throws Exception {

        if (!Platform.isFxApplicationThread()) {
            CountDownLatch latch = new CountDownLatch(1);
            new JFXPanel(); 
            Platform.runLater(latch::countDown);
            latch.await(); 
        }

        CountDownLatch setupLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            anchorPane = new AnchorPane();
            borderPane = new BorderPane();
            paneEditor = new AnchorPane();
            paper = new Paper(anchorPane, borderPane,paneEditor);
            testShape = new Rectangle();
            setupLatch.countDown();
        });
        setupLatch.await(); 
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
    public void testRemoveOnPaper() {
        paper.addOnPaper(testShape);
        paper.removeOnPaper(testShape);
        assertTrue(!anchorPane.getChildren().contains(testShape));
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
    
    @Test
    public void testGetBorderPane() {
        assertEquals(borderPane,this.paper.getBorderPane());
    }
    
}
