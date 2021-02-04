package nl.rug.oop.grapheditor.controller.edits;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class AddEdgeEdit extends AbstractUndoableEdit {
	private final GraphModel graphModel;

	/**
	 * Creates a new undoable action to add an edge.
	 *
	 * @param graphModel the Graph Model
	 */
	public AddEdgeEdit(GraphModel graphModel) {
		this.graphModel = graphModel;
 	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
		if (graphModel.getEdges().size() > 1) {
			graphModel.removeFromEdges(graphModel.getEdges().get(graphModel.getEdges().size() - 1));
		}
	}

	@Override
	public void redo() throws CannotRedoException {
		if (super.canRedo()) {
			super.redo();
		}
		if (graphModel.getSelectedNode() != null) {
			graphModel.setAddEdgeButtonPressed(true);
		}
 	}
}
