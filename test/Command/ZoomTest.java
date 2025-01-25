/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafxapplication1.Paper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cassd
 */
public class ZoomTest {
    
    private Zoom zoom;
    private Paper targetPaper;
    double MIN_ZOOM = 1;
    double INCREMENTO = 0.2;
    double MAX_ZOOM = MIN_ZOOM + 8*INCREMENTO;
    public ZoomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
     @Before
    public void setUp() {
        targetPaper = new Paper(new AnchorPane(),null);
        targetPaper.getAnchorPanePaper().setScaleX(1.0); 
        targetPaper.getAnchorPanePaper().setScaleY(1.0); 
        zoom = new Zoom(targetPaper,null); 
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class Zoom.
     */
    @Test
    public void testExecute() {
        System.out.println("TEST: execute()");
        
        double init_zoomX = targetPaper.getAnchorPanePaper().getScaleX();
        double init_zoomY = targetPaper.getAnchorPanePaper().getScaleY();
        zoom.execute();
        double risX = init_zoomX + INCREMENTO;
        double risY = init_zoomY + INCREMENTO;
        assertEquals(risX, targetPaper.getAnchorPanePaper().getScaleX(), 0.001);
        assertEquals(risY, targetPaper.getAnchorPanePaper().getScaleY(), 0.001);
    }

    /**
     * Test of undo method, of class Zoom.
     */
    @Test
    public void testUndo() {
        System.out.println("TEST: undo()");
        zoom.execute();
        double init_zoomX = targetPaper.getAnchorPanePaper().getScaleX();
        double init_zoomY = targetPaper.getAnchorPanePaper().getScaleY();
        zoom.undo();
        double risX = init_zoomX - INCREMENTO;
        double risY = init_zoomY - INCREMENTO;
        assertEquals(risX, targetPaper.getAnchorPanePaper().getScaleX(), 0.001);
        assertEquals(risY, targetPaper.getAnchorPanePaper().getScaleY(), 0.001);
    }

    /**
     * Test of redo method, of class Zoom.
     */
    @Test
    public void testRedo() {
        System.out.println("TEST: undo()");
        zoom.execute();
        double risX = targetPaper.getAnchorPanePaper().getScaleX();
        double risY = targetPaper.getAnchorPanePaper().getScaleY();
        zoom.undo();
        zoom.redo();
        // Controlla che il fattore di scala sia aumentato correttamente
        assertEquals(risX, targetPaper.getAnchorPanePaper().getScaleX(), 0.001);
        assertEquals(risY, targetPaper.getAnchorPanePaper().getScaleY(), 0.001);
    }
    
}
