/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
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
public class InvokerTest {
    
    private Invoker testInvoker;
    private Command testCommand;
    private Paper testDrawingPaper;
    private Paper drawingPaper;
    private Line testLine;

    
    public InvokerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testDrawingPaper = new Paper(new AnchorPane());
        testLine = new Line(0, 0, 20, 20);
        this.testInvoker = Invoker.getInvoker();
        testCommand = new AddShape(testDrawingPaper,testLine);
        testInvoker.executeCommand(testCommand);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInvoker method, of class Invoker.
     */
    @Test
    public void testGetInvoker() {
        System.out.println("TEST: getInvoker()");
        Invoker result = Invoker.getInvoker();
        assertEquals(testInvoker, result);
        
        
    }
    
    /**
     * Test of executeCommand method, of class Invoker.
     */
    @Test
    public void testExecuteCommand() {
        Line line = new Line(0, 0, 20, 20);
        System.out.println("TEST: executeCommand()");
        drawingPaper = new Paper(new AnchorPane()); 
        Invoker result = Invoker.getInvoker();
        result.executeCommand(new AddShape(drawingPaper,line));
        assertEquals(testDrawingPaper.getAnchorPanePaper().getChildren().size(), drawingPaper.getAnchorPanePaper().getChildren().size());
        
       
    }
    
}
