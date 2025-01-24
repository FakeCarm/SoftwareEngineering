/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author cassd
 */
public class LineToolTest {
    
    private Line testLine;
    private LineTool line;
    private MouseEvent clickOnPaper;
    private MouseEvent dragEvent;
    private Paper paper;
    private Color testStrokeColor;
    private Color testFillColor;
   
   
         
    public LineToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paper = new Paper(new AnchorPane(), new BorderPane());
        testStrokeColor = Color.RED;
        testLine = new Line(0, 0, 20, 20);
        testLine.setStroke(testStrokeColor);
        this.line = new LineTool(paper,testStrokeColor,testFillColor);
        
        // Genera un evento di click per il test
        clickOnPaper = new MouseEvent(MouseEvent.MOUSE_PRESSED, 
                testLine.getStartX(), testLine.getStartY(), 
                testLine.getStartX(), testLine.getStartY(), 
                MouseButton.PRIMARY, 1, 
                false, false, false, false, 
                false, false, false, false, 
                false, false, null);
        
        dragEvent = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 
                testLine.getEndX(), testLine.getEndY(), 
                testLine.getEndX(), testLine.getEndY(), 
                MouseButton.PRIMARY, 1, 
                false, false, false, false, 
                false, false, false, false, 
                false, false, null);
    }
    
    
    @After
    public void tearDown() {
    }
    /**
     * Test of onMousePressed method, of class LineTool.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("TEST :onMousePressed ()");
        line.onMousePressed(clickOnPaper);
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un rettangolo.", addedShape instanceof Line);
        Line castedLine = (Line) addedShape;
        Assert.assertEquals(testLine.getStartX(), castedLine.getStartX(), 0);
        Assert.assertEquals(testLine.getStartY(), castedLine.getStartY(), 0);
        Assert.assertEquals(testStrokeColor, castedLine.getStroke());
        
    }
    /**
     * Test of onMouseDragged method, of class LineTool.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("TEST: onMouseDragged()");
        line.onMouseDragged(clickOnPaper);
        line.onMousePressed(dragEvent);
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un rettangolo.", addedShape instanceof Line);
        Line castedLine = (Line) addedShape;
        Assert.assertEquals(testLine.getEndX(), castedLine.getEndX(), 0);
        Assert.assertEquals(testLine.getEndY(), castedLine.getEndY(), 0);
    
       
       
    }
    /**
     * Test of onMouseReleased method, of class LineTool.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("TEST: onMouseReleased()");
        line.onMouseDragged(clickOnPaper);
        line.onMousePressed(dragEvent);
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un rettangolo.", addedShape instanceof Line);
        Line castedLine = (Line) addedShape;
        Assert.assertEquals(testLine.getEndX(), castedLine.getEndX(), 0);
        Assert.assertEquals(testLine.getEndY(), castedLine.getEndY(), 0);
        
    }
    
}