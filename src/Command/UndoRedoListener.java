/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

/**
 *
 * @author vinjs
 */
public interface UndoRedoListener {
    void onUndoRedoStateChanged(boolean canUndo, boolean canRedo);
}
