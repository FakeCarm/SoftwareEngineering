/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.Stack;

/**
 *
 * @author cassd
 */
public class Invoker {

   
    
    private Stack<Command> commandStack;
    private static Invoker invoker = null;
    
    private Invoker() {
        commandStack = new Stack<>();
        
    }
     
    
    public static Invoker getInvoker() {
        if (Invoker.invoker == null) {
            Invoker.invoker = new Invoker();
        }
        return invoker;
    }
    
    public void executeCommand(Command command){
        this.commandStack.push(command);
        command.execute();
        
    }
    
   
}
