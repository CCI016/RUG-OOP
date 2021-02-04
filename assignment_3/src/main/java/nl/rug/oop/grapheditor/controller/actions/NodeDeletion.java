package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.edits.DeleteNodeEdit;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;

public class NodeDeletion extends AbstractAction {
	private final GraphModel graphModel;

	/**
	 * Creates a new action to delete a node.
	 *
	 * @param graphModel the Graph Model
	 */
	public NodeDeletion(GraphModel graphModel) {
		this.graphModel = graphModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UndoManager undoManager = graphModel.getUndoManager();
		DeleteNodeEdit deleteNodeEdit = new DeleteNodeEdit(graphModel);
		undoManager.addEdit(deleteNodeEdit);
		deleteNodeEdit.redo();
	}
}
