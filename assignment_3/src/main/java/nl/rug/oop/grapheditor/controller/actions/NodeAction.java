package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.edits.AddNodeEdit;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;

public class NodeAction extends AbstractAction {
	private final GraphModel graphModel;
	/**
	 * Creates a new action to add a node.
	 *
	 * @param graphModel the Graph Model
	 */
	public NodeAction(GraphModel graphModel) {
		this.graphModel = graphModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UndoManager undoManager = graphModel.getUndoManager();
		AddNodeEdit addNodeEdit = new AddNodeEdit(graphModel);
		undoManager.addEdit(addNodeEdit);
		addNodeEdit.redo();
	}
}
