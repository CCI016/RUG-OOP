package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.edits.DeleteEdgeEdit;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;

public class EdgeDeletion extends AbstractAction {
	private final GraphModel graphModel;

	/**
	 * Creates a new action to delete an edge.
	 *
	 * @param graphModel the Graph Model
	 */
	public EdgeDeletion(GraphModel graphModel) {
		this.graphModel = graphModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UndoManager undoManager = graphModel.getUndoManager();
		DeleteEdgeEdit deleteEdgeEdit = new DeleteEdgeEdit(graphModel, graphModel.getEdges());
		undoManager.addEdit(deleteEdgeEdit);
		deleteEdgeEdit.redo();
	}
}
