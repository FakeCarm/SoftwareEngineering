/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafxapplication1.Paper;
import javafxapplication1.PolygonTool;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolygonToolTest {
    
    private PolygonTool polygonTool;
    private Paper paper;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
            paper = new Paper(new AnchorPane(), new BorderPane(),null);
            polygonTool = new PolygonTool(paper, Color.BLACK, Color.RED);
            setupLatch.countDown();
        });
        setupLatch.await(); 
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testOnMousePressed() {
        MouseEvent pressEvent = new MouseEvent(MouseEvent.MOUSE_PRESSED, 50, 60, 50, 60, MouseButton.PRIMARY, 1, 
                                               false, false, false, false, true, false, false, false, false, false, null);
        
        polygonTool.onMousePressed(pressEvent);
     
        Polygon polygon = (Polygon) this.paper.getAnchorPanePaper().getChildren().toArray()[0];
        assertNotNull("Il poligono non deve essere nullo", polygon);
        assertEquals("Polygon deve avere 1 punto xy", 2, polygon.getPoints().size());
        assertEquals(pressEvent.getX(), polygon.getPoints().get(0), 0.001);
        assertEquals(pressEvent.getY(), polygon.getPoints().get(1), 0.001);
    }
    
    @Test
    public void testOnMouseDragged() {
        MouseEvent pressEvent = new MouseEvent(MouseEvent.MOUSE_PRESSED, 50, 60, 50, 60, MouseButton.PRIMARY, 1, 
                                               false, false, false, false, true, false, false, false, false, false, null);
        MouseEvent pressEvent2 = new MouseEvent(MouseEvent.MOUSE_PRESSED, 80, 80, 80, 80, MouseButton.PRIMARY, 1, 
                                               false, false, false, false, true, false, false, false, false, false, null);
        MouseEvent dragEvent = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 80, 90, 80, 90, MouseButton.PRIMARY, 1, 
                                              false, false, false, false, true, false, false, false, false, false, null);
        
        polygonTool.onMousePressed(pressEvent);
        polygonTool.onMousePressed(pressEvent2);
        
        polygonTool.onMouseDragged(dragEvent);
        
        Polygon polygon = (Polygon) this.paper.getAnchorPanePaper().getChildren().toArray()[0];
        assertEquals(dragEvent.getX(), polygon.getPoints().get(2), 0.001);
        assertEquals(dragEvent.getY(), polygon.getPoints().get(3), 0.001);
    }

    /**
     * Test of onMouseReleased method, of class PolygonTool.
     */
    @Test
    public void testOnMouseReleased() {
        
    }

   
}