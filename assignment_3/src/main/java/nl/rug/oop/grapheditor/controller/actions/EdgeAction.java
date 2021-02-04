package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.edits.AddEdgeEdit;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;

public class EdgeAction extends AbstractAction {
	private final GraphModel graphModel;

	/**
	 * Creates a new action to add an edge.
	 *
	 * @param graphModel the Graph Model
	 */
	public EdgeAction(GraphModel graphModel) {
		this.graphModel = graphModel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		UndoManager undoManager = graphModel.getUndoManager();
		AddEdgeEdit addEdgeEdit = new AddEdgeEdit(graphModel);
		undoManager.addEdit(addEdgeEdit);
		addEdgeEdit.redo();
	}
}
