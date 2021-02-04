package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.edits.RenameEdit;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;

public class RenameAction extends AbstractAction {
	private final GraphModel graphModel;

	/**
	 * Creates a new action to rename a node.
	 *
	 * @param graphModel the Graph Model
	 */
	public RenameAction(GraphModel graphModel) {
		this.graphModel = graphModel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		UndoManager undoManager = graphModel.getUndoManager();
		RenameEdit renameEdit = new RenameEdit(graphModel);
		undoManager.addEdit(renameEdit);
		renameEdit.redo();
	}
}
